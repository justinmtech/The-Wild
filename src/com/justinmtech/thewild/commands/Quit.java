package com.justinmtech.thewild.commands;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.ui.Display;

public class Quit {
    private Entity player;
    private Display display;

    public Quit(Entity player) {
        this.player = player;
        this.display = new Display();
        tryQuit();
    }

    private void tryQuit() {
        //are you sure
        //quit game
    }
}
