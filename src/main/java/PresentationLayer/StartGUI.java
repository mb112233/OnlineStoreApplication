package PresentationLayer;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartGUI  {
    
	
	JFrame frame;
	static JButton b1=new JButton("Client Operations");
	static JButton b2=new JButton("Products Operations");
	static JButton b3=new JButton("Create product order");
	/**
	 * This constructor makes a frame and adds a panel on it.
	 */
	public StartGUI() {

		frame = new JFrame("OnlineClothingShop");
		frame.setBounds(300,500,200,150);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel p1=StartGUI.panelRoot();
		frame.add(p1);
	}
	/**
	 * This method builds a panel and adds buttons and labels.The action listener inner methods of the buttons
	 * are included.
	 * @return JPanel object
	 */
	private static JPanel panelRoot() {
		
		JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
		b2.setAlignmentX(Component.CENTER_ALIGNMENT);
		b3.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				 if (e.getActionCommand().equals("Client Operations")){
					new ClientGUI();
				 }
		    }
		});	
		b2.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				 if (e.getActionCommand().equals("Products Operations")){
					 new ProductsGUI();
				 }
		    }
		});	
		b3.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				 if (e.getActionCommand().equals("Create product order")){
					 new OrderGUI();
				 }
		    }
		});	
		
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		
		return panel;
		
	}
	
	public static void main(String[] args) {
		new StartGUI();
	}
}
