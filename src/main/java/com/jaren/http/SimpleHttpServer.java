package com.jaren.http;

import com.jaren.http.HttpProcessor;
import com.jaren.http.HttpRequest;
import com.jaren.http.HttpResponse;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ClassName: SimpleHttpServer
 * Package: com.jaren.demo1
 * Description: 一个简单的HTTP服务器，
 *              通过监听特定端口（本例中为8080端口）接受客户端的连接请求，读取客户端发送的数据，
 *              然后向客户端发送响应消息（本例中仅发送了“OK”作为响应）。
 *
 *              这个服务器只是实现了最基本的网络通信功能，
 *              没有处理HTTP请求的具体细节，如请求方法（GET、POST等）、请求头的解析、状态码的返回等。
 *
 * @Author Jaren
 * @Create 2024/4/8 10:53
 * @Version 1.0
 */
public class SimpleHttpServer implements Runnable {

    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("======Server is running...========");
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();

                // 当有客户端请求过来，为连接创建Request和Response对象
                HttpRequest request = new HttpRequest(socket.getInputStream());
                System.out.println("Customer request:" + request.getMethod() + request.getUri());
                HttpResponse response = new HttpResponse(socket.getOutputStream());

                // 交给处理器去处理
                HttpProcessor processor = new HttpProcessor();
                processor.process(request, response);

                socket.shutdownOutput();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
