package com.justinmtech.thewild.ui;

import com.justinmtech.thewild.commands.*;
import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.entity.skills.Flea;
import com.justinmtech.thewild.entity.skills.Heal;
import com.justinmtech.thewild.entity.skills.Slash;
import com.justinmtech.thewild.entity.skills.Stab;
import com.justinmtech.thewild.environment.Battle;
import com.justinmtech.thewild.environment.Inn;
import com.justinmtech.thewild.environment.Shop;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandParser {
    private Entity player;
    private Entity computer;
    private Scanner scanner;
    //private CommandHandler command;
    private String input;


    public CommandParser(Entity player, Entity computer) {
        scanner = new Scanner(System.in);
        this.player = player;
        this.computer = computer;
        //this.command = new CommandHandler(this.player, this.computer);
    }

    public void parseCommand() {
        input = scanner.next();
        if (input.equalsIgnoreCase("help")) {
            new Help(player);
        } else if (input.equalsIgnoreCase("info")) {
            new Info(player);
        } else if (input.equalsIgnoreCase("shop")) {
            new Shop(player);
        } else if (input.equalsIgnoreCase("inn")) {
            new Inn(player);
        } else if (input.equalsIgnoreCase("wild")) {
            new Wild(player);
        } else if (input.equalsIgnoreCase("town")) {
            new Town(player);
        } else if (input.equalsIgnoreCase("battle")) {
            new Battle(player);
            new Info(player);
        } else if (input.equalsIgnoreCase("quit")) {
            new Quit(player);
        } else if (input.equalsIgnoreCase("save")) {
            //command.save();
        } else if (input.equalsIgnoreCase("load")) {
            //command.load();
        } else {
            System.out.println("> Command not found.. Type 'help' for help.");
        }
    }

    public ArrayList<Entity> getAttackCommand(Entity attacker, Entity defender) {
        input = scanner.next();
        if (input.equalsIgnoreCase("slash")) {
            Slash slash = new Slash(attacker, defender);
            return slash.attack();
        } else if (input.equalsIgnoreCase("stab")) {
            Stab stab = new Stab(attacker, defender);
            return stab.attack();
        } else if (input.equalsIgnoreCase("heal")) {
            Heal heal = new Heal(attacker, defender);
            return heal.doHeal();

        } else if (input.equalsIgnoreCase("flea")) {
            Flea flea = new Flea(attacker, defender);
            return flea.tryFlea();
        } else {
            System.out.println("Available Combat Commands:");
            System.out.println("> slash, stab, heal, flea");
            ArrayList<Entity> entities = new ArrayList<>();
            entities.add(attacker);
            entities.add(defender);
            return entities;
        }
    }
}
