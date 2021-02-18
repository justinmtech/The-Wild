package com.justinmtech.thewild;

import com.justinmtech.thewild.data.PlayerDataHandler;
import com.justinmtech.thewild.entity.Entity;
import com.justinmtech.thewild.user_interface.CommandParser;
import com.justinmtech.thewild.user_interface.Display;
import com.justinmtech.thewild.utilities.ScanInput;

//Start the game!
public class Game {
    private PlayerDataHandler data;
    private String username;
    private Entity player;
    private final Display display;
    private CommandParser commandParser;
    public static boolean gameRunning = true;

    public Game() {
        data = new PlayerDataHandler();
        display = new Display();
    }

    public void game() {
        display.welcome();
        username = ScanInput.getString();
        loadDataIfExists();
        while (gameRunning) {
            commandParser.parseCommand();
        }
    }

    //Load data if exists and create new player if not
    private void loadDataIfExists() {
        try {
            player = data.load(username);
            commandParser = new CommandParser(player);
            display.greeting(player);
            display.farewell();
        } catch (Exception e) {
            player = new Entity(username, 5, 100);
            commandParser = new CommandParser(player);
            display.greeting(player);
            display.farewell();
        }
    }

    //Stop the game
    public static void quitGame() {
        gameRunning = false;
    }
}
