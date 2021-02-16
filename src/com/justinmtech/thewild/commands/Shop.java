package com.justinmtech.thewild.commands;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.user_interface.Display;
import com.justinmtech.thewild.utilities.ScanInput;

//The shop command.
//A shop for purchasing armor and weapons.
public class Shop {
    private static final int SHORT_SWORD_COST = 12;
    private static final int LONG_SWORD_COST = 56;
    private static final int LEATHER_ARMOR_COST = 23;
    private static final int IRON_ARMOR_COST = 72;

    private final Entity player;
    private final Display display;
    private String input;
    private boolean quit;

    public Shop(Entity player) {
        display = new Display();
        display.goToShop();
        quit = false;
        this.player = player;
        player.setLocation("shop");
        loop();
    }

    //The player will be in the shop loop until they type 'leave'
    private void loop() {
        welcome();
        displayItems();
        talk();
        while (!quit) {
            listenToPlayerCommands(player);
        }
        player.setLocation("town");
        System.out.println("*You return to town*");
    }

    //Display welcome information for the shop.
    private void welcome() {
        System.out.println("> Lisa: Welcome traveller.");
        System.out.println("> Lisa: Take a look around.");
        System.out.println("> Lisa: Just type 'leave' to leave!");
        System.out.println("--------------------------------------------");
    }

    //A separator for shop messages
    private void talk() {
        System.out.println("--------------------------------------------");
    }

    //The items for sale in the shop.
    private void displayItems() {
        String[] items = {"Short_Sword", "Long_Sword", "Leather_Armor", "Iron_Armor"};

        int i;
        for (i = 0; i < items.length; i++) {
            System.out.println(items[i] + " [" + getCostFromCommand(items[i]) + " Coins] ");
        }
    }

    //The cost of the items in the shop.
    private int getCostFromCommand(String input) {
        int cost = 0;
        if (input.equalsIgnoreCase("Short_Sword")) {
            cost = SHORT_SWORD_COST;
        } else if (input.equalsIgnoreCase("Long_Sword")) {
            cost = LONG_SWORD_COST;
        } else if (input.equalsIgnoreCase("Leather_Armor")) {
            cost = LEATHER_ARMOR_COST;
        } else if (input.equalsIgnoreCase("Iron_Armor")) {
            cost = IRON_ARMOR_COST;
        }
        return cost;
    }

    //Scan the player's commands while in the shop.
    private void listenToPlayerCommands(Entity player) {
        input = ScanInput.getString();
        if (input.equalsIgnoreCase("leave")) {
            quit = true;
        } else if (isItemInStock(input)) {
            System.out.println("Do you want to buy " + input + " for " + getCostFromCommand(input) + "? Type 'yes' to confirm or 'no' to cancel.");
            String confirmation = ScanInput.getString();
            if (confirmation.equalsIgnoreCase("yes")) {
                doTransaction(player);
                displayItems();
            } else {
                System.out.println("Purchase cancelled..");
            }
        } else {
            System.out.println("> Lisa: I don't sell that here.");
        }
    }

    //Give a player an item if possible.
    private void putItemInInventory(Entity player) {
        int invSize = player.getInventory().length;
        int i;
        for (i = 0; i < invSize; i++) {
            if (player.getInventory()[i].equalsIgnoreCase("Air")) {
                player.getInventory()[i] = input;
                break;
            }
        }
    }

    //Check if a player has enough money to purchase something, and take the cost if so.
    private void doTransaction(Entity player) {
        if (player.getCoins() >= getCostFromCommand(input)) {
            player.setCoins(player.getCoins() - getCostFromCommand(input));
            putItemInInventory(player);
            System.out.println("You spent " + getCostFromCommand(input) + " and now have " + player.getCoins() + " Coins.");
        } else {
            System.out.println("Sorry, you don't have enough money for this!");
        }
    }

    //Check if an item is in stock and return true/false.
    private boolean isItemInStock(String input) {
        boolean inStock = false;
        String[] items = {"Short_Sword", "Long_Sword", "Leather_Armor", "Iron_Armor"};
        for (int i = 0; i < items.length; i++) {
            if (input.equalsIgnoreCase(items[i])) {
                inStock = true;
            }
        }
        return inStock;
    }
}
