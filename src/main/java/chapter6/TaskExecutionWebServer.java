package chapter6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 基于线程池的Web服务器
 */
public class TaskExecutionWebServer {
    private static final int N_THREADS = 100;
    private static final Executor exec = Executors.newFixedThreadPool(N_THREADS);

    public static void start(int port) throws IOException {
        ServerSocket server = new ServerSocket(80);
        while (true) {
            Socket connection = server.accept();
            exec.execute(() -> handleRequest(connection));
        }
    }

    private static void handleRequest(Socket connection) {
        // 请求处理逻辑
    }
}
