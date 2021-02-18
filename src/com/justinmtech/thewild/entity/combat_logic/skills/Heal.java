package com.justinmtech.thewild.entity.combat_logic.skills;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.entity.combat_logic.SetCombatMultipliers;
import com.justinmtech.thewild.utilities.RandomNumberGenerator;

import java.util.ArrayList;

//A basic healing skill that has a chance to add HP to the user.
public class Heal extends SetCombatMultipliers {
    private final Entity attacker;
    private final Entity defender;

    public Heal(Entity attacker, Entity defender) {
        this.attacker = attacker;
        this.defender = defender;
        setBaseSkillMultiplier(0);
        setMaxSkillMultiplier(5);
    }

    public ArrayList<Entity> doHeal() {
        ArrayList<Entity> entities = new ArrayList<>();
        attacker.addHP(RandomNumberGenerator.generate(getBaseSkillMultiplier(), getMaxSkillMultiplier()) * levelMultiplier(attacker));
        //Prevent healing above max hp
        if (attacker.getHp() > attacker.getMaxHP()) {
            attacker.setHp(attacker.getMaxHP());
        }
        entities.add(attacker);
        entities.add(defender);
        return entities;
    }

}
