public class WrongPassException extends Exception {

	public WrongPassException(int badPass){
		super(badPass + " is either too many, or too few passengers");
	}
}
