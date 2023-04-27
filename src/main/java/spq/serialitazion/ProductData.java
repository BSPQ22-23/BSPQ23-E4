package spq.serialitazion;

public class ProductData {
	 private String name;
	 private double price;
	 private boolean available;
	 public ProductData() {
	        // required by serialization
	    }
	 
	public ProductData(String name, double price, boolean available) {
		super();
		this.name = name;
		this.price = price;
		this.available = available;
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
	
	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "ProductData [name= " + name + ", price= " + price + ", available= " + available +"]";
	}
	 
}
