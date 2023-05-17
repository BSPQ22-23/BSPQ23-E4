package spq.serialitazion;

import spq.serialitazion.ProductData;

/**
 * Class representing sale data.
 */
public class SaleData {
	
	/*
	 *The name of the buyer
	 */
    private String buyer;
    
    /*
	 *The product being sold
	 */
    private ProductData prod;

    /**
	  * Constructs a SaleData object.
	  */
	 public SaleData() {
	     // Required by serialization
	 }
    
    /**
     * Constructs a Sale with the given buyer and product.
     * 
     * @param buyer the name of the buyer
     * @param prod the product being sold
     */
    public SaleData(String buyer, ProductData prod) {
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
    public ProductData getProduct() {
        return prod;
    }

    /**
     * Sets the product being sold.
     * 
     * @param prod the product being sold
     */
    public void setProduct(ProductData prod) {
        this.prod = prod;
    }

    /**
     * Returns a string representation of the Sale.
     * 
     * @return a string representation of the Sale
     */
    @Override
    public String toString() {
        return "SaleData [buyer=" + buyer + ", prod=" + prod.getName() + "]";
    }
}