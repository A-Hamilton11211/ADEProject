package src.main;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;


public class MockViewManager {


	
	void setupGUI() throws FileNotFoundException, IOException{
		ControlButtonsFrame CBF = new ControlButtonsFrame();
		CBF.setLocation(200,50);
		CBF.pack();
		CBF.setVisible(true);
	}
	
	public static void main(String [] args) throws FileNotFoundException, IOException
	{
		MockViewManager mock = new MockViewManager();
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	try {
					mock.setupGUI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
	}
	
}
