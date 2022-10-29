package com.justinmtech.thewild.user_interface;

import com.justinmtech.thewild.commands.*;
import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.entity.combat_logic.skills.Flea;
import com.justinmtech.thewild.entity.combat_logic.skills.Heal;
import com.justinmtech.thewild.entity.combat_logic.skills.Slash;
import com.justinmtech.thewild.entity.combat_logic.skills.Stab;
import com.justinmtech.thewild.utilities.ScanInput;

import java.util.ArrayList;
import java.util.List;

public class CommandParser {
    private final Entity player;
    private String input;

    public CommandParser(Entity player) {
        this.player = player;
    }

    public void parseCommand() {
        input = ScanInput.getString();
        switch (input) {
            case ("help"): new Help(player); break;
            case ("info"): new Info(player); break;
            case ("shop"): new Shop(player); break;
            case ("inn"): new Inn(player); break;
            case ("wild"): new Wild(player); break;
            case ("town"): new Town(player); break;
            case ("battle"): new Battle(player); break;
            case ("quit"): new Quit(player); break;
            default:
                System.out.println("> Command not found.. Type 'help' for help.");
                break;
        }
    }

    //Listen for the player's attack command, then execute it. It returns an Array of entities
    //for the attacker and defender, since the HP of both can change in a single attack.
    public List<Entity> getAttackCommand(Entity attacker, Entity defender) {
        List<Entity> entities = new ArrayList<>();
        entities.add(attacker);
        entities.add(defender);
        input = ScanInput.getString();

        switch (input) {
            case "slash" :
                Slash slash = new Slash(attacker, defender);
                return slash.attack();
            case "stab" :
                Stab stab = new Stab(attacker, defender);
                return stab.attack();
            case "heal" :
                Heal heal = new Heal(attacker, defender);
                return heal.doHeal();
            case "flea" :
                Flea flea = new Flea(attacker, defender);
                return flea.tryFlea();
            case "quit" :
                System.out.println("You can't quit while in combat!");
                return entities;
            default :
                System.out.println("Unknown command:");
                System.out.println("> slash, stab, heal, flea");
                return entities;
        }
    }
}
