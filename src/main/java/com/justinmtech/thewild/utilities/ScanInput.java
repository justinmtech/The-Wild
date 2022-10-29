package com.justinmtech.thewild.utilities;

import java.util.Scanner;

public class ScanInput {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getString() {
        return scanner.next().trim();
    }

    public static boolean getBoolean() {
        return scanner.nextBoolean();
    }

    public static int getInt() {
        return scanner.nextInt();
    }
}
