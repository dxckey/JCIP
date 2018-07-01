package chapter6;

import java.util.ArrayList;
import java.util.List;

/**
 * 串行渲染页面
 */
public abstract class SingleThreadRenderer {
    void renderPage(CharSequence source) {
        renderText(source);
        List<ImageData> imageData = new ArrayList<>();
        for (ImageInfo imageInfo : scanForImageInfo(source)) {
            imageData.add(imageInfo.downloadImage());//等待下载时间过长，可以在下载时提高效率
        }

        for (ImageData data : imageData) {
            renderImage(data);
        }
    }

    abstract void renderText(CharSequence s);

    abstract List<ImageInfo> scanForImageInfo(CharSequence s);

    abstract void renderImage(ImageData i);

    interface ImageData {
    }

    interface ImageInfo {
        ImageData downloadImage();
    }
}
