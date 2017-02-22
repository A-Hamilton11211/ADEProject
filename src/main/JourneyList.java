package src.main;
import java.util.*;
import java.io.*;

public class JourneyList
{
	// This class lacks a constructor as it's only purpose is to output the five most and five least expensive
	// Journeys to a text file
	/**
     * @return TreeSet<Journey>(expenseComparator)
     * This method creates a TreeSet of Journeys sorted based on the expenseComparator().
	 * @throws IOException 
	 * @throws FileNotFoundException 
     */ 
    public TreeSet<Journey> journeyListCreator(String compType) throws FileNotFoundException, IOException {
    	// Creates an ArrayList of ValidDestinations objects for later testing
    	ValidDestReader initalDistDestTester = new ValidDestReader();
    	ArrayList<ValidDestinations> distDestTester = initalDistDestTester.ValidDestList();
    	// Creates an ArrayList of Taxi objects for later testing
    	Taxi taxiTest = new Taxi("AFR1", "Doesntmatter");
    	ArrayList<Taxi> driverTest = taxiTest.driverFinder();
    	// Create the new TreeSet<Journey>(new expenseComparator())
    	TreeSet<Journey> journeyList = new TreeSet<Journey>(new uniqueComp());
    	if (compType.equals("e")){
    		journeyList = new TreeSet<Journey>(new expenseComparator());
    	}
		// Initialize three buffered readers that read from three different files
    	BufferedReader buff2017 = null;
		// Initialize three different lists of strings for splitting the elements of each text file based on some string char
		String data2017 [] = new String[3];
		// A try/catch block exists here to catch all potential errors in the reading of files and creation of Journey objects
		try {
			// Have the buffered readers start to read the txt files
			buff2017 = new BufferedReader(new FileReader("2017_Journeys.txt"));
	    	String input2017 = buff2017.readLine();
	    	// Makes a while statement that operates as long as the readlines() methods produce non-empty, non-null lines
	    	// (i.e. the while loop operates for as long as there's something for the buffered readers to read)
	    	while(input2017 != null && input2017.isEmpty() != true)
	    	{
	    		// The input lines are split on the basis of certain characters that the text files use to split up the fields within them
	    		data2017 = input2017.split(",");
	    		// Parses the number of passengers to be a number so that we can pass it to a new Journey object
	    		int passnum = Integer.parseInt(data2017[2]);
	    		// Removes the "miles" word from the distance field in the destinations.txt file and parses distance as a double
	    		// so it can be passed to a new Journey object
	    		double dist = 0.0;
	    		// Using the ArrayList<ValidDestinations> object we created before, tests to see if the destination
	    		// of the data match up to our valid destination list, and then provides the distance for that destination using
	    		// ValidDestinations.getDist() method
	    		for (int i = 0; i < distDestTester.size(); i++){
	    			boolean destTest = false;
	    			destTest =	(distDestTester.get(i).getDest().toUpperCase()).equals(data2017[1].toUpperCase());
	    			if (destTest == true) {
	    				dist = distDestTester.get(i).getDist();
	    			}
	    		}
	    		String driver = "";
	    		for (int j = 0; j < driverTest.size(); j++){
	    			boolean driverExists = false;
	    			driverExists = (driverTest.get(j).getregNum().equals(data2017[0]));
	    			if (driverExists == true) {
	    				driver = driverTest.get(j).getdriverName();
	    			}
	    		}
	    		// Creates a new Journey object and adds it to the TreeSet
	    		Journey d = new Journey(data2017[0], driver , data2017[1], dist, passnum);
	    	    journeyList.add(d);
	            // Reads the next line
	    	    input2017 = buff2017.readLine();
	    	}
	    // Catch blocks exist here to catch every potential error that could occur in the Journey creation process or in 
	    // the reading process
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
	    // Finally block exists to close the files and handle any potential exceptions that can happen as a result
	    } finally  {
        	try{
        		buff2017.close();
        	}
        	catch (IOException ioe) {
        		//don't do anything
        	}
		
		}
		// The new JourneyList of type TreeSet<Journey>(expenseComparator) is returned
		return journeyList;
	}
    /**
     * This void method writes to an output file specified by the String path in our desired output format.
     * Note that as a result of using this method the JourneyList used becomes damaged (it has 10 entries removed),
     * as well as overwriting anything that the output txt file contained previously.
     */ 
    public void writeToFileTopLeast(String path) throws IOException {
    	// Creates a TreeSet<Journey> using the journeyListCreator() method
    	TreeSet<Journey> jlist = journeyListCreator("e");
    	// Initializes a filewriter and a printwriter to write lines to a file
    	FileWriter write = new FileWriter(path, false);
    	PrintWriter print_line = new PrintWriter(write);
    	// Always prints this line first, then uses the for loop to iterate through the TreeSet and list the 5 most
    	// profitable journeys under it
    	print_line.printf("%s" + "%n", "TOP 5 MOST PROFITABLE JOURNEYS OF 2017:");
    	for (int i = 0; i < 5; i++){
    		// Takes the first (highest cost) entry in TreeSet and passes it to the printwriter, which prints it.
    		// After this is done the first entry is removed, which iterates us through the TreeSet
    		Journey current = jlist.first();
    		print_line.printf("%s" + "%n", current.toString());
    		jlist.remove(current);
    	}
    	print_line.println();
    	print_line.printf("%s" + "%n", "TOP 5 LEAST PROFITABLE JOURNEYS OF 2017:");
    	// Takes the last (lowest cost) entry in TreeSet and passes it to the printwriter, which prints it.
		// After this is done the last entry is removed, which iterates us through the TreeSet backwards
    	for (int v = 0; v < 5; v++){
    		Journey current = jlist.last();
    		print_line.printf("%s" + "%n", current.toString());
    		jlist.remove(current);
    	}
    	// Closes the printwriter method when it's task is done
    	print_line.close();
    	
    }
    
