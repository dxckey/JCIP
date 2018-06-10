package chapter5;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用FutureTask来提前加载稍后需要的数据
 */
public class PreLoader {
    private final FutureTask<ProductInfo> future = new FutureTask<>(this::loadProductInfo);

    private Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    ProductInfo loadProductInfo() throws DataLoadException {
        return null;
    }

    public ProductInfo get() throws DataLoadException, InterruptedException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException) {
                throw (DataLoadException) cause;
            } else if (cause instanceof InterruptedException) {
                throw (InterruptedException) cause;
            }
        }
        return null;
    }

    interface ProductInfo {}
}

class DataLoadException extends Exception {

}
