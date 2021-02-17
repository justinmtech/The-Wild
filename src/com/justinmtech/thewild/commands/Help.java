package com.justinmtech.thewild.commands;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.user_interface.Display;

//The help command.
//This command lists help commands based on what situation your character is in.
public class Help extends Command {

    public Help(Entity player) {
        setLabel("help");
        setDisplay(new Display());
        setPlayer(player);
    }

    //Help command logic
    public void showHelp() {
            if (getPlayer().getLocation().equals("town")) getDisplay().townHelp();
            else if (getPlayer().getLocation().equals("wild") && getPlayer().isInCombat()) getDisplay().combatHelp();
            else if (getPlayer().getLocation().equals("wild")) getDisplay().wildHelp();
            else getDisplay().otherHelp();
        }
}
