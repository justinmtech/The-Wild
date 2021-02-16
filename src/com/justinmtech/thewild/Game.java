package com.justinmtech.thewild;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.ui.CommandParser;
import com.justinmtech.thewild.ui.Display;

import java.util.Scanner;

public class Game {
    private final Entity player;
    private final Entity computer;
    private final Scanner scanner;
    private CommandParser commandParser;
    private Display display;
    private boolean gameRunning;

    public Game() {
        scanner = new Scanner(System.in);
        computer = new Entity(true);
        player = new Entity("Player", 5, 100);
        this.display = new Display();
        gameRunning = true;
    }

    public void game() {
        display.welcome();
        String name = scanner.next();
        player.setName(name);
        commandParser = new CommandParser(player, computer);

        display.greeting(player);
        display.farewell();
        while (gameRunning) {
            commandParser.parseCommand();
        }
    }
}
