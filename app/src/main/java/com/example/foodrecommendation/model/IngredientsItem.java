package com.example.foodrecommendation.model;

import com.google.gson.annotations.SerializedName;

public class IngredientsItem{

	@SerializedName("image")
	private String image;

	@SerializedName("quantity")
	private Object quantity;

	@SerializedName("measure")
	private String measure;

	@SerializedName("foodId")
	private String foodId;

	@SerializedName("weight")
	private Object weight;

	@SerializedName("text")
	private String text;

	@SerializedName("foodCategory")
	private String foodCategory;

	@SerializedName("food")
	private String food;

	public String getImage(){
		return image;
	}

	public Object getQuantity(){
		return quantity;
	}

	public String getMeasure(){
		return measure;
	}

	public String getFoodId(){
		return foodId;
	}

	public Object getWeight(){
		return weight;
	}

	public String getText(){
		return text;
	}

	public String getFoodCategory(){
		return foodCategory;
	}

	public String getFood(){
		return food;
	}
}