package com.tutexpsoft.ecommercedev.ServerResponseModel;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class MetaDataItem{

	@SerializedName("id")
	private int id;

	@SerializedName("value")
	private String value;

	@SerializedName("key")
	private String key;

	public int getId(){
		return id;
	}

	public String getValue(){
		return value;
	}

	public String getKey(){
		return key;
	}

	@Override
 	public String toString(){
		return 
			"MetaDataItem{" + 
			"id = '" + id + '\'' + 
			",value = '" + value + '\'' + 
			",key = '" + key + '\'' + 
			"}";
		}
}