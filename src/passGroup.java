package src.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class passGroup implements Runnable {
	
	private String destination;
	private int passengers;
	private ArrayList<passGroup> passGrpQueue = new ArrayList<passGroup>();
	
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
			} catch (InterruptedException e){
				System.out.println(e.getMessage());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) throws ImpossibleDistException, WrongPassException{
		Journey j = new Journey("AFR1", "Borf", "Floop", 1.0, 1);
		passGroup test = new passGroup(j);
		test.run();
	}
	
}