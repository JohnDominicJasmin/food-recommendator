package com.example.foodrecommendation.model;

import com.google.gson.annotations.SerializedName;

public class Links{

	@SerializedName("next")
	private Next next;

	@SerializedName("self")
	private Self self;

	public Next getNext(){
		return next;
	}

	public Self getSelf(){
		return self;
	}
}