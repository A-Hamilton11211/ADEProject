import java.util.*;
import java.io.*;

public class JourneyList
{
	private TreeSet<Journey> journeyList;
	
    public JourneyList() {
		journeyList = new TreeSet<Journey>(new expenseComparator());
		BufferedReader buff2017 = null;
		BufferedReader destbuff = null;
		BufferedReader taxibuff = null;
		String data2017 [] = new String[3];
		String destdata [] = new String[2];
		String taxidata [] = new String[2];
		try {
			buff2017 = new BufferedReader(new FileReader("2017_Journeys.txt"));
			destbuff = new BufferedReader(new FileReader("destinations.txt"));
			taxibuff = new BufferedReader(new FileReader("taxi_details.txt"));
	    	String input2017 = buff2017.readLine();
	    	String destinput = destbuff.readLine();
	    	String taxiinput = taxibuff.readLine();
	    	while(input2017 != null && input2017.isEmpty() != true
	    			&& destinput != null && destinput.isEmpty() != true
	    			&& destinput != null && destinput.isEmpty() != true)
	    		{
	    		data2017 = input2017.split(",");
	    		destdata = destinput.split(";");
	    		taxidata = taxiinput.split(";");
	    		int passnum = Integer.parseInt(data2017[2]);
	    		String milesraw = destdata[1].replaceAll("miles", "");
	    		double miles = Double.parseDouble(milesraw.trim());
	    		Journey d = new Journey(data2017[0], taxidata[1], data2017[1], miles, passnum);
	    	    journeyList.add(d);
	            input2017 = buff2017.readLine();
	            destinput = destbuff.readLine();
	            taxiinput = taxibuff.readLine();
	    		}
	    } catch(FileNotFoundException e) {
	        	System.out.println(e.getMessage());
	            System.exit(1);
	    } catch (IOException e) {
	        	e.printStackTrace();
	            System.exit(1);        	
	    } catch (WrongPassException e) {
	    		System.out.println(e.getMessage());
	    		System.exit(1);
	    } catch (ImpossibleDistException e) {
	    		System.out.println(e.getMessage());
	    		System.exit(1);
	    } finally  {
        	try{
        		buff2017.close();
        		destbuff.close();
        		taxibuff.close();
        	}
        	catch (IOException ioe) {
        		//don't do anything
        	}
		
		}
	}
    
    public String printOutput() {
    	String fiveMost = "";
    	String fiveLeast = "";
    	JourneyList jlist = new JourneyList();
    	//just need to iterate over the expenseComparator sorted list of journeys to find
    	// the 5 most expensive journeys and the 5 least expensive journeys
    	return fiveMost;
    }
    
}