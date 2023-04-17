package com.example.foodrecommendation.model;

import com.google.gson.annotations.SerializedName;

public class Images{

	@SerializedName("REGULAR")
	private REGULAR rEGULAR;

	@SerializedName("SMALL")
	private SMALL sMALL;

	@SerializedName("THUMBNAIL")
	private THUMBNAIL tHUMBNAIL;

	@SerializedName("LARGE")
	private LARGE lARGE;

	public REGULAR getREGULAR(){
		return rEGULAR;
	}

	public SMALL getSMALL(){
		return sMALL;
	}

	public THUMBNAIL getTHUMBNAIL(){
		return tHUMBNAIL;
	}

	public LARGE getLARGE(){
		return lARGE;
	}
}