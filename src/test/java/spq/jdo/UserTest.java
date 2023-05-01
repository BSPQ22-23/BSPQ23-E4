package spq.jdo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.mockito.Mockito;
import org.mockito.MockedStatic;

import org.junit.Before;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class UserTest {
    
    private static ZonedDateTime timestamp = ZonedDateTime.of(2023, 03, 23, 19, 15, 22, 0, ZoneId.of("Europe/Madrid"));
    private User user;

    @Before
    public void setUp() {
        user = new User("test-login", "passwd", 12.34, 0);
    }

    @Test
    public void testGetName() {
        assertEquals("test-login", user.getName());
    }

    @Test
    public void testGetPassword() {
        assertEquals("passwd", user.getPassword());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("newpasswd");
        assertEquals("newpasswd", user.getPassword());
    }

    @Test
    public void testGetPurse() {
    	assertEquals(12.34, user.getPurse(), 0.01);
    }
    
    @Test
    public void testSetPurse() {
    	double p = user.getPurse() + 12.00;
        user.setPurse(p);
        assertEquals(p, user.getPurse() , 0.01);
    }
    
    @Test
    public void testGetType() {
        int type = 0;
        assertEquals(type, user.getType());
    }


    @Test
    public void testToString() {
        String expectedString = "Name = test-login, Password = passwd, Your money = 12.34";
        assertEquals(expectedString, user.toString());
    }
    
    
}
