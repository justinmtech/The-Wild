package com.justinmtech.thewild.commands;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.user_interface.Display;

//The town command.
//Takes the player to town if they meet the requirements!
public class Town extends Command {

    public Town(Entity player) {
        setLabel("town");
        setPlayer(player);
        setDisplay(new Display());
        goToTown();
    }

    //Change the player's location to "town" if they meet requirements.
    private void goToTown() {
        if (!getPlayer().isInCombat() && !getPlayer().getLocation().equals("town")) {
            getPlayer().setLocation("town");
            getDisplay().returnTown();
        } else if (getPlayer().getLocation().equals("town")) {
            getDisplay().alreadyInTown();
        }
    }
}
