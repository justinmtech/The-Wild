package com.justinmtech.thewild.persistence;

public class SaveData implements java.io.Serializable {

    private static final long serialVersionUID = 1l;

    public String name;
    public double hp;
    public int coins;
    public int xp;
    public String location;
    public String[] inventory;
}
