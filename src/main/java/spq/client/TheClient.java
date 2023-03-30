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


import java.awt.EventQueue;
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
	private static final String USER = "clienteparapruebas";
	private static final String PASSWORD = "clienteparapruebas";
    
	protected static final Logger logger = LogManager.getLogger();
    private Client client;
    private WebTarget webTarget;
	
    
    
    public TheClient(String hostname, String port) {
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
	}
    
    public void registerUser(String name, String password,double purse, int type) {
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
			System.out.println("Good");
		}
    	
    
    }
    
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

    public static void main(String[] args) {
		if (args.length != 2) {
			logger.info("Use: java Client.Client [host] [port]");
			System.exit(0);
			
			 
		}

		String hostname = args[0];
		String port = args[1];
		
		/*TheClient newclient= new TheClient(hostname, port);
		newclient.registerUser(USER, PASSWORD, 23, 0);*/
		
		//new LoginWindow();
		/*LoginWindow frame = new LoginWindow();
		frame.setVisible(true);*/
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow(hostname, port);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
    }
    
   
    
}
