package com.justinmtech.thewild.commands;

import com.justinmtech.thewild.utilities.ConfigHandler;
import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.entity.combat_logic.EnemyAI;
import com.justinmtech.thewild.user_interface.CommandParser;
import com.justinmtech.thewild.user_interface.Display;
import com.justinmtech.thewild.utilities.RandomNumberGenerator;

import java.util.List;

//The battle command.
//Used for organizing battle instances when the player issues the "battle" command in the wild.
//This class handles all logic around the battles of entities.
@SuppressWarnings("SuspiciousMethodCalls")
public class Battle extends Command {
    private final int CPU_LEVEL_DIFFERENCE_LOW;
    private final int CPU_LEVEL_DIFFERENCE_HIGH;
    private final int COIN_LOOT_MULTIPLIER;
    private final int XP_MULTIPLIER_LOW;
    private final int XP_MULTIPLIER_HIGH;

    private final CommandParser parser;
    private final EnemyAI enemyAI;
    private List<Entity> entities;

    public Battle(Entity player) {
        ConfigHandler config = new ConfigHandler();
        CPU_LEVEL_DIFFERENCE_LOW = (int)config.getConfig().get("battle").get("CPU_LEVEL_DIFFERENCE_LOW");
        CPU_LEVEL_DIFFERENCE_HIGH = (int)config.getConfig().get("battle").get("CPU_LEVEL_DIFFERENCE_HIGH");
        COIN_LOOT_MULTIPLIER = (int)config.getConfig().get("battle").get("COIN_LOOT_MULTIPLIER");
        XP_MULTIPLIER_LOW = (int)config.getConfig().get("battle").get("XP_MULTIPLIER_LOW");
        XP_MULTIPLIER_HIGH = (int)config.getConfig().get("battle").get("XP_MULTIPLIER_HIGH");

        setLabel("battle");
        setPlayer(player);
        player.setInCombat(true);
        Entity computer = new Entity(pickFoe(), setRelativeComputerLevel(), true);
        setComputer(computer);
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
            entities = enemyAI.doSkill(getComputer(), getPlayer());
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
        return (getPlayer().isAlive() && getComputer().isAlive() && getPlayer().isInCombat() && getComputer().isInCombat());
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
            getComputer().setInventory(new String[]{"Bronze_Short_Sword", "", ""});
        } else if (randomNumber >= 5) {
            getComputer().setInventory(new String[]{"Bronze_Long_Sword", "", ""});
        } else if (randomNumber >= 2) {
            getComputer().setInventory(new String[]{"Iron_Long_Sword", "Leather_Armor", ""});
        } else {
            getComputer().setInventory(new String[]{"Iron_Short_Sword", "Iron_Armor", ""});
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
