/*package spq.client;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import spq.client.TheClient;
import spq.serialitazion.ProductData;
import spq.serialitazion.UserData;

public class TheClientTest {
    @Mock
    private WebTarget webTarget;
    
    @InjectMocks
    private TheClient TheClient;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testAddProduct() throws Exception {
        ProductData productData = new ProductData();
        productData.setName("Test Product");
        productData.setPrice(9.99);
        productData.setAvailable(true);
        
        Response response = mock(Response.class);
        when(response.getStatus()).thenReturn(Status.OK.getStatusCode());
        
        Invocation.Builder builder = mock(Invocation.Builder.class);
        when(builder.post(Entity.entity(productData, MediaType.APPLICATION_JSON))).thenReturn(response);
        
        when(webTarget.path("add")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
        
        TheClient.addProduct(productData.getName(), productData.getPrice(), productData.isAvailable());
        
        verify(webTarget).path("add");
        verify(webTarget).request(MediaType.APPLICATION_JSON);
        verify(builder).post(Entity.entity(productData, MediaType.APPLICATION_JSON));
    }
    
    @Test
    public void testRegisterUser() {
    	UserData userData = new UserData();
        userData.setName("user");
        userData.setPassword("contra");
        userData.setPurse(12.34);
        userData.setType(0);
        
        Response response = mock(Response.class);
        when(response.getStatus()).thenReturn(Status.OK.getStatusCode());
        
        Invocation.Builder builder = mock(Invocation.Builder.class);
        when(builder.post(Entity.entity(userData, MediaType.APPLICATION_JSON))).thenReturn(response);
        
        when(webTarget.path("add")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
        
        TheClient.registerUser(userData.getName(), userData.getPassword(), userData.getPurse(), userData.getType());
        
        verify(webTarget).path("add");
        verify(webTarget).request(MediaType.APPLICATION_JSON);
        verify(builder).post(Entity.entity(userData, MediaType.APPLICATION_JSON));
    }

}

*/

