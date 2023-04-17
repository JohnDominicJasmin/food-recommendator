package com.example.foodrecommendation.api;


import android.content.Context;


import com.example.foodrecommendation.core.di.NetworkModule;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodClient {


    private static final String MEALDB_API_BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static final String EDAMAM_API_BASE_URL = "https://api.edamam.com/api/recipes/v2/";

    public Retrofit getFoodClient(Context context){
        return new Retrofit.Builder()
                .baseUrl(MEALDB_API_BASE_URL)
                .client(NetworkModule.providesOkHttpClient(context))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getFoodNutrients(Context context){
        return new Retrofit.Builder().baseUrl(EDAMAM_API_BASE_URL)
                .client(NetworkModule.providesOkHttpClient(context))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



}
