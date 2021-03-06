package com.justinmtech.thewild.entity.combat_logic.skills;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.entity.combat_logic.SetCombatMultipliers;
import com.justinmtech.thewild.utilities.RandomNumberGenerator;

import java.util.ArrayList;

//The basic punch/slash attack, used with or without a weapon.
public class Slash extends SetCombatMultipliers {
    private final Entity attacker;
    private final Entity defender;

    public Slash(Entity attacker, Entity defender) {
        this.attacker = attacker;
        this.defender = defender;
        setBaseSkillMultiplier(2);
        setMaxSkillMultiplier(6);
    }

    public ArrayList<Entity> attack() {
        ArrayList<Entity> entities = new ArrayList<>();
        defender.removeHP (RandomNumberGenerator.generate(getBaseSkillMultiplier(), getMaxSkillMultiplier()) + getWeaponDamageMultiplier(attacker) * levelMultiplier(attacker) / getArmorRatingMultiplier(defender));
        entities.add(attacker);
        entities.add(defender);
        return entities;
    }

}
