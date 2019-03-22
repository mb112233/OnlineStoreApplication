package BuildJTable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BuildJtable{
	
	private  List<Object> list;
	public static ArrayList<String> columnNames = null;
	public static Object[][] matrix;
	
	public BuildJtable() {
		list=new ArrayList<Object>();
		
	}
	
    /**
     * This method gets the instance variables of Object o.
     * @param o
     * @return ArrayList<String> an array of String objects representing the instance variables of Object o. 
     */
    public static ArrayList<String> getColumnNames(Object o)  {
    	columnNames=new ArrayList<String>();
    	for (Field field : o.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				String value =field.getName();
				columnNames.add(value);
				

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
    	}
    	return columnNames;
    }
    /**
     * This method constructs an 2D matrix with the instance fields of each object from list parameter.
     * @param list
     * @return Object[][] matrix 
     */
    public static Object[][] makeTable(ArrayList<?> list) {
  
    	 int i=0;
    	 int j=0;
    	 Object o=list.get(0);
    	 Class<? extends Object> cls=o.getClass();
    	 ArrayList<?> c=getColumnNames(o);
    	 matrix=new Object[list.size()][c.size()];
    	 
    	for (Object m :list) {
        	Field[] fields=cls.getDeclaredFields();
        	j=0;
    		for (Field n:fields) {
    			n.setAccessible(true);
    			Object value;
    			try {
    				value=n.get(m);
    				matrix[i][j]=value;
    				j++;
    			}catch(Exception e) {
    				e.printStackTrace();;
    			}
    		}
    		i++;
    	}
    	return matrix;
    }
   
}
