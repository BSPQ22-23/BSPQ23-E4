package Junit;

import org.junit.Before;
import org.junit.Test;

import spq.serialitazion.UserData;

import static org.junit.Assert.assertEquals;

public class UserDataTest {
    
    private UserData userData;
    
    @Before
    public void setUp() {
        userData = new UserData("markel", "password", 100.0, 1);
    }

    @Test
    public void testGetName() {
        assertEquals("markel", userData.getName());
    }

    @Test
    public void testSetName() {
        userData.setName("Jane");
        assertEquals("Jane", userData.getName());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password", userData.getPassword());
    }

    @Test
    public void testSetPassword() {
        userData.setPassword("newPassword");
        assertEquals("newPassword", userData.getPassword());
    }

    @Test
    public void testGetPurse() {
        assertEquals(100.0, userData.getPurse(), 0.001);
    }

    @Test
    public void testSetPurse() {
        userData.setPurse(50.0);
        assertEquals(50.0, userData.getPurse(), 0.001);
    }

    @Test
    public void testGetType() {
        assertEquals(1, userData.getType());
    }

    @Test
    public void testSetType() {
        userData.setType(2);
        assertEquals(2, userData.getType());
    }

    @Test
    public void testToString() {
        String expectedString = "UserData [name=markel, password=password]";
        assertEquals(expectedString, userData.toString());
    }
}
