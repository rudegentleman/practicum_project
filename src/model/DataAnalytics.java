package model;
import org.apache.commons.math3.stat.inference.TTest;

public class DataAnalytics {


	private static TTest atest = new TTest();


	// this function do a test to decide whether the parameters are in the right dimensions
	public static void ttestToBestMean(double sample[], double bestMean){


		System.out.println((atest.tTest(bestMean,sample)));
	}


	public static void main(String args[]){


		ttestToBestMean(new double[]{2,4,4,4,4,4,4,4,},0);



	}
}
