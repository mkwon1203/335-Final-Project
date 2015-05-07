package model;

public class InvalidLocationException extends Exception{
	public InvalidLocationException(){
		System.out.println("Location clicked is not on the map.");
		this.printStackTrace();
	}
}
