package com.justinmtech.thewild;

import com.justinmtech.thewild.utilities.ConfigHandler;

public class Main {
    public static void main(String[] args) {
        ConfigHandler configHandler = new ConfigHandler();
        Game start = new Game();
        start.game();
    }
}
