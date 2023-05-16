package spq.serialitazion;

/**
 * Class representing product data.
 */
public class ProductData {
	 private String name;
	 private double price;
	 private boolean available;
	 
	 /**
	  * Constructs a ProductData object.
	  */
	 public ProductData() {
	     // Required by serialization
	 }
	 
	 /**
	  * Constructs a ProductData object with the specified parameters.
	  * 
	  * @param name The name of the product.
	  * @param price The price of the product.
	  * @param available The availability of the product.
	  */
	public ProductData(String name, double price, boolean available) {
		super();
		this.name = name;
		this.price = price;
		this.available = available;
	}

	/**
	 * Gets the name of the product.
	 * 
	 * @return The name of the product.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the product.
	 * 
	 * @param name The name of the product.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the price of the product.
	 * 
	 * @return The price of the product.
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Sets the price of the product.
	 * 
	 * @param price The price of the product.
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Checks if the product is available.
	 * 
	 * @return true if the product is available, false otherwise.
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * Sets the availability of the product.
	 * 
	 * @param available The availability of the product.
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "ProductData [name= " + name + ", price= " + price + ", available= " + available + "]";
	}
}
