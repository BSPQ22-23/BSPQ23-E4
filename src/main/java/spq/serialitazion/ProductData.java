package spq.serialitazion;

public class ProductData {
	 private String name;
	 private double price;
	 public ProductData() {
	        // required by serialization
	    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "ProductData [name=" + name + ", price=" + price + "]";
	}
	 
}
