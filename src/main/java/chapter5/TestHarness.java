package chapter5;

import java.util.concurrent.CountDownLatch;

/**
 * 在计时测试中使用CountDownLatch来启动和停止线程
 */
public class TestHarness {
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGates = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread thread = new Thread(() -> {
                try {
                    startGate.await();
                    try {
                        task.run();
                    } finally {
                        endGates.countDown();
                    }
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            });
            thread.start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGates.await();//等待事件
        long end = System.nanoTime();
        return end - start;
    }
}
