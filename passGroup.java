package src.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import src.main.Observer;

public class passGroup implements Runnable, Subject {
	
	private String destination;
	private int passengers;
	private ArrayList<passGroup> passGrpQueue = new ArrayList<passGroup>();
	private List<Observer> registeredObservers = new LinkedList<Observer>();
	
	public passGroup (Journey j){
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
	
	public synchronized void addQueue() throws FileNotFoundException, IOException {
		JourneyList unimportant = new JourneyList();
		passGroup next = unimportant.randomPassGroupGenerator();
		passGrpQueue.add(next);
	}
	
	public synchronized void decQueue() throws FileNotFoundException, IOException {
		passGrpQueue.remove(0);
	}

	@Override
	public void run() {
		for (int i = 0; i <= 10; i++){
			try {
				Thread.sleep(2000);
				addQueue();
				notifyObservers();
			} catch (InterruptedException e){
				System.out.println(e.getMessage());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
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
	public void notifyObservers() {
		for (Observer obs: registeredObservers){
			obs.update();
		}
	}
	
}