package com.example.drinksdexjava;

import java.util.ArrayList;

public class DrinksRepository {
    private ArrayList<Drink> listDrinks;
    private static DrinksRepository instance;

    private DrinksRepository(){
        listDrinks = new ArrayList<>();
        criarDrinksFalsos();

    }

    public void addDrink(Drink d){
        listDrinks.add(d);
    }

    public ArrayList<Drink> listDrink(){
        return listDrinks;
    }

    public Drink searchForIndex(int indice){
        if(indice>=0 && indice<listDrinks.size()){
            return listDrinks.get(indice);
        }
        return null;
    }

    public static DrinksRepository getInstance(){
        if(instance==null){
            instance = new DrinksRepository();
        }
        return instance;
    }

    private void criarDrinksFalsos() {
        listDrinks = new ArrayList<>();
        listDrinks.add(new Drink("Caipirinha", "Limão e Cachaça",null, 20, null, 4));
        listDrinks.add(new Drink("Mojito", "Rum e Hortelã", null,15, null, 4));
        listDrinks.add(new Drink("Gin Tônica", "Gin e Tônica", null,12, null, 4));
        listDrinks.add(new Drink("Negroni", "Gin, Vermute, Campari", null,25, null, 4));
        listDrinks.add(new Drink("Mojito", "Rum, hortelã, limão e soda", null,13, null, 4));
        listDrinks.add(new Drink("Gin Tônica", "Gin, água tônica e limão", null,12, null, 4));
        listDrinks.add(new Drink("Margarita", "Tequila, licor de laranja e limão", null,26, null, 4));
        listDrinks.add(new Drink("Old Fashioned", "Whiskey, angostura e açúcar", null,32, null, 4));
        listDrinks.add(new Drink("Cosmopolitan", "Vodka, cranberry e limão", null,22, null, 4));
        listDrinks.add(new Drink("Negroni", "Gin, vermute tinto e campari", null,24, null, 4));
        listDrinks.add(new Drink("Piña Colada", "Rum, leite de coco e abacaxi", null,13, null, 4));
        listDrinks.add(new Drink("Dry Martini", "Gin e vermute seco", null,36, null, 4));
        listDrinks.add(new Drink("Whiskey Sour", "Whiskey, limão e clara de ovo", null,16, null, 4));
        listDrinks.add(new Drink("Aperol Spritz", "Prosecco, Aperol e água com gás", null,11, null, 4));
    }
}
