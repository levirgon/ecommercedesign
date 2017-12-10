package com.tutexpsoft.ecommercedev.ServerResponseModel;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Dimensions{

	@SerializedName("length")
	private String length;

	@SerializedName("width")
	private String width;

	@SerializedName("height")
	private String height;

	public String getLength(){
		return length;
	}

	public String getWidth(){
		return width;
	}

	public String getHeight(){
		return height;
	}

	@Override
 	public String toString(){
		return 
			"Dimensions{" + 
			"length = '" + length + '\'' + 
			",width = '" + width + '\'' + 
			",height = '" + height + '\'' + 
			"}";
		}
}