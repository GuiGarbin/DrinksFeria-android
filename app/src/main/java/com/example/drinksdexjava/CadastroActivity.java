package com.example.drinksdexjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class CadastroActivity extends AppCompatActivity {
    private EditText name;
    private EditText ingredients;
    private EditText recipe;
    private EditText alcoholContent;
    private EditText starReview;
    private ImageView imagePhotoDrink;
    private Drink drinkReceived = null;
    private Button buttonSaveDrink;
    private String currentURIPhoto = null;
    private ActivityResultLauncher<String> launcherGallery;
    private AlcoholBase selectedAlcoholType;
    private DrinkTemperature selectedTempType;
    private DrinkCategory selectedCategoryType;
    private TextView txtSummary;

    private TextView txtDisplayBase, txtDisplayTemp, txtDisplaySize;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        initializeComponents();
        initializeLauncherPhoto();
        editDrink();
        configButton();
    }

    private void configButton(){
        ImageButton buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v->{
            finish();
        });

        buttonSaveDrink.setOnClickListener(v->{
            saveDrink();
        });

        imagePhotoDrink.setOnClickListener(v-> {
            String[] options = {"Tirar Foto", "Escolher da Galeria"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Definir Imagem");
            builder.setItems(options, (dialog, which) -> {
                if (which == 0) {
                    // Lógica da Câmera (implementaremos depois)
                } else if (which == 1) {
                    launcherGallery.launch("image/*");
                }
            });
            builder.show();
        });

        LinearLayout buttonAddCategories = findViewById(R.id.typeDrinkButtonLayout);
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

        configSpinner(spinAlcohol, AlcoholBase.values());
        configSpinner(spinTemp, DrinkTemperature.values());
        configSpinner(spinCategory, DrinkCategory.values());



        if (drinkReceived != null || selectedAlcoholType != null) {

            if (spinAlcohol.getAdapter() != null && selectedAlcoholType != null) {
                ArrayAdapter adapter = (ArrayAdapter) spinAlcohol.getAdapter();
                int pos = adapter.getPosition(selectedAlcoholType);
                if (pos >= 0) spinAlcohol.setSelection(pos);
            }

            if (spinCategory.getAdapter() != null && selectedCategoryType != null) {
                ArrayAdapter adapter = (ArrayAdapter) spinCategory.getAdapter();
                int pos = adapter.getPosition(selectedCategoryType);
                if (pos >= 0) spinCategory.setSelection(pos);
            }

            if (spinTemp.getAdapter() != null && selectedTempType != null) {
                ArrayAdapter adapter = (ArrayAdapter) spinTemp.getAdapter();
                int pos = adapter.getPosition(selectedTempType);
                if (pos >= 0) spinTemp.setSelection(pos);
            }
        }


        Button buttonSaveCategories = dialog.findViewById(R.id.addCategoryButton);
        buttonSaveCategories.setOnClickListener(v-> {
            selectedAlcoholType = (AlcoholBase) spinAlcohol.getSelectedItem();
            selectedCategoryType = (DrinkCategory) spinCategory.getSelectedItem();
            selectedTempType = (DrinkTemperature) spinTemp.getSelectedItem();

            if(!verifyCategories()){
                return;
            }

            String resume = selectedAlcoholType.toString() + " . " +
                    selectedCategoryType.toString() + " . " +
                    selectedTempType.toString();

            txtSummary.setText(resume);

            dialog.dismiss();
        });
        dialog.show();
    }

    private void updateViewCategories() {
        if (selectedAlcoholType != null && selectedAlcoholType != AlcoholBase.NON_TYPE) {
            txtDisplayBase.setText(selectedAlcoholType.toString());
            txtDisplayBase.setVisibility(View.VISIBLE);
        }

        if (selectedTempType != null && selectedTempType != DrinkTemperature.NON_TYPE) {
            txtDisplayTemp.setText(selectedTempType.toString());
            txtDisplayTemp.setVisibility(View.VISIBLE);
        }

        if (selectedCategoryType != null && selectedCategoryType != DrinkCategory.NON_TYPE) {
            txtDisplaySize.setText(selectedCategoryType.toString());
            txtDisplaySize.setVisibility(View.VISIBLE);
        }
    }

    private boolean verifyCategories(){
        if(selectedAlcoholType==AlcoholBase.NON_TYPE ||
                selectedCategoryType==DrinkCategory.NON_TYPE||
                selectedTempType == DrinkTemperature.NON_TYPE) {
            Toast.makeText(this, "Selecione todas as categorias", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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
                starReview.setText(String.valueOf(drinkReceived.getRating()));

                if(drinkReceived.getPhoto()!=null){
                    imagePhotoDrink.setImageURI(Uri.parse(drinkReceived.getPhoto()));
                    currentURIPhoto = drinkReceived.getPhoto();
                }

                selectedCategoryType = drinkReceived.getDrinkCategory();
                selectedTempType = drinkReceived.getDrinkTemperature();
                selectedCategoryType = drinkReceived.getDrinkCategory();

                buttonSaveDrink.setText("Atualizar");
            }
        }
    }

    private void saveDrink(){
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
            drinkReceived.setRating(starReviewD);
            drinkReceived.setPhoto(currentURIPhoto);

            drinkReceived.setAlcoholBase(selectedAlcoholType);
            drinkReceived.setDrinkTemperature(selectedTempType);
            drinkReceived.setDrinkCategory(selectedCategoryType);

            DrinksRepository.getInstance().editDrink(drinkReceived);
            Toast.makeText(this, "Drink atualizado", Toast.LENGTH_SHORT).show();
        } else {
            int id = DrinksRepository.getInstance().getSize();
            Drink newDrink = new Drink(id,
                    nameD,
                    ingredientsD,
                    recipeD,
                    alcoholContentD,
                    currentURIPhoto,
                    starReviewD,
                    selectedAlcoholType,
                    selectedTempType,
                    selectedCategoryType
            );

            DrinksRepository.getInstance().addDrink(newDrink);
            Toast.makeText(this, "Drink criado", Toast.LENGTH_SHORT).show();

        }
        finish();
    }

    private boolean validateData(){
        if(currentURIPhoto ==null){
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

    private void initializeComponents(){
        name = findViewById(R.id.drinkNameEditText);
        ingredients = findViewById(R.id.ingredientsEditText);
        recipe = findViewById(R.id.recipeEditText);
        alcoholContent = findViewById(R.id.alcoholContentEditText);
        starReview = findViewById(R.id.reviewStarEditText);
        buttonSaveDrink = findViewById(R.id.saveButton);
        imagePhotoDrink = findViewById(R.id.addPhotoButton);

//        txtDisplayBase = findViewById(R.id.txtDisplayBase);
//        txtDisplayTemp = findViewById(R.id.txtDisplayTemp);
//        txtDisplaySize = findViewById(R.id.txtDisplaySize);

        txtSummary = findViewById(R.id.txtCategoriesSummary);

    }

    private void initializeLauncherPhoto(){
        launcherGallery = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri->{
                    if(uri!=null) {
                        imagePhotoDrink.setImageURI(uri);
                        currentURIPhoto = uri.toString();
                        getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                }
        );
    }

    private <T> void configSpinner(Spinner spinner, T[] valores) {
        ArrayAdapter<T> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, valores);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
