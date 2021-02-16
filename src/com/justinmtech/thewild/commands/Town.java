package com.justinmtech.thewild.commands;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.user_interface.Display;

//The town command.
//Takes the player to town if they meet the requirements!
public class Town {
    private final Entity player;
    private final Display display;

    public Town(Entity player) {
        this.player = player;
        this.display = new Display();
        goToTown();
    }

    private void goToTown() {
        if (!player.isInCombat() && !player.getLocation().equals("town")) {
            player.setLocation("town");
            display.returnTown();
        } else if (player.getLocation().equals("town")) {
            display.alreadyInTown();
        }
    }
}
