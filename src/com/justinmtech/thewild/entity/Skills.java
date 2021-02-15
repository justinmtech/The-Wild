package com.justinmtech.thewild.entity;

public class Skills {
    private Entity self;
    private Entity enemy;

    public Skills(Entity self, Entity enemy) {
        this.self = self;
        this.enemy = enemy;
    }

    public Entity slash() {
        double afterAttackHP = enemy.getHp() - (((getRandomNumber(1, 2) + weaponDamage()) * levelMultiplier()) / armorRating());
        enemy.setHp(afterAttackHP);
        return enemy;
    }

    public Entity stab() {
        double afterAttackHP = enemy.getHp() - (((getRandomNumber(0, 3) + weaponDamage()) * levelMultiplier()) / armorRating());
        enemy.setHp(afterAttackHP);
        return enemy;
    }

    public Entity heal() {
        double afterHealHP = self.getHp() + (getRandomNumber(0, 1) * levelMultiplier());
        self.setHp(afterHealHP);
        return self;
    }

    public Entity flea() {
        int chance = getRandomNumber(0, 2);
        double afterFleaHP;
        if (chance == 0) {
            afterFleaHP = self.getHp() / 2;
            self.setHp(afterFleaHP);
            self.setInCombat(false);
        } else if (chance == 1) {
            self.setInCombat(false);

        } else {
            System.out.println("You were not able to flea the battle..");
        }
        return self;
    }

    private int getRandomNumber(int min, int max) {
        int randomNumber = (int) (Math.random() * ((max - min) + 1)) + min;
        return randomNumber;
    }

    public Entity doRandomSkill() {
        int number = getRandomNumber(0, 10);
        if (number > 2) {
            return slash();
        } else if (number < 6) {
            return stab();
        } else {
            return heal();
        }
    }

    public double levelMultiplier() {
        double multiplier = (self.getLevel() / 10) + 1;
        return multiplier;
    }

    public int getLevel() {
        if (self.isComputer()) {
            return self.getLevel();
        } else {
            int level = (self.getXp() / 10) + 1;
            return level;
        }
    }

    public double weaponDamage() {
        int i;
        int damage = 1;
        for (i = 0; i < self.getInventory().length; i++) {

            if (self.getInventory()[i].equalsIgnoreCase("Short_Sword")) {
                damage = 2;
                break;
            } else if (self.getInventory()[i].equalsIgnoreCase("Long_Sword")) {
                damage = 3;
                break;
            } else {
                damage = 1;
            }
        }
        return damage;
    }

    public double armorRating() {
        int i;
        int rating = 0;
        for (i = 0; i < enemy.getInventory().length; i++) {

            if (enemy.getInventory()[i].equalsIgnoreCase("Leather_Armor")) {
                rating = 2;
                break;
            } else if (enemy.getInventory()[i].equalsIgnoreCase("Iron_Armor")) {
                rating = 3;
                break;
            } else {
                rating = 1;
            }
        }
            return rating;
    }
}
