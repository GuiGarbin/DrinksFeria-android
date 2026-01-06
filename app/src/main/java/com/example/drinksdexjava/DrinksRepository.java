package com.example.drinksdexjava;

import java.util.ArrayList;

public class DrinksRepository {
    private ArrayList<Drink> listDrinks;
    private static DrinksRepository instance;

    private DrinksRepository(){
        listDrinks = new ArrayList<>();
        createFakeDrinks();

    }

    public void addDrink(Drink d){
        listDrinks.add(d);
    }

    public ArrayList<Drink> listDrink(){
        return listDrinks;
    }

    public Drink getDrinkByIndex(int indice){
        if(indice>=0 && indice<listDrinks.size()){
            return listDrinks.get(indice);
        }
        return null;
    }

    public void editDrink(Drink drinkModified){
        for(int i = 0; i < listDrinks.size(); i++){
            if(listDrinks.get(i).getId() == drinkModified.getId()){
                listDrinks.set(i, drinkModified);
                return;
            }
        }
    }

    public static DrinksRepository getInstance(){
        if(instance==null){
            instance = new DrinksRepository();
        }
        return instance;
    }

    public int getSize(){
        return listDrinks.size() + 1;
    }

    private void createFakeDrinks() {
        listDrinks = new ArrayList<>();
        listDrinks.add(new Drink(0, "teste", "teste sério", "bater tudo", 12,null, 2, (AlcoholBase) AlcoholBase.GIN, (DrinkTemperature)DrinkTemperature.HOT, (DrinkCategory)DrinkCategory.LONG_DRINK));
        listDrinks.add(new Drink(1,"Caipirinha", "Limão e Cachaça","", 20, 4));
        listDrinks.add(new Drink(2,"Mojito", "Rum e Hortelã", "",15,  4));
        listDrinks.add(new Drink(3,"Gin Tônica", "Gin e Tônica", "",12,  4));
        listDrinks.add(new Drink(4,"Negroni", "Gin, Vermute, Campari", "",25, 4));
        listDrinks.add(new Drink(5,"Mojito", "Rum, hortelã, limão e soda", "",13,  4));
        listDrinks.add(new Drink(6,"Gin Tônica", "Gin, água tônica e limão", "",12,  4));
        listDrinks.add(new Drink(7,"Margarita", "Tequila, licor de laranja e limão", "",26,  4));
        listDrinks.add(new Drink(8,"Old Fashioned", "Whiskey, angostura e açúcar", "",32,  4));
        listDrinks.add(new Drink(9,"Cosmopolitan", "Vodka, cranberry e limão", "",22,  4));
        listDrinks.add(new Drink(10,"Negroni", "Gin, vermute tinto e campari", "",24,  4));
        listDrinks.add(new Drink(11,"Piña Colada", "Rum, leite de coco e abacaxi", "",13,  4));
        listDrinks.add(new Drink(12,"Dry Martini", "Gin e vermute seco", "",36,  4));
        listDrinks.add(new Drink(13,"Whiskey Sour", "Whiskey, limão e clara de ovo", "",16,  4));
        listDrinks.add(new Drink(14, "Aperol Spritz", "Prosecco, Aperol e água com gás", "",11,  4));
    }
}
