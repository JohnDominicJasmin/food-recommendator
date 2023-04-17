package com.example.foodrecommendation.model.logmeal;

public class RecipeItem{
	private String unit;
	private Measure measure;
	private String name;
	private Object weight;
	private int id;

	public String getUnit(){
		return unit;
	}

	public Measure getMeasure(){
		return measure;
	}

	public String getName(){
		return name;
	}

	public Object getWeight(){
		return weight;
	}

	public int getId(){
		return id;
	}
}
