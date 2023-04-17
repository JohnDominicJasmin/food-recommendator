package com.example.foodrecommendation.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodNutrients{

	@SerializedName("hits")
	private List<HitsItem> hits;

	@SerializedName("_links")
	private Links links;

	@SerializedName("count")
	private int count;

	@SerializedName("from")
	private int from;

	@SerializedName("to")
	private int to;

	public List<HitsItem> getHits(){
		return hits;
	}

	public Links getLinks(){
		return links;
	}

	public int getCount(){
		return count;
	}

	public int getFrom(){
		return from;
	}

	public int getTo(){
		return to;
	}
}