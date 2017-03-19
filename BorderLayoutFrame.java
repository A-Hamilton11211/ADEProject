import java.awt.*;
import javax.swing.*;
public class BorderLayoutFrame extends JFrame
{ 
	JLabel n,s,e,w,c;

	public BorderLayoutFrame() {
		//default layout for pane is BorderLayout 
		//with no gaps between the components
		//this command specifies horiz and vert gaps between components
		this.setLayout(new FlowLayout(5));
		n = createOneLabel("Top", Color.GREEN);
		this.add(n, BorderLayout.NORTH);
		
		s = createOneLabel("Bottom", Color.WHITE);
		this.add(s, BorderLayout.SOUTH);
		
		e = createOneLabel("Right", Color.RED);
		this.add(e, BorderLayout.EAST);
		
		w = createOneLabel("Left", Color.CYAN);
		this.add(w, BorderLayout.WEST);
		
		c = createOneLabel("Middle", Color.YELLOW);
		this.add(c,BorderLayout.CENTER);
		
	}

	private JLabel createOneLabel (String s, Color c) {
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 18);
		JLabel label= new JLabel(s, JLabel.CENTER);
		label.setFont(f);
		label.setBackground(c);
		label.setOpaque(true);
		return label;
	}

	
	public static void main(String [] args)
	{
		BorderLayoutFrame bf = new BorderLayoutFrame();
		bf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//bf.setSize(300,320);
		//pack works out the best size of the frame for itself
		//based on what components there are
		bf.pack();  
		bf.setVisible(true);
	}

}
