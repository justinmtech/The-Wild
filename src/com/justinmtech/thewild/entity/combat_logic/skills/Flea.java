package com.justinmtech.thewild.entity.combat_logic.skills;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.entity.combat_logic.SetCombatMultipliers;
import com.justinmtech.thewild.utilities.RandomNumberGenerator;

import java.util.ArrayList;

//A skill that allows the user to try to leave a battle mid-fight.
public class Flea extends SetCombatMultipliers {
    private Entity attacker;
    private Entity defender;
    private ArrayList<Entity> entities;
    private int chance;

    public Flea(Entity attacker, Entity defender) {
        chance = RandomNumberGenerator.generate(1, 3);
        this.attacker = attacker;
        this.defender = defender;
        this.entities = new ArrayList<>();
        tryFlea();
    }

    public ArrayList<Entity> tryFlea() {
        if (chance == 3) {
            attacker.setInCombat(false);
            attacker.removeHP(defender.getLevel() * 4);
        }
        entities.add(attacker);
        entities.add(defender);
        return entities;
    }
}
