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
    private ResourceManager resourceManager;

    public CommandHandler(Entity player, Entity computer) {
        this.player = player;
        this.computer = computer;
        this.display = new Display(player, computer);
        this.skills = new Skills(player, computer);
        this.shop = new Shop();
        this.inn = new Inn();

    }

    public void help(Entity entity) {
        if (entity.getLocation().equalsIgnoreCase("town")) {
            display.townHelp();
        } else if (entity.getLocation().equalsIgnoreCase("wild")) {
            display.wildHelp();
        } else if (entity.getLocation().equalsIgnoreCase("wild") && entity.isInCombat()) {
            display.combatHelp();
        } else {
            display.otherHelp();
        }
    }

    public void save(Entity player) {
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

    public void load(Entity player) {
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

    public void info(Entity player) {
        display.info(player);
    }

    public void goToShop(Entity player) {
        display.goToShop();
        player.setLocation("shop");
        shop.loop(player);
    }

    public void goToWild(Entity player) {
        if (player.getLocation().equalsIgnoreCase("wild")) {
            display.alreadyInWild();
        } else {
            display.goToWild();
            player.setLocation("wild");
        }
    }

    public void goToInn(Entity player) {
        inn.loop(player);
    }

    public void goToTown(Entity player) {
        if (player.getLocation().equalsIgnoreCase("town")) {
            display.alreadyInTown();
        } else {
            display.returnTown();
            player.setLocation("town");
            player.setInCombat(false);
        }
    }

    public void battle(Entity player) {
        if (player.getLocation().equalsIgnoreCase("wild")) {
            Battle start = new Battle(player, computer);
            player.setInCombat(true);
            display.searchBattle();
            start.combatLoop();

        } else {
            display.noEnemiesNear();
        }
    }

    public void punch(Entity enemy, Entity self) {
        skills.slash();
    }

    public void kick(Entity enemy, Entity self) {
        skills.stab();
    }

    public void heal(Entity enemy, Entity self) {
        skills.heal();
    }

    public void flea(Entity enemy, Entity self) {
        skills.flea();
    }
}
