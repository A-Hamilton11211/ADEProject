package src.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class regNum implements Runnable, Subject{
	
	private String registration;
	private ArrayList<regNum> taxiQueue = new ArrayList<regNum>();
	private List<Observer> registeredObservers = new LinkedList<Observer>();
	
	public regNum (Journey j){
		this.registration = j.getReg();
	}
	
	public String getRegistration(){
		return registration;
	}
	
	public ArrayList<regNum> getQueue(){
		return taxiQueue;
	}
	
	public synchronized void addQueue() throws FileNotFoundException, IOException {
		JourneyList unimportant = new JourneyList();
	    regNum next = unimportant.regNumGenerator();
		taxiQueue.add(next);
	}
	
	public synchronized void decQueue() throws FileNotFoundException, IOException {
		taxiQueue.remove(0);
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