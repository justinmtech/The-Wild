package com.justinmtech.thewild.testing;

import com.justinmtech.thewild.entity.Entity;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

//Used for unit testing in the Shop class
public class TestShop {
    Entity player = new Entity("Bob", 5, 100);
    String input = "Big_Potion";

    @Test
    int getItemType() {
        int itemType = 0;
        if (input.contains("Armor")) {
            itemType = 1;
        } else if (input.contains("Potion")) {
            itemType = 2;
        }
        assertEquals(2, itemType);
        return itemType;
    }

    @Test
    void putItemInInventory() {
            player.getInventory()[getItemType()] = input;
            assertEquals(2, getItemType());
    }
}
