package model;

import java.util.ArrayList;

public class MathClass {

	/*
	 * 
	 * this class returns the current day mean after inserting a new reading
	 * 
	 * */

	public static double dailyMean(double currReading, double currMean, int numOfreadings){


		return currMean+(currReading/(1+numOfreadings));
	}
	
	
	
	// calculating the mean from raw readings
	public static double mean(ArrayList <String> readings){
		
		
		double average =0;
		double total=0;
		
		int i=0;
		for(String num:readings ){
			
			
			total+= Double.parseDouble(num);
			i++;
			
		}
		
		average = total/i;
		
		
		return average;
		
	}

}
