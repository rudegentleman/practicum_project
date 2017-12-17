package model;

import java.util.ArrayList;

public class MathClass {

	/*
	 * 
	 * this class returns the current day mean after inserting a new reading
	 * 
	 * */

	public static double dailyMean(double currReading, double currMean, int numOfreadings){


		return (currMean*numOfreadings+currReading)/(1+numOfreadings);
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
	
	public static double min(ArrayList<String> elements){
		double min=10000;
		double currEl=0;
		
		for(String elt:elements){
			
			if((currEl=Double.parseDouble(elt))<min)
				min=currEl;
			
		}
		
		
		return min;	
		
		
	}
	
	public static double max(ArrayList<String> elements){
		double max=0;
		double currEl=0;
		
		for(String elt:elements){
			
			if((currEl=Double.parseDouble(elt))>max)
				max=currEl;
			
		}
		
		return max;	
		
		
	}

}
