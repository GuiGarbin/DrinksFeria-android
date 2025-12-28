package com.example.drinksdexjava;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DrinkViewHolder extends RecyclerView.ViewHolder {
    public TextView nome;
    public TextView ingredientes;
    public ImageView foto;

    public DrinkViewHolder(@NonNull View itemView){
        super(itemView);
        nome = itemView.findViewById(R.id.itemName);
        ingredientes = itemView.findViewById(R.id.txtDescriptionDrink);
        foto = itemView.findViewById(R.id.itemImage);
    }
}
