package com.justinmtech.thewild.commands;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.user_interface.Display;

//The wild command.
//This takes your player to the wilderness, where enemies can be fought.
public class Wild {
    private Entity player;
    private Display display;

    public Wild(Entity player) {
        this.player = player;
        this.display = new Display();
        tryWild();
    }

    private void tryWild() {
        if (player.isInCombat() || player.getLocation().equals("wild")) {
            System.out.println("You are already in the wild!");
        } else {
            display.goToWild();
            player.setLocation("wild");
        }
    }
}
