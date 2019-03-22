package BuildJTable;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class JTableGUI {
	
    JFrame frame;
	JTable table;
	private JScrollPane scrollPane ;
	
    /**
     * This constructor takes a 2D Object matrix and makes a JTable with it. It also added on a frame 
     * with a scroll panel. 
     * @param o
     */
	public JTableGUI(Object[][] o) {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		table=new JTable(BuildJtable.matrix,BuildJtable.columnNames.toArray());
		scrollPane = new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		frame.add(scrollPane);
		frame.pack();
	}
	
	
}
