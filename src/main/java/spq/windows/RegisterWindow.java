package spq.windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import spq.client.TheClient;

import javax.swing.JButton;
import javax.swing.JTextField;

public class RegisterWindow extends JFrame {
	private JTextField txtName, txtPurse;
	private JPasswordField txtPassword;
	private JFrame currentWindow, previousWindow;
	
	
    public RegisterWindow(JFrame cw, String hostname, String port) {
        super("Register Window"); // Set the title of the window
        previousWindow = cw;
		currentWindow = this;
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the application when the window is closed
        setSize(400, 300); // Set the size of the window
        setLocationRelativeTo(null); // Center the window on the screen
        
        JPanel panelNorth = new JPanel();
        getContentPane().add(panelNorth, BorderLayout.NORTH);
        
        JLabel lblWelcome = new JLabel("Please enter your data");
        lblWelcome.setHorizontalAlignment(SwingConstants.LEFT);
        panelNorth.add(lblWelcome);
        
        JPanel panelSouth = new JPanel();
        getContentPane().add(panelSouth, BorderLayout.SOUTH);
        
        JButton btnRegister = new JButton("Register");
        panelSouth.add(btnRegister);
        
        JButton btnBack = new JButton("Return to Login");
        panelSouth.add(btnBack);
        
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new GridLayout(3, 2, 0, 0));
        getContentPane().add(panelCenter, BorderLayout.CENTER);
        
        JLabel lblName = new JLabel("Enter your name");
        panelCenter.add(lblName);
        
        txtName = new JTextField();
        panelCenter.add(txtName);
        txtName.setColumns(10);
        
        JLabel lblPassword = new JLabel("Enter your password");
        panelCenter.add(lblPassword);
        
        txtPassword = new JPasswordField();
        panelCenter.add(txtPassword);
        txtPassword.setColumns(10);
        
        JLabel lblPurse = new JLabel("Enter your starting money");
        panelCenter.add(lblPurse);
        
        txtPurse = new JTextField();
        panelCenter.add(txtPurse);
        txtPurse.setColumns(10);
        setVisible(true); // Show the window
        
        btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentWindow.dispose();
				previousWindow.setVisible(true);
			}
		});
        btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ERnick = "[A-Za-z]{1,15}";
				String nick = txtName.getText();
				String contraseya = txtPassword.getText();
				String ERcontraseya = "{4,15}";
				double purse = Double.parseDouble(txtPurse.getText());
				boolean correctoContra = Pattern.matches(ERcontraseya, contraseya);
				boolean correctoNick = Pattern.matches(ERnick, nick);
				if((correctoNick) && (correctoContra)) {
					
					TheClient newclient= new TheClient(hostname, port);
					newclient.registerUser(nick, contraseya, purse, 0);
				}
				txtName.setText("");
				txtPassword.setText("");
				txtPurse.setText("");
			}
		});	
        
        setVisible(true);
        
		
    }

	
    
    
}
