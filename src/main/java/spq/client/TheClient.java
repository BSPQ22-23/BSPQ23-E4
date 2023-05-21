/**********************************************************************
Copyright (c) 2003 Andy Jefferson and others. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Contributors:
    ...
**********************************************************************/
package spq.client;


import java.util.Iterator;

import java.util.List;
import java.util.Random;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Extent;
import javax.jdo.Query;
import javax.jdo.JDOHelper;
import javax.jdo.Transaction;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import spq.jdo.Product;
import spq.jdo.Sale;
import spq.jdo.User;
import spq.serialization.ProductData;
import spq.serialization.SaleData;
import spq.serialization.UserData;
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

/**
 * Controlling application for the DataNucleus Tutorial using JDO.
 * Relies on the user defining a file "datanucleus.properties" to be in the CLASSPATH
 * and to include the JDO properties for the DataNucleus PersistenceManager.
 * @author      Diego Lopez-de-Ipina 
 * @version     1.0                                    
 * @since       2012-02-01        
 */
public class TheClient
{
	//coment and exampl
	private static final String USER = "client";
	private static final String PASSWORD = "password";
    
	protected static final Logger logger = LogManager.getLogger();
    private Client client;
    private WebTarget webTarget;
	
    
    /**
     * Constructor for TheClient class that creates a new instance of a web client with a specified hostname and port
     * @param hostname A string representing the hostname of the server
     * @param port A string representing the port number of the server
     */
    
    public TheClient(String hostname, String port) {
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
	}
    
    /*public void registerUser(String name, String password,double purse, int type) {
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
		} else {
			logger.info("User correctly registered");
		}
    	
    
    }*/
    
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
    
    /**
     * Adds a new sale with the specified buyer and product to the server.
     *
     * @param buyer the name of the buyer
     * @param p the product being sold
     */

