package com.justinmtech.thewild.commands;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.user_interface.Display;

//The info command.
//Info displays useful information about your player.
public class Info extends Command {
    public Info(Entity player) {
        setLabel("info");
        setDisplay(new Display());
        setPlayer(player);
        showInfo();
    }
    private void showInfo() {
        getDisplay().info(getPlayer());
    }
}
