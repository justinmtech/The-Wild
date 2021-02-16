package com.justinmtech.thewild.entity.skills;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.ui.Display;

import java.util.ArrayList;

public class Flea extends CombatSkill {
    private Entity attacker;
    private Entity defender;
    private Display display;
    private ArrayList<Entity> entities;

    public Flea(Entity attacker, Entity defender) {
        this.attacker = attacker;
        this.defender = defender;
        this.display = new Display();
        this.entities = new ArrayList<>();
        tryFlea();
    }

    public ArrayList<Entity> tryFlea() {
        int chance = getRandomNumber(0, 2);
        if (chance == 2) {
            attacker.setInCombat(false);
            attacker.removeHP(defender.getLevel() * 4);
        }
        entities.add(attacker);
        entities.add(defender);
        return entities;
    }
}
