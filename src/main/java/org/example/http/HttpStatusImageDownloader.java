package org.example.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.example.utils.Utils;

import java.io.*;

public class HttpStatusImageDownloader {
    public static void downloadStatusImage(int code) throws IOException {
        String url = HttpStatusChecker.getStatusImage(code);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);

            String filename = code + Utils.EXTENSION;

            InputStream content = response.getEntity().getContent();

            saveFile(content, filename);
        }
    }

    private static void saveFile(InputStream in, String filename) throws IOException {
        File directory = new File(Utils.DIRECTORY_FOR_SAVE);
        if (!directory.exists()) {
            directory.mkdir();
        }

        String path = Utils.DIRECTORY_FOR_SAVE + filename;
        File file = new File(path);
        if (!file.exists()) {
            FileOutputStream fos = new FileOutputStream(file);

            byte[] buffer = new byte[1024];

            int read;
            while ((read = in.read(buffer)) != -1) {
                fos.write(buffer, 0, read);
            }
        } else {
            System.out.println("File : " + path + " already exists");
        }
    }
}
