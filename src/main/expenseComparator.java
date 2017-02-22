package src.main;
import java.util.Comparator;
public class expenseComparator implements Comparator<Journey> {
	public int compare(Journey s1, Journey s2){
		// Get our values
		double t1 = s1.getCost();
		double t2 = s2.getCost();
		String d1 = s1.getDest();
		String d2 = s2.getDest();
		String r1 = s1.getReg();
		String r2 = s2.getReg();
		// Checks to see if the destinations and registration numbers equal each other.
		// If they do, we sort exclusively using cost
		if (d1.equals(d2) && r1.equals(r2)){
			if (t1<t2)return 1;
			else if (t1>t2) return -1;
			else return 0;
		// Otherwise, since they're clearly different journeys, they won't equal each other
		// so the comparator can't be allowed to return 0
		} else {
			if (t1<t2)return 1;
			else return -1;
		}
	}
}