package com.justin;

import java.util.Scanner;

public class CommandParser {
    Scanner scanner = new Scanner(System.in);

    public void parseCommand(Entity player) {
        String input = scanner.next();
        CommandHandler command = new CommandHandler();
        if (input.equalsIgnoreCase("help")) {
            command.help(player);
        } else if (input.equalsIgnoreCase("info")) {
            command.info(player);
        } else if (input.equalsIgnoreCase("shop")) {
            command.goToShop(player);
        } else if (input.equalsIgnoreCase("inn")) {
            command.goToInn(player);
        } else if (input.equalsIgnoreCase("wild")) {
            command.goToWild(player);
        } else if (input.equalsIgnoreCase("town")) {
            command.goToTown(player);
        } else if (input.equalsIgnoreCase("battle")) {
            command.battle(player);
            player.isAlive = true;
            command.info(player);
        } else if (input.equalsIgnoreCase("quit")) {
        } else if (input.equalsIgnoreCase("save")) {
            command.save(player);
        } else if (input.equalsIgnoreCase("load")) {
            command.load(player);
        } else {
            System.out.println("> Command not found.. Type 'help' for help.");
        }
    }

    public void parseCombatCommands(Entity enemy, Entity self) {
        String input = scanner.next();
        CommandHandler command = new CommandHandler();
        if (input.equalsIgnoreCase("slash")) {
            command.punch(enemy, self);
        } else if (input.equalsIgnoreCase("stab")) {
            command.kick(enemy, self);
        } else if (input.equalsIgnoreCase("heal")) {
            command.heal(enemy, self);
        } else if (input.equalsIgnoreCase("flea")) {
            command.flea(enemy, self);
        } else if (input.equalsIgnoreCase("quit")) {
        } else if (input.equalsIgnoreCase("info")) {
            command.info(self);
        } else {
            System.out.println("Available Combat Commands:");
            System.out.println("> slash, stab, heal, flea");
        }
    }
}
