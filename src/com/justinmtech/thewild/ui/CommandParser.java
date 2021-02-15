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
        scanner = new Scanner(System.in);
        this.player = player;
        this.computer = computer;
        this.command = new CommandHandler(this.player, this.computer);
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

    public Entity parseCombatCommands(Entity self, Entity enemy) {
        input = scanner.next();
        if (input.equalsIgnoreCase("slash")) {
            enemy = command.punch();
            return enemy;
        } else if (input.equalsIgnoreCase("stab")) {
            enemy = command.kick();
            return enemy;
        } else if (input.equalsIgnoreCase("heal")) {
            self = command.heal();
            return self;
        } else if (input.equalsIgnoreCase("flea")) {
            self = command.flea();
            return self;
        } else if (input.equalsIgnoreCase("quit")) {
        } else if (input.equalsIgnoreCase("info")) {
            command.info();
        } else {
            System.out.println("Available Combat Commands:");
            System.out.println("> slash, stab, heal, flea");
        }
        if (self == null) {
            return enemy;
        } else {
            return self;
        }
    }
}
