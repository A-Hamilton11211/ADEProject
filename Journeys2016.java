import java.io.*;
import java.util.*;

public class Journeys2016 {
	
	private String destination;
	
	public Journeys2016(String destination)
	{
		this.destination = destination;
	}
	
	public String getDest() {
		return destination;
	}
	
	public ArrayList<Journeys2016> readFile() {
		
		ArrayList<Journeys2016> entries = new ArrayList<Journeys2016>();
		BufferedReader buff = null;
		try {
			buff = new BufferedReader(new FileReader("2016_Journeys.txt"));
	    	String inputLine = buff.readLine();  //read first line
	    	while(inputLine != null){  
	    		//create staff object
	    		Journeys2016 j = new Journeys2016(inputLine);
	    		//add to list
	            entries.add(j);
	            //read next line
	            inputLine = buff.readLine();
	    	}
	    }
		catch(FileNotFoundException e) {
	        	System.out.println(e.getMessage());
	            System.exit(1);
	        }
	    catch (IOException e) {
	        	e.printStackTrace();
	            System.exit(1);        	
	        }
		finally  {
        	try{
        		buff.close();
        	}
        	catch (IOException ioe) {
        		//don't do anything
        	}
		
		}
		return entries;
	}

}