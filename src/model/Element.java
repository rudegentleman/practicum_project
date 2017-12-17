package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.websocket.MessageHandler.Whole;

public class Element  implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5984149669996049833L;
	/**
	 * 
	 * 
	 * */
	private String elementName;
	private double currMeasure;
	private double meanSofar;
	private double error;
	private String warninng;

	public double getCurrMeasure() {
		return currMeasure;
	}
	public void setCurrMeasure(double currMeasure) {
		this.currMeasure = currMeasure;
	}
	public double getMeanSofar() {
		return meanSofar;
	}
	public void setMeanSofar(double meanSofar) {
		this.meanSofar = meanSofar;
	}
	public double getError() {
		return error;
	}
	//
	public void setError(double bestMean) {
		if(bestMean!=Double.NaN){
			error = this.meanSofar - bestMean;
			
		}
		else{
			System.out.println("NaN");
		}
	}
	public String getElementName() {
		return elementName;
	}
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	public String getWarninng() {

		return warninng;
	}


	//this function bases its warnings on the p value obtained after a t test
	public void setWarning(double bestMean, ArrayList<Double> sample_) {
		DataAnalytics analytics= new DataAnalytics();
		double [] sample = new double[sample_.size()];
		
		int i=0;
		for(Double dataPoint:sample_  ){
			
			sample[i++]=dataPoint;
			
		}
		
		
		
		
		
		
		double pValue = analytics.ttestToBestMean(sample, bestMean);
		  System.out.println("jjj"+pValue);


		       // we use significance value of 5%
				if(pValue>0.05)
					this.warninng= this.elementName+" is in a good measure";
				else if(pValue<0.05&& pValue>0.03)
					this.warninng= this.elementName+" difference is acceptable";
				else
					if(error>0)
						this.warninng= this.elementName+" Measure Too high ";
					else if(error==0)
						this.warninng= this.elementName+" Good Measure ";

						
					else 
						this.warninng= this.elementName+" Measure Too low ";


	}
	/**
	 * 
	 * this constructor constructs an element  all attributes of the class. it will be displayed in a table to be displayed
	 * 
	 * 
	 * */
	public Element(String elementName,double curr, double mean,double bestMean,ArrayList<Double> wholsample){
		System.out.println(wholsample);
		setElementName(elementName);
		setCurrMeasure(curr);
		setMeanSofar(mean);
		setError(bestMean);
		
		
		
		
		
		
		setWarning(bestMean,wholsample);

	}



	public Element() {
		// TODO Auto-generated constructor stub
	}
	




}
