package com.justinmtech.thewild.persistence;

import com.justinmtech.thewild.entity.Entity;

//Handles the saving and loading of player data.
//Player data is saved in player-data in the format (username).data
public class PlayerDataHandler {

    //Data is saved only when the player quits the game successfully with 'quit'
    //Logic can be found in Quit
    public void save(Entity player) {
        SaveData data = new SaveData();

        data.name = player.getName();
        data.hp = player.getHp();
        data.coins = player.getCoins();
        data.xp = player.getXp();
        data.location = player.getLocation();
        data.inventory = player.getInventory();

        try {
            ResourceManager.save(data, "player-data/" + player.getName() + ".data");
        }
        catch (Exception e) {
            System.out.println("Couldn't save: " + e.getMessage());
        }
    }

    //Data is loaded from the Game class
    public Entity load(String username) throws Exception {
        SaveData data = (SaveData) ResourceManager.load("player-data/" + username + ".data");

        String playerName = data.name;
        double playerHP = data.hp;
        int playerCoins = data.coins;
        int playerXP = data.xp;
        String playerLoc = data.location;
        String[] playerInv = data.inventory;

        Entity player = new Entity();
        player.setName(playerName);
        player.setHp(playerHP);
        player.setCoins(playerCoins);
        player.setXp(playerXP);
        player.setLocation(playerLoc);
        player.setInventory(playerInv);

        System.out.println("Data loaded!");

        return player;
    }
}
