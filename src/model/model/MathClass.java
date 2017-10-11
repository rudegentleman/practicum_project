package model;

public class MathClass {

	/*
	 * 
	 * this class returns the current day mean after inserting a new reading
	 * 
	 * */

	public static double dailyMean(double currReading, double currMean, int numOfreadings){


		return currMean+(currReading/(1+numOfreadings));
	}

}
