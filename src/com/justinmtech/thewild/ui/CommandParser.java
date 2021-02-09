package com.justinmtech.thewild.ui;

import com.justinmtech.thewild.entity.Entity;

import java.util.Scanner;

public class CommandParser {
    private Entity player;
    private Entity computer;
    private Scanner scanner;
    private CommandHandler command;
    private String input;


    public CommandParser(Entity player, Entity computer) {
        this.player = player;
        this.computer = computer;
        scanner = new Scanner(System.in);
        this.command = new CommandHandler(player, computer);
    }

    public void parseCommand() {
        input = scanner.next();
        if (input.equalsIgnoreCase("help")) {
            command.help();
        } else if (input.equalsIgnoreCase("info")) {
            command.info();
        } else if (input.equalsIgnoreCase("shop")) {
            command.goToShop();
        } else if (input.equalsIgnoreCase("inn")) {
            command.goToInn();
        } else if (input.equalsIgnoreCase("wild")) {
            command.goToWild();
        } else if (input.equalsIgnoreCase("town")) {
            command.goToTown();
        } else if (input.equalsIgnoreCase("battle")) {
            command.battle();
            player.setAlive(true);
            command.info();
        } else if (input.equalsIgnoreCase("quit")) {
        } else if (input.equalsIgnoreCase("save")) {
            command.save();
        } else if (input.equalsIgnoreCase("load")) {
            command.load();
        } else {
            System.out.println("> Command not found.. Type 'help' for help.");
        }
    }

    public void parseCombatCommands() {
        input = scanner.next();
        if (input.equalsIgnoreCase("slash")) {
            command.punch();
        } else if (input.equalsIgnoreCase("stab")) {
            command.kick();
        } else if (input.equalsIgnoreCase("heal")) {
            command.heal();
        } else if (input.equalsIgnoreCase("flea")) {
            command.flea();
        } else if (input.equalsIgnoreCase("quit")) {
        } else if (input.equalsIgnoreCase("info")) {
            command.info();
        } else {
            System.out.println("Available Combat Commands:");
            System.out.println("> slash, stab, heal, flea");
        }
    }
}
