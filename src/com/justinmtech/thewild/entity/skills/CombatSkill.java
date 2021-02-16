package com.justinmtech.thewild.entity.skills;

import com.justinmtech.thewild.entity.Entity;

import java.util.ArrayList;

//The base for all of the Skills.
public class CombatSkill {
    private final static double BASE_MULTIPLIER = 1;
    private final static double MEDIUM_MULTIPLIER = 2;
    private final static double HIGH_MULTIPLIER = 3;
    private final static double DIVIDE_LEVEL_BY = 10;

    private int baseMultiplier;
    private int maxMultiplier;

    int getRandomNumber(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
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
        return (attacker.getLevel() / DIVIDE_LEVEL_BY) + BASE_MULTIPLIER;
    }

    public double getWeaponDamageMultiplier(Entity self) {
        double damage = BASE_MULTIPLIER;
        for (int i = 0; i < self.getInventory().length; i++) {
            if (self.getInventory()[i].equalsIgnoreCase("Long_Sword")) {
                damage = HIGH_MULTIPLIER;
                break;
            } else if (self.getInventory()[i].equalsIgnoreCase("Short_Sword")) {
                damage = MEDIUM_MULTIPLIER;
            }
        }
        return damage;
    }

    public double getArmorRatingMultiplier(Entity defender) {
        double rating = BASE_MULTIPLIER;
        for (int i = 0; i < defender.getInventory().length; i++) {
            if (defender.getInventory()[i].equalsIgnoreCase("Iron_Armor")) {
                rating = HIGH_MULTIPLIER;
                break;
            } else if (defender.getInventory()[i].equalsIgnoreCase("Leather_Armor")) {
                rating = MEDIUM_MULTIPLIER;
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
