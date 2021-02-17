package com.justinmtech.thewild.commands;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.utilities.ScanInput;

//The Inn command, usable while in town.
//Using the Inn heals the player for a cost.
public class Inn extends Command {
    //The cost to stay in the Inn
    private final static int COST_TO_STAY = 2;

    public Inn(Entity player) {
        setLabel("inn");
        setPlayer(player);
        loop();
    }

    //Initiate the Inn loop until the player leaves or gets a room.
    private void loop() {
        welcome();
        scanInput(getPlayer());
    }

    //Display a welcome message.
    private void welcome() {
        System.out.println("> Gunthor: Welcome traveller.");
        System.out.println("> Gunthor: Would you like a room? It's " + COST_TO_STAY + " Coins for a night.");
    }

    //Listen to the player's commands while visiting the inn.
    private void scanInput(Entity player) {
        String input = ScanInput.getString();
        if (input.equalsIgnoreCase("yes") && player.getCoins() >= COST_TO_STAY) {
            player.resetHp();
            player.setCoins(player.getCoins() - COST_TO_STAY);
            System.out.println("*You feel well rested*");
            System.out.println("*You return to town*");
        } else if (input.equalsIgnoreCase("yes") && player.getCoins() < COST_TO_STAY) {
            System.out.println("> Gunthor: You can't pay me with hugs, sorry..");
            System.out.println("*You return to town*");
        } else {
            System.out.println("*You return to town*");
        }
    }
}
