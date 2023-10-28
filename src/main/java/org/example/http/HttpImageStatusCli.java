package org.example.http;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HttpImageStatusCli {
    public static void askStatus() {
        Scanner scanner = new Scanner(System.in);
        boolean inputError = true;

        do {
            try {
                System.out.println("Enter HTTP status code");
                int code = scanner.nextInt();
                HttpStatusImageDownloader.downloadStatusImage(code);
                inputError = false;
            } catch (InputMismatchException e) {
                System.out.println("Please enter valid number!");
                scanner.next();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (inputError);
    }
}
