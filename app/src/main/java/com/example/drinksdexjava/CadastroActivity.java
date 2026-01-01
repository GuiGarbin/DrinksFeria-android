package com.example.drinksdexjava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CadastroActivity extends AppCompatActivity {
    private EditText name;
    private EditText ingredients;
    private EditText recipe;
    private EditText alcoholContent;
    private EditText starReview;


    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        configButton();
        inicializeComponents();
    }

    private void configButton(){
        ImageButton buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v->{
            finish();
        });

        Button buttonSaveDrink = findViewById(R.id.saveButton);
        buttonSaveDrink.setOnClickListener(v->{
            drinkSave();
        });
    }

    private void inicializeComponents(){
        name = findViewById(R.id.drinkNameEditText);
        ingredients = findViewById(R.id.ingredientsEditText);
        recipe = findViewById(R.id.recipeEditText);
        alcoholContent = findViewById(R.id.alcoholContentEditText);
        starReview = findViewById(R.id.reviewStarEditText);
    }

    private void drinkSave(){
        String nameD = name.getText().toString().trim();
        String ingredientsD = ingredients.getText().toString().trim();
        String recipeD = recipe.getText().toString().trim();
        String alcoholContentText = alcoholContent.getText().toString().trim();
        double alcoholContentD = 0;
        if(!alcoholContentText.isEmpty()){
            alcoholContentD = Double.parseDouble(alcoholContentText);
        }
        String starReviwText = starReview.getText().toString();
        float starReviewD = 0;
        if(!starReviwText.isEmpty()){
            starReviewD = Float.parseFloat(starReviwText);
        }

        if(!validateData()){
            return;
        }

        Drink newDrink = new Drink(nameD, ingredientsD, recipeD, alcoholContentD, null, starReviewD);

        DrinksRepository.getInstance().addDrink(newDrink);
        finish();
    }

    private boolean validateData(){
        if(name.getText().toString().trim().isEmpty()){
            name.setError("Campo obrigatório");
            name.requestFocus();
            return false;
        }
        if(ingredients.getText().toString().isEmpty()){
            ingredients.setError("Campo obrigatório");
            ingredients.requestFocus();
            return false;
        }
        if(recipe.getText().toString().isEmpty()){
            recipe.setError("Campo obrigatório");
            recipe.requestFocus();
            return false;
        }
        String alcoholText = alcoholContent.getText().toString().replace(",", ".").trim();
        if (alcoholText.isEmpty()) {
            alcoholContent.setError("Campo obrigatório"); // Ou defina como 0 se preferir
            alcoholContent.requestFocus();
            return false;
        }
        if(Double.parseDouble(alcoholContent.getText().toString())<0||Double.parseDouble(alcoholContent.getText().toString())>100){
            alcoholContent.setError("Valor deve estar entre 0 e 100");
            alcoholContent.requestFocus();
            return false;
        }
        String starReviewText = starReview.getText().toString().replace(",", ".").trim();
        if (starReviewText.isEmpty()) {
            starReview.setError("Campo obrigatório"); // Ou defina como 0 se preferir
            starReview.requestFocus();
            return false;
        }
        if(Float.parseFloat(starReview.getText().toString())<0||Float.parseFloat(starReview.getText().toString())>5){
            starReview.setError("Valor deve estar entre 0 e 5");
            starReview.requestFocus();
            return false;
        }
        return true;
    }
}
