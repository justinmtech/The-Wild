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

    public Battle(Entity player) {
        this.player = player;
        player.setInCombat(true);
        this.computer = new Entity(pickFoe(), setComputerLevel(), true);
        setComputerLoadout();
        this.command = new CommandParser(player, computer);
        this.commandHandler = new CommandHandler(player, computer);
        this.display = new Display();
    }

    public void combatLoop() {
        display.newBattle(computer);
        Entity entity;

        while (battleIsNotOver()) {
            skills = new Skills(player, computer);
            entity = command.parseCombatCommands(player, computer);
            if (entity.isComputer()) {
                computer = entity;
            } else {
                player = entity;
            }

            skills = new Skills(computer, player);
            entity = skills.doRandomSkill();
            if (entity.isComputer()) {
                computer = entity;
            } else {
                player = entity;
            }
            if (battleIsNotOver()) {
                display.combatOutput(player, computer);
            } else {
                if (player.isAlive()) {
                    display.combatOutcome(player, computer);
                    player.setCoins(player.getCoins() + getRandomNumber(computer.getLevel(), player.getBattles() * 4));
                    player.setXp(player.getXp() + getRandomNumber(computer.getLevel() * 25, computer.getLevel() * 100));
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

    public boolean battleIsNotOver() {
        boolean isBattleOver = (player.isAlive() && computer.isAlive());
        return isBattleOver;
    }

    public String pickFoe() {
        int randomNumber = getRandomNumber(0, 10);
        if (randomNumber < 5) {
            return "Wolf";
        } else {
            return "Bandit";
        }
    }

    public void setComputerLoadout() {
        int randomNumber = getRandomNumber(0 , 10);
        if (randomNumber > 4 && randomNumber < 6) {
            computer.setInventory(new String[]{"Short Sword"});
        } else if (randomNumber < 4 && randomNumber > 2) {
            computer.setInventory(new String[]{"Long Sword"});
        } else if (randomNumber < 2) {
            computer.setInventory(new String[]{"Long Sword"});
            computer.setInventory(new String[]{"Long Sword", "Iron Armor"});

        } else {
            computer.setInventory(new String[]{"Air", "Air"});
        }
    }

    public short setComputerLevel() {
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
