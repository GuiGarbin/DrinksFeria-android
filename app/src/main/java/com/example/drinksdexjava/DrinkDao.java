package com.example.drinksdexjava;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DrinkDao {

    // Salvar (Se já existir o mesmo ID, substitui)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Drink drink);

    // Atualizar
    @Update
    void update(Drink drink);

    // Deletar um específico
    @Delete
    void delete(Drink drink);

    // Buscar TODOS (Retorna LiveData para o MVVM funcionar sozinho)
    @Query("SELECT * FROM table_drink ORDER BY name ASC")
    LiveData<List<Drink>> getAllDrinks();

    // Buscar Por ID (Para edição)
    @Query("SELECT * FROM table_drink WHERE id = :drinkId")
    Drink getDrinkById(int drinkId);
}
