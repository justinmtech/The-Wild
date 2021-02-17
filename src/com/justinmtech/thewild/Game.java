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
    private Entity loadedPlayer;
    private final Display display;
    private CommandParser commandParser;
    public static boolean gameRunning = true;

    public Game() {
        loadedPlayer = new Entity();
        player = new Entity("Player", 5, 100);
        data = new PlayerDataHandler();
        display = new Display();
    }

    public void game() throws Exception {
        display.welcome();
        username = ScanInput.getString();

        try {
            loadedPlayer = new Entity();
            loadedPlayer = loadData();
            commandParser = new CommandParser(loadedPlayer);
            display.greeting(player);
            display.farewell();
        } catch (NullPointerException e) {
            System.out.println(e.getCause());
            commandParser = new CommandParser(player);
            player.setName(username);
            display.greeting(player);
            display.farewell();
        }

        while (gameRunning) {
            commandParser.parseCommand();
        }
    }

    private Entity loadData() throws Exception {
        return data.load(username);
    }

    //Stop the game
    public static void quitGame() {
        gameRunning = false;
    }
}
