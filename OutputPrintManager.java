import java.io.*;
import java.util.*;

public class OutputPrintManager {
		//manager class that creates the text files with required outputs
	
public static void main(String[] args) {
	
		Places testlist = new Places();
		testlist.Places2016Reader();
		testlist.Places2017Reader();
		testlist.SetupHelperSets();
		testlist.writePlacesToFile("OutputDestinationBoth.txt",testlist.compareDestinationsBoth());
		testlist.writePlacesToFile("OutputDestination2016.txt",testlist.compareDestinations2016());
		testlist.writePlacesToFile("OutputDestination2017.txt",testlist.compareDestinations2017());
		
		JourneyList test = new JourneyList();
		test.writeToFile("OutputJourneyList.txt");
		
		//placeholder for taxi printouts
	}
	
}
