package com.example.foodrecommendation.model.logmeal;

import java.util.List;

public class LogMealApiDto{
	private List<Integer> dishId;
	private List<String> foodName;
	private boolean isCombo;
	private int imageId;
	private Object servingSize;
	private List<RecipeItem> recipe;
	private Object source;
	private List<RecipePerItemItem> recipePerItem;

	public List<Integer> getDishId(){
		return dishId;
	}

	public List<String> getFoodName(){
		return foodName;
	}

	public boolean isIsCombo(){
		return isCombo;
	}

	public int getImageId(){
		return imageId;
	}

	public Object getServingSize(){
		return servingSize;
	}

	public List<RecipeItem> getRecipe(){
		return recipe;
	}

	public Object getSource(){
		return source;
	}

	public List<RecipePerItemItem> getRecipePerItem(){
		return recipePerItem;
	}
}