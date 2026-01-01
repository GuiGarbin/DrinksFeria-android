package com.example.drinksdexjava;

import java.util.ArrayList;

public class DrinksRepository {
    private ArrayList<Drink> listDrinks;
    private static DrinksRepository instance;

    private DrinksRepository(){
        listDrinks = new ArrayList<>();
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
}
