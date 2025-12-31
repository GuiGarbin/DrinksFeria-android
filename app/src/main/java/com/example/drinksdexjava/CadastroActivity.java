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
            salvarDrink();
        });
    }

    private void inicializeComponents(){
        name = findViewById(R.id.drinkNameEditText);
        ingredients = findViewById(R.id.ingredientsEditText);
        recipe = findViewById(R.id.recipeEditText);
        alcoholContent = findViewById(R.id.alcoholContentEditText);
        starReview = findViewById(R.id.reviewStarEditText);
    }

    private void salvarDrink(){
        String nameD = name.getText().toString();
        String ingredientsD = ingredients.getText().toString();
        String recipeD = recipe.getText().toString();
        String alcoholContentText = alcoholContent.getText().toString();
        double alcoholContentD = 0;
        if(!alcoholContentText.isEmpty()){
            alcoholContentD = Double.parseDouble(alcoholContentText);
        }
        String starReviwText = starReview.getText().toString();
        float starReviewD = 0;
        if(!starReviwText.isEmpty()){
            starReviewD = Float.parseFloat(starReviwText);
        }

        if(nameD.isEmpty()||ingredientsD.isEmpty()||recipeD.isEmpty()){
            Toast.makeText(this, "Preencha os campos obrigatórios", Toast.LENGTH_SHORT).show();
            return;
        }

        Drink newDrink = new Drink(nameD, ingredientsD, recipeD, alcoholContentD, null, starReviewD);

        Intent intentReturn = new Intent();
        intentReturn.putExtra("Drink cadastrado", newDrink);

        setResult(RESULT_OK, intentReturn);
        finish();
    }

}
