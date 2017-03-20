import java.util.logging.Logger;

public class SingletonLogInstance {

	//create logger
	private final static Logger myLogger = Logger.getLogger(SingletonLogTest.class.getName());
	
	public SingletonLogInstance() {}
	
	
	public static void main (String [] args) {
		
		SingletonLogTest.getInstance();
		
		// call logger method
		SingletonLogTest.setupLogger();
		myLogger.info("my log");
	}

}
