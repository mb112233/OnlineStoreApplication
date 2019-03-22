package EntititesLayer;

public class Products {
	
   private int idproducts;
   private String name;
   private double price;
   private String category;
   private int stock;
   

   public Products(int idproducts, String name, double price, String category,int stock) {

	this.idproducts = idproducts;
	this.name = name;
	this.price = price;
	this.category = category;
	this.stock=stock;
   }

   public String getName() {
	  return name;
    }
   public void setName(String name) {
	 this.name = name;
   }
   public int getIdproducts() {
	return idproducts;
   }
   public void setIdproducts(int idproducts) {
	this.idproducts = idproducts;
   }
   public double getPrice() {
	return price;
   }
   public void setPrice(double price) {
	this.price = price;
   }
   public String getCategory() {
	return category;
   }
   public void setCategory(String category) {
	this.category = category;
   }
   public String toString() {
	return "Product [id="+this.idproducts+" price="+this.price+" category="+this.category+"]";
   }

   public int getStock() {
	return stock;
   }

   public void setStock(int stock) {
	this.stock = stock;
   }
   
  }
