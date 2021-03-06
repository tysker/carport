/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import functionLayer.Product;
import dbAccess.Connector;
import dbAccess.ProductMapper;
import functionLayer.LogicFacade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jesper
 */
public class ProductMapperTest {

    private static Connection testConnection;
    private static final String USER = "joerg";
    private static final String USERPW = "joerg/3085";
    private static final String DBNAME = "carportTest";
    private static final String HOST = "142.93.173.199";

    @Before
    public void setUp() {
        try {
            // avoid making a new connection for each test
            if (testConnection == null || testConnection.isClosed()) {
                String url = String.format("jdbc:mysql://%s:3306/%s", HOST, DBNAME);
                Class.forName("com.mysql.jdbc.Driver");
                testConnection = DriverManager.getConnection(url, USER, USERPW);
                // Make mappers use test 
                Connector.setConnection(testConnection);
            }
            //Reset database
            try (Statement stmt = testConnection.createStatement()) {
                stmt.execute("DROP TABLE IF EXISTS product;");
                stmt.execute("CREATE TABLE product LIKE productTest;");
                stmt.execute("INSERT into product SELECT * from productTest;");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

//     TODO add test methods here.
//     The methods must be annotated with annotation @Test. For example:
    
    
    @Test
    public void testSetUpOK() {
        // Just check that we have a connection.
        assertNotNull(testConnection);
    }

    @Test
    public void testGetAllProducts() {
        //Arrange
        ProductMapper pm = new ProductMapper();

        List<Product> product = pm.allProducts();

        //Assert
        assertTrue(product.size() > 2);
    }

    @Test
    public void testUpdatePrice() {
        //Arrange
        ProductMapper pm = new ProductMapper();
        //Act
        double actual = 200.00;
        LogicFacade.updatePrice(1, actual);

        Product expected = pm.getProductByID(1);

        //Assert
        assertEquals(expected.getPrice(), actual, 0.5);
    }

    @Test
    public void testAddProduct() {
        //Arrange
        ProductMapper pm = new ProductMapper();
        Product p = new Product("testProduct", "testCategory", 200.0, 200.0, 200.0, 200.0);

        //Act
        List<Product> productListBefore = pm.allProducts();
        pm.addProducts(p);
        List<Product> productListAfter = pm.allProducts();

        //Assert
        assertTrue(productListBefore.size() < productListAfter.size());
    }

    @Test
    public void testDeleteProduct() {
        //Arrange
        ProductMapper pm = new ProductMapper();

        //Act
        List<Product> productListBefore = pm.allProducts();
        pm.deleteProduct(1);
        List<Product> productListAfter = pm.allProducts();

        //Assert
        assertTrue(productListBefore.size() > productListAfter.size());
    }
    
    @Test
    public void testSearchProduct() {
        //Arrange
        List<Product> listOfProducts = LogicFacade.searchInDatabaseProductTable("rem");
        
        //Act
        Product product = listOfProducts.get(0);
        
        //Assert
        assertTrue(product.getCategory().equals("rem"));
    }
    
    @Test
    public void testGetProductById() {
        //Arrange
        ProductMapper pm = new ProductMapper();
        Product product = pm.getProductByID(1);
        
        //Act
        
        //Assert
        assertNotNull(product);
        
    }
    
    @Test
    public void testGetAllRemLength() {
        //Arrange
        List<Product> list = LogicFacade.remOrderedByLength();
        
        //Act
        
        
        //Assert
        assertTrue(list.size() > 2);
    }
    

}
