package com.example.drinksdexjava;

public enum DrinkCategory {
    NON_TYPE("Selecione a categorio..."),
    LONG_DRINK("Long Drink"),
    SHORT_DRINK("Short Drink"),
    SHOT("Shot"),
    COQUETEL("Coquetel");

    private final String txtScreen;

    DrinkCategory(String txtScreen) {
        this.txtScreen = txtScreen;
    }

    @Override
    public String toString(){
        return txtScreen;
    }
}
