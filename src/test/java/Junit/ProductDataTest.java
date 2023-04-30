package Junit;

import static org.junit.Assert.*;

import org.junit.Test;

import spq.serialitazion.ProductData;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ProductDataTest {

    private ProductData productData;
    
    @Before
    public void setUp() {
        productData = new ProductData("PC-Dell", 10.5,false);
    }

    @Test
    public void testGetName() {
        assertEquals("PC-Dell", productData.getName());
    }

    @Test
    public void testSetName() {
        productData.setName("PC-MSI");
        assertEquals("PC-MSI", productData.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(10.5, productData.getPrice(), 0.0);
    }

    @Test
    public void testSetPrice() {
        productData.setPrice(20.5);
        assertEquals(20.5, productData.getPrice(), 0.0);
    }

    @Test
    public void testToString() {
        assertEquals("ProductData [name=PC-Dell, price=10.5]", productData.toString());
    }
}
