package JUnitPerfT;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import spq.client.TheClient;
import spq.jdo.Product;
import spq.serialitazion.ProductData;
import spq.server.Server;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.slf4j.LoggerFactory;

import com.github.noconnor.junitperf.JUnitPerfRule;
import com.github.noconnor.junitperf.JUnitPerfTest;
import com.github.noconnor.junitperf.JUnitPerfTestRequirement;
import com.github.noconnor.junitperf.reporting.providers.HtmlReportGenerator;

/**
 * The `Tests` class contains JUnit tests to verify the functionality of the `TheClient` class.
 * 
 * @author [Author Name]
 * @version [Version Number]
 */
public class Tests {
    
    /** The logger for this class. */
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Tests.class);
    
    /** The hostname of the server to connect to. */
    private String hostname = "localhost";
    
    /** The port number of the server to connect to. */
    private String port = "8080";
    
    /** The client to use for testing. */
    private TheClient newclient;
    
    /** A product data object to use for testing. */
    ProductData p = new ProductData("Movil", 234, true);

    /**
     * Set up the test environment by creating a new client and product data object.
     */
    @Before
    public void setUp() {
        newclient = new TheClient(hostname, port);
        ProductData p = new ProductData("Movil", 234, true);
    }

    /** 
     * A rule that generates a JUnitPerf report in HTML format.
     */
    @Rule
    public JUnitPerfRule rule = new JUnitPerfRule(new HtmlReportGenerator("target/junitperf/report.html"));
    
    /** 
     * A rule that generates a JUnitPerf report in CSV format.
     */
    @Rule
    public JUnitPerfRule rule1 = new JUnitPerfRule(new HtmlReportGenerator("target/junitperf/report.csv"));
    
    /** 
     * A mocked server to use in testing.
     */
    @Mock
    Server s;
    
    /**
     * Test the `loginUser` method of the `TheClient` class.
     * 
     * @throws Exception if an error occurs while running the test
     */
    @Test
    @JUnitPerfTest(durationMs = 3000, threads = 30, warmUpMs = 500, maxExecutionsPerSecond = 500)
    public void testLoginUser() throws Exception {

        // Set up test data
        String name = "admin";
        String password = "admin";
        
        // Perform the test
        assertNotNull(newclient.loginUser(name, password));
    }

    /**
     * Test the `addProduct` method of the `TheClient` class.
     */
    @Test
    @JUnitPerfTest(durationMs = 3000, threads = 30, warmUpMs = 500, maxExecutionsPerSecond = 500)
    public void TestaddProduct() {
        
        // Perform the test
        assertNotNull(newclient.addProduct(p.getName(), p.getPrice(), true));
    }
    
    /**
     * Test the `getAvailableProducts` method of the `TheClient` class.
     */
    @Test
    @JUnitPerfTest(threads = 10, durationMs = 5000)
    public void testGetAvailableProducts() {
        
        // Perform the test
        List<ProductData> products = newclient.getAvailableProducts();
        assertNotNull(products);
    }
}
