package com.example.foodrecommendation.view.search;


import com.example.foodrecommendation.model.MealsItem;

import java.util.List;

public interface GetMealByIngredient {
    void onSuccess(List<MealsItem> meals);
    void onError(String message);
}