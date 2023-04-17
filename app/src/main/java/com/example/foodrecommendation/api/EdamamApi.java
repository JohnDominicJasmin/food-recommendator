package com.example.foodrecommendation.api;


import com.example.foodrecommendation.model.FoodNutrients;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EdamamApi {

    @GET("/api/recipes/v2?type=public")
    Call<FoodNutrients> searchRecipes(
            @Query("q") String query,
            @Query("app_id") String appId,
            @Query("app_key") String appKey
    );
}
