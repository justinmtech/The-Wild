package com.justinmtech.thewild.utilities;

public class RandomNumberGenerator {

    public static int generate(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }
}
