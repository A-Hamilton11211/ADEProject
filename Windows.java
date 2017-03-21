package src.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Windows implements Observer, Runnable {

	public long sleeptimer = 1000;
	private String transaction;
	private passGroup passFeeder;
	private regNum regFeeder;
	private Boolean firstTrans = true;
	private Boolean finished = false;
	private Boolean last = false;
	//private KioskWindowsFrame queueFrame;
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
	
	//public void setF1(KioskWindowsFrame f1){
	//	this.queueFrame = f1;
	//}
	
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
	public synchronized void update() throws InterruptedException {
		finished = false;
		if (finished == false && last == false){
			try {
				if (passFeeder.getQueue().isEmpty() != true  && regFeeder.getQueue().isEmpty() != true && firstTrans == true){
					Thread.sleep(sleeptimer);
					ArrayList<passGroup> passQueue = passFeeder.getQueue();
					ArrayList<regNum> regQueue = regFeeder.getQueue();
					passGroup passengers = passQueue.get(0);
					regNum taxi = regQueue.get(0);
					String passNum = String.valueOf(passengers.getPassNumbers());
					transaction = "Destination: " + passengers.getPassDest() + "\nTaxi: " + taxi.getRegistration() + "\nPassenger Number: " + passNum;
					firstTrans = false;
	//				queueFrame.setText(transaction);
					passFeeder.decQueue();
					regFeeder.decQueue();
					finished = true;
					System.out.println(passFeeder.stringifyPass());
					System.out.println(regFeeder.stringifyReg());
				} else if (passFeeder.getQueue().isEmpty() != true  && regFeeder.getQueue().isEmpty() != true && firstTrans == false){
					Thread.sleep(sleeptimer);
					ArrayList<passGroup> passQueue = passFeeder.getQueue();
					ArrayList<regNum> regQueue = regFeeder.getQueue();
					history.add(transaction);
					transaction = "";
					passGroup passengers = passQueue.get(0);
					regNum taxi = regQueue.get(0);
					String passNum = String.valueOf(passengers.getPassNumbers());
					transaction = "Destination: " + passengers.getPassDest() + "\nTaxi: " + taxi.getRegistration() + "\nPassenger Number: " + passNum;
	//				queueFrame.setText(transaction);
					passFeeder.decQueue();
					regFeeder.decQueue();
					finished = true;
					System.out.println(passFeeder.stringifyPass());
					System.out.println(regFeeder.stringifyReg());
				} else {
					// This is the point when either all the taxis have left, or all the passengers have been served.  At this point, the 
					// log class will write all of the events of the simulation (which are kept in history) to the file
					Thread.sleep(sleeptimer);
					history.add(transaction);
					transaction = "Closed for the Day";
					history.add(transaction);
	//				queueFrame.setText(transaction);
					passFeeder.removeObserver(this);
					regFeeder.removeObserver(this);
					finished = true;
					last = true;
					// log.writeToFile(getHistory());
				}
				
			} catch (FileNotFoundException e) {
						e.printStackTrace();
			} catch (IOException e) {
						e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		try {
			while (last == false){
				Thread.sleep(sleeptimer);
				regFeeder.notifyObservers();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
		JourneyList j = new JourneyList();
		passGroup p = j.randomPassGroupGenerator();
		regNum r = j.regNumGenerator();
		p.makeQueue();
		r.makeQueue();
		Windows w1 = new Windows(p,r);
		Windows w2 = new Windows(p,r);
		w1.run();
		w2.run();
		System.out.println(w1.getHistory());
		System.out.println(w2.getHistory());
	}
}
	
