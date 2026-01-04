package com.example.drinksdexjava;

public enum TypeCategoryDrink {
    LONG_DRINK("Long Drink"),
    SHORT_DRINK("Short Drink"),
    SHOT("Shot"),
    COQUETEL("Coquetel");

    private final String txtScreen;

    TypeCategoryDrink(String txtScreen) {
        this.txtScreen = txtScreen;
    }

    @Override
    public String toString(){
        return txtScreen;
    }
}
