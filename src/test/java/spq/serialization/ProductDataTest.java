package spq.serialization;

import static org.junit.Assert.*;

import org.junit.Test;

import spq.serialitazion.ProductData;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ProductDataTest {

	/**
	 * 
	 * @file ProductDataTest.java
	 * @brief Unit tests for the ProductData class.
	 * @author [Your Name]
	 * @date [Current Date]
	 */

	private ProductData productData;

	/**
	 * 
	 * @brief Set up the ProductData object for testing.
	 */
	@Before
	public void setUp() {
		productData = new ProductData("PC-Dell", 10.5, false);
	}

	/**
	 * 
	 * @brief Test the getName() method.
	 */
	@Test
	public void testGetName() {
		assertEquals("PC-Dell", productData.getName());
	}
	/**

	@brief Test the setName() method.
	*/
	@Test
	public void testSetName() {
		productData.setName("PC-MSI");
		assertEquals("PC-MSI", productData.getName());
	}
	/**

	@brief Test the getPrice() method.
	*/
	@Test
	public void testGetPrice() {
		assertEquals(10.5, productData.getPrice(), 0.0);
	}
	/**

	@brief Test the setPrice() method.
	*/
	@Test
	public void testSetPrice() {
		productData.setPrice(20.5);
		assertEquals(20.5, productData.getPrice(), 0.0);
	}
	

	@Test
	public void testToString() {
		assertEquals("ProductData [name= PC-Dell, price= 10.5, available= false]", productData.toString());
	}
}
