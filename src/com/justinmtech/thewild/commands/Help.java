package com.justinmtech.thewild.commands;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.ui.Display;

public class Help {
    private Entity player;
    private Display display;

    public Help(Entity player) {
        this.player = player;
        this.display = new Display();
        showHelp();
    }

    private void showHelp() {
            if (player.getLocation().equals("town")) display.townHelp();
            else if (player.getLocation().equals("wild") && player.isInCombat()) display.combatHelp();
            else if (player.getLocation().equals("wild")) display.wildHelp();
            else display.otherHelp();
        }
}
