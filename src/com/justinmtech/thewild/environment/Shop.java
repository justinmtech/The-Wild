package com.justinmtech.thewild.environment;

import com.justinmtech.thewild.entity.Entity;

import java.util.Scanner;

public class Shop {
    private Entity player;
    private String input;
    private boolean quit = false;
    private boolean shopping = true;

    public Shop(Entity player) {
        this.player = player;
    }

    public void loop() {
        welcome();
        displayItems();
        talk();
        while (!quit) {
            scanInput(player);
        }
        player.setLocation("town");
        System.out.println("*You return to town*");
    }

    public void welcome() {
        System.out.println("> Lisa: Welcome traveller.");
        System.out.println("> Lisa: Take a look around.");
        System.out.println("> Lisa: Just type 'leave' to leave!");
        System.out.println("--------------------------------------------");
    }

    public void talk() {
        System.out.println("--------------------------------------------");
    }

    public void displayItems() {
        String[] items = {"Short_Sword", "Long_Sword", "Leather_Armor", "Iron_Armor"};

        int i;
        for (i = 0; i < items.length; i++) {
            System.out.println(items[i] + " [" + getCost(items[i]) + " Coins] ");
        }
    }

    public int getCost(String input) {
        int cost = 0;
        if (input.equalsIgnoreCase("Short_Sword")) {
            cost = 10;
        } else if (input.equalsIgnoreCase("Long_Sword")) {
            cost = 15;
        } else if (input.equalsIgnoreCase("Leather_Armor")) {
            cost = 25;
        } else if (input.equalsIgnoreCase("Iron_Armor")) {
            cost = 35;
        }
        return cost;
    }

    public void scanInput(Entity player) {
        Scanner scanner = new Scanner(System.in);
        input = scanner.next();
        if (input.equalsIgnoreCase("leave")) {
            quit = true;
        }
        else if (itemInStock(input)) {
            System.out.println("Do you want to buy " + input + " for " + getCost(input) + "? Type 'yes' to confirm or 'no' to cancel.");
            String confirmation = scanner.next();
            if (confirmation.equalsIgnoreCase("yes")) {
                transaction(player);
                displayItems();
            } else {
                System.out.println("Purchase cancelled..");
            }
        } else {
            System.out.println("> Lisa: I don't sell that here.");
        }
    }

    public void giveItem(Entity player) {
        boolean itemGiven = false;
        int invSize = player.getInventory().length;
        int i;
        for (i = 0; i < invSize; i++) {
            if (player.getInventory()[i].equalsIgnoreCase("Air")) {
                player.getInventory()[i] = input;
                itemGiven = true;
                break;
            }
            if (i == invSize && itemGiven == false) {
                System.out.println("Your inventory is full.");
            }
        }
    }

    public void transaction(Entity player) {
        if (player.getCoins() >= getCost(input)) {
            int newBalance = player.getCoins() - getCost(input);
            player.setCoins(newBalance);
            giveItem(player);
            System.out.println("You spent " + getCost(input) + " and now have " + player.getCoins() + " Coins.");
        } else {
            System.out.println("Sorry, you don't have enough money for this!");
        }
    }

    public boolean itemInStock(String input) {
        boolean inStock = false;
        int i;
        String[] items = {"Short_Sword", "Long_Sword", "Leather_Armor", "Iron_Armor"};
        for (i = 0; i < items.length; i++) {
            if (input.equalsIgnoreCase(items[i])) {
                inStock = true;
            } else {
                inStock = false;
            }
        }
        return inStock;
    }
}
