package com.example.drinksdexjava;

import java.io.Serializable;

public class Drink implements Serializable {

    private int id;
    private String name;
    private String ingredients;
    private String recipe;
    private double alcoholContent;
    private String photo;
    private float rating;

    private AlcoholBase alcoholBase;
    private DrinkCategory drinkCategory;
    private DrinkTemperature drinkTemperature;

    // Construtor
    public Drink(int id,
                 String name,
                 String ingredients,
                 String recipe,
                 double alcoholContent,
                 String photo,
                 float rating,
                 AlcoholBase alcoholBase,
                 DrinkTemperature drinkTemperature,
                 DrinkCategory drinkCategory) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.recipe = recipe;
        this.alcoholContent = alcoholContent;
        this.photo = photo;
        this.rating = rating;
        this.alcoholBase = alcoholBase;
        this.drinkCategory = drinkCategory;
        this.drinkTemperature = drinkTemperature;
    }

    //CONSTRUTOR PRA TESTE DA LISTA INICIAL DE DRINKS
    //EXCLUIR NA VERSAO FINAL
    public Drink(int id, String name, String ingredients, String recipe, double alcoholContent, float rating) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.recipe = recipe;
        this.alcoholContent = alcoholContent;
        this.photo = photo;
        this.rating = rating;
    }

    // Getters (Para o Adapter conseguir ler os dados)
    public int getId() { return id; }

    public String getName() { return name; }
    public String getIngredients() { return ingredients; }
    public double getAlcoholContent() { return alcoholContent; }
    public String getPhoto() { return photo; }

    public String getRecipe(){ return recipe; }

    public float getRating(){ return rating; }

    public void setName(String name) { this.name = name; }

    public void setIngredients(String ingredients) { this.ingredients = ingredients; }

    public void setRecipe(String recipe) { this.recipe = recipe; }

    public void setAlcoholContent(double alcoholContent) { this.alcoholContent = alcoholContent; }

    public void setRating(float rating) { this.rating = rating; }

    public void setPhoto(String photoURL) {
        this.photo = photoURL;
    }

    public void setDrinkTemperature(DrinkTemperature drinkTemperature) { this.drinkTemperature = drinkTemperature; }

    public void setDrinkCategory(DrinkCategory drinkCategory) { this.drinkCategory = drinkCategory; }

    public void setAlcoholBase(AlcoholBase alcoholBase) { this.alcoholBase = alcoholBase; }

    public AlcoholBase getAlcoholBase() {
        return alcoholBase;
    }

    public DrinkCategory getDrinkCategory() {
        return drinkCategory;
    }

    public DrinkTemperature getDrinkTemperature() {
        return drinkTemperature;
    }
}
