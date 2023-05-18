package spq.serialization;

import org.junit.Before;
import org.junit.Test;

import spq.serialization.UserData;

import static org.junit.Assert.assertEquals;

public class UserDataTest {
	/**

	This is a test class for the UserData class.

	It contains test cases for all the public methods of the UserData class.
	*/
    private UserData userData;
    /**

    Sets up the test fixture.
    Called before every test case method.
    */
    @Before
    public void setUp() {
        userData = new UserData("markel", "password", 100.0, 1);
    }
    /**

    
    It tests whether the getName method returns the correct name.
    */

    @Test
    public void testGetName() {
        assertEquals("markel", userData.getName());
    }
    /**

    
    It tests whether the setName method sets the name correctly.
    */
    @Test
    public void testSetName() {
        userData.setName("Jane");
        assertEquals("Jane", userData.getName());
    }
    /**

    .
    It tests whether the getPassword method returns the correct password.
    */

    @Test
    public void testGetPassword() {
        assertEquals("password", userData.getPassword());
    }
    /**

    
    It tests whether the setPassword method sets the password correctly.
    */
    @Test
    public void testSetPassword() {
        userData.setPassword("newPassword");
        assertEquals("newPassword", userData.getPassword());
    }
    /**

    
    It tests whether the getPurse method returns the correct purse balance.
    */
    @Test
    public void testGetPurse() {
    assertEquals(100.0, userData.getPurse(), 0.001);
    }
    /**

    Test case for the setPurse method of the UserData class.
    It tests whether the setPurse method sets the purse balance correctly.
    */
    @Test
    public void testSetPurse() {
    userData.setPurse(50.0);
    assertEquals(50.0, userData.getPurse(), 0.001);
    }
    /**

    Test case for the getType method of the UserData class.
    It tests whether the getType method returns the correct user type.
    */
    @Test
    public void testGetType() {
    assertEquals(1, userData.getType());
    }
    /**

    Test case for the setType method of the UserData class.
    It tests whether the setType method sets the user type correctly.
    */
    @Test
    public void testSetType() {
    userData.setType(2);
    assertEquals(2, userData.getType());
    }
    /**

    Test case for the toString method of the UserData class.
    It tests whether the toString method returns the correct string representation.
    */
    @Test
    public void testToString() {
    String expectedString = "UserData [name=markel, password=password, purse=100.0, type=1]";
    assertEquals(expectedString, userData.toString());
    }
    }