package com.example.drinksdexjava;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        recyclerViewDrinks = findViewById(R.id.drinksRecyclerView);//vincula a variavel no recyclerView dos xml
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);//cria um formato pra lista
        recyclerViewDrinks.setLayoutManager(layoutManager);//aplica a ordem criada
        this.criarDrinksFalsos();//cria os drinks
        drinkAdapter = new DrinkAdapter(listaDeDrinks);//cria variavel q carrega os drinks
        recyclerViewDrinks.setAdapter(drinkAdapter);//manda os drinks pra recyclerView mostrar
    }

    public void criarDrinksFalsos() {
        listaDeDrinks = new ArrayList<>();
        listaDeDrinks.add(new Drink("Caipirinha", "Limão e Cachaça", "20%", R.drawable.ic_local_bar));
        listaDeDrinks.add(new Drink("Mojito", "Rum e Hortelã", "15%", R.drawable.ic_local_bar));
        listaDeDrinks.add(new Drink("Gin Tônica", "Gin e Tônica", "12%", R.drawable.ic_local_bar));
        listaDeDrinks.add(new Drink("Negroni", "Gin, Vermute, Campari", "25%", R.drawable.ic_local_bar));
    }
}
