package spq.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.jdo.JDOHelper;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import spq.jdo.User;
import spq.serialization.UserData;




public class ServerTest {

    private Server server;

    @Mock
    private PersistenceManager persistenceManager;

    @Mock
    private Transaction transaction;

    /**
     * Set up the test environment by initializing the required objects and dependencies.
     * This method is executed before each test case.
     * It initializes the mock objects, such as the PersistenceManagerFactory, and configures their behavior.
     * The tested object, Server, is instantiated with the mock dependencies.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // initializing static mock object PersistenceManagerFactory
        try (MockedStatic<JDOHelper> jdoHelper = Mockito.mockStatic(JDOHelper.class)) {
            PersistenceManagerFactory pmf = mock(PersistenceManagerFactory.class);
            jdoHelper.when(() -> JDOHelper.getPersistenceManagerFactory("datanucleus.properties")).thenReturn(pmf);
            
            when(pmf.getPersistenceManager()).thenReturn(persistenceManager);
            when(persistenceManager.currentTransaction()).thenReturn(transaction);

            // instantiate tested object with mock dependencies
            server = new Server();
        }
    }

    /**
     * This method tests the registration of a user in the server.
     * It verifies that the user is correctly registered and that the password is set.
     * It also checks the response status of the registration process.
     */
    @Test
    public void testRegisterUser() {
        // prepare mock Persistence Manager to return User
        UserData userData = new UserData();
        userData.setName("user");
        userData.setPassword("pwd");
        userData.setPurse(12.34);
        userData.setType(0);

        // simulate that 
        User user = spy(User.class);
        when(persistenceManager.getObjectById(User.class, userData.getName())).thenReturn(user);

        // call tested method
        Response response = server.registerUser(userData);

        // check that the user is set by the code with the password
        ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);
        verify(user).setPassword(passwordCaptor.capture());
        assertEquals("pwd", passwordCaptor.getValue());

        // check expected response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }
}