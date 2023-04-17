package com.example.foodrecommendation.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealIngredients{

	@SerializedName("meals")
	private List<MealsItem> meals;

	public List<MealsItem> getMeals(){
		return meals;
	}
}