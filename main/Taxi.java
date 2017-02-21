package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Taxi {
	
	static String regNum;
	static String driverName;
	
	public static void main(String[] args) throws IOException, FileNotFoundException {  
		
		Scanner scanner = new Scanner(new File("taxi_details.txt"));
			
		while(scanner.hasNext()) {
			String line = scanner.nextLine();
		    String elements[] = line.split(";");
	
		    regNum = elements [0];
		    driverName = elements[1];
	
		      System.out.println(regNum);  
		      System.out.println(driverName);
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
    
    public String getdriverName( ) {
    	return driverName;
    }
  
    public String setdriverName() {
        return driverName;
    }
    


}
