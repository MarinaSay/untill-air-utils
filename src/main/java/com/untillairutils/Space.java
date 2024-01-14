package com.untillairutils;

public class Space {
    public String name;

    public String numberOfTables;

    public Space(String name, String numberOfTables) {
        this.name = name;
        this.numberOfTables = numberOfTables;
    }

    public static Space[] inputSpaces() {
        Space[] spaces = new Space[3];
        spaces[0] = new Space("Restaurant", "10");
        spaces[1] = new Space("Bar", "6");
        spaces[2] = new Space("Terrace", "5");
        return spaces;
    }
}
