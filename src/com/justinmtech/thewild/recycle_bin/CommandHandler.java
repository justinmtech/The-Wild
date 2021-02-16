package com.justinmtech.thewild.recycle_bin;

/*public class CommandHandler {
    private final Entity player;
    private final Display display;

    public CommandHandler(Entity player, Entity computer) {
        this.player = player;
        this.display = new Display();

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
        new Shop(player);
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
        display.goToInn();
        new Inn(player);
    }

    public void goToTown() {
        if (player.getLocation().equalsIgnoreCase("town")) {
            display.alreadyInTown();
        } else {
            display.returnTown();
            player.setLocation("town");
        }
    }

    public void battle() {
        if (player.getLocation().equalsIgnoreCase("wild")) {
            new Battle(player);
            System.out.println(player.getLevel());
            display.searchBattle();

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
}*/
