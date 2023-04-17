package com.example.foodrecommendation.model;

import com.google.gson.annotations.SerializedName;

public class HitsItem{

	@SerializedName("_links")
	private Links links;

	@SerializedName("recipe")
	private Recipe recipe;

	public Links getLinks(){
		return links;
	}

	public Recipe getRecipe(){
		return recipe;
	}
}