package com.justinmtech.thewild.environment;

import com.justinmtech.thewild.ui.Display;
import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.entity.skills.CombatSkill;
import com.justinmtech.thewild.ui.CommandParser;

import java.util.ArrayList;
import java.util.Arrays;

//TODO simplify battle class as much as possible. Make it only responsible for what it should be.

public class Battle {
    private final CommandParser command;
    private final Display display;
    private Entity player;
    private Entity computer;
    private CombatSkill combatSkill;
    private ArrayList<Entity> entities;

    public Battle(Entity player) {
        this.player = player;
        player.setInCombat(true);
        this.computer = new Entity(pickFoe(), setComputerLevel(), true);
        setComputerLoadout();
        this.command = new CommandParser(player, computer);
        this.display = new Display();
        combatSkill = new CombatSkill();
        display.newBattle(computer);
        combatLoop();
    }

    private void combatLoop() {
        while (battleIsNotOver()) {
            System.out.println(Arrays.toString(computer.getInventory()));
            entities = command.getAttackCommand(player, computer);
            updateEntitiesPlayerAttack();
            entities = combatSkill.computerDoRandomSkill(computer, player);
            updateEntitiesComputerAttack();
            if (battleIsNotOver()) display.combatOutput(player, computer);
        }
        if (player.isAlive() && player.isInCombat()) playerWin();
            else playerLose();
    }

    private void updateEntitiesPlayerAttack() {
        this.player = entities.get(0);
        this.computer = entities.get(1);
    }

    private void updateEntitiesComputerAttack() {
        this.computer = entities.get(0);
        this.player = entities.get(1);
    }

    private void playerWin() {
        display.combatOutcome(player, computer);
        player.giveCoins(getRandomNumber(computer.getLevel(), player.getBattles() * 4));
        player.addXp(getRandomNumber(computer.getLevel() * 25, computer.getLevel() * 100));
        player.incrementBattles();
        player.setInCombat(false);
    }

    private void playerLose() {
        display.combatOutcome(player, computer);
        player.setCoins(0);
        player.setLocation("town");
        player.resetInventory();
        player.resetHp();
        player.incrementBattles();
        player.setInCombat(false);
    }

    private boolean battleIsNotOver() {
        boolean isBattleOver = (player.isAlive() && computer.isAlive() && player.isInCombat() && computer.isInCombat());
        return isBattleOver;
    }

    private String pickFoe() {
        int randomNumber = getRandomNumber(1, 10);
        if (randomNumber <= 5) {
            return "Wolf";
        } else {
            return "Bandit";
        }
    }

    private void setComputerLoadout() {
        int randomNumber = getRandomNumber(0 , 10);
        if (randomNumber >= 4 && randomNumber <= 6) {
            computer.setInventory(new String[]{"Short Sword"});
        } else if (randomNumber <= 4 && randomNumber >= 2) {
            computer.setInventory(new String[]{"Long Sword"});
        } else if (randomNumber < 2) {
            computer.setInventory(new String[]{"Long Sword"});
            computer.setInventory(new String[]{"Long Sword", "Iron Armor"});

        } else {
            computer.setInventory(new String[]{"Air", "Air"});
        }
    }

    private short setComputerLevel() {
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
