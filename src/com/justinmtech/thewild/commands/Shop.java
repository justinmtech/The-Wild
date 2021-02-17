package com.justinmtech.thewild.commands;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.user_interface.Display;
import com.justinmtech.thewild.utilities.ScanInput;

//The shop command.
//A shop for purchasing armor and weapons.
public class Shop extends Command {
    private static final int BRONZE_SHORT_SWORD_COST = 12;
    private static final int BRONZE_LONG_SWORD_COST = 36;
    private static final int IRON_SHORT_SWORD_COST = 48;
    private static final int IRON_LONG_SWORD_COST = 144;
    private static final int STEEL_SHORT_SWORD_COST = 192;
    private static final int STEEL_LONG_SWORD_COST = 576;
    private static final int DIAMOND_SHORT_SWORD_COST = 768;
    private static final int DIAMOND_LONG_SWORD_COST = 2_304;
    private static final int LEATHER_ARMOR_COST = 46;
    private static final int BRONZE_ARMOR_COST = 230;
    private static final int IRON_ARMOR_COST = 1_150;
    private static final int STEEL_ARMOR_COST = 5_750;
    private static final int DIAMOND_ARMOR_COST = 28_750;
    private static final int COMMON_HEALING_POTION_COST = 24;
    private static final int UNCOMMON_HEALING_POTION_COST = 48;
    private static final int RARE_HEALING_POTION_COST = 96;
    private static final int LEGENDARY_HEALING_POTION_COST = 192;
    private static final String[] stock = {"Bronze_Short_Sword", "Bronze_Long_Sword",
            "Iron_Short_Sword", "Iron_Long_Sword",
            "Steel_Short_Sword", "Steel_Long_Sword",
            "Diamond_Short_Sword", "Diamond_Long_Sword",
            "Leather_Armor", "Bronze_Armor", "Iron_Armor", "Steel_Armor", "Diamond_Armor", "Common_Healing_Potion", "Uncommon_Healing_Potion", "Rare_Healing_Potion", "Legendary_Healing_Potion"};

    private String input;
    private boolean quit;

    public Shop(Entity player) {
        setLabel("shop");
        setPlayer(player);
        setDisplay(new Display());
        getDisplay().goToShop();
        quit = false;
        getPlayer().setLocation("shop");
        loop();
    }

    //The player will be in the shop loop until they type 'leave'
    private void loop() {
        welcome();
        displayItems();
        talk();
        while (!quit) {
            listenToPlayerCommands();
        }
        getPlayer().setLocation("town");
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
        for (int i = 0; i < stock.length; i++) {
            System.out.println(stock[i] + " [" + getCostFromCommand(stock[i]) + " Coins] ");
        }
    }

    //The cost of the items in the shop.
    private int getCostFromCommand(String input) {
        switch (input) {
            case "Bronze_Short_Sword":
                return BRONZE_SHORT_SWORD_COST;
            case "Bronze_Long_Sword":
                return BRONZE_LONG_SWORD_COST;
            case "Iron_Short_Sword":
                return IRON_SHORT_SWORD_COST;
            case "Iron_Long_Sword":
                return IRON_LONG_SWORD_COST;
            case "Steel_Short_Sword":
                return STEEL_SHORT_SWORD_COST;
            case "Steel_Long_Sword":
                return STEEL_LONG_SWORD_COST;
            case "Diamond_Short_Sword":
                return DIAMOND_SHORT_SWORD_COST;
            case "Diamond_Long_Sword":
                return DIAMOND_LONG_SWORD_COST;
            case "Leather_Armor" :
                return LEATHER_ARMOR_COST;
            case "Bronze_Armor" :
                return BRONZE_ARMOR_COST;
            case "Iron_Armor" :
                return IRON_ARMOR_COST;
            case "Steel_Armor" :
                return STEEL_ARMOR_COST;
            case "Diamond_Armor" :
                return DIAMOND_ARMOR_COST;
            case "Common_Healing_Potion" :
                return COMMON_HEALING_POTION_COST;
            case "Uncommon_Healing_Potion" :
                return UNCOMMON_HEALING_POTION_COST;
            case "Rare_Healing_Potion" :
                return RARE_HEALING_POTION_COST;
            case "Legendary_Healing_Potion" :
                return LEGENDARY_HEALING_POTION_COST;
            default :
                return 0;
        }
    }

    //Scan the player's commands while in the shop.
    private void listenToPlayerCommands() {
        input = ScanInput.getString();
        if (input.equalsIgnoreCase("leave")) {
            quit = true;
        } else if (isItemInStock(input)) {
            System.out.println("Do you want to buy " + input + " for " + getCostFromCommand(input) + "? Type 'yes' to confirm or 'no' to cancel.");
            String confirmation = ScanInput.getString();
            if (confirmation.equalsIgnoreCase("yes")) {
                doTransaction(getPlayer());
                displayItems();
            } else {
                System.out.println("Purchase cancelled..");
            }
        } else {
            System.out.println("> Lisa: I don't sell that here.");
        }
    }

    //Give a player an item if possible.
    private void putItemInInventory() {
        if (getPlayer().getInventory().size() < 32) {
            getPlayer().getInventory().add(input);
        }
        else {
            System.out.println("Inventory full!");
        }
    }

    //Check if a player has enough money to purchase something, and take the cost if so.
    private void doTransaction(Entity player) {
        if (player.getCoins() >= getCostFromCommand(input)) {
            player.setCoins(player.getCoins() - getCostFromCommand(input));
            putItemInInventory();
            System.out.println("You spent " + getCostFromCommand(input) + " and now have " + player.getCoins() + " Coins.");
        } else {
            System.out.println("Sorry, you don't have enough money for this!");
        }
    }

    //Check if an item is in stock and return true/false.
    private boolean isItemInStock(String input) {
        boolean inStock = false;
        for (int i = 0; i < stock.length; i++) {
            if (input.equals(stock[i])) {
                inStock = true;
            }
        }
        return inStock;
    }
}
