package com.justinmtech.thewild.commands;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.ui.Display;

public class Info {
    private Entity player;
    private Display display;

    public Info(Entity player) {
        display = new Display();
        this.player = player;
        showInfo();
    }

    private void showInfo() {
        display.info(player);
    }
}
