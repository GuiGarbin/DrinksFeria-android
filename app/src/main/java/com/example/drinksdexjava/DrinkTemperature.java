package com.example.drinksdexjava;

public enum DrinkTemperature {
    NON_TYPE("Selecione a temperatura..."),
    HOT("Hot"),
    COLD("Cold"),
    FROZEN("Frozen"),
    ROOM("Room");

    private final String txtScreen;

    DrinkTemperature(String txtScreen) {
        this.txtScreen = txtScreen;
    }

    @Override
    public String toString(){
        return txtScreen;
    }
}
