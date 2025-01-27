package com.example.project;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean continueLoop = true;
        while (continueLoop) {
            ANSI.clearScreen();
            System.out.println(ANSI.bold("Book Store").underline());
            System.out.println(" [1] ");

            scanner.nextLine();
        }

        scanner.close();
    }
}
