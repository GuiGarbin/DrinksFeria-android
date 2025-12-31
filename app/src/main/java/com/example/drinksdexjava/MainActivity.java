package com.example.drinksdexjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewDrinks;
    private DrinkAdapter drinkAdapter;
    private List<Drink> listDrinks;
    ActivityResultLauncher<Intent> resultRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);//inicializa o app
        setContentView(R.layout.activity_main);//chama a tela inicial
        inicializeLauncher();
        this.criarDrinksFalsos();//cria os drinks
        configRecyclerView();//chama a funcao que configura a recycler view
        configButton();//chama a funcao que configura os botoes
    }

    private void configButton(){
        FloatingActionButton butao = findViewById(R.id.buttonAddDrink);//adiciona o botao pra mudar de pagina
        butao.setOnClickListener(v -> {//clique no bbotao
            Intent intent = new Intent(this, CadastroActivity.class);//cria a possibilidade de mudar
            resultRegister.launch(intent);
        });
    }

    private void configRecyclerView(){
        recyclerViewDrinks = findViewById(R.id.drinksRecyclerView);//vincula a variavel no recyclerView dos xml
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);//cria um formato pra lista
        recyclerViewDrinks.setLayoutManager(new LinearLayoutManager(this));//aplica a ordem criada
        drinkAdapter = new DrinkAdapter(listDrinks, this::mostrarDetalhes);//cria variavel q carrega os drinks
        recyclerViewDrinks.setAdapter(drinkAdapter);//manda os drinks pra recyclerView mostrar
    }

    private void inicializeLauncher(){
        resultRegister = registerForActivityResult(
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
        listDrinks.add(d);
        drinkAdapter.notifyItemInserted(listDrinks.size()-1);
    }


    private void mostrarDetalhes(Drink drink){
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.layout_details_drink);

        TextView name = dialog.findViewById(R.id.detailsName);
        TextView ingredients = dialog.findViewById(R.id.detailsIngredients);
        TextView recipe = dialog.findViewById(R.id.detailRecipe);
        ImageView photo = dialog.findViewById(R.id.detailsPhoto);
        TextView alcoholContent = dialog.findViewById(R.id.detailsAlcoholConten);
        TextView reviewStar = dialog.findViewById(R.id.detailsReviewStar);

        name.setText(drink.getName());
        ingredients.setText(drink.getIngredients());
        recipe.setText(drink.getRecipe());
        if(drink.getPhoto()!=null){
            photo.setImageURI(Uri.parse(drink.getPhoto()));
        } else {
            photo.setImageResource(R.drawable.baseline_local_drink_24);
        }
        alcoholContent.setText(String.format("%.1f%% vol", drink.getAlcoholContent()));
        reviewStar.setText(String.format("%.1f review", drink.getReviewStar()));
        dialog.show();
    }

    public void criarDrinksFalsos() {
        listDrinks = new ArrayList<>();
        listDrinks.add(new Drink("Caipirinha", "Limão e Cachaça",null, 20, null, 4));
        listDrinks.add(new Drink("Mojito", "Rum e Hortelã", null,15, null, 4));
        listDrinks.add(new Drink("Gin Tônica", "Gin e Tônica", null,12, null, 4));
        listDrinks.add(new Drink("Negroni", "Gin, Vermute, Campari", null,25, null, 4));
        listDrinks.add(new Drink("Mojito", "Rum, hortelã, limão e soda", null,13, null, 4));
        listDrinks.add(new Drink("Gin Tônica", "Gin, água tônica e limão", null,12, null, 4));
        listDrinks.add(new Drink("Margarita", "Tequila, licor de laranja e limão", null,26, null, 4));
        listDrinks.add(new Drink("Old Fashioned", "Whiskey, angostura e açúcar", null,32, null, 4));
        listDrinks.add(new Drink("Cosmopolitan", "Vodka, cranberry e limão", null,22, null, 4));
        listDrinks.add(new Drink("Negroni", "Gin, vermute tinto e campari", null,24, null, 4));
        listDrinks.add(new Drink("Piña Colada", "Rum, leite de coco e abacaxi", null,13, null, 4));
        listDrinks.add(new Drink("Dry Martini", "Gin e vermute seco", null,36, null, 4));
        listDrinks.add(new Drink("Whiskey Sour", "Whiskey, limão e clara de ovo", null,16, null, 4));
        listDrinks.add(new Drink("Aperol Spritz", "Prosecco, Aperol e água com gás", null,11, null, 4));
    }
}
