package com.tutexpsoft.ecommercedev.ServerResponseModel;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ImagesItem{

	@SerializedName("date_modified_gmt")
	private String dateModifiedGmt;

	@SerializedName("date_modified")
	private String dateModified;

	@SerializedName("src")
	private String src;

	@SerializedName("date_created")
	private String dateCreated;

	@SerializedName("name")
	private String name;

	@SerializedName("alt")
	private String alt;

	@SerializedName("date_created_gmt")
	private String dateCreatedGmt;

	@SerializedName("id")
	private int id;

	@SerializedName("position")
	private int position;

	public String getDateModifiedGmt(){
		return dateModifiedGmt;
	}

	public String getDateModified(){
		return dateModified;
	}

	public String getSrc(){
		return src;
	}

	public String getDateCreated(){
		return dateCreated;
	}

	public String getName(){
		return name;
	}

	public String getAlt(){
		return alt;
	}

	public String getDateCreatedGmt(){
		return dateCreatedGmt;
	}

	public int getId(){
		return id;
	}

	public int getPosition(){
		return position;
	}

	@Override
 	public String toString(){
		return 
			"ImagesItem{" + 
			"date_modified_gmt = '" + dateModifiedGmt + '\'' + 
			",date_modified = '" + dateModified + '\'' + 
			",src = '" + src + '\'' + 
			",date_created = '" + dateCreated + '\'' + 
			",name = '" + name + '\'' + 
			",alt = '" + alt + '\'' + 
			",date_created_gmt = '" + dateCreatedGmt + '\'' + 
			",id = '" + id + '\'' + 
			",position = '" + position + '\'' + 
			"}";
		}
}