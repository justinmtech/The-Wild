package com.justinmtech.thewild.entity;

public class Skills {
    private Entity self;
    private Entity enemy;

    public Skills(Entity self, Entity enemy) {
        this.self = self;
        this.enemy = enemy;
    }

    public void slash() {
        double afterAttackHP = enemy.getHp() - ((((getRandomNumber(1, 2) + weaponDamage()) * levelMultiplier())) / armorRating(enemy));
        enemy.setHp(afterAttackHP);
        enemy.isAlive(enemy);
    }

    public void stab() {
        double afterAttackHP = enemy.getHp() - ((((getRandomNumber(0, 3) + weaponDamage()) * levelMultiplier())) / armorRating(enemy));
        enemy.setHp(afterAttackHP);
        enemy.isAlive(enemy);
    }

    public void heal() {
        double afterHealHP = self.getHp() + (getRandomNumber(0, 1) * levelMultiplier());
        self.setHp(afterHealHP);
        self.isAlive(self);
    }

    public void flea() {
        int chance = getRandomNumber(0, 2);
        double afterFleaHP;
        if (chance == 0) {
            afterFleaHP = self.getHp() / 2;
            self.setHp(afterFleaHP);
            self.isAlive(self);
            self.setInCombat(false);
        } else if (chance == 1) {
            self.setInCombat(false);

        } else {
            System.out.println("You were not able to flea the battle..");
            self.isAlive(self);
        }

    }

    private int getRandomNumber(int min, int max) {
        int randomNumber = (int) (Math.random() * ((max - min) + 1)) + min;
        return randomNumber;
    }

    public void doRandomSkill() {
        int number = getRandomNumber(0, 10);
        if (number > 2) {
            slash();
        } else if (number < 6) {
            stab();
        } else {
            heal();
        }
    }

    public double levelMultiplier() {
        double multiplier = (getLevel(self) / 10) + 1;
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

            if (self.getInventory()[i].equalsIgnoreCase("Short Sword")) {
                damage = 2;
                break;
            } else if (self.getInventory()[i].equalsIgnoreCase("Long Sword")) {
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
        for (i = 0; i < self.getInventory().length; i++) {

            if (self.getInventory()[i].equalsIgnoreCase("Leather Armor")) {
                rating = 2;
                break;
            } else if (self.getInventory()[i].equalsIgnoreCase("Iron Armor")) {
                rating = 3;
                break;
            } else {
                rating = 1;
            }
        }
            return rating;
    }
}
