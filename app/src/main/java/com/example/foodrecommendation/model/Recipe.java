package com.example.foodrecommendation.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe{

	@SerializedName("image")
	private String image;

	@SerializedName("shareAs")
	private String shareAs;

	@SerializedName("images")
	private Images images;

	@SerializedName("cautions")
	private List<String> cautions;

	@SerializedName("healthLabels")
	private List<String> healthLabels;

	@SerializedName("totalTime")
	private Object totalTime;

	@SerializedName("mealType")
	private List<String> mealType;

	@SerializedName("label")
	private String label;

	@SerializedName("source")
	private String source;

	@SerializedName("calories")
	private Object calories;

	@SerializedName("cuisineType")
	private List<String> cuisineType;

	@SerializedName("uri")
	private String uri;

	@SerializedName("url")
	private String url;

	@SerializedName("totalNutrients")
	private TotalNutrients totalNutrients;

	@SerializedName("dietLabels")
	private List<Object> dietLabels;

	@SerializedName("dishType")
	private List<String> dishType;

	@SerializedName("yield")
	private Object yield;

	@SerializedName("totalWeight")
	private Object totalWeight;

	@SerializedName("digest")
	private List<DigestItem> digest;

	@SerializedName("ingredients")
	private List<IngredientsItem> ingredients;

	@SerializedName("totalDaily")
	private TotalDaily totalDaily;

	@SerializedName("ingredientLines")
	private List<String> ingredientLines;

	public String getImage(){
		return image;
	}

	public String getShareAs(){
		return shareAs;
	}

	public Images getImages(){
		return images;
	}

	public List<String> getCautions(){
		return cautions;
	}

	public List<String> getHealthLabels(){
		return healthLabels;
	}

	public Object getTotalTime(){
		return totalTime;
	}

	public List<String> getMealType(){
		return mealType;
	}

	public String getLabel(){
		return label;
	}

	public String getSource(){
		return source;
	}

	public Object getCalories(){
		return calories;
	}

	public List<String> getCuisineType(){
		return cuisineType;
	}

	public String getUri(){
		return uri;
	}

	public String getUrl(){
		return url;
	}

	public TotalNutrients getTotalNutrients(){
		return totalNutrients;
	}

	public List<Object> getDietLabels(){
		return dietLabels;
	}

	public List<String> getDishType(){
		return dishType;
	}

	public Object getYield(){
		return yield;
	}

	public Object getTotalWeight(){
		return totalWeight;
	}

	public List<DigestItem> getDigest(){
		return digest;
	}

	public List<IngredientsItem> getIngredients(){
		return ingredients;
	}

	public TotalDaily getTotalDaily(){
		return totalDaily;
	}

	public List<String> getIngredientLines(){
		return ingredientLines;
	}
}