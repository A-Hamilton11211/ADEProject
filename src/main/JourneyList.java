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
     */ 
    public TreeSet<Journey> journeyListCreator() {
		// Create the new TreeSet<Journey>(new expenseComparator())
    	TreeSet<Journey> journeyList = new TreeSet<Journey>(new expenseComparator());
		// Initialize three buffered readers that read from three different files
    	BufferedReader buff2017 = null;
		BufferedReader destbuff = null;
		BufferedReader taxibuff = null;
		// Initialize three different lists of strings for splitting the elements of each text file based on some string char
		String data2017 [] = new String[3];
		String destdata [] = new String[2];
		String taxidata [] = new String[2];
		// A try/catch block exists here to catch all potential errors in the reading of files and creation of Journey objects
		try {
			// Have the buffered readers start to read the txt files
			buff2017 = new BufferedReader(new FileReader("2017_Journeys.txt"));
			destbuff = new BufferedReader(new FileReader("destinations.txt"));
			taxibuff = new BufferedReader(new FileReader("taxi_details.txt"));
	    	String input2017 = buff2017.readLine();
	    	String destinput = destbuff.readLine();
	    	String taxiinput = taxibuff.readLine();
	    	// Makes a while statement that operates as long as the readlines() methods produce non-empty, non-null lines
	    	// (i.e. the while loop operates for as long as there's something for the buffered readers to read)
	    	while(input2017 != null && input2017.isEmpty() != true
	    			&& destinput != null && destinput.isEmpty() != true
	    			&& destinput != null && destinput.isEmpty() != true)
	    		{
	    		// The input lines are split on the basis of certain characters that the text files use to split up the fields within them
	    		data2017 = input2017.split(",");
	    		destdata = destinput.split(";");
	    		taxidata = taxiinput.split(";");
	    		// Parses the number of passengers to be a number so that we can pass it to a new Journey object
	    		int passnum = Integer.parseInt(data2017[2]);
	    		// Removes the "miles" word from the distance field in the destinations.txt file and parses distance as a double
	    		// so it can be passed to a new Journey object
	    		String milesraw = destdata[1].replaceAll("miles", "");
	    		double miles = Double.parseDouble(milesraw.trim());
	    		// Creates a new Journey object and adds it to the TreeSet
	    		Journey d = new Journey(data2017[0], taxidata[1], data2017[1], miles, passnum);
	    	    journeyList.add(d);
	            // Reads the next line
	    	    input2017 = buff2017.readLine();
	            destinput = destbuff.readLine();
	            taxiinput = taxibuff.readLine();
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
        		destbuff.close();
        		taxibuff.close();
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
    public void writeToFile(String path) throws IOException {
    	// Creates a TreeSet<Journey> using the journeyListCreator() method
    	TreeSet<Journey> jlist = journeyListCreator();
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
    		print_line.printf("%s" + "%n", current.jlistString());
    		jlist.remove(current);
    	}
    	print_line.println();
    	print_line.printf("%s" + "%n", "TOP 5 LEAST PROFITABLE JOURNEYS OF 2017:");
    	// Takes the last (lowest cost) entry in TreeSet and passes it to the printwriter, which prints it.
		// After this is done the last entry is removed, which iterates us through the TreeSet backwards
    	for (int i = 0; i < 5; i++){
    		Journey current = jlist.last();
    		print_line.printf("%s" + "%n", current.jlistString());
    		jlist.remove(current);
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
    	test.writeToFile("/home/ajh/tester.txt");
    }
    
}