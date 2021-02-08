package com.justinmtech.thewild.ui;

import com.justinmtech.thewild.entity.Entity;

public class Display {

    public void combatOutput(Entity player, Entity computer) {
        line();
        System.out.println("Your HP: " + (int)player.hp + "/" + player.maxHP + " || " + computer.name + " HP: " + (int)computer.hp + "/" + computer.maxHP);
        line();
    }

    public void combatOutcome(Entity player, Entity computer) {
        if (player.isAlive && player.inCombat) {
            line();
            System.out.println("You killed the " + computer.name);
            System.out.println("Total Coins: " + player.coins + " Coins");
        } else if (player.isAlive && !player.inCombat) {
            line();
            System.out.println("You fled the battle.. " + (int)player.hp + "/" + player.maxHP + " HP");
            line();
        } else if (computer.isAlive) {
            line();
            System.out.println("You were slain by the " + computer.name);
            line();
            System.out.println("*You lose your items and respawn in town*");
        } else {
            line();
            System.out.println("You both died in battle");
        }
    }

    public void newBattle(Entity computer) {
        line();
        System.out.println("You are now in battle with a " + computer.name + " (Lvl " + computer.level + ")");
        line();
    }

    public void otherHelp() {
        System.out.println("Available Commands:");
        System.out.println("> info - get player info");
        System.out.println("> wild - go to the wilderness");
        System.out.println("> shop - visit the local shop");
        System.out.println("> inn - visit the town inn");
        System.out.println("> quit - quit the game");
    }

    public void townHelp() {
        System.out.println("Available Commands:");
        System.out.println("> info - get player info");
        System.out.println("> wild - go to the wilderness");
        System.out.println("> shop - visit the local shop");
        System.out.println("> inn - visit the town inn");
        System.out.println("> quit - quit the game");
    }

    public void wildHelp() {
        System.out.println("Available Commands:");
        System.out.println("> info - get player info");
        System.out.println("> town - go back to town");
        System.out.println("> battle - search for an enemy to fight");
        System.out.println("> quit - quit the game");
    }

    public void combatHelp() {
        System.out.println("Available Commands:");
        System.out.println("> info - get player info");
        System.out.println("> slash - slash the enemy");
        System.out.println("> stab - stab the enemy");
        System.out.println("> flea - run away");
        System.out.println("> quit - quit the game");
    }

    public void info(Entity player) {
        line();
        System.out.println("Player Info: " + player.name);
        line();
        System.out.println("HP: " + (int)player.hp + "/" + player.maxHP);
        System.out.println("Coins: " + player.coins);
        System.out.println("Level: " + player.level);
        System.out.println("XP: " + player.xp);
        System.out.println("Location: " + player.location);
        System.out.print("Inventory: ");
        int i;
        for (i = 0; i < player.inventory.length; i++) {
            if (!player.inventory[i].equalsIgnoreCase("Air")) {
                System.out.print(player.inventory[i] + " ");
            }
        }
        System.out.println();
        line();
    }

    public void alreadyInWild() {
        System.out.println("You are already in the wild!");
    }

    public void alreadyInTown() {
        System.out.println("You are already in town!");
    }

    public void goToShop() {
        System.out.println("*You walk to the local shop*");
    }

    public void returnTown() {
        System.out.println("*You return to town*");
    }

    public void goToWild() {
        System.out.println("*You go to the wilderness*");
        System.out.println("> Type 'battle' to start a fight!");
        System.out.println("Available Combat Commands:");
        System.out.println("> slash, stab, heal, flea");
    }

    public void searchBattle() {
        System.out.println("*Searching for an enemy*");
    }

    public void noEnemiesNear() {
        System.out.println("There are no enemies nearby.");
    }

    public void line() {
        System.out.println("----------------------------------------------");
    }
}
