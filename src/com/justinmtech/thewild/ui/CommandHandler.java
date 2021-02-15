package com.justinmtech.thewild.ui;

import com.justinmtech.thewild.data.ResourceManager;
import com.justinmtech.thewild.data.SaveData;
import com.justinmtech.thewild.environment.Battle;
import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.entity.Skills;
import com.justinmtech.thewild.environment.Inn;
import com.justinmtech.thewild.environment.Shop;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class CommandHandler {
    private Entity player;
    private Entity computer;
    private Display display;
    private Skills skills;
    private Shop shop;
    private Inn inn;
    private Battle battle;
    private ResourceManager resourceManager;

    public CommandHandler(Entity player, Entity computer) {
        this.player = player;
        this.computer = computer;
        this.display = new Display();
        this.skills = new Skills(player, computer);
        this.shop = new Shop(player);
        this.inn = new Inn();
        this.resourceManager = new ResourceManager();

    }

    public void help() {
        if (player.getLocation().equalsIgnoreCase("town")) {
            display.townHelp();
        } else if (player.getLocation().equalsIgnoreCase("wild")) {
            display.wildHelp();
        } else if (player.getLocation().equalsIgnoreCase("wild") && player.isInCombat()) {
            display.combatHelp();
        } else {
            display.otherHelp();
        }
    }

    public void save() {
        SaveData data = new SaveData();

        data.name = player.getName();
        data.hp = player.getHp();
        data.coins = player.getCoins();
        data.xp = player.getXp();
        data.location = player.getLocation();
        data.inventory = player.getInventory();

        try {
            ResourceManager.save(data, player.getName() + "_data");
        }
        catch (Exception e) {
            System.out.println("Couldn't save: " + e.getMessage());
        }
    }

    public void load() {
        try {
            SaveData data = (SaveData) ResourceManager.load(player.getName() + "_data");

            String playerName = data.name;
            double playerHP = data.hp;
            int playerCoins = data.coins;
            int playerXP = data.xp;
            String playerLoc = data.location;
            String[] playerInv = data.inventory;

            player.setName(playerName);
            player.setHp(playerHP);
            player.setCoins(playerCoins);
            player.setXp(playerXP);
            player.setLocation(playerLoc);
            player.setInventory(playerInv);

            System.out.println("Data loaded!");
        }
        catch (Exception e) {
            System.out.println("Couldn't load save data: " + e.getMessage());
        }
    }

    public void info() {
        display.info(player);
    }

    public void goToShop() {
        display.goToShop();
        player.setLocation("shop");
        shop.loop();
    }

    public void goToWild() {
        if (player.getLocation().equalsIgnoreCase("wild")) {
            display.alreadyInWild();
        } else {
            display.goToWild();
            player.setLocation("wild");
        }
    }

    public void goToInn() {
        inn.loop(player);
    }

    public void goToTown() {
        if (player.getLocation().equalsIgnoreCase("town")) {
            display.alreadyInTown();
        } else {
            display.returnTown();
            player.setLocation("town");
            player.setInCombat(false);
        }
    }

    public void battle() {
        if (player.getLocation().equalsIgnoreCase("wild")) {
            battle = new Battle(player);
            System.out.println(player.getLevel());
            display.searchBattle();
            battle.combatLoop();

        } else {
            display.noEnemiesNear();
        }
    }

    public Entity punch() {
        return skills.slash();
    }

    public Entity kick() {
        return skills.stab();
    }

    public Entity heal() {
        return skills.heal();
    }

    public Entity flea() {
        return skills.flea();
    }
}
