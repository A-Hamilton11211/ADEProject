package src.main;
public class ValidDestinations {
	
	private String name;
	private double miles;
	
	// This class creates a basic valid destination class that has getter methods for distance and the name of the destination
	// as well as a simple constructor to make it
	public ValidDestinations(String name, double miles)
	{
		this.name = name;
		this.miles = miles;
	}
	
	public String getDest() {
		return name;
	}
	
	public double getDist() {
		return miles;
	}

}