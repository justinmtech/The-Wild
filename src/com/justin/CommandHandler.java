package com.justin;

public class CommandHandler {
    Display display = new Display();
    Skills skills = new Skills();
    Shop shop = new Shop();
    Inn inn = new Inn();
    ResourceManager resourceManager = new ResourceManager();

    public void help(Entity entity) {
        if (entity.location.equalsIgnoreCase("town")) {
            display.townHelp();
        } else if (entity.location.equalsIgnoreCase("wild")) {
            display.wildHelp();
        } else if (entity.location.equalsIgnoreCase("wild") && entity.inCombat) {
            display.combatHelp();
        } else {
            display.otherHelp();
        }
    }

    public void save(Entity player) {
        SaveData data = new SaveData();

        data.name = player.name;
        data.hp = player.hp;
        data.coins = player.coins;
        data.xp = player.xp;
        data.location = player.location;
        data.inventory = player.inventory;

        try {
            ResourceManager.save(data, player.name + "_data");
        }
        catch (Exception e) {
            System.out.println("Couldn't save: " + e.getMessage());
        }
    }

    public void load(Entity player) {
        try {
            SaveData data = (SaveData) ResourceManager.load(player.name + "_data");

            String playerName = data.name;
            double playerHP = data.hp;
            int playerCoins = data.coins;
            int playerXP = data.xp;
            String playerLoc = data.location;
            String[] playerInv = data.inventory;

            player.name = playerName;
            player.hp = playerHP;
            player.coins = playerCoins;
            player.xp = playerXP;
            player.location = playerLoc;
            player.inventory = playerInv;

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
        player.location = "shop";
        shop.loop(player);
    }

    public void goToWild(Entity player) {
        if (player.location.equalsIgnoreCase("wild")) {
            display.alreadyInWild();
        } else {
            display.goToWild();
            player.location = "wild";
        }
    }

    public void goToInn(Entity player) {
        inn.loop(player);
    }

    public void goToTown(Entity player) {
        if (player.location.equalsIgnoreCase("town")) {
            display.alreadyInTown();
        } else {
            display.returnTown();
            player.location = "town";
            player.inCombat = false;
        }
    }

    public void battle(Entity player) {
        if (player.location.equalsIgnoreCase("wild")) {
            Battle start = new Battle();
            player.inCombat = true;
            display.searchBattle();
            start.combatLoop(player);

        } else {
            display.noEnemiesNear();
        }
    }

    public void punch(Entity enemy, Entity self) {
        skills.slash(enemy, self);
    }

    public void kick(Entity enemy, Entity self) {
        skills.stab(enemy, self);
    }

    public void heal(Entity enemy, Entity self) {
        skills.heal(enemy, self);
    }

    public void flea(Entity enemy, Entity self) {
        skills.flea(enemy, self);
    }
}
