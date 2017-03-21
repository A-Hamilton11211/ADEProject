package src.main;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

public class ControlButtonsFrame extends JFrame implements ActionListener, Subject{
	
	JLabel ControlScheme;
	JButton Start,SpeedUp,SlowDown,Close,AddWindow,DeleteWindow;
	
	private List<Observer> registeredObservers = new LinkedList<Observer>();
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
	
	
	private QueueLayoutFrame queueFrame;
    public void setFrameF1(QueueLayoutFrame f1) {
        this.queueFrame = f1;
    }
    
    private KioskWindowsFrame windowFrame;
    public void setFrameF2(KioskWindowsFrame f2) {
        this.windowFrame = f2;
    }
	
	public ControlButtonsFrame(){
		
		setTitle("Control Center");
		this.setLayout(new FlowLayout(20));
		setupButtons();
		
	}
	
	private void setupButtons(){
		
		JPanel eastPanel = new JPanel();
		
		Start = new JButton("Start the simulation");
        Start.addActionListener(this);
        
        SpeedUp = new JButton("Speed up the simulation");
        SpeedUp.addActionListener(this);
        
        SlowDown = new JButton("Slow down the simulation");
        SlowDown.addActionListener(this);
        
        AddWindow = new JButton("Add a Window");
        AddWindow.addActionListener(this);
        
        DeleteWindow = new JButton("Delete a Window");
        DeleteWindow.addActionListener(this);
        
        Close = new JButton("Close");
        Close.addActionListener(this);
        
        eastPanel.add(Start);
        eastPanel.add(SpeedUp);
        eastPanel.add(SlowDown);
        eastPanel.add(AddWindow);
        eastPanel.add(DeleteWindow);
        eastPanel.add(Close);
        this.add(eastPanel, BorderLayout.EAST);
	}
	
	public void Starter() throws FileNotFoundException, IOException{
		JourneyList j = new JourneyList();
		passGroup p = j.randomPassGroupGenerator();
		regNum r = j.regNumGenerator();
		p.makeQueue();
		r.makeQueue();
		QueueLayoutFrame QLF = new QueueLayoutFrame();
		QLF.pack();
		QLF.setLocation(50,200);
		QLF.setVisible(true);
		GUIWindows w = new GUIWindows(p,r,1,QLF);
		w.setF1(windowFrame, 1);
		w.run();
	}
	
	public void actionPerformed(ActionEvent e)
    { 
		if (e.getSource() == SpeedUp) {
			Windows.sleeptimer=Windows.sleeptimer/2;
			//queueFrame.displayList1.setText(queueFrame.testlist.compareDestinationsBoth());
    	}

		else if (e.getSource() == Start) {
			
			JourneyList j = new JourneyList();
			try{
			passGroup p = j.randomPassGroupGenerator();
			regNum r = j.regNumGenerator();
			p.makeQueue();
			r.makeQueue();
			
			QueueLayoutFrame QLF = new QueueLayoutFrame();
			QLF.pack();
			//QLF.setSize(400,100);
			QLF.setLocation(50,200);
			QLF.setVisible(true);
			
			Thread number1 = new Thread(new GUIWindows(p,r,1,QLF));
			Thread number2 = new Thread(new GUIWindows(p,r,2,QLF));
			number1.start();
			number2.start();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}
		
		else if (e.getSource() == SlowDown) {
			Windows.sleeptimer=Windows.sleeptimer*2;
			//windowFrame.Window1.setText(queueFrame.testlist.compareDestinationsBoth());
    	}
		else if (e.getSource() == AddWindow) {
			JTextArea Window3 = new JTextArea(15,20);
			Window3.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
			Window3.setEditable(false);
			JScrollPane W3scrollList = new JScrollPane(Window3);
			windowFrame.northPanel.add(W3scrollList, BorderLayout.SOUTH);
			windowFrame.revalidate();
			windowFrame.pack();
			windowFrame.setVisible(true);
    	}
		else if (e.getSource() == Close) {
    		JOptionPane.showMessageDialog(this, 
    	       "Add logger call and stuff");
    		System.exit(0);
    	}
		/*
    	if (e.getSource() == showBoth) {
    		displayList.setText(testlist.compareDestinationsBoth());
    	}
    	else if (e.getSource() == show2017 ) {
    		displayList.setText(testlist.compareDestinations2017());
    	}
    	else if (e.getSource() == show2016 ) {
    		displayList.setText(testlist.compareDestinations2016());
    	}
    	else if (e.getSource() == close) {
    		JOptionPane.showMessageDialog(this, 
    	       "Do 'end of program' things instead of showing this");
    		//calling the singleton logger
    		System.exit(0);
    	}
    	*/
    }  

}
