import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MockViewManager {

	public static void main(String [] args)
	{
		Places testlist = new Places();
		testlist.Places2016Reader();
		testlist.Places2017Reader();
		testlist.SetupHelperSets();
		
		QueueLayoutFrame QLF = new QueueLayoutFrame(testlist);
		QLF.pack();
		QLF.setLocation(50,200);
		QLF.setVisible(true);
		
		ControlButtonsFrame CBF = new ControlButtonsFrame();
		CBF.setFrameF1(QLF);
		CBF.setLocation(200,50);
		CBF.pack();
		CBF.setVisible(true);
		
		KioskWindowsFrame KWF = new KioskWindowsFrame();
		CBF.setFrameF2(KWF);
		KWF.pack();
		KWF.setLocation(850,150);
		KWF.setVisible(true);
		
	}
	
}
