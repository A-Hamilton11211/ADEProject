import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class SingletonLogTest {
	
	//create logger
	private final static Logger myLogger = Logger.getLogger(SingletonLogTest.class.getName());
	
	// create the only instance of the class. 
	private static SingletonLogTest instance = null;
	
	// private Constructor to prevent any other class from instantiating.
	private SingletonLogTest() {
		System.out.println("Starting... ");
	}
	
		/* Public static method that returns the instance of the class, this is the 
		 * global access point for outer world to get the instance of the singleton 
		 * class. (double - checked locking)  
		 */
		public static SingletonLogTest getInstance() {
			if (instance  == null){  //check
				// synchronized is used here to prevent it from being slow
				synchronized (SingletonLogTest.class){
					
					if (instance  == null){  //check again
						
						SingletonLogTest.setupLogger();
						// Lazy instantiation - instance is only created when needed
						instance = new SingletonLogTest();
					}
				}
			}
			return instance;
		}
		
		
		
		public static void setupLogger(){
			// gets rid of any root handlers
			LogManager.getLogManager().reset();
			myLogger.setLevel(Level.ALL);
			
			 
			try {
				FileHandler fh = new FileHandler ("LogExample.txt", true);
				fh.setFormatter(new CustomFormatter());
				fh.setLevel(Level.FINE);
				myLogger.addHandler(fh);
				
			} catch (SecurityException | IOException e) {
				System.err.println("ERROR: Could not write to log file");
			}
		}	

}
