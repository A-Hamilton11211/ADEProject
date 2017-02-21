package testing;
import static org.junit.Assert.*;

import org.junit.Test;

import main.ValidDestinations;

public class ValidDestinationsTest {

	@Test // A tester for the ValidDestinations getter methods
	public void getTester() {
		ValidDestinations tester = new ValidDestinations("Stonehaven", 1.0);
		assertEquals("Destination name nonequal", "Stonehaven", tester.getDest());
		assertEquals("Distance nonequal", 1.0, tester.getDist(), 0.000);
	}

}
