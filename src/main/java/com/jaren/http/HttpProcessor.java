package com.jaren.http;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.SQLOutput;

/**
 * ClassName: HttpProcessor
 * Package: com.jaren.http
 * Description:
 *
 * @Author Jaren
 * @Create 2024/4/8 14:35
 * @Version 1.0
 */
public class HttpProcessor {
    // HTTP服务根目录
    private static final String ROOT_DIR = System.getProperty("user.dir") + File.separator +"src\\main\\resources"+ File.separator + "web";

    public void process(HttpRequest request, HttpResponse response) throws IOException {
        String uri = request.getUri();
        File file = new File(ROOT_DIR, uri);
        if (file.exists()) {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            response.write("HTTP/1.1 200 OK\n");
            response.write("content-length: " + file.length() + "\n\n");
            response.write(new String(fileContent, Charset.defaultCharset()));
        }else {
            // 文件不存在，返回404页
            file = new File(ROOT_DIR, "404.html");
            byte[] fileContent = Files.readAllBytes(file.toPath());
            response.write("HTTP/1.1 404 Not Found\n");
            response.write("content-length: " + file.length() + "\n\n");
            response.write(new String(fileContent, Charset.defaultCharset()));
        }
    }
}
