package chapter6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 串行的web服务器，不适合多用户
 */
public class SingleThreadWebServer {
    public static void start(int port) throws IOException {
        ServerSocket server = new ServerSocket(port);
        while (true) {
            Socket connection = server.accept();
            handleRequest(connection);
        }
    }

    private static void handleRequest(Socket connection) {
        // 请求处理逻辑
    }
}
