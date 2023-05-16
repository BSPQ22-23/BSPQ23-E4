package spq.jdo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.mockito.Mockito;
import org.mockito.MockedStatic;

import org.junit.Before;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * This class contains unit tests for the User class.
 */
public class UserTest {
    
    private static ZonedDateTime timestamp = ZonedDateTime.of(2023, 03, 23, 19, 15, 22, 0, ZoneId.of("Europe/Madrid"));
    private User user;

    /**
     * Set up the test environment by initializing a User object with test data.
     * This method is executed before each test case.
     */
    @Before
    public void setUp() {
        user = new User("test-login", "passwd", 12.34, 0);
    }

    /**
     * Test case to verify the correctness of the getName() method.
     */
    @Test
    public void testGetName() {
        assertEquals("test-login", user.getName());
    }

    /**
     * Test case to verify the correctness of the getPassword() method.
     */
    @Test
    public void testGetPassword() {
        assertEquals("passwd", user.getPassword());
    }

    /**
     * Test case to verify the correctness of the setPassword() method.
     */
    @Test
    public void testSetPassword() {
        user.setPassword("newpasswd");
        assertEquals("newpasswd", user.getPassword());
    }

    /**
     * Test case to verify the correctness of the getPurse() method.
     */
    @Test
    public void testGetPurse() {
    	assertEquals(12.34, user.getPurse(), 0.01);
    }
    
    /**
     * Test case to verify the correctness of the setPurse() method.
     */
    @Test
    public void testSetPurse() {
    	double p = user.getPurse() + 12.00;
        user.setPurse(p);
        assertEquals(p, user.getPurse() , 0.01);
    }
    
    /**
     * Test case to verify the correctness of the getType() method.
     */
    @Test
    public void testGetType() {
        int type = 0;
        assertEquals(type, user.getType());
    }

    /**
     * Test case to verify the correctness of the toString() method.
     */
    @Test
    public void testToString() {
        String expectedString = "Name = test-login, Password = passwd, Your money = 12.34";
        assertEquals(expectedString, user.toString());
    }
    
    
}
