import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ControlButtonsFrame extends JFrame implements ActionListener {
	
	JLabel ControlScheme;
	JButton Start,SpeedUp,SlowDown,Close,AddWindow,DeleteWindow;
	
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
	
	public void actionPerformed(ActionEvent e)
    { 
		if (e.getSource() == SpeedUp) {
			queueFrame.displayList1.setText(queueFrame.testlist.compareDestinationsBoth());
    	}
		else if (e.getSource() == SlowDown) {
			windowFrame.Window1.setText(queueFrame.testlist.compareDestinationsBoth());
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
