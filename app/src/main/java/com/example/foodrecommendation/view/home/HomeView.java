package com.example.foodrecommendation.view.home;



import com.example.foodrecommendation.model.Categories;
import com.example.foodrecommendation.model.Meals;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void setMeal(List<Meals.Meal> meal);
    void setCategory(List<Categories.Category> category);
    void onErrorLoading(String message);
}
