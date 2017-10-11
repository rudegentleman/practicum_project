package model;

import DatabaseConnection.DatabaseConnection;

public class Test {
	
	public static void main(String [] argv){
		
		
		
		DatabaseConnection bd = new DatabaseConnection();
		
		bd.read("name");
		
		
	}

}
