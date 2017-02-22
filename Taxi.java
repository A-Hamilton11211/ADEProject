package src.main;
import java.io.*;
import java.util.*;

public class Taxi {
	
	private String regNum;
	private String driverName;
	
	public Taxi(String regNum, String driverName){
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
    	// Simple test to check if driver name is empty
    	if (driverName != "") {
         	this.driverName = driverName.trim();
        } else {
         	System.err.println("Driver Name is Empty");
        }
	}
	
	public ArrayList<Taxi> driverFinder() throws IOException, FileNotFoundException {  
		Scanner scanner = new Scanner(new File("taxi_details.txt"));
		ArrayList<Taxi> driverList = new ArrayList<Taxi>();	
		while(scanner.hasNext()) {
			String line = scanner.nextLine();
		    String elements[] = line.split(";");
		    Taxi currentDriver = new Taxi(elements[0],elements[1]);
		    driverList.add(currentDriver);
		 }
		scanner.close();
		return driverList;
	}
	
    public String getregNum() {
    	return regNum;
    }
    
    public String getdriverName( ) {
    	return driverName;
    }
    
    public static void main(String[] args) throws IOException, FileNotFoundException{
    	Taxi tester = new Taxi("AFR1","Pointless");
    	ArrayList<Taxi> driverList = tester.driverFinder();
    	for (int i = 0; i < driverList.size(); i++){
    		System.out.println(driverList.get(i).getregNum() + " ; " + driverList.get(i).getdriverName());
    	}
    		
    	
    }
 

}
