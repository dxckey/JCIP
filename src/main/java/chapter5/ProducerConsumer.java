package chapter5;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 桌面搜索应用程序中的生产者任务和消费者任务
 * 生产者---生产--->队列---消费--->消费者
 */
public class ProducerConsumer {
    private static final int BOUND = 10;
    private static final int N_CONSUMERS = Runtime.getRuntime().availableProcessors();

    public static void startIndexing(File[] roots) {
        BlockingQueue<File> queue = new LinkedBlockingDeque<>(BOUND);
        FileFilter filter = (file) -> true;

        for (File root : roots) {//生产者任务
            new Thread(new FileCrawler(queue, filter, root)).start();
        }

        for (int i = 0; i < N_CONSUMERS; i++) {//消费者任务
            new Thread(new Indexer(queue)).start();
        }
    }

    /**
     * 生产者
     */
    static class FileCrawler implements Runnable {
        private final BlockingQueue<File> fileQueue;
        private final FileFilter fileFilter;
        private final File root;

        public FileCrawler(BlockingQueue<File> fileQueue, final FileFilter fileFilter, File root) {
            this.fileQueue = fileQueue;
            this.fileFilter = (file) -> file.isDirectory() || fileFilter.accept(file);
            this.root = root;
        }

        @Override
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private boolean alreadyIndexed(File file) {
            return false;
        }

        private void crawl(File root) throws InterruptedException {
            File[] entries = root.listFiles(fileFilter);
            if (entries != null) {
                for (File entry : entries) {
                    if (entry.isDirectory()) {
                        crawl(entry);
                    } else if (!alreadyIndexed(entry)) {
                        fileQueue.put(entry);
                    }
                }
            }
        }
    }

    /**
     * 消费者
     */
    static class Indexer implements Runnable {
        private final BlockingQueue<File> queue;

        public Indexer(BlockingQueue<File> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    indexFile(queue.take());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public void indexFile(File file) {
            //从队列中取出文件名称并对它们建立索引
        }
    }
}
