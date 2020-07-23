package com.justin;

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
        if (input.equalsIgnoreCase("yes") && player.coins >= 2) {
            player.hp = player.maxHP;
            int newBalance = player.coins - 2;
            player.coins = newBalance;
            System.out.println("*You feel well rested*");
            System.out.println("*You return to town*");

        } else if (input.equalsIgnoreCase("yes") && player.coins < 2) {
            System.out.println("> Gunthor: You can't pay me with hugs, sorry..");
            System.out.println("*You return to town*");

        } else {
            System.out.println("*You return to town*");
        }
    }

}
