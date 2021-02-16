package com.justinmtech.thewild.entity.skill_logic.skills;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.entity.skill_logic.CombatSkillsUtil;
import com.justinmtech.thewild.utilities.RandomNumberGenerator;

import java.util.ArrayList;

//A basic healing skill that has a chance to add HP to the user.
public class Heal extends CombatSkillsUtil {
    private Entity attacker;
    private Entity defender;

    public Heal(Entity attacker, Entity defender) {
        this.attacker = attacker;
        this.defender = defender;
        setBaseMultiplier(0);
        setMaxMultiplier(12);
    }

    public ArrayList<Entity> doHeal() {
        ArrayList<Entity> entities = new ArrayList<>();
        attacker.addHP(RandomNumberGenerator.generate(getBaseMultiplier(), getMaxMultiplier()) * levelMultiplier(attacker));
        entities.add(attacker);
        entities.add(defender);
        return entities;
    }

}
