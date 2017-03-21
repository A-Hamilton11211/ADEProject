package src.main;


import java.awt.BorderLayout;
import java.awt.Dimension;
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

public class GUIWindows extends JFrame implements Observer, Runnable {

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
	private Boolean last = false;
	private QueueLayoutFrame QULF;
	
	public GUIWindows(passGroup pass, regNum reg, int windowNumber,QueueLayoutFrame QULF){
		this.passFeeder = pass;
		this.regFeeder = reg;
		pass.registerObserver(this);
		reg.registerObserver(this);
		this.windowNumber=windowNumber;
		setTitle("Kiosk Displays");
		this.setLayout(new FlowLayout(20));
		northPanel = new JPanel();
		setupWindows();
		this.QULF=QULF;
		
		
	
	}
	
	private void setupWindows(){
		
		//JPanel northPanel = new JPanel();
		Window1 = new JTextArea(15,20);
		Window1.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
		Window1.setSize(500,150);
		Window1.setEditable(false);
		W1scrollList = new JScrollPane(Window1);
		/*
		Window2 = new JTextArea(15,20);
		Window2.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
		Window2.setEditable(false);
		W2scrollList = new JScrollPane(Window2);
		*/
		/*
		Window3 = new JTextArea(15,20);
		Window3.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
		Window3.setEditable(false);
		Window4 = new JTextArea(15,20);
		Window4.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
		Window4.setEditable(false);
		*/

	    northPanel.add(W1scrollList);
	    //northPanel.add(W2scrollList);
	    
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
	}
	
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
					Window1.setText(transaction);
					QULF.displayList1.setText(passFeeder.stringifyPass());
					QULF.displayList2.setText(regFeeder.stringifyReg());
					System.out.println(passFeeder.stringifyPass());
					System.out.println(regFeeder.stringifyReg());
					SingletonLogTest.myLogger.info(transaction);
					passFeeder.decQueue();
					regFeeder.decQueue();
					finished = true;
					System.out.println("1");
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
					Window1.setText(transaction);
					QULF.displayList1.setText(passFeeder.stringifyPass());
					QULF.displayList2.setText(regFeeder.stringifyReg());
					SingletonLogTest.myLogger.info(transaction);
					passFeeder.decQueue();
					regFeeder.decQueue();
					finished = true;
					System.out.println("2");
				} else {
					// This is the point when either all the taxis have left, or all the passengers have been served.  At this point, the 
					// log class will write all of the events of the simulation (which are kept in history) to the file
					System.out.println("3");
					Thread.sleep(sleeptimer);
					history.add(transaction);
					transaction = "Closed for the Day";
					history.add(transaction);
	//				queueFrame.setText(transaction);
					Window1.setText(transaction);
					QULF.displayList1.setText(passFeeder.stringifyPass());
					QULF.displayList2.setText(regFeeder.stringifyReg());
					SingletonLogTest.myLogger.info(transaction);
					passFeeder.removeObserver(this);
					regFeeder.removeObserver(this);
					finished = true;
					last = true;
					System.out.println("3");
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
		try {while (last == false){
			this.pack();
			//this.setSize(600,200);
			this.W1scrollList.setPreferredSize(new Dimension(400, 100));
			this.pack();
			if(windowNumber==1){this.setLocation(850,150);}
			if(windowNumber==2){this.setLocation(450,300);}
			this.setVisible(true);
			System.out.println("Thread Number: "+ windowNumber);
			Thread.sleep(sleeptimer);
			regFeeder.notifyObservers();
		}} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}
	
	

	
	public static void main(String[] args) throws FileNotFoundException, IOException{
		//test method
		JourneyList j = new JourneyList();
		passGroup p = j.randomPassGroupGenerator();
		regNum r = j.regNumGenerator();
		p.makeQueue();
		r.makeQueue();
		QueueLayoutFrame QLF = new QueueLayoutFrame();
		QLF.pack();
		QLF.setLocation(50,200);
		QLF.setVisible(true);
		Thread number1 = new Thread(new GUIWindows(p,r,1,QLF));
		Thread number2 = new Thread(new GUIWindows(p,r,2,QLF));
		number1.start();
		number2.start();
		
	}
}