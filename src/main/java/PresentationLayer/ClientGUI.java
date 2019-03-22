package PresentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BuildJTable.BuildJtable;
import BuildJTable.JTableGUI;
import BussinessLayer.ClientBLL;
import EntititesLayer.Client;

public class ClientGUI {
	
	private JFrame frame;
	static JLabel l1=new JLabel("Insert client id");
	static JLabel l2=new JLabel("Insert client name");
	static JLabel l3=new JLabel("Insert client address");
	static JLabel l4=new JLabel("Insert client email");
	static JLabel l5=new JLabel("Insert client age");
	static JLabel l6=new JLabel("Insert client id");
	static JLabel l7=new JLabel("Update client with id");
	static JLabel l8=new JLabel("Update client name");
	static JLabel l9=new JLabel("Update client address");
	static JLabel l10=new JLabel("Update client email");
	static JLabel l11=new JLabel("Update client age");
	static JLabel l12=new JLabel("Update client id");
	static JTextField t1=new JTextField();
	static JTextField t2=new JTextField();
	static JTextField t3=new JTextField();
	static JTextField t4=new JTextField();
	static JTextField t5=new JTextField();
	static JTextField t6=new JTextField();
	static JTextField t7=new JTextField();
	static JTextField t8=new JTextField();
	static JTextField t9=new JTextField();
	static JTextField t10=new JTextField();
	static JTextField t11=new JTextField();
	static JTextField t12=new JTextField();
	static JButton b1=new JButton("Insert client");
	static JButton b2=new JButton("Delete client");
	static JButton b3=new JButton("Update client");
	static JButton b4=new JButton("Show table");
	/**
	 * This constructor makes a frame and adds a panel on it.
	 */
	public ClientGUI() {
		frame = new JFrame("OnlineClothingShop");		
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel p1=ClientGUI.panelClientProcessing();
		frame.add(p1);
		frame.pack();
	}
	/**
	 * This method builds a panel and adds buttons and labels.The action listener inner methods of the buttons
	 * are included.
	 * @return JPanel object
	 */
	private static JPanel panelClientProcessing() {
		JPanel panel =new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				 if (e.getActionCommand().equals("Insert client")){
					int id=Integer.parseInt(t1.getText());
					String name=t2.getText();
					String address=t3.getText();
					String email=t4.getText();
					int age=Integer.parseInt(t5.getText());
					Client c=new Client(id,name,address,email,age);
					ClientBLL.insertClient(c);
				 }
		    }
		});	
		b2.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				 if (e.getActionCommand().equals("Delete client")){
					 int id=Integer.parseInt(t6.getText());
					 ClientBLL.deleteClient(id);
				 }
		    }
		});	
		b3.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				 if (e.getActionCommand().equals("Update client")){
					
							int id=Integer.parseInt(t7.getText());
							String name=t8.getText();
							String address=t9.getText();
							String email=t10.getText();
							int age=Integer.parseInt(t11.getText());
							Client c=new Client(id,name,address,email,age);
							ClientBLL.updateClient(id,c);
						 
				 }
		    }
		});	
		b4.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				 if (e.getActionCommand().equals("Show table")){
					 ArrayList<?> clients=ClientBLL.selectAll();
					 Object[][] o=BuildJtable.makeTable(clients);
					 new JTableGUI(o);
						 
				 }
		    }
		});	

		panel.add(l1);
		panel.add(t1);
		panel.add(l2);
		panel.add(t2);
		panel.add(l3);
		panel.add(t3);
		panel.add(l4);
		panel.add(t4);
		panel.add(l5);
		panel.add(t5);
		panel.add(b1);
		
		panel.add(l6);
		panel.add(t6);
		panel.add(b2);
		
		panel.add(l7);
		panel.add(t7);
		panel.add(l8);
		panel.add(t8);
		panel.add(l9);
		panel.add(t9);
		panel.add(l10);
		panel.add(t10);
		panel.add(l11);
		panel.add(t11);
		panel.add(b3);
		
		panel.add(b4);
		return panel;
	}
	
	public static void main(String[] args) {
		new ClientGUI();
	}
}
