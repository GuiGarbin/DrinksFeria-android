package com.example.drinksdexjava;

public enum TypeAlcoholDrink {
    VODKA("Vodka"),
    GIN("Gin"),
    RUM("Rum"),
    TEQUILA("Tequila"),
    WHISKY("Whisky"),
    CACHACA("Cachaca"),
    VINHO("Vinho"),
    SEM_ALCOOL("Sem_Alcool");

    private final String txtScreen;

    TypeAlcoholDrink(String txtScreen) {
        this.txtScreen = txtScreen;
    }

    @Override
    public String toString(){
        return txtScreen;
    }
}