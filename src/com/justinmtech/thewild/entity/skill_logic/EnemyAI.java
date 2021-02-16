package com.justinmtech.thewild.entity.skill_logic;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.entity.skill_logic.skills.Heal;
import com.justinmtech.thewild.entity.skill_logic.skills.Slash;
import com.justinmtech.thewild.entity.skill_logic.skills.Stab;
import com.justinmtech.thewild.utilities.RandomNumberGenerator;

import java.util.ArrayList;

public class EnemyAI {


    public ArrayList<Entity> computerDoRandomSkill(Entity attacker, Entity defender) {
        int number = RandomNumberGenerator.generate(0, 10);
        if (number > 2) {
            Slash slash = new Slash(attacker, defender);
            return slash.attack();
        } else if (number < 6) {
            Stab stab = new Stab(attacker, defender);
            return stab.attack();
        } else {
            Heal heal = new Heal(attacker, defender);
            return heal.doHeal();
        }
    }

}
