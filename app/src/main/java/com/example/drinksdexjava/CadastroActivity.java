package com.example.drinksdexjava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CadastroActivity extends AppCompatActivity {
    private EditText name;
    private EditText ingredients;
    private EditText recipe;
    private EditText alcoholContent;
    private EditText starReview;
    private ImageView imagePhotoDrink;
    private Drink drinkReceived = null;
    private Button buttonSaveDrink;
    private String stringURISave = null;
    private ActivityResultLauncher<String> launcherGalery;
    private TypeAlcoholDrink categoriaBase;
    private TypeTempDrink categoriaOcasiao;
    private TypeCategoryDrink categoriaTamanho;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inicializeComponents();
        inicializeLauncherPhoto();
        editDrink();
        configButton();
    }

    private void configButton(){
        ImageButton buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v->{
            finish();
        });

        buttonSaveDrink.setOnClickListener(v->{
            drinkSave();
        });

        imagePhotoDrink.setOnClickListener(v-> {
            String[] options = {"Tirar Foto", "Escolher da Galeria"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Definir Imagem");
            builder.setItems(options, (dialog, which) -> {
                if (which == 0) {
                    // Lógica da Câmera (implementaremos depois)
                } else if (which == 1) {
                    launcherGalery.launch("image/*");
                }
            });
            builder.show();
        });

        Button buttonAddCategories = findViewById(R.id.typeDrinkButton);
        buttonAddCategories.setOnClickListener(v-> {
            getCategories();
        });

    }
    private void getCategories(){
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.layout_characteristics_selection);
        Spinner spinAlcohol = dialog.findViewById(R.id.alcoholTypeSelect);
        Spinner spinTemp = dialog.findViewById(R.id.tempTypeSelect);
        Spinner spinCategory = dialog.findViewById(R.id.categoryTypeSelect);

        configurarSpinner(spinAlcohol, TypeAlcoholDrink.values());
        configurarSpinner(spinTemp, TypeTempDrink.values());
        configurarSpinner(spinCategory, TypeCategoryDrink.values());

        Button buttonSaveCategories = dialog.findViewById(R.id.addCategoryButton);
        buttonSaveCategories.setOnClickListener(v-> {
            categoriaBase = (TypeAlcoholDrink) spinAlcohol.getSelectedItem();
            categoriaTamanho = (TypeCategoryDrink) spinCategory.getSelectedItem();
            categoriaOcasiao = (TypeTempDrink) spinTemp.getSelectedItem();

            dialog.dismiss();
        });
        dialog.show();
    }


    private void editDrink(){
        Intent intent = getIntent();
        if(intent.hasExtra("editDrink")){
            drinkReceived = (Drink) intent.getSerializableExtra("editDrink");
            if(drinkReceived!=null){
                name.setText(drinkReceived.getName());
                ingredients.setText(drinkReceived.getIngredients());
                recipe.setText(drinkReceived.getRecipe());
                alcoholContent.setText(String.valueOf(drinkReceived.getAlcoholContent()));
                starReview.setText(String.valueOf(drinkReceived.getReviewStar()));

                if(drinkReceived.getPhoto()!=null){
                    imagePhotoDrink.setImageURI(Uri.parse(drinkReceived.getPhoto()));
                    stringURISave = drinkReceived.getPhoto();
                }

                buttonSaveDrink.setText("Atualizar");
            }
        }
    }

    private void drinkSave(){
        if(!validateData()){
            return;
        }

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

        if(drinkReceived!=null){
            drinkReceived.setName(nameD);
            drinkReceived.setIngredients(ingredientsD);
            drinkReceived.setRecipe(recipeD);
            drinkReceived.setAlcoholContent(alcoholContentD);
            drinkReceived.setReviewStar(starReviewD);
            drinkReceived.setPhoto(stringURISave);

            DrinksRepository.getInstance().editDrink(drinkReceived);
            Toast.makeText(this, "Drink atualizado", Toast.LENGTH_SHORT).show();
        } else {
            int id = DrinksRepository.getInstance().getSize();
            Drink newDrink = new Drink(id,
                    nameD,
                    ingredientsD,
                    recipeD,
                    alcoholContentD,
                    stringURISave,
                    starReviewD,
                    categoriaBase,
                    categoriaOcasiao,
                    categoriaTamanho
            );

            DrinksRepository.getInstance().addDrink(newDrink);
            Toast.makeText(this, "Drink criado", Toast.LENGTH_SHORT).show();

        }
        finish();
    }

    private boolean validateData(){
        if(stringURISave==null){
            Toast.makeText(this,"Selecione uma imagem para o drink", Toast.LENGTH_SHORT).show();
            imagePhotoDrink.requestFocus();
            return false;
        }
        if(name.getText().toString().trim().isEmpty()){
            name.setError("Campo obrigatório");
            name.requestFocus();
            return false;
        }
        if(ingredients.getText().toString().trim().isEmpty()){
            ingredients.setError("Campo obrigatório");
            ingredients.requestFocus();
            return false;
        }
        if(recipe.getText().toString().trim().isEmpty()){
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

    private void inicializeComponents(){
        name = findViewById(R.id.drinkNameEditText);
        ingredients = findViewById(R.id.ingredientsEditText);
        recipe = findViewById(R.id.recipeEditText);
        alcoholContent = findViewById(R.id.alcoholContentEditText);
        starReview = findViewById(R.id.reviewStarEditText);
        buttonSaveDrink = findViewById(R.id.saveButton);
        imagePhotoDrink = findViewById(R.id.addPhotoButton);
    }

    private void inicializeLauncherPhoto(){
        launcherGalery = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri->{
                    if(uri!=null) {
                        imagePhotoDrink.setImageURI(uri);
                        stringURISave = uri.toString();
                        getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                }
        );
    }

    private <T> void configurarSpinner(Spinner spinner, T[] valores) {
        ArrayAdapter<T> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, valores);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
