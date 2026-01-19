package com.example.drinksdexjava;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Drink.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class DrinkDatabase extends RoomDatabase {

    public abstract DrinkDao drinkDao();

    private static volatile DrinkDatabase INSTANCE;

    // Cria 4 threads de fundo para não travar a UI ao salvar
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    public static DrinkDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DrinkDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DrinkDatabase.class, "drink_database")
                            .fallbackToDestructiveMigration() // Se mudar o banco, limpa tudo (seguro pra dev)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
