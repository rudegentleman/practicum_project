package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteRows {
	
	
	private Connection myConnection;
	private PreparedStatement statement;

	public DeleteRows() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String db = "jdbc:mysql://localhost:3306/practicum";
			//myConnection = DriverManager.getConnection(db, "dniwemugisha", "Student@123");
			myConnection = DriverManager.getConnection(db, "root", "");
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}


	}
	
	
	
	public void deleteNullRows(){
		ResultSet set=null;
		
		String query ="DELETE FROM `practicum`.`my_table` Where oxygen IS NULL";
		
		try {
			statement = myConnection.prepareStatement(query);
		    //statement.setString(1,null);	
		    statement.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	
	public static void main(String [] f){
			
		DeleteRows d = new DeleteRows();
		
		d.deleteNullRows();
		
		
	}
}
