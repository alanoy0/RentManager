package com.epf.rentmanager.model;

public class Vehicle {
	private int id;
	private String constructor;
	//private String model;
	private short nbPlaces;
	
	
	
	public Vehicle(int id, String constructor, short nbPlaces) {
		this.id = id;
		this.constructor = constructor;
		//this.model = model;
		this.nbPlaces = nbPlaces;
	}



	public Vehicle() {
	}



	public String toString() {
		return "Id : " + id + " constructor : " + constructor +  " Nb de places : " + nbPlaces;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getConstructor() {
		return constructor;
	}



	public void setConstructor(String constructor) {
		this.constructor = constructor;
	}


	public short getNbPlaces() {
		return nbPlaces;
	}



	public void setNbPlaces(short nbPlaces) {
		this.nbPlaces = nbPlaces;
	}
	
	
	
	
}
