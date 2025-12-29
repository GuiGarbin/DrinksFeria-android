package com.example.drinksdexjava;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewDrinks;
    private DrinkAdapter drinkAdapter;
    private List<Drink> listaDeDrinks;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);//inicializa o app
        setContentView(R.layout.activity_main);//chama a tela inicial
        FloatingActionButton butao = findViewById(R.id.buttonAddDrink);//adiciona o botao pra mudar de pagina
        butao.setOnClickListener(v -> {//clique no bbotao
            Intent intent = new Intent(this, CadastroActivity.class);//cria a possibilidade de mudar
            startActivity(intent);//muda de pagina
        });
        recyclerViewDrinks = findViewById(R.id.drinksRecyclerView);//vincula a variavel no recyclerView dos xml
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);//cria um formato pra lista
        recyclerViewDrinks.setLayoutManager(layoutManager);//aplica a ordem criada
        this.criarDrinksFalsos();//cria os drinks
        drinkAdapter = new DrinkAdapter(listaDeDrinks);//cria variavel q carrega os drinks
        recyclerViewDrinks.setAdapter(drinkAdapter);//manda os drinks pra recyclerView mostrar
    }


    public void criarDrinksFalsos() {
        listaDeDrinks = new ArrayList<>();
        listaDeDrinks.add(new Drink("Caipirinha", "Limão e Cachaça", "20%", null));
        listaDeDrinks.add(new Drink("Mojito", "Rum e Hortelã", "15%", null));
        listaDeDrinks.add(new Drink("Gin Tônica", "Gin e Tônica", "12%", null));
        listaDeDrinks.add(new Drink("Negroni", "Gin, Vermute, Campari", "25%", null));
        listaDeDrinks.add(new Drink("Mojito", "Rum, hortelã, limão e soda", "13%", null));
        listaDeDrinks.add(new Drink("Gin Tônica", "Gin, água tônica e limão", "12%", null));
        listaDeDrinks.add(new Drink("Margarita", "Tequila, licor de laranja e limão", "26%", null));
        listaDeDrinks.add(new Drink("Old Fashioned", "Whiskey, angostura e açúcar", "32%", null));
        listaDeDrinks.add(new Drink("Cosmopolitan", "Vodka, cranberry e limão", "22%", null));
        listaDeDrinks.add(new Drink("Negroni", "Gin, vermute tinto e campari", "24%", null));
        listaDeDrinks.add(new Drink("Piña Colada", "Rum, leite de coco e abacaxi", "13%", null));
        listaDeDrinks.add(new Drink("Dry Martini", "Gin e vermute seco", "36%", null));
        listaDeDrinks.add(new Drink("Whiskey Sour", "Whiskey, limão e clara de ovo", "16%", null));
        listaDeDrinks.add(new Drink("Aperol Spritz", "Prosecco, Aperol e água com gás", "11%", null));
    }
}
