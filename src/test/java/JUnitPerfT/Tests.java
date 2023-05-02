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

public class Tests {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Tests.class);
	private String hostname = "localhost";
	private String port = "8080";
	private TheClient newclient;
	ProductData p = new ProductData("Movil", 234, true);

	@Before
	public void setUp() {
		newclient = new TheClient(hostname, port);
		ProductData p = new ProductData("Movil", 234, true);
	}

	@Rule
	public JUnitPerfRule rule = new JUnitPerfRule(new HtmlReportGenerator("target/junitperf/report.html"));
	@Rule
	public JUnitPerfRule rule1 = new JUnitPerfRule(new HtmlReportGenerator("target/junitperf/report.csv"));
	@Mock
	Server s;

	@Test
	@JUnitPerfTest(durationMs = 3000, threads = 30, warmUpMs = 500, maxExecutionsPerSecond = 500)
	public void testLoginUser() throws Exception {

		String name = "admin";
		String password = "admin";
		assertNotNull(newclient.loginUser(name, password));
	}

	@Test
	@JUnitPerfTest(durationMs = 3000, threads = 30, warmUpMs = 500, maxExecutionsPerSecond = 500)
	public void TestaddProduct() {
		assertNotNull(newclient.addProduct(p.getName(), p.getPrice(), true));
	}
	@Test
    @JUnitPerfTest(threads = 10, durationMs = 5000)
    public void testGetAvailableProducts() {
    	 List<ProductData> products = newclient.getAvailableProducts();
         assertNotNull(products);
    }
}
