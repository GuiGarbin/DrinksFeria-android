package com.example.drinksdexjava;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class DrinkAdapter extends RecyclerView.Adapter<DrinkViewHolder> {
    private List<Drink> listDrinks;
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

    @Override
    public int getItemCount() {
        return listDrinks.size();
    }
}
