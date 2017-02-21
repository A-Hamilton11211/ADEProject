package src.main;
public class Journey implements Comparable<Journey>
{
	private String regNum;
    private String driverName;
    private String destName;
    private double dist;
    private int passNum;
    private double cost;

    /**
     * Set up the journey class
     * @param regNum The registration number
     * @param driverName The name of the driver
     * @param destName The destination name
     * @param dist The distance to the destination
     * @param passNum The number of passengers
     * @param cost The cost of the journey (computed during instance creation)
     */
    public Journey(String regNum, String driverName, String destName, double dist, int passNum) throws ImpossibleDistException, WrongPassException
    {   
    	// Use a try/catch block to test the format of regNum to check that it matches our format
    	try {
    		// Creates regTest which takes the substring of between 4-regNum.length() to test if that substring is able to be parsed as an int.
    		int regTest = Integer.parseInt(regNum.substring(3, regNum.length()));
    		// Tests if the first three chars of regNum are equal to "AFR" and that regTest is nonzero.  If not, spits an error that AFR must be the prefix
    		if (regNum.substring(0,3).contains("AFR") && regTest != 0){
    			this.regNum =regNum.trim();
    		} else {
    			System.err.println("Improper Registration Format: AFR must be the prefix and must have a postfix above 0");
    		}
    	// If try/catch block spits a NumberFormatException, which happens when regTest doesn't have exclusively numbers, catch block goes into effect
    	} catch (NumberFormatException e){
    		System.err.println("Improper Registration Format: Not exclusively numbers in " + regNum.substring(3, regNum.length()));
    	}
        if (driverName != "") {
        	this.driverName = driverName.trim();
        } else {
        	System.err.println("Driver Name is Empty");
        }
        if (destName != ""){
        	this.destName = destName.trim();
        } else {
        	System.err.println("Destination Name is Empty");
        }
        // if/else block ensures that distance is above 0, or throws an impossible distance exception
        if (dist > 0) {
        	this.dist = dist;
        } else {
        	throw new ImpossibleDistException(dist);
        }
        // if/else block ensures that passNum is between 1-6 and changes how cost is calculated depending on if the number of passengers is between
        // 1-3 or 4-6.  This also ensures that cost is always consistent as cost is not provided by the user and just calculated on the basis of a formula
        // within the journey constructor
        if (passNum <= 3 && passNum > 0){
        	this.passNum = passNum;
        	this.cost = 1.80 + (1.00 * dist);
        } else if (passNum > 3 && passNum <= 6){
        	this.passNum = passNum;
        	this.cost = 1.80 + (1.50 * dist);
        } else {
        	throw new WrongPassException(passNum);
        }
       
    }
    
    /**
     * @return The Registration Number.
     */    
    public String getReg() {
    	return regNum;
    }
    
    /**
     * @return The Driver name.
     */
    public String getDriver()
    {
        return driverName;
    }
    
    /**
     * @return The Destination name.
     */
    public String getDest()
    {
        return destName;
    }
    
    /**
     * @return The Distance
     */
    public double getDist()
    {
        return dist;
    }
    
    /**
     * @return The Passenger Number
     */
    public int getPass()
    {
        return passNum;
    }
    /**
     * @return The cost
     */
    public double getCost()
    {
    	return cost;
    }
    

    
    /**
     * Test for content equality between two objects.
     * @param other The object to compare to this one.
     * @return true if the argument object has same id
     */
    public boolean equals(Object other)
    {
        // equals() method doesn't use all traits in journey, only the ones that the user provides (e.g. not cost), as those are the only traits
    	// needed to uniquely identify each instance.
    	if(other instanceof Journey) {
            Journey otherJourney = (Journey) other;
            return regNum.equals(otherJourney.getReg()) &&
            		driverName.equals(otherJourney.getDriver()) &&
            		destName.equals(otherJourney.getDest()) &&
            		passNum == (otherJourney.getPass());
        }
        else {
            return false;
        }
    }

    /**
     * Compare this Journey object against another, for the purpose
     * of sorting. The fields are compared by id.
     * @param otherDetails The other journey to be compared against.
     * @return a negative integer if this id comes before the parameter's id,
     *         zero if they are equal and a positive integer if this
     *         comes after the other.
     */

    public int compareTo(Journey otherDetails)
    {
        int comp1 = Integer.parseInt(regNum.substring(3));
        int comp2 = Integer.parseInt(otherDetails.getReg().substring(3));
    	if (comp1 == comp2){
    		return 0;
    	} else if (comp1 > comp2) {
    		return -1;
    	} else {
    		return 1;
    	}
    }    

    /**
     * @return A  string containing all details.
     * Note: This method have been extensively tested at the console level and does print exactly how we want
     */
    
    public String toString()
    {
        return String.format("%s ", regNum ) + String.format(" %s ", driverName) +
                 String.format(" %s ", destName ) + String.format(" %.2f miles ", dist ) +
                 String.format(" %d ", passNum ) + String.format(" %.2f", cost);
    }
    
    /**
     * @return A  string containing the details necessary for output in the JourneyList class.
     * Note: This method have been extensively tested at the console level and does print exactly how we want
     */
    public String jlistString()
    {
    	return String.format("%s ", regNum ) +  String.format(" %s ", destName ) + String.format(" %.2f miles ", dist ) +
                String.format(" %d Passengers  ", passNum ) + String.format("  £%.2f", cost);
    }

}
