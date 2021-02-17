package com.justinmtech.thewild.commands;

import com.justinmtech.thewild.Game;
import com.justinmtech.thewild.data.PlayerDataHandler;
import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.utilities.ScanInput;

//The quit command.
//Quits the game and optionally saves your data.
public class Quit extends Command {
    private PlayerDataHandler data;

    public Quit(Entity player) {
        setLabel("quit");
        setPlayer(player);
        data = new PlayerDataHandler();
        tryQuit();
    }

    private void tryQuit() {
        if (!getPlayer().isInCombat()) {
            System.out.println("Are you sure you want to quit?");

            //Listen for "yes" input, and quit if entered
            if (ScanInput.getString().equalsIgnoreCase("yes")) {

                System.out.println("Thanks for playing!");
                data.save(getPlayer());
                Game.quitGame();
            } else {
                System.out.println("Quit cancelled!");
            }
        } else {
            System.out.println("You can't quit while in combat!");
        }
    }
}
