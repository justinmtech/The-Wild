package com.justinmtech.thewild.commands;

import com.justinmtech.thewild.data.LoadConfiguration;
import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.user_interface.Display;
import com.justinmtech.thewild.utilities.ScanInput;

//The shop command.
//A shop for purchasing armor and weapons.
public class Shop extends Command {
    //Below is all of the items stocked and their cost
    private final int BRONZE_SHORT_SWORD_COST;
    private final int BRONZE_LONG_SWORD_COST;
    private final int IRON_SHORT_SWORD_COST;
    private final int IRON_LONG_SWORD_COST;
    private final int STEEL_SHORT_SWORD_COST;
    private final int STEEL_LONG_SWORD_COST;
    private final int DIAMOND_SHORT_SWORD_COST;
    private final int DIAMOND_LONG_SWORD_COST;
    private final int LEATHER_ARMOR_COST;
    private final int BRONZE_ARMOR_COST;
    private final int IRON_ARMOR_COST;
    private final int STEEL_ARMOR_COST;
    private final int DIAMOND_ARMOR_COST;
    private final int COMMON_HEALING_POTION_COST;
    private final int UNCOMMON_HEALING_POTION_COST;
    private final int RARE_HEALING_POTION_COST;
    private final int LEGENDARY_HEALING_POTION_COST;
    private static final String[] stock = {"Bronze_Short_Sword", "Bronze_Long_Sword",
            "Iron_Short_Sword", "Iron_Long_Sword",
            "Steel_Short_Sword", "Steel_Long_Sword",
            "Diamond_Short_Sword", "Diamond_Long_Sword",
            "Leather_Armor", "Bronze_Armor", "Iron_Armor", "Steel_Armor", "Diamond_Armor", "Common_Healing_Potion", "Uncommon_Healing_Potion", "Rare_Healing_Potion", "Legendary_Healing_Potion"};

    private String input;
    private boolean hasLeftShop;

    public Shop(Entity player) {
        LoadConfiguration config = new LoadConfiguration();
        BRONZE_SHORT_SWORD_COST = (int)config.getConfig().get("shop_prices").get("BRONZE_SHORT_SWORD");
        BRONZE_LONG_SWORD_COST = (int)config.getConfig().get("shop_prices").get("BRONZE_LONG_SWORD");
        IRON_SHORT_SWORD_COST = (int)config.getConfig().get("shop_prices").get("IRON_SHORT_SWORD");
        IRON_LONG_SWORD_COST = (int)config.getConfig().get("shop_prices").get("IRON_LONG_SWORD");
        STEEL_SHORT_SWORD_COST = (int)config.getConfig().get("shop_prices").get("STEEL_SHORT_SWORD");
        STEEL_LONG_SWORD_COST = (int)config.getConfig().get("shop_prices").get("STEEL_LONG_SWORD");
        DIAMOND_SHORT_SWORD_COST = (int)config.getConfig().get("shop_prices").get("DIAMOND_SHORT_SWORD");
        DIAMOND_LONG_SWORD_COST = (int)config.getConfig().get("shop_prices").get("DIAMOND_LONG_SWORD");
        LEATHER_ARMOR_COST = (int)config.getConfig().get("shop_prices").get("LEATHER_ARMOR");
        BRONZE_ARMOR_COST = (int)config.getConfig().get("shop_prices").get("BRONZE_ARMOR");
        IRON_ARMOR_COST = (int)config.getConfig().get("shop_prices").get("IRON_ARMOR");
        STEEL_ARMOR_COST = (int)config.getConfig().get("shop_prices").get("STEEL_ARMOR");
        DIAMOND_ARMOR_COST = (int)config.getConfig().get("shop_prices").get("DIAMOND_ARMOR");
        UNCOMMON_HEALING_POTION_COST = (int)config.getConfig().get("shop_prices").get("UNCOMMON_HEALING_POTION");
        COMMON_HEALING_POTION_COST = (int)config.getConfig().get("shop_prices").get("COMMON_HEALING_POTION");
        RARE_HEALING_POTION_COST = (int)config.getConfig().get("shop_prices").get("RARE_HEALING_POTION");
        LEGENDARY_HEALING_POTION_COST = (int)config.getConfig().get("shop_prices").get("LEGENDARY_HEALING_POTION");

        setLabel("shop");
        setPlayer(player);
        setDisplay(new Display());
        getDisplay().goToShop();
        hasLeftShop = false;
        getPlayer().setLocation("shop");
        loop();
    }

    //The player will be in the shop loop until they type 'leave' or until hasLeftShop is true.
    private void loop() {
        welcome();
        displayItems();
        talk();
        while (!hasLeftShop) {
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
            hasLeftShop = true;
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

    //Put the item in the proper inventory slot (0-2) based on what type it is.
    //This replaces the item that was in the slot previously, if there was one.
    private void putItemInInventory() {
        getPlayer().getInventory()[getItemType()] = input;
    }

    //Get an item's type from the input
    //0 = swords, 1 = armor, 2 = potions
    private int getItemType() {
        int itemType = 0;
        if (input.contains("Armor")) {
            itemType = 1;
        } else if (input.contains("Potion")) {
            itemType = 2;
        }
        return itemType;
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
