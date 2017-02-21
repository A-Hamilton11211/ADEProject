import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


	public class Journeys_2017 {
		
		static String regNum;
		static String destinationName;
		static String passengerNum;

		
		public static void main(String[] args) throws IOException, FileNotFoundException {  
			
			Scanner scanner = new Scanner(new File("2017_journeys.txt"));
			
			while(scanner.hasNext()) {
				String line = scanner.nextLine();
			    String elements[] = line.split(",");
		
			    regNum = elements [0];
			    destinationName = elements[1];
			    passengerNum = elements[2];
			    
			      System.out.println(regNum);  
			      System.out.println(destinationName);
			      System.out.println(passengerNum);
			      System.out.println();
			 }
				scanner.close();
		}
		
	    public String getregNum() {
	    	return regNum;
	    }
	  
	    public String setregNum() {
	        return regNum;
	    }
	    
	    public String getdestinationName( ) {
	    	return destinationName;
	    }
	  
	    public String setdestinationName() {
	        return destinationName;
	    }
	    
	    public String getpassengerNum( ) {
	    	return passengerNum;
	    }
	  
	    public String setpassengerNum() {
	        return passengerNum;
	    }

}
	
