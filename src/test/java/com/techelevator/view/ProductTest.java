package com.techelevator.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ProductTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Product#Product(String, String, double, String)}
     *   <li>{@link Product#setPrice(double)}
     *   <li>{@link Product#setProductLocation(String)}
     *   <li>{@link Product#setProductName(String)}
     *   <li>{@link Product#setProducts(Map)}
     *   <li>{@link Product#setType(String)}
     *   <li>{@link Product#getPrice()}
     *   <li>{@link Product#getProductLocation()}
     *   <li>{@link Product#getProductName()}
     *   <li>{@link Product#getProducts()}
     *   <li>{@link Product#getType()}
     * </ul>
     */
    @Test
    public void testConstructor() {
        Product actualProduct = new Product("Product Location", "Product Name", 10.0d, "Type");
        actualProduct.setPrice(10.0d);
        actualProduct.setProductLocation("Product Location");
        actualProduct.setProductName("Product Name");
        HashMap<String, Double> stringResultDoubleMap = new HashMap<>();
        actualProduct.setProducts(stringResultDoubleMap);
        actualProduct.setType("Type");
        assertEquals(10.0d, actualProduct.getPrice(), 0.0);
        assertEquals("Product Location", actualProduct.getProductLocation());
        assertEquals("Product Name", actualProduct.getProductName());
        assertSame(stringResultDoubleMap, actualProduct.getProducts());
        assertEquals("Type", actualProduct.getType());
    }

    /**
     * Method under test: {@link Product#Product(String, String, double, String)}
     */
    @Test
    public void testConstructor2() {
        Product actualProduct = new Product("Product Location", "Product Name", 10.0d, "Type");

        assertEquals(10.0d, actualProduct.getPrice(), 0.0);
        assertEquals("Type", actualProduct.getType());
        assertEquals("5", actualProduct.getQuantity());
        assertEquals("Product Name", actualProduct.getProductName());
        assertEquals("Product Location", actualProduct.getProductLocation());
    }

    /**
     * Method under test: {@link Product#getQuantity()}
     */
    @Test
    public void testGetQuantity() {
        assertEquals("5", (new Product("Product Location", "Product Name", 10.0d, "Type")).getQuantity());
    }

    /**
     * Method under test: {@link Product#setQuantity(int)}
     */
    @Test
    public void testSetQuantity() {
        Product product = new Product("Product Location", "Product Name", 10.0d, "Type");
        assertTrue(product.setQuantity(1));
        assertEquals("1", product.getQuantity());
    }

    /**
     * Method under test: {@link Product#setQuantity(int)}
     */
    @Test
    public void testSetQuantity2() {
        assertFalse((new Product("Product Location", "Product Name", 10.0d, "Type")).setQuantity(-1));
    }

    /**
     * Method under test: {@link Product#setQuantity(int)}
     */
    @Test
    public void testSetQuantity3() {
        assertFalse((new Product("Product Location", "Product Name", 10.0d, "Type")).setQuantity(102719));
    }

    /**
     * Method under test: {@link Product#sound()}
     */
    @Test
    public void testSound() {
        Product product = new Product("Product Location", "Product Name", 10.0d, "Type");
        product.sound();
        assertEquals(10.0d, product.getPrice(), 0.0);
        assertEquals("Type", product.getType());
        assertEquals("5", product.getQuantity());
        assertEquals("Product Name", product.getProductName());
        assertEquals("Product Location", product.getProductLocation());
    }

    /**
     * Method under test: {@link Product#sound()}
     */
    @Test
    public void testCandySound() {
        Product product = new Product("Product Location", "Product Name", 10.0d, "candy");
        product.sound();
        assertEquals(10.0d, product.getPrice(), 0.0);
        assertEquals("candy", product.getType());
        assertEquals("5", product.getQuantity());
        assertEquals("Product Name", product.getProductName());
        assertEquals("Product Location", product.getProductLocation());
    }

    /**
     * Method under test: {@link Product#sound()}
     */
    @Test
    public void testChipSound() {
        Product product = new Product("Product Location", "Product Name", 10.0d, "chip");
        product.sound();
        assertEquals(10.0d, product.getPrice(), 0.0);
        assertEquals("chip", product.getType());
        assertEquals("5", product.getQuantity());
        assertEquals("Product Name", product.getProductName());
        assertEquals("Product Location", product.getProductLocation());
    }

    /**
     * Method under test: {@link Product#sound()}
     */
    @Test
    public void testDrinkSound() {
        Product product = new Product("Product Location", "Product Name", 10.0d, "drink");
        product.sound();
        assertEquals(10.0d, product.getPrice(), 0.0);
        assertEquals("drink", product.getType());
        assertEquals("5", product.getQuantity());
        assertEquals("Product Name", product.getProductName());
        assertEquals("Product Location", product.getProductLocation());
    }

    /**
     * Method under test: {@link Product#sound()}
     */
    @Test
    public void testGumSound() {
        Product product = new Product("Product Location", "Product Name", 10.0d, "gum");
        product.sound();
        assertEquals(10.0d, product.getPrice(), 0.0);
        assertEquals("gum", product.getType());
        assertEquals("5", product.getQuantity());
        assertEquals("Product Name", product.getProductName());
        assertEquals("Product Location", product.getProductLocation());
    }
}

