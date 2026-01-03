package com.example.drinksdexjava;

public enum TypeAlcoholDrink {
    VODKA,
    GIN,
    RUM,
    TEQUILA,
    WHISKY,
    CACHACA,
    VINHO,
    SEM_ALCOOL;

    private int id;
    private String descricao;

    TypeAlcoholDrink(){
    }

    TypeAlcoholDrink(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static TypeAlcoholDrink buscarPorId(int idBusca) {
        for (TypeAlcoholDrink c : values()) {
            if (c.id == idBusca) {
                return c;
            }
        }
        throw new IllegalArgumentException("Id de categoria inválido: " + idBusca);
    }

    public static boolean existeId(int id) {
        for (TypeAlcoholDrink c : values()) {
            if (c.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
