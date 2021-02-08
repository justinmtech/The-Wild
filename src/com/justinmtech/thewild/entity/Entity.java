package com.justinmtech.thewild.entity;

public class Entity {
    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isInCombat() {
        return inCombat;
    }

    public void setInCombat(boolean inCombat) {
        this.inCombat = inCombat;
    }

    public boolean isComputer() {
        return isComputer;
    }

    public void setComputer(boolean computer) {
        isComputer = computer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String[] getInventory() {
        return inventory;
    }

    public void setInventory(String[] inventory) {
        this.inventory = inventory;
    }

    public int getBattles() {
        return battles;
    }

    public void setBattles(int battles) {
        this.battles = battles;
    }

    private int xp;
    private int level;
    private int maxHP;
    private double hp;
    private boolean isAlive;
    private boolean inCombat;
    private boolean isComputer;
    private String name;
    private String location = "town";
    private int coins;
    private String[] inventory;
    private int battles = 0;


    public Entity(String name) {
        this.name = name;
        isAlive = true;
        inCombat = false;
        isComputer = false;
        inventory = new String[]{"Air", "Air", "Air", "Air"};
        battles = 0;
    }

    public Entity(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public boolean isAlive(Entity entity) {
        boolean alive = entity.hp > 0;
        this.isAlive = alive;
        return alive;
    }

    public void setHP(Entity entity) {
        if (entity.isComputer) {
            entity.maxHP = entity.level * 15;
            entity.hp = entity.maxHP;
        } else {
        entity.maxHP = entity.level * 20;
        if (!entity.inCombat)
        entity.hp = entity.maxHP;
        }
    }

    public void calculateLevel(Entity entity) {
        int level = (entity.xp / 100);
        if (level < 1) {
            level = 1;
        }
        entity.level = level;
    }

}
