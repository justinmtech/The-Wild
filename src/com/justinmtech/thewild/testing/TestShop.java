package com.justinmtech.thewild.testing;

import com.justinmtech.thewild.entity.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestShop {
    Entity player = new Entity("Bob", 5, 100);
    String input = "Dragon_Blade";

    @BeforeEach
    void setup() {
        player.setInventory(new ArrayList<>(Arrays.asList("Iron_Sword", "Steel_Sword", "Leather_Armor")));
    }

    //Give a player an item if possible.
    @Test
    void putItemInInventory() {
        if (player.getInventory().size() < 32) {
            player.getInventory().add(input);
        }
        else {
            System.out.println("Inventory full!");
        }
    }
}
