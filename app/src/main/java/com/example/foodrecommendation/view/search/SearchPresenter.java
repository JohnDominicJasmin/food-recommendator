package com.example.foodrecommendation.view.search;

import android.content.Context;

import androidx.annotation.NonNull;


import com.example.foodrecommendation.Utils;
import com.example.foodrecommendation.model.MealIngredients;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter {

    private SearchView view;

    public SearchPresenter(SearchView searchView) {
        this.view = searchView;
    }
    void getMealByIngredient(Context context, String ingredient, GetMealByIngredient getMealByIngredient) {
        view.showLoading();
        Utils.getFoodApi(context).getMealByIngredient(ingredient)
                .enqueue(new Callback<MealIngredients>() {
                    @Override
                    public void onResponse(@NonNull Call<MealIngredients> call, @NonNull Response<MealIngredients> response) {
                        view.hideLoading();

                        if (response.isSuccessful() && response.body() != null) {
                            getMealByIngredient.onSuccess(response.body().getMeals());
                        }
                        else {
                            getMealByIngredient.onError(response.message());
                            view.onErrorLoading(response.message());
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<MealIngredients> call, @NonNull Throwable t) {
                        getMealByIngredient.onError(t.getLocalizedMessage());
                        view.onErrorLoading(t.getLocalizedMessage());
                    }
                });

    }

}
