package EntititesLayer;

public class OrderProduct {
    private int idordeproduct;
    private int idorder;
    private int idproduct;
    
    
    
	public OrderProduct(int idordeproduct, int idorder, int idproduct) {
		
		this.idordeproduct = idordeproduct;
		this.idorder = idorder;
		this.idproduct = idproduct;
	}
	public int getIdordeproduct() {
		return idordeproduct;
	}
	public void setIdordeproduct(int idordeproduct) {
		this.idordeproduct = idordeproduct;
	}
	public int getIdorder() {
		return idorder;
	}
	public void setIdorder(int idorder) {
		this.idorder = idorder;
	}
	public int getIdproduct() {
		return idproduct;
	}
	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}
    
    
}
