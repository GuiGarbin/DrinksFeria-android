package com.example.drinksdexjava;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DrinkViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView ingredients;
    public ImageView photo;

    public DrinkViewHolder(@NonNull View itemView){
        super(itemView);
        name = itemView.findViewById(R.id.itemName);
        ingredients = itemView.findViewById(R.id.itemIngredients);
        photo = itemView.findViewById(R.id.itemPhoto);
    }
}
