import java.io.*;
import java.util.*;

public class Journeys2016Reader 
{
	private ArrayList<Journeys2016> entries;
	
	public Journeys2016Reader() {
			
			entries = new ArrayList<Journeys2016>();
			BufferedReader buff = null;
			try {
				buff = new BufferedReader(new FileReader("2016_Journeys.txt"));
		    	String inputLine = buff.readLine();  //read first line
		    	while(inputLine != null && inputLine.isEmpty() != true){  
		    		//create staff object
		    		Journeys2016 d = new Journeys2016(inputLine);
		    		//add to list
		            entries.add(d);
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
	}
	
	public static void main(String arg[])
	{
		Journeys2016Reader test = new Journeys2016Reader();
		System.out.println(test.entries);
	}

}
