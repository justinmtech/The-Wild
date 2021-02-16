package com.justinmtech.thewild.entity.skills;

import com.justinmtech.thewild.entity.Entity;

import java.util.ArrayList;

public class Heal extends CombatSkill {
    private Entity attacker;
    private Entity defender;

    public Heal(Entity attacker, Entity defender) {
        this.attacker = attacker;
        this.defender = defender;
        setBaseMultiplier(0);
        setMaxMultiplier(3);
    }

    public ArrayList<Entity> doHeal() {
        ArrayList<Entity> entities = new ArrayList<>();
        attacker.addHP(getRandomNumber(getBaseMultiplier(), getMaxMultiplier()) * levelMultiplier(attacker));
        entities.add(attacker);
        entities.add(defender);
        return entities;
    }

}
