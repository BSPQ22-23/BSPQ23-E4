package spq.server;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.Transaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.Logger;

import spq.jdo.Product;
import spq.jdo.Sale;
import spq.jdo.User;
import spq.serialization.ProductData;
import spq.serialization.UserCredentials;
import spq.serialization.UserData;

import org.apache.logging.log4j.LogManager;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

@Path("/resource")
@Produces(MediaType.APPLICATION_JSON)

public class Server {

	/**
	 * The logger used to log information about the HTTP requests and responses.
	 */
	protected static final Logger logger = LogManager.getLogger();

	/**
	 * The counter used to assign a unique ID to each product.
	 */
	private int cont = 0;

	/**
	 * The PersistenceManager used to manage the JDO objects.
	 */
	private PersistenceManager pm = null;

	/**
	 * The Transaction used to manage the database transactions.
	 */
	private Transaction tx = null;

	/**
	 * The SecurityContext used to manage the authentication and authorization of
	 * the users.
	 */
	@Context
	private SecurityContext securityContext;

	/**
	 * The constructor of the Server class. It initializes the PersistenceManager
	 * and Transaction objects.
	 */
	public Server() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = pmf.getPersistenceManager();
		this.tx = pm.currentTransaction();
	}

	/**
	 * The registerUser method is a HTTP POST request that registers a new user in
	 * the database or updates an existing one. If the user already exists, the
	 * method updates the user's password, purse, and type with the values in the
	 * userData object. If the user does not exist, the method creates a new user
	 * with the values in the userData object.
	 * 
	 * @param userData The UserData object that contains the information about the
	 *                 user to register or update.
	 * @return A HTTP response with a status code 200 (OK) if the operation was
	 *         successful, or a HTTP response with a status code 400 (BAD REQUEST)
	 *         if there was an error during the operation.
	 */
	@POST
	@Path("/register")
	public Response registerUser(UserData userData) {
		try {
			tx.begin();
			logger.info("Checking whether the user already exits or not: '{}'", userData.getName());
			User user = null;
			try {
				user = pm.getObjectById(User.class, userData.getName());
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}
			logger.info("User: {}", user);
			if (user != null) {
				logger.info("Setting password user: {}", user);
				user.setPassword(userData.getPassword());
				logger.info("Password set user: {}", user);

				logger.info("Setting purse user: {}", user);
				user.setPurse(userData.getPurse());
				logger.info("Purse set user:{}", user);

				logger.info("Setting type user: {}", user);
				user.setType(userData.getType());
				logger.info("Type set user:{}", user);

			} else {
				logger.info("Creating user: {}", user);
				user = new User(userData.getName(), userData.getPassword(), userData.getPurse(), userData.getType());
				pm.makePersistent(user);
				logger.info("User created: {}", user);
			}
			tx.commit();
			return Response.ok().build();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
	}
	
	// @POST
	// @Path("/addsale")
	// public Response addSale(String buyer, ProductData prodData) {
	// 	try {
	// 		tx.begin();
	// 		logger.info("Trying to add new sale for: '{}'", buyer);
	// 		Product p = new Product(prodData.getName(), prodData.getPrice(), prodData.isAvailable());
	// 		Sale sale = new Sale(buyer, p);
	// 		logger.info("Creating sale");
	// 		pm.makePersistent(sale);
	// 		logger.info("Sale created");
			
	// 		tx.commit();
	// 		return Response.ok().build();

	// 	} finally {
	// 		if (tx.isActive()) {
	// 			tx.rollback();
	// 		}
	// 	}
	// }

	/**
	 * 
	 * @brief Buy a product for a user. This method allows a user to buy a product
	 *        if they have sufficient balance in their purse.
	 * @param userData The user data.
	 * @param price    The price of the product.
	 * @param name     The name of the product.
	 * @return A response indicating whether the purchase was successful or not. If
	 *         the purchase was successful, a response with status code 200 OK is
	 *         returned. If the user data is invalid, a response with status code
	 *         400 BAD REQUEST and an error message is returned. If the product is
	 *         not found, a response with status code 400 BAD REQUEST and an error
	 *         message is returned. If the user does not have sufficient balance, a
	 *         response with status code 400 BAD REQUEST and an error message is
	 *         returned.
	 * @see User
	 * @see Product
	 */
	@POST
	@Path("/buyProduct")
	public Response buyProduct(UserData userData, @QueryParam("price") Double price, @QueryParam("name") String name) {
		tx.begin();

		User user = pm.getObjectById(User.class, userData.getName());
		Product product = pm.getObjectById(Product.class, name);
		if (user == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Invalid user data").build();
		}

		if (product == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Product not found").build();
		}
		if (user.canBuyProduct(product.getPrice()) == true) {
			user.reducePurse(product.getPrice());
			product.setAvailable(false);
			tx.commit();
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).entity("Insufficient balance").build();
		}
	}
	
	@POST
	@Path("/updatePurse")
	public Response updatePurse(UserData userData ,@QueryParam("amount") double amount,@QueryParam("purse") double purse) {
		
		tx.begin();

		User user = pm.getObjectById(User.class, userData.getName());
		if (user == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Invalid user data").build();
		}

	
		
			user.setPurse(purse + amount);
			pm.makePersistent(user);
		
			tx.commit();
			return Response.ok().build();
		
		
	}

	/**
	 * 
	 * Adds or updates a product to the system with the given information.
	 * 
	 * @param productData the information about the product to add or update.
	 * @return a Response object indicating the result of the operation.
	 * @throws javax.jdo.JDOObjectNotFoundException if the product to update does
	 *                                              not exist.
	 * @throws RuntimeException                     if there is an error persisting
	 *                                              the product data.
	 */
	@POST
	@Path("/add")
	public Response addProduct(ProductData productData) {
		try {
			tx.begin();
			logger.info("Checking whether the product already exits or not: '{}'", productData.getName());
			Product product = null;
			try {
				product = pm.getObjectById(Product.class, productData.getName());
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}
			logger.info("Product: {}", product);
			if (product != null) {
				logger.info("Setting price product: {}", product);
				product.setPrice(productData.getPrice());
				logger.info("Price set product: {}", product);

				logger.info("Setting available product: {}", product);
				product.setAvailable(productData.isAvailable());
				logger.info("Available set product:{}", product);

			} else {
				logger.info("Creating product: {}", product);
				product = new Product(productData.getName(), productData.getPrice(), productData.isAvailable());
				pm.makePersistent(product);
				logger.info("Product created: {}", product);
			}
			tx.commit();
			return Response.ok().build();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
	}

	/**
	 * 
	 * Authenticates a user with the provided credentials by querying the database.
	 * 
	 * @param userData the user data containing the user name and password
	 * 
	 * @return a response containing the user data if authentication was successful,
	 *         or a 401 unauthorized response otherwise
	 */
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response login(UserData userData) {
		try {
			PersistenceManager pm = JDOHelper.getPersistenceManagerFactory("datanucleus.properties")
					.getPersistenceManager();
			Query query = pm.newQuery(User.class, "name == :name");
			query.setUnique(true);
			User user = (User) query.execute(userData.getName());
			if (user == null) {
				return Response.status(Response.Status.UNAUTHORIZED).build();
			} else if (user.getPassword().equals(userData.getPassword())) {
				return Response.ok(user).build();
			} else {
				return Response.status(Response.Status.UNAUTHORIZED).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	/**
	 * 
	 * Deletes the user with the given name from the database.
	 * 
	 * @param userData the user data containing the name of the user to be deleted.
	 * @return a response indicating whether the user was successfully deleted or
	 *         not.
	 */

	@DELETE
	@Path("/deleteUser")
	public Response deleteUser(UserData userData) {
		try {
			tx.begin();
			User user = pm.getObjectById(User.class, userData.getName());
			if (user != null) {
				pm.deletePersistent(user);
				tx.commit();
				logger.info("User '{}' deleted", user.getName());
				return Response.ok().build();
			} else {
				logger.error("User with name '{}' not found", userData.getName());
				return Response.status(Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			logger.error("Error deleting user '{}': {}", userData.getName(), e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
	}

	/**
	 * 
	 * Changes the password of the user with the given name.
	 * 
	 * @param name     the name of the user whose password needs to be changed
	 * @param userData the updated user data containing the new password
	 * @return a Response object indicating whether the operation was successful or
	 *         not
	 */
	@PUT
	@Path("/changePassword")
	public Response changePassword(@PathParam("name") String name, UserData userData) {
		try {
			tx.begin();
			User user = pm.getObjectById(User.class, name);
			if (user != null) {
				user.setPassword(userData.getPassword());
				pm.makePersistent(user);
				tx.commit();
				logger.info("Password changed for user '{}'", user.getName());
				return Response.ok().build();
			} else {
				logger.error("User with name '{}' not found", name);
				return Response.status(Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			logger.error("Error changing password for user '{}': {}", name, e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
	}

	


	/**
	 * 
	 * @brief Retrieves a list of available products.
	 * 
	 *        This function sends a GET request to the "/available" endpoint of the
	 *        server to retrieve a list of available products.
	 * 
	 *        The list of products is filtered by the "available" flag in the
	 *        database.
	 * 
	 * @return A list of ProductData objects representing the available products.
	 * 
	 * @throws WebApplicationException if an error occurs while processing the
	 *                                 request.
	 * 
	 * @see ProductData
	 * 
	 */
	@GET
	@Path("/available")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductData> getAvailableProducts() {
		List<ProductData> products = new ArrayList<>();
		try {
			tx.begin();
			Query<Product> query = pm.newQuery(Product.class, "available == true");
			List<Product> results = (List<Product>) query.execute();
			for (Product product : results) {
				ProductData productData = new ProductData();
				productData.setName(product.getName());
				productData.setPrice(product.getPrice());
				if (product.isAvailable() == true) {
					productData.setAvailable(product.isAvailable());
					products.add(productData);
				}
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return products;
	}
	
	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public Response sayHello() {
		return Response.ok("Hello world!").build();
	}

}
