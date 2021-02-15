package com.justinmtech.thewild;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.ui.CommandParser;
import com.justinmtech.thewild.ui.CommandHandler;

import java.util.Scanner;

public class Game {
    private Entity player;
    private Entity computer;
    private Scanner scanner;
    private CommandParser commandParser;
    private CommandHandler commandHandler;
    private boolean gameRunning;

    public Game() {
        scanner = new Scanner(System.in);
        computer = new Entity(true);
        gameRunning = true;
    }

    public void game() {
        System.out.println("> Guard: Welcome to Izengar. Who are you?");
        String name = scanner.next();
        player = new Entity(name, 5, 100);
        System.out.println(player.getLevel());
        commandParser = new CommandParser(player, computer);
        commandHandler = new CommandHandler(player, computer);
        commandHandler.load();

        System.out.println("> Guard: Nice to meet ya' " + player.getName() + ".");
        System.out.println("> Guard: Use 'wild' to go adventure in the wild or 'help' for a list of commands.");

        while (gameRunning) {
            commandParser.parseCommand();
        }
    }
}
