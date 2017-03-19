import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KioskWindowsFrame extends JFrame{

	public JTextArea Window1, Window2, Window3, Window4;
	JScrollPane W1scrollList,W2scrollList;
	JPanel northPanel;
	
public KioskWindowsFrame(){
		
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
	
}
