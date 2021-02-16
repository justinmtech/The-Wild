package com.justinmtech.thewild.environment;

import com.justinmtech.thewild.entity.Entity;

import java.util.Scanner;

//The player's retreat, located in the town. Staying in the Inn heals the player for a cost.
public class Inn {
    private final Entity player;
    private final static int COST = 2;

    public Inn(Entity player) {
        this.player = player;
        loop();
    }

    private void loop() {
        welcome();
        scanInput(player);
    }

    private void welcome() {
        System.out.println("> Gunthor: Welcome traveller.");
        System.out.println("> Gunthor: Would you like a room? It's 2 Coins for a night.");
    }

    private void scanInput(Entity player) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if (input.equalsIgnoreCase("yes") && player.getCoins() >= COST) {
            player.resetHp();
            player.setCoins(player.getCoins() - COST);
            System.out.println("*You feel well rested*");
            System.out.println("*You return to town*");
        } else if (input.equalsIgnoreCase("yes") && player.getCoins() < COST) {
            System.out.println("> Gunthor: You can't pay me with hugs, sorry..");
            System.out.println("*You return to town*");
        } else {
            System.out.println("*You return to town*");
        }
    }
}
