package model;
import org.apache.commons.math3.stat.inference.TTest;
public class Element {
	
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
		if(bestMean!=Double.NaN)
			error = this.meanSofar - bestMean;
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
	
	
	//
	public void setWarninng() {
		  if(error<0)
			  this.warninng = this.elementName+" is Higher than the best measure";
		  else if (error>0)
			  this.warninng= this.elementName+" is lower than the best measure";
		  else
			  this.warninng= this.elementName+" is in a good measure";

	}
	 
	
	
	/**
	 * 
	 * this constructor constructs an element  all attributes of the class. it will be displayed in a table to be displayed
	 * 
	 * 
	 * */
	public Element(String elementName,double curr, double mean,double bestMean){
		setElementName(elementName);
		setCurrMeasure(curr);
		setMeanSofar(mean);
		setError(bestMean);
		setWarninng();
		
	}
	
	
	
	
	
	
	

}
