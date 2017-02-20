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
    	try {
    		int regTest = Integer.parseInt(regNum.substring(3, regNum.length()));
    		if (regNum.substring(0,3).contains("AFR") && regTest != 0){
    			this.regNum =regNum.trim();
    		} else {
    			System.err.println("Improper Registration Format");
    		}
    	} catch (NumberFormatException e){
    		System.err.println("Improper Registration Format: No Numbers");
    	}
        this.driverName = driverName.trim();
        this.destName = destName.trim();
        if (dist > 0) {
        	this.dist = dist;
        } else {
        	throw new ImpossibleDistException(dist);
        }
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
     * @param otherDetails The details to be compared against.
     * @return a negative integer if this id comes before the parameter's id,
     *         zero if they are equal and a positive integer if this
     *         comes after the other.
     */

    public int compareTo(Journey otherDetails)
    {
        return regNum.compareTo(otherDetails.getReg());
    }    

    /**
     * @return A  string containing all details.
     */
    
    public String toString()
    {
        return String.format("%s ", regNum ) + String.format(" %s ", driverName) +
                 String.format(" %s ", destName ) + String.format(" %.2f miles ", dist ) +
                 String.format(" %d ", passNum ) + String.format(" %.2f", cost);
    }
    
    public String jlistString()
    {
    	return String.format("%s ", regNum ) +  String.format(" %s ", destName ) + String.format(" %.2f miles ", dist ) +
                String.format(" %d Passengers  ", passNum ) + String.format("  £%.2f", cost);
    }

}
