package EntititesLayer;

public class OrdersTable {
	
    private int idorders;
    private String shippingStatus;
    private int amount;
    
	public OrdersTable(int idorders, String shippingStatus, int amount) {
	
		this.idorders = idorders;
		this.shippingStatus = shippingStatus;
		this.amount=amount;
	}

	public int getIdorders() {
		return idorders;
	}
    public void setIdorders(int idorders) {
		this.idorders = idorders;
	}
    public String getShippingStatus() {
		return shippingStatus;
	}
    public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}
    public String toString() {
    	return "Order [id="+this.idorders+" shippingStatus="+this.shippingStatus+"]";
    }

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
