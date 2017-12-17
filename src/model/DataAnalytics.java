package model;
import java.util.Arrays;

import org.apache.commons.math3.stat.inference.TTest;

public class DataAnalytics {
/**
 * this student t test is a one sample
 * It compares the expected mean(Best) mean with the samples recorded
 * the null hypothesis is : " The samples is exactly of the same mean as the expected mean"
 * the sample will be passed to the function together with the Best mean.
 * Because tea quality is too sensitive to the change in chemical quantity slight variation
 * the significance level will be lowered to 3%
 * 
 * */
	



	private  TTest aTest = new TTest();


	// this function do a test to decide whether the parameters are in the right dimensions
	public  double ttestToBestMean(double sample[], double bestMean){

		return aTest.tTest(bestMean,sample);
	}


	public static void main(String args[]){


		//ttestToBestMean(new double[]{2,4,4,4,4,4,4,4,},0);



	}
}
