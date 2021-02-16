package com.justinmtech.thewild.commands;

import com.justinmtech.thewild.Game;
import com.justinmtech.thewild.entity.Entity;

import java.util.Scanner;

//The quit command.
//Quits the game and optionally saves your data.
public class Quit {
    private Entity player;

    public Quit(Entity player) {
        this.player = player;
        tryQuit();
    }

    private void tryQuit() {
        if (!player.isInCombat()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Are you sure you want to quit?");

            //Listen for "yes" input, and quit if entered
            if (scanner.next().equalsIgnoreCase("yes")) {

                System.out.println("Thanks for playing!");
                Game.quitGame();
            } else {
                System.out.println("Quit cancelled!");
            }
        } else {
            System.out.println("You can't quit while in combat!");
        }
    }
}
