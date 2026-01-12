package com.example.drinksdexjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewDrinks;
    private DrinkAdapter drinkAdapter;
    private List<Drink> listDrinks;
    ActivityResultLauncher<Intent> addDrinkLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);//inicializa o app
        setContentView(R.layout.activity_main);//chama a tela inicial
        initializeLauncher();
        listDrinks = DrinksRepository.getInstance().listDrink();
        configRecyclerView();//chama a funcao que configura a recycler view
        configButton();//chama a funcao que configura os botoes
    }

    private void configButton(){
        FloatingActionButton fabAddDrink = findViewById(R.id.buttonAddDrink);//adiciona o botao pra mudar de pagina
        fabAddDrink.setOnClickListener(v -> {//clique no bbotao
            Intent intent = new Intent(MainActivity.this, CadastroActivity.class);//cria a possibilidade de mudar
            addDrinkLauncher.launch(intent);
        });
    }

    private void configRecyclerView(){
        recyclerViewDrinks = findViewById(R.id.drinksRecyclerView);//vincula a variavel no recyclerView dos xml
        recyclerViewDrinks.setLayoutManager(new LinearLayoutManager(this));//aplica a ordem criada
        drinkAdapter = new DrinkAdapter(listDrinks, this::showDetails);//cria variavel q carrega os drinks
        recyclerViewDrinks.setAdapter(drinkAdapter);//manda os drinks pra recyclerView mostrar
    }

    private void initializeLauncher(){
        addDrinkLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result-> {
                    if(result.getResultCode()== MainActivity.RESULT_OK){
                        Intent data = result.getData();

                        if(data!=null){
                            Drink newDrink = (Drink) data.getSerializableExtra("Drink cadastrado");
                            saveDrink(newDrink);
                        }
                    }
                }
        );
    }

    private void saveDrink(Drink d){
        DrinksRepository.getInstance().addDrink(d);
        drinkAdapter.notifyItemInserted(listDrinks.size()-1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        drinkAdapter.notifyDataSetChanged();
    }


    private void showDetails(Drink drink){
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.layout_details_drink);

        FloatingActionButton editbutton = dialog.findViewById(R.id.editButton);
        editbutton.setOnClickListener(v->{
            Intent intent = new Intent(this, CadastroActivity.class);
            intent.putExtra("editDrink", drink);
            addDrinkLauncher.launch(intent);
            dialog.dismiss();
        });

        TextView name = dialog.findViewById(R.id.detailsName);
        TextView ingredients = dialog.findViewById(R.id.detailsIngredients);
        TextView recipe = dialog.findViewById(R.id.detailRecipe);
        TextView alcoholContent = dialog.findViewById(R.id.detailsAlcoholConten);
        RatingBar reviewStar = dialog.findViewById(R.id.detailsReviewStar);
        ImageView photo = dialog.findViewById(R.id.detailsPhoto);

        TextView alcoholBase = dialog.findViewById(R.id.detailsAlcoholBase);
        TextView drinkTemperature = dialog.findViewById(R.id.detailsDrinkTemperature);
        TextView drinkCategory = dialog.findViewById(R.id.detailsDrinkCategory);

        name.setText(drink.getName());
        ingredients.setText(drink.getIngredients());
        recipe.setText(drink.getRecipe());
        if(drink.getPhoto()!=null){
            photo.setImageURI(Uri.parse(drink.getPhoto()));
        } else {
            photo.setImageResource(R.drawable.baseline_local_drink_24);
        }
        alcoholContent.setText(String.format("%.1f%% vol", drink.getAlcoholContent()));
        reviewStar.setRating(drink.getRating());

        if (drink.getAlcoholBase() != null) {
            alcoholBase.setText(drink.getAlcoholBase().toString());
        } else {
            alcoholBase.setText("-");
        }

        if (drink.getDrinkTemperature() != null) {
            drinkTemperature.setText(drink.getDrinkTemperature().toString());
        } else {
            drinkTemperature.setText("-");
        }

        if (drink.getDrinkCategory() != null) {
            drinkCategory.setText(drink.getDrinkCategory().toString());
        } else {
            drinkCategory.setText("-");
        }
        dialog.show();
    }
}
