import java.util.Comparator;
public class expenseComparator implements Comparator<Journey> {
	//comparator class for sorting journey object
	public int compare(Journey s1, Journey s2){
		double t1 = s1.getCost();
		double t2 = s2.getCost();
		if (t1<t2)return 1;
		else if (t1>t2) return -1;
		else return 0;
	}
}