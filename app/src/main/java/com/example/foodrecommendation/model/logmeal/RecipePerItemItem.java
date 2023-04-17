package com.example.foodrecommendation.model.logmeal;

import java.util.List;

public class RecipePerItemItem{
	private int dishId;
	private String unit;
	private Measure measure;
	private Object servingSize;
	private List<RecipeItem> recipe;
	private int foodItemPosition;
	private boolean hasRecipe;
	private Levels levels;

	public int getDishId(){
		return dishId;
	}

	public String getUnit(){
		return unit;
	}

	public Measure getMeasure(){
		return measure;
	}

	public Object getServingSize(){
		return servingSize;
	}

	public List<RecipeItem> getRecipe(){
		return recipe;
	}

	public int getFoodItemPosition(){
		return foodItemPosition;
	}

	public boolean isHasRecipe(){
		return hasRecipe;
	}

	public Levels getLevels(){
		return levels;
	}
}