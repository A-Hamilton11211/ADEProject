package src.testing;
import static org.junit.Assert.*;



import org.junit.Test;

import src.main.*;
@SuppressWarnings("unused")
public class JourneyTest {
	
	@Test(expected = ImpossibleDistException.class)
	// for checking to see if the ImpossibleDistException works as intended
	public void testImpossibleDist() throws ImpossibleDistException, WrongPassException {
		Journey tester = new Journey("AFR1", "Bob Dole", "Stonehaven", 0, 1);
	}
	
	@Test(expected = WrongPassException.class)
	// for checking to see if the WrongPassException triggers when passengers are 0
	public void testWrongPass() throws ImpossibleDistException, WrongPassException {
		Journey tester = new Journey("AFR1", "Bob Dole", "Stonehaven",1.0, 0);
	}
	
	@Test(expected = WrongPassException.class)
	// for checking to see if the WrongPassException triggers when over 6  passengers
	public void testWrongPass2() throws ImpossibleDistException, WrongPassException {
		Journey tester = new Journey("AFR1", "Bob Dole", "Stonehaven",1.0, 7);
	}
	
	@Test // for checking to see if the get methods work as intended
	public void testJourneyGetters() throws ImpossibleDistException, WrongPassException {
		Journey tester = new Journey("AFR1", "Bob Dole", "Stonehaven",1.0, 1);
		assertEquals("Nonequal RegNum", "AFR1", tester.getReg());
		assertEquals("Nonequal Driver Name", "Bob Dole", tester.getDriver());
		assertEquals("Nonequal Destination", "Stonehaven", tester.getDest());
		assertEquals("Nonequal Distance", 1.0 , tester.getDist(), 0.000);
		assertEquals("Nonequal Passengers", 1 , tester.getPass());
		assertEquals("Nonequal Cost", 2.80 , tester.getCost(), 0.000);
	}
	
	@Test // for checking to see if the equals() function works as expected
	public void testEquals() throws ImpossibleDistException, WrongPassException{
		Journey tester = new Journey("AFR1", "Bob Dole", "Stonehaven",1.0, 1);
		Journey tester2 = new Journey("AFR1", "Bob Dole", "Stonehaven",1.0, 1);
		assertTrue("tester and tester2 nonequal", tester.equals(tester2));
		Journey tester3 = new Journey("AFR01", "Bob Dole", "Stonehaven",1.0, 1);
		assertFalse("tester and tester3 are equal", tester.equals(tester3));
		Journey tester4 = new Journey("AFR1", "Bob Doles", "Stonehaven",1.0, 1);
		assertFalse("tester and tester4 are equal", tester.equals(tester4));
		Journey tester5 = new Journey("AFR1", "Bob Dole", "Stonehavens",1.0, 1);
		assertFalse("tester and tester5 are equal", tester.equals(tester5));
		Journey tester6 = new Journey("AFR1", "Bob Dole", "Stonehaven",1.1, 1);
		assertTrue("tester and tester6 nonequal", tester.equals(tester6));
		Journey tester7 = new Journey("AFR1", "Bob Dole", "Stonehaven",1.0, 2);
		assertFalse("tester and tester7 are equal", tester.equals(tester7));
	}
	
	@Test // for checking to see if the compareTo() function works as expected
	public void testCompare() throws ImpossibleDistException, WrongPassException{
		Journey tester = new Journey("AFR10", "Bob Dole", "Stonehaven",1.0, 1);
		Journey tester2 = new Journey("AFR10", "Bob Dole", "Stonehaven",1.0, 1);
		assertEquals("Tester and tester2 have unequal ids", tester.compareTo(tester2), 0);
		Journey tester3 = new Journey("AFR9", "Bob Dole", "Stonehaven",1.0, 1);
		assertEquals("Tester and tester3 have unequal ids", tester.compareTo(tester3), -1);
		Journey tester4 = new Journey("AFR11", "Bob Dole", "Stonehaven",1.0, 1);
		assertEquals("Tester and tester4 have unequal ids", tester.compareTo(tester4), 1);
	}
}
