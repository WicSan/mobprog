package com.example.sandr.communication;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sandr on 21.03.2018.
 */

public class HttpUtil {
    public static InputStream openHttpConnection(URL url) throws MalformedURLException {
        HttpURLConnection httpConnection = null;
        InputStream content = null;
        try {
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.connect();
            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                content = httpConnection.getInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}
