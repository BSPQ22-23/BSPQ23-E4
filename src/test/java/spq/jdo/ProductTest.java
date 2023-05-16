package spq.jdo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * This class contains unit tests for the Product class.
 */
public class ProductTest {
	private Product p;
    
	/**
     * Set up the test environment by initializing a Product object with test data.
     * This method is executed before each test case.
     */
    @Before
    public void setUp() {
        p = new Product("PC-Dell", 10.5,false);
    }

    /**
     * Test case to verify the correctness of the getName() method.
     */
    @Test
    public void testGetName() {
        assertEquals("PC-Dell", p.getName());
    }

    /**
     * Test case to verify the correctness of the setName() method.
     */
    @Test
    public void testSetName() {
        p.setName("PC-MSI");
        assertEquals("PC-MSI", p.getName());
    }

    /**
     * Test case to verify the correctness of the getPrice() method.
     */
    @Test
    public void testGetPrice() {
        assertEquals(10.5, p.getPrice(), 0.0);
    }

    /**
     * Test case to verify the correctness of the setPrice() method.
     */
    @Test
    public void testSetPrice() {
        p.setPrice(20.5);
        assertEquals(20.5, p.getPrice(), 0.0);
    }

    /**
     * Test case to verify the correctness of the toString() method.
     */
    @Test
    public void testToString() {
        assertEquals("Product [name= PC-Dell, price= 10.5, available= false]", p.toString());
    }
}
