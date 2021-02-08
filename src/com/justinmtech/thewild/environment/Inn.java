package com.justinmtech.thewild.environment;

import com.justinmtech.thewild.entity.Entity;

import java.util.Scanner;

public class Inn {

    public void loop(Entity player) {
        welcome();
        scanInput(player);
    }

    public void welcome() {
        System.out.println("> Gunthor: Welcome traveller.");
        System.out.println("> Gunthor: Would you like a room? It's 2 Coins for a night.");
    }

    public void scanInput(Entity player) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if (input.equalsIgnoreCase("yes") && player.getCoins() >= 2) {
            player.setHp(player.getMaxHP());
            int newBalance = player.getCoins() - 2;
            player.setCoins(newBalance);
            System.out.println("*You feel well rested*");
            System.out.println("*You return to town*");

        } else if (input.equalsIgnoreCase("yes") && player.getCoins() < 2) {
            System.out.println("> Gunthor: You can't pay me with hugs, sorry..");
            System.out.println("*You return to town*");

        } else {
            System.out.println("*You return to town*");
        }
    }

}
