package com.example.foodrecommendation.model;

import com.google.gson.annotations.SerializedName;

public class VITB12{

	@SerializedName("unit")
	private String unit;

	@SerializedName("quantity")
	private Object quantity;

	@SerializedName("label")
	private String label;

	public String getUnit(){
		return unit;
	}

	public Object getQuantity(){
		return quantity;
	}

	public String getLabel(){
		return label;
	}
}