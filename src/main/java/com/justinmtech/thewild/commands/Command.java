package com.justinmtech.thewild.commands;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.user_interface.Display;

//The parent class for the main commands of the program found in /commands
public class Command {
    private String label;
    private Entity player;
    private Entity computer;
    private Display display;

    public Entity getComputer() {
        return computer;
    }

    public void setComputer(Entity computer) {
        this.computer = computer;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Entity getPlayer() {
        return player;
    }

    public void setPlayer(Entity player) {
        this.player = player;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

}
