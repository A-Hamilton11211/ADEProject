import java.io.*;
import java.util.*;

public class ValidDestReader 
{
	private ArrayList<ValidDestinations> entries;
	
	public ValidDestReader() {
			
			entries = new ArrayList<ValidDestinations>();
			BufferedReader buff = null;
			String data [] = new String[2];
			try {
				buff = new BufferedReader(new FileReader("destinations.txt"));
		    	String inputLine = buff.readLine();  //read first line
		    	while(inputLine != null && inputLine.isEmpty() != true){  
		    		//create staff object
		    		data = inputLine.split(";");
		    		String milesraw = data[1].replaceAll("miles", "");
		    		double miles = Double.parseDouble(milesraw.trim());
		    		ValidDestinations d = new ValidDestinations(data[0],miles);
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
		ValidDestReader test = new ValidDestReader();
		System.out.println(test.entries);
	}
}
