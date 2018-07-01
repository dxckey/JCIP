package chapter6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在Web服务器中为每个请求启动一个线程，线程开销大，消耗资源，稳定性低，不要这么做
 */
public class ThreadPerTaskWebServer {
    public static void start(int port) throws IOException {
        ServerSocket server = new ServerSocket(port);
        while (true) {
            Socket connection = server.accept();
            new Thread(() -> handleRequest(connection)).start();
        }
    }

    private static void handleRequest(Socket connection) {
        // 请求处理逻辑
    }
}
