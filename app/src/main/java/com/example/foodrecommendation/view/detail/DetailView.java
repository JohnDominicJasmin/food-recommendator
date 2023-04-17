package com.example.foodrecommendation.view.detail;

import com.example.foodrecommendation.model.Meals;
import com.example.foodrecommendation.model.TotalNutrients;

public interface DetailView {


    void showloading();
    void hideloading();
    void setMeal(Meals.Meal meal);
    void setFoodNutrients(TotalNutrients nutrients);
    void onErrorloading(String message);
}
