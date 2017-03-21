package src.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import src.main.Observer;

public class passGroup implements Subject {
	
	private String destination;
	private int passengers;
	private ArrayList<passGroup> passGrpQueue = new ArrayList<passGroup>();
	private List<Observer> registeredObservers = new LinkedList<Observer>();
	
	public passGroup (Journey j) throws FileNotFoundException, IOException{
		this.destination = j.getDest();
		this.passengers = j.getPass();
	}
	
	public String getPassDest() {
		return destination;
	}
	
	public int getPassNumbers() {
		return passengers;
	}
	
	public ArrayList<passGroup> getQueue(){
		return passGrpQueue;
	}
	
	public void addQueue() throws FileNotFoundException, IOException {
		JourneyList unimportant = new JourneyList();
		passGroup next = unimportant.randomPassGroupGenerator();
		passGrpQueue.add(next);
	}
	
	public void decQueue() throws FileNotFoundException, IOException {
		passGrpQueue.remove(0);
	}
	
	public void makeQueue() throws FileNotFoundException, IOException{
		for (int i = 0; i <= 10; i++){
			addQueue();
		}
	}
	
	public String stringifyPass(){
		String fin = "";
		for (passGroup indP: passGrpQueue){
			String dest = indP.getPassDest();
			String passNum = String.valueOf(indP.getPassNumbers());
			String output = "Destination: " + dest + "; Passengers: " + passNum + "\n";
			fin = fin.concat(output);
		}
		return fin;
		
	}
	
	@Override
	public void registerObserver(Observer obs) {
		registeredObservers.add(obs);	
	}

	@Override
	public void removeObserver(Observer obs) {
		registeredObservers.remove(obs);		
	}

	@Override
	public void notifyObservers() throws InterruptedException {
		for (Observer obs: registeredObservers){
			obs.update();
		}
	}
	
}