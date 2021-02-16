package com.justinmtech.thewild.entity.skill_logic.skills;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.entity.skill_logic.CombatSkillsUtil;
import com.justinmtech.thewild.utilities.RandomNumberGenerator;

import java.util.ArrayList;

//The basic punch/slash attack, used with or without a weapon.
public class Slash extends CombatSkillsUtil {
    private Entity attacker;
    private Entity defender;

    public Slash(Entity attacker, Entity defender) {
        this.attacker = attacker;
        this.defender = defender;
        setBaseMultiplier(2);
        setMaxMultiplier(6);
    }

    public ArrayList<Entity> attack() {
        ArrayList<Entity> entities = new ArrayList<>();
        defender.removeHP (RandomNumberGenerator.generate(getBaseMultiplier(), getMaxMultiplier()) + getWeaponDamageMultiplier(attacker) * levelMultiplier(attacker) / getArmorRatingMultiplier(defender));
        entities.add(attacker);
        entities.add(defender);
        return entities;
    }

}
