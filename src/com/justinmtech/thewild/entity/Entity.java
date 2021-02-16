package com.justinmtech.thewild.entity;

public class Entity {
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

    public Entity(String name, int level, int coins) {
        this.name = name;
        isAlive = true;
        inCombat = false;
        isComputer = false;
        inventory = new String[]{"Air", "Air", "Air", "Air"};
        battles = 0;
        this.level = level;
        setXPFromLevel();
        this.hp = 10 + (level * 10);
        this.maxHP = 10 + (level * 10);
        this.coins = coins;
    }

    public Entity(boolean computer) {
        isComputer = computer;
        isAlive = true;
        inCombat = true;
        inventory = new String[]{"Air", "Air", "Air", "Air"};
        battles = 0;
        this.level = 1;
        setXPFromLevel();
        this.hp = 10 + (level * 10);
        this.maxHP = 10 + (level * 10);
    }

    public Entity(String name, int level, boolean computer) {
        this.name = name;
        this.level = level;
        this.isComputer = computer;
        this.isAlive = true;
        this.inCombat = true;
        inventory = new String[]{"Air", "Air", "Air", "Air"};
        setXPFromLevel();
        this.hp = 10 + (level * 10);
        this.maxHP = 10 + (level * 10);
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
        setLevelFromXP();
        updateMaxHP();
    }

    public void addXp(int amount) {
        this.xp += amount;
        setLevelFromXP();
        updateMaxHP();
    }

    private void updateMaxHP() {
        maxHP = 10 + (level * 10);
    }

    public void removeHP(double amount) {
        hp -= amount;
    }

    public void addHP(double amount) {
        hp += amount;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        setXPFromLevel();
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
        isAlive = isAlive();
    }

    public void resetHp() {
        this.hp = maxHP;
    }

    public boolean isAlive() {
        return getHp() > 0;
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

    public void giveCoins(int amount) {
        this.coins += amount;
    }

    public String[] getInventory() {
        return inventory;
    }

    public void setInventory(String[] inventory) {
        this.inventory = inventory;
    }

    public void resetInventory() {
        this.inventory = new String[]{"Air", "Air", "Air", "Air"};
    }

    public int getBattles() {
        return battles;
    }

    public void setBattles(int battles) {
        this.battles = battles;
    }

    public void incrementBattles() {
        this.battles = battles++;
    }

    private void setLevelFromXP() {
        level = xp / 100;
        if (level < 1) {
            level = 1;
        }
    }

    private void setXPFromLevel() {
        xp = level * 100;
        if (level == 1) {
            xp = 0;
        }
    }

}
