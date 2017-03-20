import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {
	
	
	public String format(LogRecord rec){
		
		StringBuffer buf = new StringBuffer(1000);
		
		
		for (int i = 1; i < 5; ){
			
			buf.append("W"+ i + ":");
			buf.append("   ");
			buf.append("Destination: ");
			buf.append(formatMessage(rec));
			buf.append("   ");
			
			buf.append("Passengers: ");
			buf.append(formatMessage(rec));
			buf.append("   ");
			
			buf.append("Taxi: ");
			buf.append(formatMessage(rec));
			buf.append("\n" + "\n");
			
			i++;
		}
			return buf.toString();
	}

	public CustomFormatter() {
		// TODO Auto-generated constructor stub
	}

}
