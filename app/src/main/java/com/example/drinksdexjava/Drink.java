package com.example.drinksdexjava;

import java.io.Serializable;

public class Drink implements Serializable {

    private int id;
    private String name;
    private String ingredients;
    private String recipe;
    private double alcoholContent;
    private String photo;
    private float reviewStar;

    private TypeAlcoholDrink typeAlcoholDrink;
    private TypeCategoryDrink typeCategoryDrink;
    private TypeTempDrink typeTempDrink;

    // Construtor
    public Drink(int id,
                 String name,
                 String ingredients,
                 String recipe,
                 double alcoholContent,
                 String photo,
                 float reviewStar,
                 TypeAlcoholDrink typeAlcoholDrink,
                 TypeTempDrink typeTempDrink,
                 TypeCategoryDrink typeCategoryDrink) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.recipe = recipe;
        this.alcoholContent = alcoholContent;
        this.photo = photo;
        this.reviewStar = reviewStar;
        this.typeAlcoholDrink = typeAlcoholDrink;
        this.typeCategoryDrink = typeCategoryDrink;
        this.typeTempDrink = typeTempDrink;
    }

    //CONSTRUTOR PRA TESTE DA LISTA INICIAL DE DRINKS
    //EXCLUIR NA VERSAO FINAL
    public Drink(int id, String name, String ingredients, String recipe, double alcoholContent, float reviewStar) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.recipe = recipe;
        this.alcoholContent = alcoholContent;
        this.photo = photo;
        this.reviewStar = reviewStar;
    }

    // Getters (Para o Adapter conseguir ler os dados)
    public int getId() { return id; }

    public String getName() { return name; }
    public String getIngredients() { return ingredients; }
    public double getAlcoholContent() { return alcoholContent; }
    public String getPhoto() { return photo; }

    public String getRecipe(){ return recipe; }

    public float getReviewStar(){ return reviewStar; }

    public void setName(String name) { this.name = name; }

    public void setIngredients(String ingredients) { this.ingredients = ingredients; }

    public void setRecipe(String recipe) { this.recipe = recipe; }

    public void setAlcoholContent(double alcoholContent) { this.alcoholContent = alcoholContent; }

    public void setReviewStar(float reviewStar) { this.reviewStar = reviewStar; }

    public void setPhoto(String photoURL) {
        this.photo = photoURL;
    }

    public void setTypeTempDrink(TypeTempDrink typeTempDrink) {
        this.typeTempDrink = typeTempDrink;
    }

    public void setTypeCategoryDrink(TypeCategoryDrink typeCategoryDrink) {
        this.typeCategoryDrink = typeCategoryDrink;
    }

    public void setTypeAlcoholDrink(TypeAlcoholDrink typeAlcoholDrink) {
        this.typeAlcoholDrink = typeAlcoholDrink;
    }
}
