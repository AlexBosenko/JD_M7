package org.example.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.example.utils.Utils;

import java.io.*;

public class HttpStatusChecker {
    public static String getStatusImage(int code) throws IOException {
        String urlByStatusCode = Utils.CATS_URL + "/" + code;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(urlByStatusCode);

            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 404) {
                throw new FileNotFoundException("There is not image for HTTP status " + code);
            }

            return urlByStatusCode + Utils.EXTENSION;
        }
    }
}
