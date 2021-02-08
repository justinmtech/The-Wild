package com.justinmtech.thewild.entity;

public class Skills {

    public void slash(Entity enemy, Entity self) {
        double afterAttackHP = enemy.hp - ((((getRandomNumber(1, 2) + weaponDamage(self)) * levelMultiplier(self))) / armorRating(enemy));
        enemy.hp = afterAttackHP;
        enemy.isAlive(enemy);
    }

    public void stab(Entity enemy, Entity self) {
        double afterAttackHP = enemy.hp - ((((getRandomNumber(0, 3) + weaponDamage(self)) * levelMultiplier(self))) / armorRating(enemy));
        enemy.hp = afterAttackHP;
        enemy.isAlive(enemy);
    }

    public void heal(Entity enemy, Entity self) {
        double afterHealHP = self.hp + (getRandomNumber(0, 1) * levelMultiplier(self));
        self.hp = afterHealHP;
        self.isAlive(self);
    }

    public void flea(Entity enemy, Entity self) {
        int chance = getRandomNumber(0, 2);
        double afterFleaHP;
        if (chance == 0) {
            afterFleaHP = self.hp / 2;
            self.hp = afterFleaHP;
            self.isAlive(self);
            self.inCombat = false;
        } else if (chance == 1) {
            self.inCombat = false;

        } else {
            System.out.println("You were not able to flea the battle..");
            self.isAlive(self);
        }

    }

    private int getRandomNumber(int min, int max) {
        int randomNumber = (int) (Math.random() * ((max - min) + 1)) + min;
        return randomNumber;
    }

    public void doRandomSkill(Entity enemy, Entity self) {
        int number = getRandomNumber(0, 10);
        if (number > 2) {
            slash(enemy, self);
        } else if (number < 6) {
            stab(enemy, self);
        } else {
            heal(enemy, self);
        }
    }

    public double levelMultiplier(Entity self) {
        double multiplier = (getLevel(self) / 10) + 1;
        return multiplier;
    }

    public int getLevel(Entity self) {
        if (self.isComputer) {
            return self.level;
        } else {
            int level = (self.xp / 10) + 1;
            return level;
        }
    }

    public double weaponDamage(Entity self) {
        int i;
        int damage = 1;
        for (i = 0; i < self.inventory.length; i++) {

            if (self.inventory[i].equalsIgnoreCase("Short Sword")) {
                damage = 2;
                break;
            } else if (self.inventory[i].equalsIgnoreCase("Long Sword")) {
                damage = 3;
                break;
            } else {
                damage = 1;
            }
        }
        return damage;
    }

    public double armorRating(Entity self) {
        int i;
        int rating = 0;
        for (i = 0; i < self.inventory.length; i++) {

            if (self.inventory[i].equalsIgnoreCase("Leather Armor")) {
                rating = 2;
                break;
            } else if (self.inventory[i].equalsIgnoreCase("Iron Armor")) {
                rating = 3;
                break;
            } else {
                rating = 1;
            }
        }
            return rating;
    }
}
