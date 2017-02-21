package src.main;
@SuppressWarnings("serial")
public class WrongPassException extends Exception {
	//This class implements an exception that limits the number of passengers to be between 1 to 6 within the journey class
	public WrongPassException(int badPass){
		super(badPass + " is either too many, or too few passengers");
	}
}
