package com.justinmtech.thewild;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.ui.CommandParser;
import com.justinmtech.thewild.ui.CommandHandler;

import java.util.Scanner;

public class Game extends Entity {
    Scanner scanner = new Scanner(System.in);
    CommandParser commandParser = new CommandParser();
    CommandHandler commandHandler = new CommandHandler();
    boolean gameRunning = true;

    public void game() {
        System.out.println("> Guard: Welcome to Izengar. Who are you?");
        String name = scanner.next();
        Entity player = new Entity(name);
        commandHandler.load(player);
        player.coins = 4;
        calculateLevel(player);
        setHP(player);

        System.out.println("> Guard: Nice to meet ya' " + player.name + ".");
        System.out.println("> Guard: Use 'wild' to go adventure in the wild or 'help' for a list of commands.");

        while (gameRunning) {
            commandParser.parseCommand(player);
        }
    }
}
