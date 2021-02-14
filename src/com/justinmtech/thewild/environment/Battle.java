package com.justinmtech.thewild.environment;

import com.justinmtech.thewild.ui.Display;
import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.entity.Skills;
import com.justinmtech.thewild.ui.CommandHandler;
import com.justinmtech.thewild.ui.CommandParser;

public class Battle {
    private CommandParser command;
    private CommandHandler commandHandler;
    private Display display;
    private Entity player;
    private Entity computer;
    private Skills skills;

    public Battle(Entity player, Entity computer) {
        this.player = player;
        this.computer = computer;
        this.command = new CommandParser(player, computer);
        this.commandHandler = new CommandHandler(player, computer);
        this.display = new Display();
    }

    public void combatLoop() {
        computer = new Entity(pickFoe(), setLevel(), true);
        pickLoadout(computer);
        computer.setInCombat(true);
        player.setInCombat(true);

        display.newBattle(computer);

        while (!isBattleOver()) {
            skills = new Skills(player, computer);
            command.parseCombatCommands();
            skills.doRandomSkill();
            if (!isBattleOver()) {
                display.combatOutput(player, computer);
            } else {
                if (player.isAlive()) {
                    player.setCoins(player.getCoins() + getRandomNumber(computer.getLevel(), player.getBattles() * 4));
                    player.setXp(player.getXp() + getRandomNumber(computer.getLevel() * 25, computer.getLevel() * 100));
                    display.combatOutcome(player, computer);
                    player.calculateLevel(player);
                    player.setBattles(player.getBattles() + 1);
                } else {
                    display.combatOutcome(player, computer);
                    player.setCoins(0);
                    player.setLocation("town");
                    player.setInventory(new String[]{"Air", "Air", "Air", "Air"});
                    player.setHp(player.getMaxHP());
                }
                player.setInCombat(false);
                commandHandler.save();
            }
        }
    }

    public boolean isBattleOver() {
        boolean isBattleOver = (!player.isAlive() || !computer.isAlive() || !player.isInCombat());
        return isBattleOver;
    }

    public String pickFoe() {
        int randomNumber = getRandomNumber(0, 10);
        String foeName;
        if (randomNumber < 5) {
            foeName = "Wolf";
        } else {
            foeName = "Bandit";
        }
        return foeName;
    }

    public void pickLoadout(Entity entity) {
        int randomNumber = getRandomNumber(0 , 10);
        if (randomNumber > 4 && randomNumber < 6) {
            entity.setInventory(new String[]{"Short Sword"});
        } else if (randomNumber < 4 && randomNumber > 2) {
            entity.setInventory(new String[]{"Long Sword"});
        } else if (randomNumber < 2) {
            entity.setInventory(new String[]{"Long Sword"});
            entity.setInventory(new String[]{"Long Sword", "Iron Armor"});

        } else {
            entity.setInventory(new String[]{"Air", "Air"});
        }
    }

    public short setLevel() {
        short level;
        if (player.getLevel() > 2) {
            level = (short) getRandomNumber(player.getLevel() - 1, player.getLevel() + 2);
        } else level = 1;
        return level;
    }

    private int getRandomNumber(int min, int max) {
        int randomNumber = (int)(Math.random() * ((max - min) + 1)) + min;
        return randomNumber;
    }
}
