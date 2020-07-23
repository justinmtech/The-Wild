package com.justin;

public class Battle extends Entity{

    public void combatLoop(Entity player) {
        CommandParser command = new CommandParser();
        CommandHandler commandHandler = new CommandHandler();
        Display display = new Display();
        Entity computer = new Entity(pickFoe(), setLevel(player));
        pickLoadout(computer);
        computer.isComputer = true;
        player.inCombat = true;
        setHP(computer);

        Skills skills = new Skills();

        display.newBattle(computer);

        while (!isBattleOver(player, computer)) {
            command.parseCombatCommands(computer, player);
            skills.doRandomSkill(player, computer);
            if (!isBattleOver(player, computer)) {
                display.combatOutput(player, computer);
            } else {
                if (player.isAlive) {
                    player.coins = player.coins + getRandomNumber(computer.level, player.battles * 4);
                    player.xp = player.xp + getRandomNumber(computer.level * 25, computer.level * 100);
                    display.combatOutcome(player, computer);
                    calculateLevel(player);
                    setHP(player);
                    player.battles++;
                    player.inCombat = false;
                    commandHandler.save(player);
                } else {
                    display.combatOutcome(player, computer);
                    player.coins = 0;
                    player.location = "town";
                    player.inventory[0] = "Air";
                    player.inventory[1] = "Air";
                    player.inventory[2] = "Air";
                    player.inventory[3] = "Air";
                    player.hp = player.maxHP;
                    player.inCombat = false;
                    commandHandler.save(player);
                }
            }
        }
    }

    public boolean isBattleOver(Entity player, Entity computer) {
        boolean isBattleOver = (!player.isAlive || !computer.isAlive || !player.inCombat);
        return isBattleOver;
    }

    public String pickFoe() {
        int randomNumber = getRandomNumber(0, 10);
        String foeName;
        if (randomNumber < 5) {
            foeName = "Wolf";
        } else {
            foeName = "Bandit";
        }
        return foeName;
    }

    public void pickLoadout(Entity entity) {
        int randomNumber = getRandomNumber(0 , 10);
        if (randomNumber > 4 && randomNumber < 6) {
            entity.inventory[0] = "Short Sword";
        } else if (randomNumber < 4 && randomNumber > 2) {
            entity.inventory[0] = "Long Sword";
        } else if (randomNumber < 2) {
            entity.inventory[0] = "Long Sword";
            entity.inventory[1] = "Iron Armor";

        } else {
            entity.inventory[0] = "Air";
            entity.inventory[1] = "Air";
        }
    }

    public short setLevel(Entity player) {
        short level;
        if (player.level > 2) {
            level = (short) getRandomNumber(player.level - 1, player.level + 2);
        } else level = 1;
        return level;
    }

    private int getRandomNumber(int min, int max) {
        int randomNumber = (int)(Math.random() * ((max - min) + 1)) + min;
        return randomNumber;
    }

    public int battleProgression(int battles) {
        int battleProgression = battles / 1;
        return battles;
    }
}
