package com.justinmtech.thewild.entity.combat_logic;

import com.justinmtech.thewild.data.LoadConfiguration;
import com.justinmtech.thewild.entity.Entity;

//TODO Make values here configurable from config.yml

//A multiplier utility class used by everything that effects entity HP in combat including: skills, weapons/potions and entity levels.
//Each weapon and set of armor has a designated TIER which acts as a multiplier for damage.
public class SetCombatMultipliers {

    public SetCombatMultipliers() {
        LoadConfiguration config = new LoadConfiguration();
        LEVEL_MULTIPLIER_BASE = (double)config.getConfig().get("level_multipliers").get("base");
        INCREASE_MULTIPLIER_INCREMENT = (double)config.getConfig().get("level_multipliers").get("increment");
        TIER_1 = (double)config.getConfig().get("multiplier_tiers").get("TIER_1");
        TIER_2 = (double)config.getConfig().get("multiplier_tiers").get("TIER_2");
        TIER_2B = (double)config.getConfig().get("multiplier_tiers").get("TIER_2B");
        TIER_3 = (double)config.getConfig().get("multiplier_tiers").get("TIER_3");
        TIER_3B = (double)config.getConfig().get("multiplier_tiers").get("TIER_3B");
        TIER_4 = (double)config.getConfig().get("multiplier_tiers").get("TIER_4");
        TIER_4B = (double)config.getConfig().get("multiplier_tiers").get("TIER_4B");
        TIER_5 = (double)config.getConfig().get("multiplier_tiers").get("TIER_5");
        TIER_5B = (double)config.getConfig().get("multiplier_tiers").get("TIER_5B");
        TIER_6 = (double)config.getConfig().get("multiplier_tiers").get("TIER_6");
    }

    //The available weapon, armor and potion tiers
    private final double TIER_1;
    private final double TIER_2;
    private final double TIER_2B;
    private final double TIER_3;
    private final double TIER_3B;
    private final double TIER_4;
    private final double TIER_4B;
    private final double TIER_5;
    private final double TIER_5B;
    private final double TIER_6;

    //This amount is added to the level multiplier formula to prevent negative values at low entity levels.
    //Setting this lower than 1 is not recommended.
    private final double LEVEL_MULTIPLIER_BASE;


    //The rate at which an entity's level multiplier increases.
    //A setting of 5 means their multiplier increases at every 5th level (lvl 5, 10, 15, etc).
    //A setting of 1 means their multiplier increases by 1 every level gained (lvl 1, 2, 3, etc).
    private final double INCREASE_MULTIPLIER_INCREMENT;

    //This should equal where the sword, armor and potions are stored the inventory string index.
    //Items are assigned inventory slots in the Shop class when they are purchased.
    //Default: swords = 0, armor = 1, potions = 2
    private final static int SWORD_SLOT = 0;
    private final static int ARMOR_SLOT = 1;
    private final static int POTION_SLOT = 2;

    public double levelMultiplier(Entity attacker) {
        return (attacker.getLevel() / INCREASE_MULTIPLIER_INCREMENT) + LEVEL_MULTIPLIER_BASE;
    }

    //Get the weapon equipped if any and set the multiplier for it
    public double getWeaponDamageMultiplier(Entity self) {
        double rating;
            switch (self.getInventory()[SWORD_SLOT]) {
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
                default:
                    rating = TIER_1;
                    break;
            }
        return rating;
    }

    //Get the armor equipped if any and set its multiplier
    public double getArmorRatingMultiplier(Entity defender) {
        double rating;
            switch (defender.getInventory()[ARMOR_SLOT]) {
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
                default:
                    rating = TIER_1;
                    break;
            }
        return rating;
    }

    //These are used by individual skills in entity/skill_logic/skills.
    //This class is a parent to all Skill classes, and the two fields below are set by Constructors.
    private int baseSkillMultiplier;
    private int maxSkillMultiplier;

    public int getBaseSkillMultiplier() {
        return baseSkillMultiplier;
    }

    public void setBaseSkillMultiplier(int baseSkillMultiplier) {
        this.baseSkillMultiplier = baseSkillMultiplier;
    }

    public int getMaxSkillMultiplier() {
        return maxSkillMultiplier;
    }

    public void setMaxSkillMultiplier(int maxSkillMultiplier) {
        this.maxSkillMultiplier = maxSkillMultiplier;
    }
}
