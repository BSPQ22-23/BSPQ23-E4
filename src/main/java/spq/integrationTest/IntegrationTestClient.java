package spq.integrationTest;

import java.util.Iterator;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Extent;
import javax.jdo.Query;
import javax.jdo.JDOHelper;
import javax.jdo.Transaction;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import spq.jdo.Product;
import spq.jdo.User;
import spq.serialitazion.ProductData;
import spq.serialitazion.SaleData;
import spq.serialitazion.UserData;
import spq.windows.LoginWindow;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class IntegrationTestClient {
    private static final String USER = "client";
	private static final String PASSWORD = "password";
    
	protected static final Logger logger = LogManager.getLogger();
    private Client client;
    private WebTarget webTarget;
	
    
    /**
     * Constructor for IntegrationTestClient class that creates a new instance of a web client with a specified hostname and port
     * @param hostname A string representing the hostname of the server
     * @param port A string representing the port number of the server
     */
    
    public IntegrationTestClient(String hostname, String port) {
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
	}
    
    
    /**
     * Registers a new user in the system with the given name, password, purse and type
     * @param name The name of the user to be registered
     * @param password The password of the user to be registered
     * @param purse The initial amount of money in the user's purse
     * @param type The type of the user to be registered (0 for regular user, 1 for administrator)
     * @return A boolean indicating whether the registration was successful or not
     */
    
    
    public boolean registerUser(String name, String password,double purse, int type) {
    	WebTarget registerUserWebTarget = webTarget.path("register");
    	Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
		
    	UserData userData =new UserData();
    	userData.setName(name);
    	userData.setPassword(password);
    	userData.setPurse(purse);
    	userData.setType(type);
    	Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
    	if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
			return false;
		} else {
			logger.info("User correctly registered");
			return true;
		}
    	
    
    }
    

    public static void main(String[] args) {
		if (args.length != 2) {
			logger.info("Use: java Client.Client [host] [port]");
			System.exit(0);
			
			 
		}
		

		String hostname = args[0];
		String port = args[1];
		
		IntegrationTestClient newclient= new IntegrationTestClient(hostname, port);
		newclient.registerUser(USER, PASSWORD, 23, 0);
		
		
    }
}
