package main;
import java.io.*;
import java.util.*;

public class Journeys2016Reader 
{
	private ArrayList<Journeys2016> entries;
	/**
     * This constructor creates an ArrayList of Journeys2016 objects and populates it with the entries present in destinations.txt
     */ 
	public Journeys2016Reader() {
			// Creates a new ArrayList of Journeys2016 objects and a new buffered reader
			entries = new ArrayList<Journeys2016>();
			BufferedReader buff = null;
			try {
				// Buffered reader reads the lines the 2016_Journeys.txt file 
				buff = new BufferedReader(new FileReader("2016_Journeys.txt"));
		    	String inputLine = buff.readLine();  //read first line
		    	// The buffered reader continues to read the file until the file ends
		    	while(inputLine != null && inputLine.isEmpty() != true){  
		    		// Creates a Journeys2016 object from the 2016_Journeys.txt file
		    		Journeys2016 d = new Journeys2016(inputLine);
		    		// Add to the ArrayList
		            entries.add(d);
		            // Read the next line
		            inputLine = buff.readLine();
		    	}
		    } // Catches any errors that come from not being able to read the file
			catch(FileNotFoundException e) {
		        	System.out.println(e.getMessage());
		            System.exit(1);
		        }
		    catch (IOException e) {
		        	e.printStackTrace();
		            System.exit(1);        	
		        }
			// Tries to close the file
			finally  {
	        	try{
	        		buff.close();
	        	}
	        	catch (IOException ioe) {
	        		//don't do anything
	        	}
			
			}
	}
	
	/**
     * This main method tests the Journeys2016Reader and is not meant for usage in the final product
     */
	public static void main(String arg[])
	{
		Journeys2016Reader test = new Journeys2016Reader();
		System.out.println(test.entries);
	}

}
