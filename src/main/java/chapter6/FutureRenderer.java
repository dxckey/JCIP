package chapter6;

import chapter5.LaunderThrowable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 使用Future等待图像的下载
 */
public abstract class FutureRenderer {
    private final ExecutorService executor = Executors.newCachedThreadPool();

    void renderPage(CharSequence source) {
        List<ImageInfo> imageInfos = scanForImageInfo(source);
        Callable<List<ImageData>> task = () -> {
            List<ImageData> result = new ArrayList<>();
            for (ImageInfo imageInfo : imageInfos) {
                result.add(imageInfo.downloadImage());
            }
            return result;
        };

        Future<List<ImageData>> future = executor.submit(task);
        renderText(source);
        try {
            List<ImageData> imageDatas = future.get();//此处可能会出现问题使任务被中断
            for (ImageData imageData : imageDatas) {
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            //重置当前线程的中断状态
            Thread.currentThread().interrupt();
            //不再需要该任务的结果，取消任务
            future.cancel(true);
        } catch (ExecutionException e) {
            throw LaunderThrowable.launderThrowable(e.getCause());
        }
    }

    abstract List<ImageInfo> scanForImageInfo(CharSequence s);

    abstract void renderText(CharSequence s);

    abstract void renderImage(ImageData i);

    interface ImageData {
    }

    interface ImageInfo {
        ImageData downloadImage();
    }
}
