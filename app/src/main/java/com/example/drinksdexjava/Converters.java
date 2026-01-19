package com.example.drinksdexjava;

import androidx.room.TypeConverter;

public class Converters {

    // --- AlcoholBase ---
    @TypeConverter
    public static AlcoholBase toAlcoholBase(String value) {
        return value == null ? null : AlcoholBase.valueOf(value);
    }

    @TypeConverter
    public static String fromAlcoholBase(AlcoholBase value) {
        return value == null ? null : value.name();
    }

    // --- DrinkCategory ---
    @TypeConverter
    public static DrinkCategory toDrinkCategory(String value) {
        return value == null ? null : DrinkCategory.valueOf(value);
    }

    @TypeConverter
    public static String fromDrinkCategory(DrinkCategory value) {
        return value == null ? null : value.name();
    }

    // --- DrinkTemperature ---
    @TypeConverter
    public static DrinkTemperature toDrinkTemp(String value) {
        return value == null ? null : DrinkTemperature.valueOf(value);
    }

    @TypeConverter
    public static String fromDrinkTemp(DrinkTemperature value) {
        return value == null ? null : value.name();
    }
}
