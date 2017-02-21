package src.main;
import java.util.Comparator;
public class distanceComparator implements Comparator<Journey> {
	public int compare(Journey s1, Journey s2){
		double t1 = s1.getDist();
		double t2 = s2.getDist();
		if (t1<t2)return 1;
		else if (t1>t2) return -1;
		else return 0;
	}
}
