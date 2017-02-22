package src.main;
import java.util.Comparator;
// This class extends comparator to create a completely unique comparator that will be different for each journey
// (provided that the journeys are actually different)
public class uniqueComp implements Comparator<Journey> {
	public int compare(Journey s1, Journey s2){
		// Get some values of journey (last two digits of regNum, passengers, and distance) and make them strings.
		// In every meaningfully different journey, these values will be different.
		String r1 = s1.getReg().substring(3);
		String r2 = s2.getReg().substring(3);
		String p1 = String.valueOf(s1.getPass());
		String p2 = String.valueOf(s2.getPass());
		String d1 = String.valueOf(s1.getDist());
		String d2 = String.valueOf(s2.getDist());
		// Combine the two stringified ints and then the double (e.g. 1 + 5 + 20.0 = 1520.0)
		String out1 = r1 + p1 + d1;
		String out2 = r2 + p2 + d2;
		// Turn the combined strings into doubles 
		double finalid1 = Double.parseDouble(out1);
		double finalid2 = Double.parseDouble(out2);
		// Compare them on the basis of size
		if (finalid1<finalid2)return 1;
		else if (finalid1>finalid2) return -1;
		else return 0;
	}
}