package com.example.foodrecommendation.model;

import com.google.gson.annotations.SerializedName;

public class Next{

	@SerializedName("href")
	private String href;

	@SerializedName("title")
	private String title;

	public String getHref(){
		return href;
	}

	public String getTitle(){
		return title;
	}
}