package spq.server;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.Logger;

import spq.jdo.Product;
import spq.jdo.User;
import spq.serialitazion.ProductData;
import spq.serialitazion.UserCredentials;
import spq.serialitazion.UserData;

import org.apache.logging.log4j.LogManager;
@Path("/resource")
@Produces(MediaType.APPLICATION_JSON)

public class Server {
	
	protected static final Logger logger = LogManager.getLogger();
	private int cont = 0;
	private PersistenceManager pm=null;
	private Transaction tx=null;

	public Server() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = pmf.getPersistenceManager();
		this.tx = pm.currentTransaction();
	}
	
	
	@POST
	@Path("/register")
	public Response registerUser(UserData userData) {
		try {
			tx.begin();
			logger.info("Checking whether the user already exits or not: '{}'",userData.getName());
			User user=null;
			try {
				user= pm.getObjectById(User.class,userData.getName());
			}catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}
			logger.info("User: {}",user);
			if (user!=null) {
				logger.info("Se tting password user: {}", user);
				user.setPassword(userData.getPassword());
				logger.info("Password set user: {}", user);
				
				logger.info("Setting purse user: {}", user);
				user.setPurse(userData.getPurse());
				logger.info("Purse set user:{}", user);
				
				logger.info("Setting type user: {}", user);
				user.setType(userData.getType());
				logger.info("Type set user:{}", user);
				
			}else {
				logger.info("Creating user: {}", user);
				user= new User(userData.getName(),userData.getPassword(),userData.getPurse(),userData.getType());
				pm.makePersistent(user);
				logger.info("User created: {}", user);
			}
			tx.commit();
			return Response.ok().build();

			
		}finally {
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
		}
	}
	
	
	@POST
	@Path("/add")
	public Response addProduct(ProductData productData) {
		try {
			tx.begin();
			logger.info("Checking whether the product already exits or not: '{}'",productData.getName());
			Product product=null;
			try {
				product= pm.getObjectById(Product.class,productData.getName());
			}catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}
			logger.info("Product: {}",product);
			if (product!=null) {
				logger.info("Setting price product: {}", product);
				product.setPrice(productData.getPrice());
				logger.info("Price set product: {}", product);
				
				logger.info("Setting available product: {}", product);
				product.setAvailable(productData.isAvailable());
				logger.info("Available set product:{}", product);
				
			}else {
				logger.info("Creating product: {}", product);
				product = new Product(productData.getName(), productData.getPrice(), productData.isAvailable());
				pm.makePersistent(product);
				logger.info("Product created: {}", product);
			}
			tx.commit();
			return Response.ok().build();

			
		}finally {
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
		}
	}@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

		
		public Response login(UserData userData) {
		    try {
		        PersistenceManager pm = JDOHelper.getPersistenceManagerFactory("datanucleus.properties").getPersistenceManager();
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
	
	
//	    try {
//	        tx.begin();
//	        logger.info("Checking user credentials for user: '{}'",userData.getName());
//	        User user = pm.getObjectById(User.class, userData.getName());
//	        if (user == null) {
//	            logger.info("User not found: '{}'", userData.getName());
//	            return Response.status(Response.Status.UNAUTHORIZED).build();
//	        } else if (user.getPassword().equals(userData.getPassword())) {
//	            logger.info("User credentials are valid: '{}'", userData.getName());
//	            return Response.ok().build();
//	            
//	        } else {
//	            logger.info("User credentials are invalid for user: '{}'", userData.getName());
//	            return Response.status(Response.Status.UNAUTHORIZED).build();
//	        }
//	    } finally {
//	        if (tx.isActive()) {
//	            tx.rollback();
//	        }
//	    }
	

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

	@PUT
	@Path("/updatePurse/{name}/{amount}")
	public Response updatePurse(@PathParam("name") String name, @PathParam("amount") double amount) {
		try {
			tx.begin();
			User user = pm.getObjectById(User.class, name);
			if (user != null) {
				user.setPurse(amount);
				pm.makePersistent(user);
				tx.commit();
				logger.info("Purse updated for user '{}'", user.getName());
				return Response.ok().build();
			} else {
				logger.error("User with name '{}' not found", name);
				return Response.status(Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			logger.error("Error updating purse for user '{}': {}", name, e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
	}

	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public Response sayHello() {
		return Response.ok("Hello world!").build();
	}
	
	
}
