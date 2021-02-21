package com.sisotik.note.model.response;

import com.google.gson.annotations.SerializedName;

public class ResultItem{

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("descNote")
	private String descNote;

	@SerializedName("__v")
	private int V;

	@SerializedName("titleNote")
	private String titleNote;

	@SerializedName("_id")
	private String id;

	@SerializedName("nimStudent")
	private String nimStudent;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setDescNote(String descNote){
		this.descNote = descNote;
	}

	public String getDescNote(){
		return descNote;
	}

	public void setV(int V){
		this.V = V;
	}

	public int getV(){
		return V;
	}

	public void setTitleNote(String titleNote){
		this.titleNote = titleNote;
	}

	public String getTitleNote(){
		return titleNote;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setNimStudent(String nimStudent){
		this.nimStudent = nimStudent;
	}

	public String getNimStudent(){
		return nimStudent;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"createdAt = '" + createdAt + '\'' + 
			",descNote = '" + descNote + '\'' + 
			",__v = '" + V + '\'' + 
			",titleNote = '" + titleNote + '\'' + 
			",_id = '" + id + '\'' + 
			",nimStudent = '" + nimStudent + '\'' + 
			"}";
		}
}