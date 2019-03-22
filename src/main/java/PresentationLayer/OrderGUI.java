package PresentationLayer;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
import BussinessLayer.OrderProductBLL;
import BussinessLayer.OrdersTableBLL;
import BussinessLayer.ProductsBLL;
import EntititesLayer.Client;
import EntititesLayer.OrderProduct;
import EntititesLayer.OrdersTable;
import EntititesLayer.Products;

public class OrderGUI {
	
	JFrame frame;
	static JLabel l1=new JLabel("Introduce client id");
	static JLabel l2=new JLabel("Introduce product id");
	static JLabel l3=new JLabel("Introduce order id");
	static JLabel l4=new JLabel("Insert order id");
	static JLabel l5=new JLabel("Insert order shippingStatus");
	static JLabel l6=new JLabel("Insert client amount");
	static JTextField t1=new JTextField ();
	static JTextField t2=new JTextField ();
	static JTextField t3=new JTextField ();
	static JTextField t4=new JTextField ();
	static JTextField t5=new JTextField ();
	static JTextField t6=new JTextField ();
	static JButton b=new JButton("Create bill");
	static JButton b1=new JButton("Insert new order");
	static JButton b2=new JButton("Show table");
	
	static Client c;
	static Products p;
	static OrdersTable o;
	/**
	 * This constructor makes a frame and adds a panel on it.
	 */
	public OrderGUI() {

		frame = new JFrame();
		//frame.setBounds(300,500,200,200);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel p1=OrderGUI.panelForOrdering();
		frame.add(p1);
		frame.pack();
	}
	/**
	 * This method builds a panel and adds buttons and labels.The action listener inner methods of the buttons
	 * are included.
	 * @return JPanel object
	 */
	private static JPanel panelForOrdering() {
		JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		b.setAlignmentX(Component.CENTER_ALIGNMENT);
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
		b2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
		    	
		    	 if (e.getActionCommand().equals("Create bill")){
		    		    int idc=Integer.parseInt(t1.getText());
				    	Client c1=ClientBLL.selectClient(idc);
				    	c=new Client(c1.getId(),c1.getName(),c1.getAddress(),c1.getEmail(),c1.getAge());
				    	
				    	 int idp=Integer.parseInt(t2.getText());
				 		 Products p1=ProductsBLL.selectProduct(idp);
				 		 p=new Products(p1.getIdproducts(),p1.getName(),p1.getPrice(),p1.getCategory(),p1.getStock());
				 		 
				 		int ido=Integer.parseInt(t3.getText());
						OrdersTable o1=OrdersTableBLL.selectOrder(ido);
						o=new OrdersTable(o1.getIdorders(),o1.getShippingStatus(),o1.getAmount());
						
					    makeBill(c,p,o);
		    	 }
		    		
			}
		});
		b1.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
		    	
		    	 if (e.getActionCommand().equals("Insert new order")){
		    		 int id=Integer.parseInt(t4.getText());
						String shippingStatus=t5.getText();
						int amount=Integer.parseInt(t6.getText());
						OrdersTable c=new OrdersTable(id,shippingStatus,amount);
						OrdersTableBLL.insertOrder(c);
						
		    	 }
		    		
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {				 
				if (e.getActionCommand().equals("Show table")){
					 ArrayList<?> order=OrdersTableBLL.selectAll();
					 Object[][] o=BuildJtable.makeTable(order);
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
		
		panel.add(b);
		
		panel.add(l4);
		panel.add(t4);
		panel.add(l5);
		panel.add(t5);
		panel.add(l6);
		panel.add(t6);
		
		panel.add(b1);
		panel.add(b2);
		return panel;
	}
	/**
	 * This method checks if a client can buy a product and also decrements the stock of a product when an
	 * order is placed. A bill is also generated as a .txt file.
	 * @param c
	 * @param p
	 * @param ot
	 */
	public static void makeBill(Client c, Products p, OrdersTable ot) {
		String text;
		if (ot.getAmount()>p.getStock()) {
			text="There are not enough products on stock for this order";
		}
		else {
			p.setStock(p.getStock()-ot.getAmount());
			
			ProductsBLL.updateProducts(p.getIdproducts(),p);
			text=c.toString()+" just bought a product "+p.toString()+
					".The bill status and informations are "+ot.toString();
			OrderProduct o=new OrderProduct(c.getId(), p.getIdproducts(), ot.getIdorders());
			OrderProductBLL.insertOrderProduct(o);
		}
		
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("Bill.txt"));
		    out.write(text);  
		    out.close();                                       
		}
		catch (IOException e)
		{
		    System.out.println("Exception ");

		}
		
	}
	public static void main(String[] args) {
		new OrderGUI();
	}
}
