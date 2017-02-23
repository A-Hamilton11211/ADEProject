import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

public class Taxi {
	
	// Stores the driver details from the "taxi_details.txt" file
	TreeMap<String,String> taxis = new TreeMap<>();
	// Stores the destination details from the "2017_journeys.txt" file
	LinkedList<Journey> journeys = new LinkedList<>();
	
    // Creates a TreeMap object to hold the Driver - Visited Places pairs
	TreeMap<String, TreeSet<String>>  namesTry = new TreeMap<String, TreeSet<String>>();
    // Creates a TreeSet object to hold the  visited places
	TreeSet<String>  destinationsTry = new TreeSet<String>();
	
	
	public Taxi() {
		
		BufferedReader brName = null;
		BufferedReader brDest = null;
		
		try {
			// Have the buffered readers start to read the text files
			brName = new BufferedReader(new FileReader("taxi_details.txt"));
			String line = brName.readLine();

			brDest = new BufferedReader(new FileReader("2017_journeys.txt"));
			String lines = brDest.readLine();

			
			while (line != null){
			
				String taxiReg = line.split(";")[0];
				String driverName = line.split(";")[1];
				taxis.put(taxiReg, driverName);
				//read next line
				line = brName.readLine();
			}
			
			while(lines != null){
				String journeyReg = lines.split(",")[0];
				String visitedDest = lines.split(",")[1];
				Journey j = new Journey();
				j.setDestName(visitedDest);
				j.setDriverName(journeyReg);
				journeys.add(j);
				//read next line
				lines = brDest.readLine();
			}
			
			for(Entry<String,String> t : taxis.entrySet()){
				destinationsTry = new TreeSet<>();
				for(Journey j : journeys){
					if(j.getDriver().equals(t.getKey())){
						destinationsTry.add(j.getDest());
					}
				}
				namesTry.put(t.getValue(), destinationsTry);
			}
			
/*			for(Entry<String,TreeSet<String>> n : namesTry.entrySet()){
				//Print only driver names with associated destinations
				if (n.getValue().size() != 0)
					System.out.println(n.getKey());
				
				//Print destination name
				for(String s : n.getValue()){
					System.out.println(" " + s);	
				}
				System.out.println();	
			}*/

	     // Catch blocks exist here to catch every potential error
	    } catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	            
	         /* Finally block exists to close the files and handle any potential exceptions 
	            that can happen as a result */
	        } finally {
	            try {
						brName.close();
						brDest.close();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        } 
	}

    /* This void method writes to an output file specified by the String path in our desired 
	output format */
	public void sendToFile(String path) throws IOException {
		
    	FileWriter write = new FileWriter(path, false);
    	PrintWriter print = new PrintWriter(write);
    	
    	
		for(Entry<String,TreeSet<String>> n : namesTry.entrySet()){
			//Print only driver names with associated destinations
			if (n.getValue().size() != 0)
				print.printf("%s" + "%n", n.getKey());
			
			//Print destination name
			for(String s : n.getValue()){
				print.printf("%s" + "%n", " " + s);	
			}
			print.println();	
		}
		print.close();
	}
	
	//main method to test code
	public static void main (String [] args) throws IOException{
		Taxi reader = new Taxi();
		reader.sendToFile("taxi.txt"); 
	}
}
