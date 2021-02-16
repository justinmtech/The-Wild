package com.justinmtech.thewild.entity.skills;

import com.justinmtech.thewild.entity.Entity;

import java.util.ArrayList;

public class Slash extends CombatSkill {
    private Entity attacker;
    private Entity defender;

    public Slash(Entity attacker, Entity defender) {
        this.attacker = attacker;
        this.defender = defender;
        setBaseMultiplier(1);
        setMaxMultiplier(2);
    }

    public ArrayList<Entity> attack() {
        ArrayList<Entity> entities = new ArrayList<>();
        defender.removeHP (getRandomNumber(getBaseMultiplier(), getMaxMultiplier()) + getWeaponDamageMultiplier(attacker) * levelMultiplier(attacker) / getArmorRatingMultiplier(defender));
        entities.add(attacker);
        entities.add(defender);
        return entities;
    }

}
