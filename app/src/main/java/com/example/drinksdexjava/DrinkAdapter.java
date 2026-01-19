package com.example.drinksdexjava;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class DrinkAdapter extends RecyclerView.Adapter<DrinkViewHolder> {
    private List<Drink> listDrinks;
    private List<Drink> listDrinksComplete;
    private final DrinkClickListener listener;

    public DrinkAdapter(List<Drink> listDrinks, DrinkClickListener listener){
        this.listDrinks = listDrinks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemDrinkList = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drink_list, parent, false);
        return new DrinkViewHolder(itemDrinkList);
    }
    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, int position) {
        Drink thisDrink = listDrinks.get(position);

        holder.name.setText(thisDrink.getName());
        holder.ingredients.setText(thisDrink.getIngredients());
        if(thisDrink.getPhoto()!=null){
            holder.photo.setImageURI(Uri.parse(thisDrink.getPhoto()));
        } else {
            holder.photo.setImageResource(R.drawable.baseline_local_drink_24);
        }
        holder.itemView.setOnClickListener(v-> {
            listener.onClickDrink(thisDrink);
        });
    }

    public void setDrinks(List<Drink> novosDrinks) {
        // 1. Atualiza o "Backup" (para a busca funcionar depois)
        this.listDrinksComplete = new ArrayList<>(novosDrinks);

        // 2. Atualiza a lista visual
        this.listDrinks = new ArrayList<>(novosDrinks);

        // 3. Avisa a tela para redesenhar
        notifyDataSetChanged();
    }

    public void filtrar(String texto) {
        listDrinks.clear(); // Limpa a tela

        if (texto.isEmpty()) {
            listDrinks.addAll(listDrinksComplete); // Restaura tudo do backup
        } else {
            texto = texto.toLowerCase();
            for (Drink item : listDrinksComplete) {
                // Se o nome contiver o texto digitado...
                if (item.getName().toLowerCase().contains(texto)) {
                    listDrinks.add(item); // ...adiciona na tela
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listDrinks.size();
    }
}
