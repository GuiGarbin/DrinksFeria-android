package com.example.drinksdexjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewDrinks;
    private DrinkAdapter drinkAdapter;
    ActivityResultLauncher<Intent> addDrinkLauncher;
    private DrinksRepository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);//inicializa o app
        setContentView(R.layout.activity_main);//chama a tela inicial
        initializeLauncher();
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
        drinkAdapter = new DrinkAdapter(new ArrayList<>(), this::showDetails);//cria variavel q carrega os drinks
        recyclerViewDrinks.setAdapter(drinkAdapter);//manda os drinks pra recyclerView mostrar
        repository = new DrinksRepository(getApplication());
        repository.getAllDrinks().observe(this, new Observer<List<Drink>>() {
            @Override
            public void onChanged(List<Drink> drinks) {
                drinkAdapter.setDrinks(drinks);
            }
        });
    }

    private void initializeLauncher(){
        addDrinkLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result-> {
                    if(result.getResultCode()== MainActivity.RESULT_OK){

                    }
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        drinkAdapter.notifyDataSetChanged();
    }


    private void showDetails(Drink drink){
        BottomSheetDialog dialog = new BottomSheetDialog(this);//cria a dialog dos detalhes
        dialog.setContentView(R.layout.layout_details_drink);//mostra a tela dos detalhes

        FloatingActionButton editbutton = dialog.findViewById(R.id.editButton);//cria o botao de editar
        editbutton.setOnClickListener(v->{//configura o clique do botao
            Intent intent = new Intent(this, CadastroActivity.class);//cria a intent pra transportar dados
            intent.putExtra("editDrink", drink);//cria a correlacao do objeto e a string pro outro lado
            addDrinkLauncher.launch(intent);//chama o launcher pra abrir a outra tela
            dialog.dismiss();//fecha a dialog
        });

        FloatingActionButton deleteButton = dialog.findViewById(R.id.deletButton);
        deleteButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Excluir Drink")
                    .setMessage("Tem certeza que deseja apagar o " + drink.getName() + "?")
                    .setPositiveButton("Sim", (dialogInterface, i) -> {
                        repository.delete(drink);
                        dialog.dismiss(); // Fecha o BottomSheet
                        Toast.makeText(this, "Drink excluído", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Não", null)
                    .show();
        });

        //cria as variaveis da tela de detalhes
        TextView name = dialog.findViewById(R.id.detailsName);
        TextView ingredients = dialog.findViewById(R.id.detailsIngredients);
        TextView recipe = dialog.findViewById(R.id.detailRecipe);
        TextView alcoholContent = dialog.findViewById(R.id.detailsAlcoholContent);
        RatingBar reviewStar = dialog.findViewById(R.id.detailsReviewStar);
        ImageView photo = dialog.findViewById(R.id.detailsPhoto);
        TextView alcoholBase = dialog.findViewById(R.id.detailsAlcoholBase);
        TextView drinkTemperature = dialog.findViewById(R.id.detailsDrinkTemperature);
        TextView drinkCategory = dialog.findViewById(R.id.detailsDrinkCategory);

        //seta os dados do drink na tela de detalhes
        name.setText(drink.getName());
        ingredients.setText(drink.getIngredients());
        recipe.setText(drink.getRecipe());
        if(drink.getPhoto()!=null){//confere se tem foto ou nao
            photo.setImageURI(Uri.parse(drink.getPhoto()));
        } else {
            photo.setImageResource(R.drawable.baseline_local_drink_24);
        }
        alcoholContent.setText(String.format("%.1f%% vol", drink.getAlcoholContent()));
        reviewStar.setRating(drink.getRating());

        //confere se as categorias foram selecionadas
        if (drink.getAlcoholBase() != null) {
            alcoholBase.setText(drink.getAlcoholBase().toString()+" . ");
        } else {
            alcoholBase.setText("-");
        }

        if (drink.getDrinkTemperature() != null) {
            drinkTemperature.setText(drink.getDrinkTemperature().toString());
        } else {
            drinkTemperature.setText("-");
        }

        if (drink.getDrinkCategory() != null) {
            drinkCategory.setText(drink.getDrinkCategory().toString()+" . ");
        } else {
            drinkCategory.setText("-");
        }
        dialog.show();//mostra a dialog de detalhes
    }
}