    public void writeToFileDriversVisit(String path) throws IOException {
    	// Creates a TreeSet<Journey> using the journeyListCreator() method
    	TreeSet<Journey> jlist = journeyListCreator("wah");
    	// Initializes a filewriter and a printwriter to write lines to a file
    	FileWriter write = new FileWriter(path, false);
    	PrintWriter print_line = new PrintWriter(write);
    	// Creates a TreeMap object to hold the Driver - Visited Places pairs
    	TreeMap<String,TreeSet<String>> fulloutput = new TreeMap<String, TreeSet<String>>();
    	while(jlist.isEmpty() != true){
    		// Iterates through one jlist, getting the current driver at the head of the list
    		TreeSet<String> destValues = new TreeSet<String>();
    		String currentDriver = jlist.first().getDriver();
    		// Creates another TreeSet<Journey> object to iterate through
    		TreeSet<Journey> jlist2 = journeyListCreator("wahwah");
    		while (jlist2.isEmpty() != true){
    			// This loop checks if certain journeys have the same driver as currentDriver.  If they do, it adds those destinations
    			// into the TreeSet<String> object of visited destinations and removes the first object
    			if (currentDriver.equals(jlist2.first().getDriver())){
    				destValues.add(jlist2.first().getDest());
    				jlist2.remove(jlist2.first());
    			} else {
    				// Otherwise, removes the first object
    				jlist2.remove(jlist2.first());
    			}
    		}
    		// After the key and values have been found, places it in our output object 
    		fulloutput.put(currentDriver, destValues);
    		// Deletes the first item so jlist can be iterated through
    		jlist.remove(jlist.first());
        }
    	while(fulloutput.isEmpty() != true){
    		// Prints the first key (the driver's name)
    		print_line.println(fulloutput.firstKey() + ":");
    		// Gets the values associated through the first key
    		TreeSet<String> valueSet = fulloutput.get(fulloutput.firstKey());
    		while (valueSet.isEmpty() != true){
    			// Iterates through the values of the first key by finding the first then deleting it, printing each one
    			print_line.println(" "+valueSet.first());
    			valueSet.remove(valueSet.first());
    		}
    		// Removes the first key so the while loop can iterate to the next item
    		fulloutput.remove(fulloutput.firstKey());
    	}
    	// Closes the printwriter method when it's task is done
    	print_line.close();
    }
    /**
     * This is just a small main method that tests the effecacy of our code.  Not actually intended to be run by itself
     */ 
    public static void main(String[] args) throws IOException
    {
    	JourneyList test = new JourneyList();
    	test.writeToFileTopLeast("/home/ajh/tester.txt");
    	test.writeToFileDriversVisit("/home/ajh/tester2.txt");
    }
    
}