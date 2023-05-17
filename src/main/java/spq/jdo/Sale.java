package spq.jdo;


/**
 * Definition of a Sale.
 */
public class Sale {
	
	/*
	 *The name of the buyer
	 */
    private String buyer;
    
    /*
	 *The product being sold
	 */
    private Product prod;

    /**
     * Constructs a Sale with the given buyer and product.
     * 
     * @param buyer the name of the buyer
     * @param prod the product being sold
     */
    public Sale(String buyer, Product prod) {
        this.buyer = buyer;
        this.prod = prod;
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
     * Gets the product being sold.
     * 
     * @return the product being sold
     */
    public Product getProduct() {
        return prod;
    }

    /**
     * Sets the product being sold.
     * 
     * @param prod the product being sold
     */
    public void setProduct(Product prod) {
        this.prod = prod;
    }

    /**
     * Returns a string representation of the Sale.
     * 
     * @return a string representation of the Sale
     */
    @Override
    public String toString() {
        return "Sale [buyer=" + buyer + ", prod=" + prod.getName() + "]";
    }
}