package com.justinmtech.thewild.entity.skills;

import com.justinmtech.thewild.entity.Entity;

import java.util.ArrayList;

public class Stab extends CombatSkill {
    private Entity attacker;
    private Entity defender;
    public Stab(Entity attacker, Entity defender) {
        this.attacker = attacker;
        this.defender = defender;
        setBaseMultiplier(0);
        setMaxMultiplier(3);
    }

    public ArrayList<Entity> attack() {
        ArrayList<Entity> entities = new ArrayList<>();
        defender.removeHP((getRandomNumber(getBaseMultiplier(), getMaxMultiplier()) + getWeaponDamageMultiplier(attacker)) * levelMultiplier(attacker) / getArmorRatingMultiplier(defender));
        entities.add(attacker);
        entities.add(defender);
        return entities;
    }
}
