package spq.jdo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class ProductTest {
	private Product p;
    
    @Before
    public void setUp() {
        p = new Product("PC-Dell", 10.5,false);
    }

    @Test
    public void testGetName() {
        assertEquals("PC-Dell", p.getName());
    }

    @Test
    public void testSetName() {
        p.setName("PC-MSI");
        assertEquals("PC-MSI", p.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(10.5, p.getPrice(), 0.0);
    }

    @Test
    public void testSetPrice() {
        p.setPrice(20.5);
        assertEquals(20.5, p.getPrice(), 0.0);
    }

    @Test
    public void testToString() {
        assertEquals("p [name=PC-Dell, price=10.5]", p.toString());
    }
}
