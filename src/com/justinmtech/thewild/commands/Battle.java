package com.justinmtech.thewild.commands;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.entity.skill_logic.EnemyAI;
import com.justinmtech.thewild.user_interface.CommandParser;
import com.justinmtech.thewild.user_interface.Display;
import com.justinmtech.thewild.utilities.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.Arrays;

//The battle command.
//Used for organizing battle instances when the player issues the "battle" command in the wild.
//This class handles all logic around the battles of entities.
public class Battle extends Command {
    private final static int CPU_LEVEL_DIFFERENCE_LOW = 4;
    private final static int CPU_LEVEL_DIFFERENCE_HIGH = 2;
    private final static int COIN_LOOT_MULTIPLIER = 4;
    private final static int XP_MULTIPLIER_LOW = 2;
    private final static int XP_MULTIPLIER_HIGH = 20;

    private final CommandParser parser;
    private final EnemyAI enemyAI;
    private ArrayList<Entity> entities;

    public Battle(Entity player) {
        setLabel("battle");
        setPlayer(player);
        player.setInCombat(true);
        setComputer(new Entity(pickFoe(), setRelativeComputerLevel(), true));
        setRandomComputerLoadout();
        this.parser = new CommandParser(player);
        setDisplay(new Display());
        this.enemyAI = new EnemyAI();

        getDisplay().newBattle(getComputer());
        combatLoop();
    }

    //Wait for player attack command, then trigger a random computer attack until the player fleas or an entity dies.
    private void combatLoop() {
        while (battleIsNotOver()) {
            entities = parser.getAttackCommand(getPlayer(), getComputer());
            updateEntities("player");
            entities = enemyAI.doRandomSkill(getComputer(), getPlayer());
            updateEntities("computer");
            if (battleIsNotOver()) getDisplay().combatOutput(getPlayer(), getComputer());
        }
        if (getPlayer().isAlive() && getPlayer().isInCombat()) playerWin();
            else playerLose();
    }

    //Update the Battle class player/computer entities with the ArrayList entity response in combatLoop().
    private void updateEntities(String type) {
        switch (type) {
            case "player":
                setPlayer(entities.get(0));
                setComputer(entities.get(1));
                break;
            case "computer":
                setComputer(entities.get(0));
                setPlayer(entities.get(1));
                break;
        }
    }

    //Triggers win conditions for the player after successfully killing an enemy.
    private void playerWin() {
        getDisplay().combatOutcome(getPlayer(), getComputer());
        getPlayer().giveCoins(RandomNumberGenerator.generate(getComputer().getLevel(), getComputer().getLevel() * COIN_LOOT_MULTIPLIER));
        getPlayer().addXp(RandomNumberGenerator.generate(getComputer().getLevel() * XP_MULTIPLIER_LOW, getComputer().getLevel() * XP_MULTIPLIER_HIGH));
        getPlayer().incrementBattles();
        getPlayer().setInCombat(false);
        getDisplay().info(getPlayer());
    }

    //Triggers lose conditions for the player after dying to an enemy.
    private void playerLose() {
        getDisplay().combatOutcome(getPlayer(), getComputer());
        getPlayer().setCoins(0);
        getPlayer().setLocation("town");
        getPlayer().resetInventory();
        getPlayer().resetHp();
        getPlayer().incrementBattles();
        getPlayer().setInCombat(false);
    }

    //Checks if a battle is over or not.
    private boolean battleIsNotOver() {
        boolean isBattleOver = (getPlayer().isAlive() && getComputer().isAlive() && getPlayer().isInCombat() && getComputer().isInCombat());
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
        int randomNumber = RandomNumberGenerator.generate(1 , 10);
        if (randomNumber >= 7) {
            getComputer().setInventory(new ArrayList<>(Arrays.asList("Bronze_Short_Sword")));
        } else if (randomNumber >= 5) {
            getComputer().setInventory(new ArrayList<>(Arrays.asList("Bronze_Long_Sword")));
        } else if (randomNumber >= 2) {
            getComputer().setInventory(new ArrayList<>(Arrays.asList("Iron_Long_Sword", "Leather_Armor")));
        } else {
            getComputer().setInventory(new ArrayList<>(Arrays.asList("Iron_Short_Sword", "Iron_Armor")));
        }
    }

    //Sets the computer's level relative to the player.
    private short setRelativeComputerLevel() {
        short level;
        if (getPlayer().getLevel() > 4) {
            level = (short) RandomNumberGenerator.generate(getPlayer().getLevel() - CPU_LEVEL_DIFFERENCE_LOW, getPlayer().getLevel() + CPU_LEVEL_DIFFERENCE_HIGH);
        } else level = (short) RandomNumberGenerator.generate(0,3);
        return level;
    }
}
