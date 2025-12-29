package com.example.drinksdexjava;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class DrinkAdapter extends RecyclerView.Adapter<DrinkViewHolder> {
    private List<Drink> listaDrinks;

    public DrinkAdapter(List<Drink> listaDrinks){
        this.listaDrinks = listaDrinks;
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item_drink_list = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drink_list, parent, false);
        return new DrinkViewHolder(item_drink_list);
    }
    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, int position) {
        Drink drinkAtual = listaDrinks.get(position);

        holder.nome.setText(drinkAtual.getNome());
        holder.ingredientes.setText(drinkAtual.getIngrediente());
        if(drinkAtual.getImage()!=null){
            holder.foto.setImageURI(Uri.parse(drinkAtual.getImage()));
        } else {
            holder.foto.setImageResource(R.drawable.baseline_local_drink_24);
        }
    }

    @Override
    public int getItemCount() {
        return listaDrinks.size();
    }
}
