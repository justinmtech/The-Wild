package com.justin;

public class Entity {
    public int xp;
    public int level;
    public int maxHP;
    public double hp;
    public boolean isAlive = true;
    public boolean inCombat = false;
    public boolean isComputer = false;
    public String name;
    public String location = "town";
    public int coins;
    public String[] inventory = {"Air", "Air", "Air", "Air"};
    int battles = 0;


    public Entity(String name) {
        this.name = name;
    }

    public Entity(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public Entity() {

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
