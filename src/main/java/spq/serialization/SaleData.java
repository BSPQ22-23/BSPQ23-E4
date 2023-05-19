package spq.serialization;

/**
 * Class representing sale data.
 */
public class SaleData {
	
	/**
	 * random number generated for the sale id
	 */
	private String saleid;
	
	/*
	 *The name of the buyer
	 */
    private String buyer;
    
    /*
	 *The product being sold
	 */
    private String prod;
    
    /**
     * The price of the product being sold
     */
    private double price;

    /**
	  * Constructs a SaleData object.
	  */
	 public SaleData() {
	     // Required by serialization
	 }
    
    
	 /**
	  * Constructs a SaleData with the given buyer, the id and product name and price
	  * 
	  * @param saleid id of the sale
	  * @param buyer the name of the buyer
	  * @param prod name of the product being sold
	  * @param price price of the product being sold
	  */
    public SaleData(String saleid, String buyer, String prod, double price) {
    	this.saleid = saleid;
        this.buyer = buyer;
        this.prod = prod;
        this.price = price;
    }
    
    /**
     * Gets the id of the sale.
     * 
     * @return the id of the sale
     */
    public String getSaleId() {
    	return saleid;
    }
    
    /**
     * Sets the id of the sale.
     * 
     * @param saleid the id of the sale
     */
    public void setSaleId(String saleid) {
    	this.saleid = saleid;
    }

    /**
     * Gets the name of the buyer.
     * 
     * @return the name of the buyer
     */
    public String getBuyer() {
        return buyer;
    }

    /**
     * Sets the name of the buyer.
     * 
     * @param buyer the name of the buyer
     */
    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    /**
     * Gets the name of the product being sold.
     * 
     * @return the name of the product being sold
     */
    public String getNameProduct() {
        return prod;
    }

    /**
     * Sets the name of the product being sold.
     * 
     * @param prod the name of the product being sold
     */
    public void setNameProduct(String prod) {
        this.prod = prod;
    }
    
    /**
     * Gets the price of the product being sold.
     * 
     * @return the price of the product being sold
     */
    public double getPriceProduct() {
    	return price;
    }
    
    public void setPriceProduct(double price) {
    	this.price = price;
    }

    /**
     * Returns a string representation of the Sale.
     * 
     * @return a string representation of the Sale
     */
    @Override
    public String toString() {
        return "SaleData [buyer=" + buyer + ", name prod=" + prod + ", price product="+ price+ "]";
    }
}