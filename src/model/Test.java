package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import DatabaseConnection.DatabaseConnection;

public class Test {
	
	public static void main(String [] argv){
		
				Date currDate = new Date();	

				  String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(currDate);

		System.out.println(timeStamp);
		
	}

}
