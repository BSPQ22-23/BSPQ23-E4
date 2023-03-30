package spq.server;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.JDOHelper;
import javax.jdo.Transaction;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.Logger;

import spq.jdo.User;
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
				logger.info("Setting password user: {}", user);
				user.setPassword(userData.getPassword());
				logger.info("Password set user: {}", user);
				
				logger.info("Setting purse user: {}", user);
				user.setPurse(userData.getPurse());
				logger.info("Purse set user:{}", user);
				
				logger.info("Setting type user: {}", user);
				user.setType(0);
				logger.info("Type set user:{}", user);
				
			}else {
				logger.info("Creating user: {}", user);
				user= new User(userData.getName(),userData.getPassword(),userData.getPurse(),0);
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
	
	
	/*@POST
	@Path("/login")
	public Response loginUser(UserData userData) {
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
				logger.info("Setting password user: {}", user);
				user.setPassword(userData.getPassword());
				logger.info("Password set user: {}", user);
				
				logger.info("Setting purse user: {}", user);
				user.setPurse(userData.getPurse());
				logger.info("Purse set user:{}", user);
				
				logger.info("Setting type user: {}", user);
				user.setType(0);
				logger.info("Type set user:{}", user);
				
			}
			tx.commit();
			return Response.ok().build();

			
		}finally {
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
		}
	}*/
	
	@POST
	@Path("/login")
	public Response login(UserData userData) {
	    try {
	        tx.begin();
	        logger.info("Checking user credentials for user: '{}'",userData.getName());
	        User user = pm.getObjectById(User.class, userData.getName());
	        if (user == null) {
	            logger.info("User not found: '{}'", userData.getName());
	            return Response.status(Response.Status.UNAUTHORIZED).build();
	        } else if (user.getPassword().equals(userData.getPassword())) {
	            logger.info("User credentials are valid: '{}'", userData.getName());
	            return Response.ok().build();
	        } else {
	            logger.info("User credentials are invalid for user: '{}'", userData.getName());
	            return Response.status(Response.Status.UNAUTHORIZED).build();
	        }
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
