package Junit;

import static org.junit.Assert.*;

import org.junit.Test;

import spq.serialitazion.UserCredentials;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UserCredentialsTest {

    private UserCredentials userCredentials;
    
    @Before
    public void setUp() {
        userCredentials = new UserCredentials("Mikel", "oldPassword", "newPassword");
    }

    @Test
    public void testGetName() {
        assertEquals("Mikel", userCredentials.getName());
    }

    @Test
    public void testSetName() {
        userCredentials.setName("Jane");
        assertEquals("Jane", userCredentials.getName());
    }

    @Test
    public void testGetOldPassword() {
        assertEquals("oldPassword", userCredentials.getOldPassword());
    }

    @Test
    public void testSetOldPassword() {
        userCredentials.setOldPassword("newOldPassword");
        assertEquals("newOldPassword", userCredentials.getOldPassword());
    }

    @Test
    public void testGetNewPassword() {
        assertEquals("newPassword", userCredentials.getNewPassword());
    }

    @Test
    public void testSetNewPassword() {
        userCredentials.setNewPassword("newNewPassword");
        assertEquals("newNewPassword", userCredentials.getNewPassword());
    }
}
