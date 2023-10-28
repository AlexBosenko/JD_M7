package org.example;

import org.example.http.HttpImageStatusCli;
import org.example.http.HttpStatusChecker;
import org.example.http.HttpStatusImageDownloader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int code = 303;

        String statusImage = HttpStatusChecker.getStatusImage(code);
        System.out.println("statusImage = " + statusImage);

        HttpStatusImageDownloader.downloadStatusImage(code);

        HttpImageStatusCli.askStatus();
    }
}