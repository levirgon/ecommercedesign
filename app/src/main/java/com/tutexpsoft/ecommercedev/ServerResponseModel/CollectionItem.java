package com.tutexpsoft.ecommercedev.ServerResponseModel;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class CollectionItem{

	@SerializedName("href")
	private String href;

	public String getHref(){
		return href;
	}

	@Override
 	public String toString(){
		return 
			"CollectionItem{" + 
			"href = '" + href + '\'' + 
			"}";
		}
}