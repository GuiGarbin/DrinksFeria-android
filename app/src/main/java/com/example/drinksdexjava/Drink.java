package com.example.drinksdexjava;

import java.io.Serializable;

public class Drink implements Serializable {
    private String name;
    private String ingredients;
    private String recipe;
    private double alcoholContent;
    private String photo;
    private float reviewStar;

    // Construtor
    public Drink(String name, String ingredients, String recipe, double alcoholContent, String photo, float reviewStar) {
        this.name = name;
        this.ingredients = ingredients;
        this.recipe = recipe;
        this.alcoholContent = alcoholContent;
        this.photo = photo;
        this.reviewStar = reviewStar;
    }

    // Getters (Para o Adapter conseguir ler os dados)
    public String getName() { return name; }
    public String getIngredients() { return ingredients; }
    public double getAlcoholContent() { return alcoholContent; }
    public String getPhoto() { return photo; }

    public String getRecipe(){ return recipe; }

    public float getReviewStar(){ return reviewStar; }

    public void setPhoto(String photoURL) {
        this.photo = photoURL;
    }
}
