package com.justinmtech.thewild.commands;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.user_interface.Display;

//The info command.
//Info displays useful information about your player.
public class Info {
    private final Entity player;
    private final Display display;

    public Info(Entity player) {
        display = new Display();
        this.player = player;
        showInfo();
    }

    private void showInfo() {
        display.info(player);
    }
}
