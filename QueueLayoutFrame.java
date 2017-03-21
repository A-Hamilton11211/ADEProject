package src.main;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class QueueLayoutFrame extends JFrame{
	
	protected Places testlist;
	
	JLabel PassengerQ, TaxiQ;
	JScrollPane PassengerscrollList,TaxiscrollList;
	JTextArea displayList1,displayList2;
	JButton MockControl,showBoth,show2017,show2016,close;
	
	public QueueLayoutFrame(){
		
		setTitle("Queue Displays");
		this.setSize(100,40);
		this.setLayout(new BorderLayout(5,5));
		
		setupEastPanel();
		setupWestPanel();		
		
	}
	
	private JLabel createOneLabel (String s, Color c) {
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 18);
		JLabel label= new JLabel(s, JLabel.CENTER);
		label.setFont(f);
		label.setBackground(c);
		label.setOpaque(true);
		return label;
	}

	
	private void setupEastPanel() {
		JPanel passpanel = new JPanel();
		passpanel.setLayout(new BorderLayout(5,5));
		PassengerQ = createOneLabel("Passenger Queue", Color.WHITE);
		passpanel.add(PassengerQ, BorderLayout.NORTH);
        displayList1 = new JTextArea(15,20);
        displayList1.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
        displayList1.setEditable(false);
        PassengerscrollList = new JScrollPane(displayList1);
        passpanel.add(PassengerscrollList, BorderLayout.SOUTH);
        
        this.add(passpanel,BorderLayout.EAST);  
    }
	private void setupWestPanel() {
		JPanel taxipanel = new JPanel();
		taxipanel.setLayout(new BorderLayout(5,5));
		TaxiQ = createOneLabel("Taxi Queue", Color.WHITE);
		taxipanel.add(TaxiQ, BorderLayout.NORTH);
        displayList2 = new JTextArea(15,20);
        displayList2.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
        displayList2.setEditable(false);
        TaxiscrollList = new JScrollPane(displayList2);
        taxipanel.add(TaxiscrollList, BorderLayout.SOUTH);
        this.add(taxipanel,BorderLayout.WEST);  
    }


	
	public static void main(String [] args)
	{

		
		QueueLayoutFrame QLF = new QueueLayoutFrame();
		QLF.setDefaultCloseOperation(EXIT_ON_CLOSE);
		QLF.pack();  
		QLF.setVisible(true);
		
		
	}

}
