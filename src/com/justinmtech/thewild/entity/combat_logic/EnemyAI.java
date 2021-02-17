package com.justinmtech.thewild.entity.combat_logic;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.entity.combat_logic.skills.Heal;
import com.justinmtech.thewild.entity.combat_logic.skills.Slash;
import com.justinmtech.thewild.entity.combat_logic.skills.Stab;
import com.justinmtech.thewild.utilities.RandomNumberGenerator;

import java.util.ArrayList;

//A simple AI to make enemy behavior change slightly based on their HP.
public class EnemyAI {
    private ArrayList<Entity> entities = new ArrayList<>();
    //The higher the aggression level, the less defensive.
    //Lower aggression level results in more healing and less stabbing.
    private int aggressionLevel;

    //Picks a skill to execute based on their aggression level and randomness.
    public ArrayList<Entity> doSkill(Entity attacker, Entity defender) {
        entities.add(attacker);
        entities.add(defender);
        setAggressionLevel(attacker);

        int number = RandomNumberGenerator.generate(1, aggressionLevel);
        if (number >= 5) {
            switch (RandomNumberGenerator.generate(1, 3)) {
                case 1 :
                    Slash slash = new Slash(attacker, defender);
                    System.out.println("Slash");
                    return slash.attack();
                case 2 :
                    Stab stab = new Stab(attacker, defender);
                    System.out.println("Stab");
                    return stab.attack();
                case 3 :
                    Heal heal = new Heal(attacker, defender);
                    System.out.println("Heal");
                    return heal.doHeal();
            }
        } else {
            switch (RandomNumberGenerator.generate(1, 2)) {
                case 1 :
                    Heal heal = new Heal(attacker, defender);
                    System.out.println("Heal");
                    return heal.doHeal();
                case 2 :
                    Slash slash = new Slash(attacker, defender);
                    System.out.println("Slash");
                    return slash.attack();
            }
        }
        return entities;
    }

    //Determines the aggression level based on HP.
    private void setAggressionLevel(Entity attacker) {
        if (attacker.getHp() >= attacker.getMaxHP() * .75) {
            aggressionLevel = 10;
        } else if (attacker.getHp() >= attacker.getMaxHP() * .50 && attacker.getHp() < attacker.getMaxHP() * .75) {
            aggressionLevel = 7;
        } else if (attacker.getHp() < attacker.getMaxHP() * .50 && attacker.getHp() >= attacker.getMaxHP() * .25) {
            aggressionLevel = 4;
        } else {
            aggressionLevel = 1;
        }
    }
}
