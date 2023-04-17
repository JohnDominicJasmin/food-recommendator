package com.example.foodrecommendation.view.home;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.foodrecommendation.Utils;
import com.example.foodrecommendation.api.CalorieMamaManager;
import com.example.foodrecommendation.api.LogMealCallback;
import com.example.foodrecommendation.model.Categories;
import com.example.foodrecommendation.model.MealIngredients;
import com.example.foodrecommendation.model.Meals;
import com.example.foodrecommendation.model.logmeal.LogMealApiDto;
import com.example.foodrecommendation.view.search.GetMealByIngredient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {

    private HomeView view;


    private List<Meals.Meal> meals = null;

    public HomePresenter(HomeView view) {
        this.view = view;
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

    void recognizeFood(Uri uri, Context cc, OnGetMealCallback onGetMealCallback) throws RuntimeException {

        CalorieMamaManager calorieMamaManager = new CalorieMamaManager();
        calorieMamaManager.getResults(uri, cc, new LogMealCallback() {
            @Override
            public void onResponse(@NonNull LogMealApiDto jsonObject) {
                String foodName = jsonObject.getFoodName().get(0);
                onGetMealCallback.onSuccess(foodName);
            }

            @Override
            public void onFailure(@NonNull String message) {
                onGetMealCallback.onError(message);
            }
        });


    }

    void loadMeals(Context context) {

        view.showLoading();

        Call<Meals> mealsCall = Utils.getFoodApi(context).getMeal();
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {
                    meals = response.body().getMeals();
                    view.setMeal(meals);

                } else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }


    void loadFoodCategories(Context context) {

        view.showLoading();

        Call<Categories> categoriesCall = Utils.getFoodApi(context).getCategories();
        categoriesCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(@NonNull Call<Categories> call,
                                   @NonNull Response<Categories> response) {

                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {

                    view.setCategory(response.body().getCategories());

                } else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Categories> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}

interface OnGetMealCallback {
    void onSuccess(String foodName);

    void onError(String message);
}