package com.example.foodrecommendation.view.detail;

import android.content.Context;

import androidx.annotation.NonNull;


import com.example.foodrecommendation.Utils;
import com.example.foodrecommendation.model.FoodNutrients;
import com.example.foodrecommendation.model.Meals;
import com.example.foodrecommendation.model.TotalNutrients;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter {
    private DetailView view;

    public DetailPresenter(DetailView view) {
        this.view = view;
    }


    void getMealByName(Context context, String mealName) {

        view.showloading();
        Utils.getFoodApi(context).getMealByName(mealName)
                .enqueue(new Callback<Meals>() {
                    @Override
                    public void onResponse(@NonNull Call<Meals> call,@NonNull Response<Meals> response) {
                        view.showloading();
                        if (response.isSuccessful()&&response.body() !=null){
                            view.setMeal(response.body().getMeals().get(0));

                        } else {
                            view.onErrorloading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Meals> call,@NonNull  Throwable t) {
                        view.hideloading();
                        view.onErrorloading(t.getLocalizedMessage());
                    }
                });

    }

    void getFoodNutrients(Context context, String foodName){
        view.showloading();
        Call<FoodNutrients> foodNutrientsCall = Utils.getEdamamApi(context).searchRecipes(foodName, "346c33e1", "d7134891a448af58c820d5432b10fea2");
        foodNutrientsCall.enqueue(new Callback<FoodNutrients>() {
            @Override
            public void onResponse(@NonNull Call<FoodNutrients> call, @NonNull Response<FoodNutrients> response) {
                view.hideloading();
                if (response.isSuccessful() && response.body() != null) {

                    TotalNutrients nutrients = response.body().getHits().get(0).getRecipe().getTotalNutrients();
                    view.setFoodNutrients(nutrients);
                } else {
                    view.onErrorloading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<FoodNutrients> call, @NonNull Throwable t) {
                view.hideloading();
                view.onErrorloading(t.getLocalizedMessage());
            }
        });
    }

}
