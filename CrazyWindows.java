package src.main;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/*
import JourneyList;
import Observer;
import passGroup;
import regNum;
*/
public class CrazyWindows extends JFrame implements Observer, Runnable {

	public static long sleeptimer = 1000;
	private String transaction;
	private passGroup passFeeder;
	private regNum regFeeder;
	private Boolean firstTrans = true;
	private Boolean finished = false;
	private KioskWindowsFrame queueFrame;
	private ArrayList<String> history = new ArrayList<String>();
	private final static Logger myLogger = Logger.getLogger(SingletonLogTest.class.getName());
	public JTextArea Window1, Window2, Window3, Window4;
	JScrollPane W1scrollList,W2scrollList;
	JPanel northPanel;
	private int windowNumber;
	
	public CrazyWindows(passGroup pass, regNum reg, int windowNumber){
		this.passFeeder = pass;
		this.regFeeder = reg;
		pass.registerObserver(this);
		reg.registerObserver(this);
		this.windowNumber=windowNumber;
		setTitle("Kiosk Displays");
		this.setLayout(new FlowLayout(20));
		northPanel = new JPanel();
		setupWindows();
		
	
	}
	
	private void setupWindows(){
		
		//JPanel northPanel = new JPanel();
		Window1 = new JTextArea(15,20);
		Window1.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
		Window1.setEditable(false);
		W1scrollList = new JScrollPane(Window1);
		Window2 = new JTextArea(15,20);
		Window2.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
		Window2.setEditable(false);
		W2scrollList = new JScrollPane(Window2);
		/*
		Window3 = new JTextArea(15,20);
		Window3.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
		Window3.setEditable(false);
		Window4 = new JTextArea(15,20);
		Window4.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
		Window4.setEditable(false);
		*/

	    northPanel.add(W1scrollList);
	    northPanel.add(W2scrollList);
	    
	    this.add(northPanel, BorderLayout.NORTH);
	}
	
	public void setTrans(String t){
		this.transaction = t;
	}
	
	private KioskWindowsFrame windowFrame;
	private int Wnumber;
	public void setF1(KioskWindowsFrame f1,int Wnumber){
		this.windowFrame = f1;
		this.Wnumber=Wnumber;
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
	
	
	public void textToWindow(String trans,int wn){
		if(wn==1){Window1.setText(trans);}
		if(wn==2){Window2.setText(trans);}
	}
	
	@Override
	public void update() throws InterruptedException {
		while (finished == false){
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
					System.out.println(transaction);
					textToWindow(transaction,Wnumber);
					//Window1.setText(transaction);
					if(windowNumber==1){Window1.setText(transaction);}
					if(windowNumber==2){Window2.setText(transaction);}
					//(SingletonLogTest.getInstance()).info(transaction);
					SingletonLogTest.myLogger.info(transaction);
					passFeeder.decQueue();
					regFeeder.decQueue();
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
					System.out.println(transaction);
					textToWindow(transaction,Wnumber);
					//Window1.setText(transaction);
					if(windowNumber==1){Window1.setText(transaction);}
					if(windowNumber==2){Window2.setText(transaction);}
					SingletonLogTest.myLogger.info(transaction);
					passFeeder.decQueue();
					regFeeder.decQueue();
				} else {
					// This is the point when either all the taxis have left, or all the passengers have been served.  At this point, the 
					// log class will write all of the events of the simulation (which are kept in history) to the file
					Thread.sleep(sleeptimer);
					history.add(transaction);
					transaction = "Closed for the Day";
					history.add(transaction);
					System.out.println(transaction);
					textToWindow(transaction,Wnumber);
					//Window1.setText(transaction);
					if(windowNumber==1){Window1.setText(transaction);}
					if(windowNumber==2){Window2.setText(transaction);}
					SingletonLogTest.myLogger.info(transaction);
					passFeeder.removeObserver(this);
					regFeeder.removeObserver(this);
					finished = true;;
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
			this.pack();
			if(windowNumber==1){this.setLocation(850,150);}
			if(windowNumber==2){this.setLocation(450,150);}
			this.setVisible(true);
			System.out.println("Thread Number: "+ windowNumber);
			Thread.sleep(sleeptimer);
			regFeeder.notifyObservers();
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
		/*
		CrazyWindows w = new CrazyWindows(p,r,1);
		//SingletonLogTest.setupLogger();
		w.pack();
		w.setLocation(850,150);
		w.setVisible(true);
		//w.run();
		CrazyWindows q = new CrazyWindows(p,r,2);
		//SingletonLogTest.setupLogger();
		q.pack();
		q.setLocation(450,150);
		q.setVisible(true);
		//q.run();
		 */
		Thread number1 = new Thread(new CrazyWindows(p,r,1));
		Thread number2 = new Thread(new CrazyWindows(p,r,2));
		number1.start();
		number2.start();
		
	}
}