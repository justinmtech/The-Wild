package com.justinmtech.thewild.entity.skill_logic.skills;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.entity.skill_logic.SetCombatMultipliers;
import com.justinmtech.thewild.utilities.RandomNumberGenerator;

import java.util.ArrayList;

public class Stab extends SetCombatMultipliers {
    private Entity attacker;
    private Entity defender;
    public Stab(Entity attacker, Entity defender) {
        this.attacker = attacker;
        this.defender = defender;
        setBaseMultiplier(0);
        setMaxMultiplier(8);
    }

    public ArrayList<Entity> attack() {
        ArrayList<Entity> entities = new ArrayList<>();
        defender.removeHP((RandomNumberGenerator.generate(getBaseMultiplier(), getMaxMultiplier()) + getWeaponDamageMultiplier(attacker)) * levelMultiplier(attacker) / getArmorRatingMultiplier(defender));
        entities.add(attacker);
        entities.add(defender);
        return entities;
    }
}
