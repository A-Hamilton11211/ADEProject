package src.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Windows implements Observer {

	private String transaction;
	private passGroup passFeeder;
	private regNum regFeeder;
	private Boolean firstTrans = true;
	private ArrayList<String> history = new ArrayList<String>();
	
	public Windows(passGroup pass, regNum reg){
		this.passFeeder = pass;
		this.regFeeder = reg;
		pass.registerObserver(this);
		reg.registerObserver(this);
	}
	
	public void setTrans(String t){
		this.transaction = t;
	}
	
	public String getTrans(){
		return transaction;
	}
	
	public void addToHistory(){
		history.add(this.transaction);
	}
	
	public ArrayList<String> getHistory(){
		return history;
	}
	
	@Override
	public void update() {
		ArrayList<passGroup> passQueue = passFeeder.getQueue();
		ArrayList<regNum> regQueue = regFeeder.getQueue();
		try {
			if (passQueue.isEmpty() != true  && regQueue.isEmpty() != true && firstTrans == true){
				passGroup passengers = passQueue.get(0);
				regNum taxi = regQueue.get(0);
				String passNum = String.valueOf(passengers.getPassNumbers());
				transaction = "Destination: " + passengers.getPassDest() + "\nTaxi: " + taxi.getRegistration() + "\nPassenger Number: " + passNum;
				firstTrans = false;
				passFeeder.decQueue();
				regFeeder.decQueue();
			} else if (passQueue.isEmpty() != true  && regQueue.isEmpty() != true && firstTrans == false){
				history.add(transaction);
				transaction = "";
				passGroup passengers = passQueue.get(0);
				regNum taxi = regQueue.get(0);
				String passNum = String.valueOf(passengers.getPassNumbers());
				transaction = "Destination: " + passengers.getPassDest() + "\nTaxi: " + taxi.getRegistration() + "\nPassenger Number: " + passNum;
				passFeeder.decQueue();
				regFeeder.decQueue();
			} else {
				// This is the point when either all the taxis have left, or all the passengers have been served.  At this point, the 
				// log class will write all of the events of the simulation (which are kept in history) to the file
				history.add(transaction);
				transaction = "Closed for the Day";
				history.add(transaction);
				passFeeder.removeObserver(this);
				regFeeder.removeObserver(this);
				// log.writeToFile(getHistory());
			}
			
			} catch (FileNotFoundException e) {
					e.printStackTrace();
			} catch (IOException e) {
					e.printStackTrace();
			}
	}
	
}