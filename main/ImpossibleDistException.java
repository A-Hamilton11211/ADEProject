package main;
@SuppressWarnings("serial")
public class ImpossibleDistException extends Exception {

	// This class implements an error that triggers when the distance of any journey is 0 or below, which is impossible
	public ImpossibleDistException(double invalidDist){
		super("Cannot have a journey equal to or below 0 miles");
	}
}
