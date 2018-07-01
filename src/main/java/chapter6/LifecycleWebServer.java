package chapter6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 支持关闭的操作的Web服务器
 */
public class LifecycleWebServer {
    private final ExecutorService service = Executors.newCachedThreadPool();
    private final static int PORT = 80;

    public void start() throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        while (!service.isShutdown()) {
            try {
                Socket connection = server.accept();
                service.execute(() -> handleRequest(connection));
            } catch (RejectedExecutionException e) {
                if (!service.isShutdown()) {
                    log("task submission rejected", e);
                }
            }
        }
    }

    public void stop() {
        service.shutdown();
    }

    private void log(String msg, Exception e) {
        Logger.getAnonymousLogger().log(Level.WARNING, msg, e);
    }

    private void handleRequest(Socket connection) {
        Request req = readRequest(connection);
        if (isShutdownRequest(req)) {
            stop();
        } else {
            dispatchRequest(req);
        }
    }

    private Request readRequest(Socket s) {
        return null;
    }

    private void dispatchRequest(Request r) {
    }

    private boolean isShutdownRequest(Request r) {
        return false;
    }

    interface Request {

    }
}
