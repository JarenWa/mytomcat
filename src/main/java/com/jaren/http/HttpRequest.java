package com.jaren.http;

import java.io.*;

/**
 * ClassName: HttpRequest
 * Package: com.jaren.http
 * Description:
 *
 * @Author Jaren
 * @Create 2024/4/8 10:42
 * @Version 1.0
 */
public class HttpRequest {

    private String uri;
    private String method;

    public HttpRequest(InputStream inputStream) {
        try {
            BufferedReader read = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = read.readLine();
            if (line != null) {
                String[] data = line.split(" ");
                if (data.length >= 2) {
                    this.method = data[0];
                    this.uri = data[1];
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getUri() {
        return uri;
    }

    public String getMethod() {
        return method;
    }
}
