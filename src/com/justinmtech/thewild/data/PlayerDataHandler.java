package com.justinmtech.thewild.data;

import com.justinmtech.thewild.entity.Entity;

public class PlayerDataHandler {
    private Entity player;

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

    public Entity load(String username) throws Exception {
        SaveData data = (SaveData) ResourceManager.load("player-data/" + username + ".data");

        String playerName = data.name;
        double playerHP = data.hp;
        int playerCoins = data.coins;
        int playerXP = data.xp;
        String playerLoc = data.location;
        String[] playerInv = data.inventory;

        player = new Entity();
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
