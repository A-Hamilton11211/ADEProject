public class Journey implements Comparable<Journey>
{
	private String regNum;
    private String driverName;
    private String destName;
    private int dist;
    private int passNum;

    /**
     * Set up the contact details. All details are trimmed to remove
     * trailing white space.
     * @param name The name.
     * @param hoursWorked The hours worked
     */
    public Journey(String regNum, String driverName, String destName, int dist, int passNum)
    {   
        this.regNum =regNum.trim();
        this.driverName = driverName.trim();
        this.destName = destName.trim();
        this.dist = dist;
        this.passNum = passNum;
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
    public int getDist()
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
            		dist == (otherJourney.getDist()) &&
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
        return String.format("%-5s", regNum ) + String.format("%-20s", driverName) +
                 String.format("%5s", destName ) + String.format("%10d", dist ) +
                 String.format("%15s", passNum );
    }

}
