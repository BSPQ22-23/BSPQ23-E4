package spq.client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import spq.client.TheClient;
import spq.jdo.User;
import spq.serialitazion.ProductData;
import spq.serialitazion.UserData;

public class TheClientTest {
	@Mock
    private Client client;

    @Mock(answer=Answers.RETURNS_DEEP_STUBS)
    private WebTarget webTarget;

    @Captor
    private ArgumentCaptor<Entity<UserData>> userDataEntityCaptor;

    @Captor
    private ArgumentCaptor<Entity<ProductData>> productDataEntityCaptor;

    private TheClient cli;
    
    /*@Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }*/
    
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // prepare static mock of ClientBuilder
        try (MockedStatic<ClientBuilder> clientBuilder = Mockito.mockStatic(ClientBuilder.class)) {
            clientBuilder.when(ClientBuilder::newClient).thenReturn(client);
            when(client.target("http://localhost:8080/rest/resource")).thenReturn(webTarget);

            cli = new TheClient("localhost", "8080");
        }
    }
    
    @Test
    public void testRegisterUser() {
        when(webTarget.path("register")).thenReturn(webTarget);

        Response response = Response.ok().build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
        assertTrue(cli.registerUser("test-login", "passwd", 12.34, 0));

        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(userDataEntityCaptor.capture());
        assertEquals("test-login", userDataEntityCaptor.getValue().getEntity().getName());
        assertEquals("passwd", userDataEntityCaptor.getValue().getEntity().getPassword());
        assertEquals(12.34, userDataEntityCaptor.getValue().getEntity().getPurse(), 0);
        assertEquals(0, userDataEntityCaptor.getValue().getEntity().getType());
        
    }
    
    @Test
    public void testAddProduct() {
        when(webTarget.path("add")).thenReturn(webTarget);

        Response response = Response.ok().build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
        assertTrue(cli.addProduct("Prod", 12.23, true));

        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(productDataEntityCaptor.capture());
        assertEquals("Prod", productDataEntityCaptor.getValue().getEntity().getName());
        assertEquals(12.23, productDataEntityCaptor.getValue().getEntity().getPrice(), 0);
        assertEquals(true, productDataEntityCaptor.getValue().getEntity().isAvailable());
        
   }
    
    /*@Test
    public void testLoginUser() {
	    when(webTarget.path("login")).thenReturn(webTarget);
	    User user = new User("admin", "admin", 0, 1);
	    Response response = webTarget.request().get();
	    when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
    	cli.registerUser("admin", "admin", 0, 1);
    	
	
	    User result = cli.loginUser("admin", "admin");
	
	    assertEquals("admin", result.getName());
	    assertEquals("admin", result.getPassword());
	    assertEquals(0, result.getPurse(), 0);
	    assertEquals(1, result.getType());
	
	    verify(webTarget.request(MediaType.APPLICATION_JSON)).post(userDataEntityCaptor.capture());
	    assertEquals("admin", userDataEntityCaptor.getValue().getEntity().getName());
	    assertEquals("admin", userDataEntityCaptor.getValue().getEntity().getPassword());
	    assertEquals(0, userDataEntityCaptor.getValue().getEntity().getPurse(),0);
	    assertEquals(1, userDataEntityCaptor.getValue().getEntity().getType());
    }*/
    
   @Test
    public void testGetAvailableProducts() {
        // Configurar el comportamiento de la simulación
        when(webTarget.path("available")).thenReturn(webTarget);
        Response response = webTarget.request().get();
        when(webTarget.request(MediaType.APPLICATION_JSON).get()).thenReturn(response);

        // Agregar un producto
        cli.addProduct("Prod", 12.23, true);

        // Ejecutar el método y comprobar el resultado
        List<ProductData> products = cli.getAvailableProducts();
        assertNull(products);

        // Verificar la invocación de método en el cliente web
        verify(webTarget.request(MediaType.APPLICATION_JSON)).get();
    }



    
}



