package JUnitPerfT;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import spq.client.TheClient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.github.noconnor.junitperf.JUnitPerfRule;
import com.github.noconnor.junitperf.JUnitPerfTest;
import com.github.noconnor.junitperf.JUnitPerfTestRequirement;
import com.github.noconnor.junitperf.reporting.providers.HtmlReportGenerator;

import spq.serialitazion.ProductData;


public class Tests {
	  private Client client;
	    private WebTarget webTarget;
	    String hostname="localhost";
		String  port = "8080";
		TheClient newclient= new TheClient(hostname, port);
	@Rule
	 public JUnitPerfRule rule = new JUnitPerfRule(new HtmlReportGenerator("target/junitperf/report.html"));
	
	
	
	    @Rule
	    public JUnitPerfRule perfRule = new JUnitPerfRule();

	    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Tests.class);

	    @Test
	    @JUnitPerfTest(durationMs = 3000, threads = 30, warmUpMs = 500, maxExecutionsPerSecond = 500)
	    public void testLoginUser() {
	       
	        String name = "admin";
	        String password = "admin";
	        
			newclient.loginUser(name, password);
	        
	        assertNotNull(newclient.loginUser(name, password));
	    }
	    @Test
	    @JUnitPerfTest(threads = 10, durationMs = 5000)
	    @JUnitPerfTestRequirement(executionsPerSec = 200, allowedErrorPercentage = (float) 0.05)
	    public void testGetAvailableProducts() {
	    	 List<ProductData> products = newclient.getAvailableProducts();
	         assertNotNull(products);
	    }
	    }
	    
	  

