package com.example.drinksdexjava;

public enum AlcoholBase {
    NON_TYPE("Selecione a base alcoolica..."),
    VODKA("Vodka"),
    GIN("Gin"),
    RUM("Rum"),
    TEQUILA("Tequila"),
    WHISKY("Whisky"),
    CACHACA("Cachaca"),
    WINE("Vinho"),
    NON_ALCOHOLIC("Sem_Alcool");

    private final String txtScreen;

    AlcoholBase(String txtScreen) {
        this.txtScreen = txtScreen;
    }

    @Override
    public String toString(){
        return txtScreen;
    }
}