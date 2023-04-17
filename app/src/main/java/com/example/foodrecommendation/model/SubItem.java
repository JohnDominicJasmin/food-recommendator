package com.example.foodrecommendation.model;

import com.google.gson.annotations.SerializedName;

public class SubItem{

	@SerializedName("schemaOrgTag")
	private String schemaOrgTag;

	@SerializedName("total")
	private Object total;

	@SerializedName("unit")
	private String unit;

	@SerializedName("daily")
	private Object daily;

	@SerializedName("hasRDI")
	private boolean hasRDI;

	@SerializedName("label")
	private String label;

	@SerializedName("tag")
	private String tag;

	public String getSchemaOrgTag(){
		return schemaOrgTag;
	}

	public Object getTotal(){
		return total;
	}

	public String getUnit(){
		return unit;
	}

	public Object getDaily(){
		return daily;
	}

	public boolean isHasRDI(){
		return hasRDI;
	}

	public String getLabel(){
		return label;
	}

	public String getTag(){
		return tag;
	}
}