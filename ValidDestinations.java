public class ValidDestinations {
	
	private String name;
	private double miles;
	
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