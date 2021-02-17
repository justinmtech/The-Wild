package com.justinmtech.thewild.user_interface;

import com.justinmtech.thewild.entity.Entity;

//Used for displaying configurable text as a response to frequently occurring game events
public class Display {

    //Shows HP of player and computer
    public void combatOutput(Entity player, Entity computer) {
        line();
        System.out.println("Your HP: " + (int) player.getHp() + "/" + player.getMaxHP() + " || " + computer.getName() + " HP: " + (int) computer.getHp() + "/" + computer.getMaxHP());
        line();
    }

    //Shows the outcome of a battle when it ends
    public void combatOutcome(Entity player, Entity computer) {
        if (player.isAlive() && player.isInCombat()) {
            line();
            System.out.println("You killed the " + computer.getName());
        } else if (player.isAlive() && !player.isInCombat()) {
            line();
            System.out.println("You fled the battle.. " + (int) player.getHp() + "/" + player.getMaxHP() + " HP");
            line();
        } else if (computer.isAlive()) {
            line();
            System.out.println("You were slain by the " + computer.getName());
            line();
            System.out.println("*You lose your items and respawn in town*");
        } else {
            line();
            System.out.println("You both died in battle");
        }
    }

    //Shows your opponent after entering a battle
    public void newBattle(Entity computer) {
        line();
        System.out.println("You are now in battle with a " + computer.getName() + " (Lvl " + computer.getLevel() + ")");
        line();
    }

    //Generic help
    public void otherHelp() {
        System.out.println("Available Commands:");
        System.out.println("> info - get player info");
        System.out.println("> wild - go to the wilderness");
        System.out.println("> shop - visit the local shop");
        System.out.println("> inn - visit the town inn");
        System.out.println("> quit - quit the game");
    }

    //Help that shows while in town
    public void townHelp() {
        System.out.println("Available Commands:");
        System.out.println("> info - get player info");
        System.out.println("> wild - go to the wilderness");
        System.out.println("> shop - visit the local shop");
        System.out.println("> inn - visit the town inn");
        System.out.println("> quit - quit the game");
    }

    //Help that shows while in wild
    public void wildHelp() {
        System.out.println("Available Commands:");
        System.out.println("> info - get player info");
        System.out.println("> town - go back to town");
        System.out.println("> battle - search for an enemy to fight");
        System.out.println("> quit - quit the game");
    }

    //Help that shows while in combat
    public void combatHelp() {
        System.out.println("Available Commands:");
        System.out.println("> info - get player info");
        System.out.println("> slash - slash the enemy");
        System.out.println("> stab - stab the enemy");
        System.out.println("> flea - run away");
        System.out.println("> quit - quit the game");
    }

    //Shows info about the player
    public void info(Entity player) {
        line();
        System.out.println("Player Info: " + player.getName());
        line();
        System.out.println("HP: " + (int) player.getHp() + "/" + player.getMaxHP());
        System.out.println("Coins: " + player.getCoins());
        System.out.println("Level: " + player.getLevel());
        System.out.println("XP: " + player.getXp());
        System.out.println("Location: " + player.getLocation());
        System.out.print("Inventory: ");
        System.out.print(player.getInventory()[0] + " " + player.getInventory()[1] + " " + player.getInventory()[2]);
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

    public void welcome() {
        System.out.println("Welcome to Izengard. Who are you?");
    }

    public void greeting(Entity player) {
        System.out.println("> Guard: Nice to meet ya' " + player.getName() + ".");
    }

    public void farewell() {
        System.out.println("> Guard: Use 'wild' to go adventure in the wild or 'help' for a list of commands.");
    }

    public void goToInn() {
            System.out.println("*You walk to the local inn*");
    }
}
