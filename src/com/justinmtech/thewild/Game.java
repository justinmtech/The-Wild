package com.justinmtech.thewild;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.ui.CommandParser;
import com.justinmtech.thewild.ui.CommandHandler;

import java.util.Scanner;

public class Game {
    private Scanner scanner;
    private CommandParser commandParser;
    private CommandHandler commandHandler;
    private boolean gameRunning;

    public Game() {
        scanner = new Scanner(System.in);
        this.commandParser = new CommandParser();
        this.commandHandler = new CommandHandler();
        gameRunning = true;
    }

    public void game() {
        System.out.println("> Guard: Welcome to Izengar. Who are you?");
        String name = scanner.next();
        Entity player = new Entity(name);
        commandHandler.load(player);

        System.out.println("> Guard: Nice to meet ya' " + player.name + ".");
        System.out.println("> Guard: Use 'wild' to go adventure in the wild or 'help' for a list of commands.");

        while (gameRunning) {
            commandParser.parseCommand(player);
        }
    }
}
