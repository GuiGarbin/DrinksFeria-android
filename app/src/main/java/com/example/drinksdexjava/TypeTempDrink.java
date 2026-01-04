package com.example.drinksdexjava;

public enum TypeTempDrink {
    HOT("Hot"),
    COLD("Cold"),
    FROZEN("Frozen"),
    ROOM("Room");

    private final String txtScreen;

    TypeTempDrink(String txtScreen) {
        this.txtScreen = txtScreen;
    }

    @Override
    public String toString(){
        return txtScreen;
    }
}
