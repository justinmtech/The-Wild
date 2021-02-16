package com.justinmtech.thewild.entity.skills;

import com.justinmtech.thewild.entity.Entity;

import java.util.ArrayList;

public class CombatSkill {
    private int baseMultiplier;
    private int maxMultiplier;

    int getRandomNumber(int min, int max) {
        int randomNumber = (int) (Math.random() * ((max - min) + 1)) + min;
        return randomNumber;
    }

    public ArrayList<Entity> computerDoRandomSkill(Entity attacker, Entity defender) {
        int number = getRandomNumber(0, 10);
        if (number > 2) {
            Slash slash = new Slash(attacker, defender);
            return slash.attack();
        } else if (number < 6) {
            Stab stab = new Stab(attacker, defender);
            return stab.attack();
        } else {
            Heal heal = new Heal(attacker, defender);
            return heal.doHeal();
        }
    }

    public double levelMultiplier(Entity attacker) {
        double multiplier = (attacker.getLevel() / 10) + 1;
        return multiplier;
    }

    public double getWeaponDamageMultiplier(Entity self) {
        double damage = 1;
        for (int i = 0; i < self.getInventory().length; i++) {
            if (self.getInventory()[i].equalsIgnoreCase("Long_Sword")) {
                damage = 3;
                break;
            } else if (self.getInventory()[i].equalsIgnoreCase("Short_Sword")) {
                damage = 2;
            }
        }
        return damage;
    }

    public double getArmorRatingMultiplier(Entity defender) {
        double rating = 1;
        for (int i = 0; i < defender.getInventory().length; i++) {
            if (defender.getInventory()[i].equalsIgnoreCase("Iron_Armor")) {
                rating = 3;
                break;
            } else if (defender.getInventory()[i].equalsIgnoreCase("Leather_Armor")) {
                rating = 2;
            }
        }
        return rating;
    }

    public int getBaseMultiplier() {
        return baseMultiplier;
    }

    public void setBaseMultiplier(int baseMultiplier) {
        this.baseMultiplier = baseMultiplier;
    }

    public int getMaxMultiplier() {
        return maxMultiplier;
    }

    public void setMaxMultiplier(int maxMultiplier) {
        this.maxMultiplier = maxMultiplier;
    }
}
