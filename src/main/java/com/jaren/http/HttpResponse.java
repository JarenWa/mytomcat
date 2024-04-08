package com.jaren.http;

import java.io.IOException;
import java.io.OutputStream;

/**
 * ClassName: HttpResponse
 * Package: com.jaren.http
 * Description:
 *
 * @Author Jaren
 * @Create 2024/4/8 10:42
 * @Version 1.0
 */
public class HttpResponse {

    public OutputStream outputStream;

    public void write(String s){
        try {
            outputStream.write(s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
