package com.justinmtech.thewild.commands;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.user_interface.Display;

//The wild command.
//This takes your player to the wilderness, where enemies can be fought.
public class Wild extends Command {

    public Wild(Entity player) {
        setLabel("wild");
        setPlayer(player);
        setDisplay(new Display());
        tryWild();
    }

    //Try to take the player to the wilderness.
    private void tryWild() {
        if (getPlayer().isInCombat() || getPlayer().getLocation().equals("wild")) {
            System.out.println("You are already in the wild!");
        } else {
            getDisplay().goToWild();
            getPlayer().setLocation("wild");
        }
    }
}
