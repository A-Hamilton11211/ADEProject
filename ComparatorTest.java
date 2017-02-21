import static org.junit.Assert.*;
import org.junit.*;

public class ComparatorTest {
	//JUnit tests for distance and expense comparator classes
	private distanceComparator DC1 = new distanceComparator();
	private expenseComparator EC1 = new expenseComparator();
	
	//before and after aren't necessary, leftovers of a skeleton code for future use
	@Before
	public void setUp(){
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testDistanceComparator() throws ImpossibleDistException, WrongPassException {
		//setting up dummy class instances for testing
		Journey DummyJourney1 = new Journey("AFR22","John Doe","Edinburgh",10.55,4);
		Journey DummyJourney2 = new Journey("AFR23","Jane Doe","Edinburgh",10.55,3);
		Journey DummyJourney3 = new Journey("AFR24","Tomato Salad","Glasgow",4,4);
		Journey DummyJourney4 = new Journey("AFR24","Tomato Salad","Glasgow",14,5);
		//testing distance comparator
		assertEquals (0,DC1.compare(DummyJourney1,DummyJourney2));
		assertEquals (-1,DC1.compare(DummyJourney1,DummyJourney3));
		assertEquals (1,DC1.compare(DummyJourney1,DummyJourney4));
		//testing expense comparator
		assertEquals (0,EC1.compare(DummyJourney1,DummyJourney1));
		assertEquals (-1,EC1.compare(DummyJourney1,DummyJourney3));
		assertEquals (1,EC1.compare(DummyJourney1,DummyJourney4));
		
	} 
	
	
}