    public boolean addSale(String buyer, Product p) {
        WebTarget addSaleWebTarget = webTarget.path("add-sale");
        Invocation.Builder invocationBuilder = addSaleWebTarget.request(MediaType.APPLICATION_JSON);

        SaleData saleData =new SaleData();
        String saleid = generateRandomSaleId();
        saleData.setSaleId(saleid);
    	saleData.setBuyer(buyer);
    	saleData.setNameProduct(p.getName());
    	saleData.setPriceProduct(p.getPrice());
    	
    	Response response = invocationBuilder.post(Entity.entity(saleData, MediaType.APPLICATION_JSON));
    	if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
			return false;
		} else {
			logger.info("Sale correctly registered");
			return true;
		}
    }

    /**
     * Generates a random sale ID consisting of 5 alphanumeric characters (numbers and uppercase letters).
     *
     * @return a randomly generated sale ID
     */
    private String generateRandomSaleId() {
        int length = 5;
        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    
    /**
     * Add a new product to the inventory
     * @param name The name of the product
     * @param price The price of the product
     * @param available Whether the product is available or not
     * @return True if the product was successfully added, false otherwise
     */
    
    public boolean addProduct(String name, double price, boolean available) {
    	WebTarget addProductWebTarget = webTarget.path("add");
    	Invocation.Builder invocationBuilder = addProductWebTarget.request(MediaType.APPLICATION_JSON);
		
    	ProductData productData =new ProductData();
    	productData.setName(name);
    	productData.setPrice(price);
    	productData.setAvailable(available);
    	Response response = invocationBuilder.post(Entity.entity(productData, MediaType.APPLICATION_JSON));
    	if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
			return false;
		} else {
			logger.info("User correctly registered");
			return true;
		}
    	
    
    }
    
    /**
     * Retrieves the list of available products from the server
     * @return a list of ProductData objects representing the available products, or null if an error occurred
     */
    public List<ProductData> getAvailableProducts() {
        WebTarget getAvailableProductsWebTarget = webTarget.path("available");
        Invocation.Builder invocationBuilder = getAvailableProductsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        if (response.getStatus() != Status.OK.getStatusCode()) {
            logger.error("Error connecting with the server. Code: {}", response.getStatus());
            return null;
        } else {
            List<ProductData> products = response.readEntity(new GenericType<List<ProductData>>() {});
            logger.info("Available products: {}", products);
            return products;
        }
    }
    
    public List<UserData> getAvailableUsers(){
    	WebTarget getAvailableUsersWebTarget = webTarget.path("getAvailableUsers");
    	Invocation.Builder invocationBuilder= getAvailableUsersWebTarget.request(MediaType.APPLICATION_JSON);
    	Response response = invocationBuilder.get();
    	 if (response.getStatus() != Status.OK.getStatusCode()) {
             logger.error("Error connecting with the server. Code: {}", response.getStatus());
             return null;
         } else {
             List<UserData> users = response.readEntity(new GenericType<List<UserData>>() {});
             logger.info("Available products: {}", users);
             return users;
         }
    }
    
    
    public List<SaleData> getSalesUser(User u) {
        WebTarget getSalesUserWebTarget = webTarget.path("sales");
        Invocation.Builder invocationBuilder = getSalesUserWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        if (response.getStatus() != Status.OK.getStatusCode()) {
            logger.error("Error connecting with the server. Code: {}", response.getStatus());
            return null;
        } else {
            List<SaleData> sales = response.readEntity(new GenericType<List<SaleData>>() {});
            logger.info("Sales of this user: {}", sales);
            return sales;
        }
    }

    

  //Client
    

    
    /**
     * Logs in a user with the given name and password, and returns the corresponding User object
     * @param name the name of the user
     * @param password the password of the user
     * @return the User object of the logged in user, or null if there was an error logging in
     */
    
    public User loginUser(String name, String password) {
	    WebTarget loginUserWebTarget = webTarget.path("login");
	    Invocation.Builder invocationBuilder = loginUserWebTarget.request(MediaType.APPLICATION_JSON);
	
	    UserData userData = new UserData();
	    userData.setName(name);
	    userData.setPassword(password);
	
	    Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
	    if (response.getStatus() != Status.OK.getStatusCode()) {
	        logger.error("Error connecting with the server. Code: {}", response.getStatus());
	        return null; 
	    } else {
	        User userDataResponse = response.readEntity(User.class);
	        logger.info("User correctly logged in");
	        return userDataResponse;
	    }
	}
    
    /**
     * Changes the password of a user
     * @param user The user whose password will be changed
     * @param newPassword The new password to set for the user
     */
    
    public void changeUserPassword(User user, String newPassword) {
        WebTarget changePasswordWebTarget = webTarget.path("changePassword");
        Invocation.Builder invocationBuilder = changePasswordWebTarget.request(MediaType.APPLICATION_JSON);

        UserData userData = new UserData();
        userData.setName(user.getName());
        userData.setPassword(newPassword);
        userData.setPurse(user.getPurse());
        userData.setType(user.getType());
        
        

        Response response = invocationBuilder.put(Entity.entity(userData, MediaType.APPLICATION_JSON));
        if (response.getStatus() != Status.OK.getStatusCode()) {
            logger.error("Error connecting with the server. Code: {}", response.getStatus());
        } else {
            logger.info("User password correctly updated");
            // update the user object with the new password
            user.setPassword(newPassword);
        }
    }
    

    
    
    /**
     * Delete a user from the server
     * @param user2 The user to be deleted
     */
    
    public void deleteUser(User user) {
        WebTarget deleteUserWebTarget = webTarget.path("deleteUser");
        Invocation.Builder invocationBuilder = deleteUserWebTarget.request(MediaType.APPLICATION_JSON);
        
        Response response = invocationBuilder.delete();
        if (response.getStatus() != Status.OK.getStatusCode()) {
            logger.error("Error connecting with the server. Code: {}", response.getStatus());
        } else {
            logger.info("User {} correctly deleted", user.getName());
        }
    }
    
    public void deleteUserTable(UserData user) {
    	WebTarget deleteUserTableWebTarget = webTarget.path("deleteUserTable");
        Invocation.Builder invocationBuilder = deleteUserTableWebTarget.request(MediaType.APPLICATION_JSON);
        
        Response response = invocationBuilder.delete();
        if (response.getStatus() != Status.OK.getStatusCode()) {
            logger.error("Error connecting with the server. Code: {}", response.getStatus());
        } else {
            logger.info("User {} correctly deleted", user.getName());
        }
    }
    /**
     * Check if a user exists in the database by their name
     * @param name The name of the user to check
     * @return True if the user exists in the database, false otherwise
     */
    
    public boolean checkUserInDatabase(String name) {
        WebTarget checkUserWebTarget = webTarget.path("users").queryParam("name", name);
        Invocation.Builder invocationBuilder = checkUserWebTarget.request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.get();
        if (response.getStatus() != Status.OK.getStatusCode()) {
            logger.error("Error connecting with the server. Code: {}", response.getStatus());
            return false;
        } else {
            List<UserData> users = response.readEntity(new GenericType<List<UserData>>() {});
            if (users.size() == 0) {
                logger.info("User {} not found in database", name);
                return false;
            } else {
                logger.info("User {} found in database", name);
                return true;
            }
        }
    }
    public void updatePurse(User user,Double amount,Double purse) {
       	WebTarget updatePurseWebTarget= webTarget.path("updatePurse").queryParam("amount",amount).queryParam("purse",purse);
       	 Invocation.Builder invocationBuilder= updatePurseWebTarget.request(MediaType.APPLICATION_JSON);
       	 Response response = invocationBuilder.post(Entity.entity(user, MediaType.APPLICATION_JSON));
       	 if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                logger.info("Purse actualizado");
                
            } else {
                logger.info("Error buying product: " + response.readEntity(String.class));
                
            }
       }

    
    /**
     * 
     * @param user
     * @param price
     * @param name
     * @return
     */
    
    public boolean buyProduct(User user,Double price,String name) {
        WebTarget buyProductWebTarget= webTarget.path("buyProduct").queryParam("price", price).queryParam("name", name);
        Invocation.Builder invocationBuilder= buyProductWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(user, MediaType.APPLICATION_JSON));
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            System.out.println("Product bought successfully.");
            return true;
        } else {
            System.out.println("Error buying product: " + response.readEntity(String.class));
            return false;
        }
    }

    public static void main(String[] args) {
		if (args.length != 2) {
			logger.info("Use: java Client.Client [host] [port]");
			System.exit(0);
			
			 
		}
		

		String hostname = args[0];
		String port = args[1];
		
		TheClient newclient= new TheClient(hostname, port);
		newclient.registerUser(USER, PASSWORD, 23, 0);
		newclient.registerUser("admin", "admin", 0, 1);
		Product p = new Product("Product 1", 12.12, true);
		newclient.addSale("a",p);
		new LoginWindow();
		
		
    }
    
   
    
}
