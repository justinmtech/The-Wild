package com.justinmtech.thewild.entity.skill_logic;

import com.justinmtech.thewild.entity.Entity;

//The base for all of the Skills.
//TODO make it to where the best weapon/armor is always the one that the rating is based on.
public class SetCombatMultipliers {
    private final static double TIER_1 = 1;
    private final static double TIER_2 = 2;
    private final static double TIER_2B = 2.5;
    private final static double TIER_3 = 3;
    private final static double TIER_3B = 3.5;
    private final static double TIER_4 = 4;
    private final static double TIER_4B = 4.5;
    private final static double TIER_5 = 5;
    private final static double TIER_5B = 5.5;
    private final static double TIER_6 = 6;

    private final static double DIVIDE_LEVEL_BY = 10;

    private int baseMultiplier;
    private int maxMultiplier;

    public double levelMultiplier(Entity attacker) {
        return (attacker.getLevel() / DIVIDE_LEVEL_BY) + TIER_1;
    }

    public double getWeaponDamageMultiplier(Entity self) {
        double rating = TIER_1;
        for (int i = 0; i < self.getInventory().size(); i++) {
            switch (self.getInventory().get(i)) {
                case "Bronze_Short_Sword":
                    rating = TIER_2;
                    break;
                case "Bronze_Long_Sword":
                    rating = TIER_2B;
                    break;
                case "Iron_Short_Sword":
                    rating = TIER_3;
                    break;
                case "Iron_Long_Sword":
                    rating = TIER_3B;
                    break;
                case "Steel_Short_Sword":
                    rating = TIER_4;
                    break;
                case "Steel_Long_Sword":
                    rating = TIER_4B;
                    break;
                case "Diamond_Short_Sword":
                    rating = TIER_5;
                    break;
                case "Diamond_Long_Sword":
                    rating = TIER_5B;
                    break;
            }
        }
        return rating;
    }

    public double getArmorRatingMultiplier(Entity defender) {
        double rating = TIER_1;
        for (int i = 0; i < defender.getInventory().size(); i++) {
            switch (defender.getInventory().get(i)) {
                case "Leather_Armor":
                    rating = TIER_2;
                    break;
                case "Bronze_Armor":
                    rating = TIER_3;
                    break;
                case "Iron_Armor":
                    rating = TIER_4;
                    break;
                case "Steel_Armor":
                    rating = TIER_5;
                    break;
                case "Diamond_Armor":
                    rating = TIER_6;
                    break;
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
