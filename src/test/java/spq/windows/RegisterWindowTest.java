package spq.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import spq.windows.RegisterWindow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;

public class RegisterWindowTest {
    private RegisterWindow registerWindow;
    private JTextField textoNombre;
    private JTextField textoPurse;
    private JPasswordField textoContrasenya;
    private JButton btnRegister;
    private JButton btnLogIn;
    /*
     *n 
     * ITS EXAMPLE TEST
     *n
     */
    @Before
    public void setUp() {
        registerWindow = new RegisterWindow();
        /*
        JPanel contentpane = registerWindow.getContentPane();
        textoNombre = (JTextField) TestUtils.getChildNamed(contentpane, "textoNombre");
        textoPurse = (JTextField) TestUtils.getChildNamed(contentpane, "textoPurse");
        textoContrasenya = (JPasswordField) TestUtils.getChildNamed(contentpane, "textoContrasenya");
        btnRegister = (JButton) TestUtils.getChildNamed(contentpane, "btnRegister");
        btnLogIn = (JButton) TestUtils.getChildNamed(contentpane, "btnLogIn");
        */
    }

    @Test
    public void testRegisterWindowCreation() {
        assertNotNull("Register window is null", registerWindow);
    }

    @Test
    public void testUserNameAndPasswordFieldsExist() {
        assertNotNull("Name field is null", textoNombre);
        assertNotNull("Purse field is null", textoPurse);
        assertNotNull("Password field is null", textoContrasenya);
    }

    @Test
    public void testRegisterButtonExists() {
        assertNotNull("Register button is null", btnRegister);
    }

    @Test
    public void testLogInButtonExists() {
        assertNotNull("Log in button is null", btnLogIn);
    }

    @Test
    public void testUserInformationRetrieval() {
        String username = "testuser";
        String password = "testpassword";
        double purseAmount = 123.45;

        textoNombre.setText(username);
        textoContrasenya.setText(password);
        textoPurse.setText(String.valueOf(purseAmount));

        assertEquals("Username not retrieved correctly", username, textoNombre.getText());
        assertEquals("Password not retrieved correctly", password, String.valueOf(textoContrasenya.getPassword()));
        assertEquals("Purse amount not retrieved correctly", purseAmount, Double.parseDouble(textoPurse.getText()), 0.01);
    }

    
    @Test
    public void testRegisterButtonAction() {
        // Set up a mock client
        //TheClientMock clientMock = new TheClientMock();
       //registerWindow.setClient(clientMock);

        // Set up user information
        String username = "testuser";
        String password = "testpassword";
        double purseAmount = 123.45;
        DecimalFormat formato = new DecimalFormat("#.00");
        textoNombre.setText(username);
        textoContrasenya.setText(password);
        textoPurse.setText(formato.format(0));
}
    /*
     *n 
     * 
     *n
     */
}