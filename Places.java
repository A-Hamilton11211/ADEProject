import java.io.*;
import java.util.*;

public class Places {
	//class including info about valid destinations
	//includes an arraylist and two hashsets
	//checks which destinations were visited in which year
	private ArrayList<AbstractJourney> PlacesList;
	private Set <String> helperSet2017= new HashSet<String>();
	private Set <String> helperSet2016= new HashSet<String>();
	
	public Places() {
		PlacesList = new ArrayList<AbstractJourney> ();
	}
	
	public String compareDestinations2016(){
		String report = "PLACES VISITED IN 2016 ONLY: \n";
		for (String s:helperSet2016) {
			if (helperSet2017.contains(s)==false){
				report+=s+"\n";	
			}
    	}
		return report;
	}
	
	public String compareDestinations2017(){
		String report = "PLACES VISITED IN 2017 ONLY: \n";
		for (String s:helperSet2017) {
			if (helperSet2016.contains(s)==false){
				report+=s+"\n";	
			}
    	}
		return report;
	}
	public String testPrinter(){
		String report="";
		for (String s:helperSet2017){report+=s+"\n";}
		for (String s:helperSet2016){report+=s+"\n";}
		return report;
	}
	public void SetupHelperSets(){
		for (AbstractJourney s:PlacesList) {
			if (s instanceof Journeys2017){helperSet2017.add(s.getDest());}
			if (s instanceof Journeys2016){helperSet2016.add(s.getDest());}
    	}
	}
	public String compareDestinationsBoth(){
		String report = "PLACES VISITED IN BOTH YEARS: \n";
		for (String s:helperSet2017) {
			if (helperSet2016.contains(s)==true){
				report+=s+"\n";	
			}
    	}
		return report;
	}
	
	public void add(AbstractJourney s) {
		PlacesList.add(s);
	}
	
	public void Places2016Reader() {
	//read 2016 destinations from a text file	
		BufferedReader buff = null;
		try {
			buff = new BufferedReader(new FileReader("2016_Journeys.txt"));
	    	String inputLine = buff.readLine();
	    	while(inputLine != null && inputLine.isEmpty() != true){  
	    		Journeys2016 d = new Journeys2016(inputLine);
	    		PlacesList.add(d);
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
	
	public void Places2017Reader() {
	//read 2017 destinations from a text file	
		BufferedReader buff = null;
		try {
			buff = new BufferedReader(new FileReader("2017_Journeys.txt"));
	    	String inputLine = buff.readLine();
	    	while(inputLine != null && inputLine.isEmpty() != true){
	    		String parts [] = inputLine.split(",");
	    		Journeys2017 d = new Journeys2017(parts[1]);
	    		PlacesList.add(d);
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
	
	public  void writePlacesToFile(String filename, String report) {
	//method to write a report string to a file
		 FileWriter fw;
		 try {
		    fw = new FileWriter(filename);
		    fw.write("The report\n");
		    fw.write(report);
		 	fw.close();
		 }
		 //message and stop if file not found
		 catch (FileNotFoundException fnf){
			 System.out.println(filename + " not found ");
			 System.exit(0);
		 }
		 //stack trace here because we don't expect to come here
		 catch (IOException ioe){
		    ioe.printStackTrace();
		    System.exit(1);
		 }
	}
	
	public static void main(String[] args) {
		//method tests
		Places testlist = new Places();
		testlist.Places2016Reader();
		testlist.Places2017Reader();
		testlist.SetupHelperSets();
		System.out.println(testlist.compareDestinations2016());
		System.out.println(testlist.compareDestinations2017());
	}
		
}
