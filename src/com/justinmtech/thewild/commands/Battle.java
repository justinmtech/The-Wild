package com.justinmtech.thewild.commands;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.entity.skill_logic.CombatSkillsUtil;
import com.justinmtech.thewild.user_interface.CommandParser;
import com.justinmtech.thewild.user_interface.Display;
import com.justinmtech.thewild.utilities.RandomNumberGenerator;

import java.util.ArrayList;

//The battle command.
//Used for organizing battle instances when the player issues the "battle" command in the wild.
//This class handles all logic around the battles of entities.
public class Battle {
    private final CommandParser command;
    private final Display display;
    private Entity player;
    private Entity computer;
    private final CombatSkillsUtil combatSkillsUtil;
    private ArrayList<Entity> entities;

    public Battle(Entity player) {
        this.player = player;
        player.setInCombat(true);
        this.computer = new Entity(pickFoe(), setRelativeComputerLevel(), true);
        setRandomComputerLoadout();
        this.command = new CommandParser(player);
        this.display = new Display();
        combatSkillsUtil = new CombatSkillsUtil();
        display.newBattle(computer);
        combatLoop();
    }

    //Wait for player attack command, then trigger a random computer attack until the player fleas or an entity dies.
    private void combatLoop() {
        while (battleIsNotOver()) {
            entities = command.getAttackCommand(player, computer);
            updateEntities("player");
            entities = combatSkillsUtil.computerDoRandomSkill(computer, player);
            updateEntities("computer");
            if (battleIsNotOver()) display.combatOutput(player, computer);
        }
        if (player.isAlive() && player.isInCombat()) playerWin();
            else playerLose();
    }

    //Update the Battle class player/computer entities with the ArrayList entity response in combatLoop().
    private void updateEntities(String type) {
        switch (type) {
            case "player":
                this.player = entities.get(0);
                this.computer = entities.get(1);
                break;
            case "computer":
                this.computer = entities.get(0);
                this.player = entities.get(1);
                break;
        }
    }

    //Triggers win conditions for the player after successfully killing an enemy.
    private void playerWin() {
        display.combatOutcome(player, computer);
        player.giveCoins(RandomNumberGenerator.generate(computer.getLevel(), player.getBattles() * 4));
        player.addXp(RandomNumberGenerator.generate(computer.getLevel() * 25, computer.getLevel() * 100));
        player.incrementBattles();
        player.setInCombat(false);
    }

    //Triggers lose conditions for the player after dying to an enemy.
    private void playerLose() {
        display.combatOutcome(player, computer);
        player.setCoins(0);
        player.setLocation("town");
        player.resetInventory();
        player.resetHp();
        player.incrementBattles();
        player.setInCombat(false);
    }

    //Checks if a battle is over or not.
    private boolean battleIsNotOver() {
        boolean isBattleOver = (player.isAlive() && computer.isAlive() && player.isInCombat() && computer.isInCombat());
        return isBattleOver;
    }

    //Picks a random foe name for the player to fight against.
    private String pickFoe() {
        int randomNumber = RandomNumberGenerator.generate(1, 10);
        if (randomNumber <= 5) {
            return "Wolf";
        } else {
            return "Bandit";
        }
    }

    //Sets a random inventory loadout for the computer.
    private void setRandomComputerLoadout() {
        int randomNumber = RandomNumberGenerator.generate(0 , 10);
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

    //Sets the computer's level relative to the player.
    private short setRelativeComputerLevel() {
        short level;
        if (player.getLevel() > 2) {
            level = (short) RandomNumberGenerator.generate(player.getLevel() - 1, player.getLevel() + 2);
        } else level = 1;
        return level;
    }
}
