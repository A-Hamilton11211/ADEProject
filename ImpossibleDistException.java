public class ImpossibleDistException extends Exception {

	public ImpossibleDistException(double invalidDist){
		super("Cannot have a journey equal to or below 0 miles");
	}
}
