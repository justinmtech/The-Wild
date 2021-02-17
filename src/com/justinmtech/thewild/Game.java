package com.justinmtech.thewild;

import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.user_interface.CommandParser;
import com.justinmtech.thewild.user_interface.Display;
import com.justinmtech.thewild.utilities.ScanInput;

//Start the game!
public class Game {
    private final Entity player;
    private final Display display;
    public static boolean gameRunning = true;

    public Game() {
        player = new Entity("Player", 5, 100);
        this.display = new Display();
    }

    public void game() {
        display.welcome();
        String name = ScanInput.getString();
        player.setName(name);
        CommandParser commandParser = new CommandParser(player);

        display.greeting(player);
        display.farewell();
        while (gameRunning) {
            commandParser.parseCommand();
        }
    }

    public static void quitGame() {
        gameRunning = false;
    }
}
