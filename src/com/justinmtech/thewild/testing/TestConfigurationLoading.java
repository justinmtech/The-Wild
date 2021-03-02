package com.justinmtech.thewild.testing;

import org.junit.Test;

import java.io.File;
import java.util.Scanner;

public class TestConfigurationLoading {
    private Scanner scanner;
    private double levelMultiplierBase;
    private double increaseMultiplierIncrement;

    public TestConfigurationLoading() {
        openFile();
        readConfig();
    }

    @Test
    public void openFile() {
        try {
            scanner = new Scanner(new File("src/main/resources/config.yml"));
        } catch(Exception e) {
            System.out.println("File not found");
        }
    }

    @Test
    public void readConfig() {
        while (scanner.hasNext()) {
            String field1 = scanner.next();
            levelMultiplierBase = scanner.nextDouble();
            String field2 = scanner.next();
            increaseMultiplierIncrement = scanner.nextDouble();

            System.out.printf("%s %s\n", levelMultiplierBase, increaseMultiplierIncrement);
        }
    }

    public double getLevelMultiplierBase() {
        return levelMultiplierBase;
    }

    public double getIncreaseMultiplierIncrement() {
        return increaseMultiplierIncrement;
    }

}
