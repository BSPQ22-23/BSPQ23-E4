package spq.windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

public class LoginWindow extends JFrame {
	private JTextField txtName;
	private JPasswordField txtPassword;
	private JFrame currentWindow;

    public LoginWindow() {
        super("My Window"); // Set the title of the window
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the application when the window is closed
        setSize(400, 300); // Set the size of the window
        setLocationRelativeTo(null); // Center the window on the screen
        
        JPanel panelNorth = new JPanel();
        getContentPane().add(panelNorth, BorderLayout.NORTH);
        
        JLabel lblWelcome = new JLabel("Welcome to TechShop");
        lblWelcome.setHorizontalAlignment(SwingConstants.LEFT);
        panelNorth.add(lblWelcome);
        
        JPanel panelSouth = new JPanel();
        getContentPane().add(panelSouth, BorderLayout.SOUTH);
        
        JButton btnLogin = new JButton("Login");
        panelSouth.add(btnLogin);
        
        JButton btnRegister = new JButton("Register");
        panelSouth.add(btnRegister);
        
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new GridLayout(0, 2, 0, 0));
        getContentPane().add(panelCenter, BorderLayout.CENTER);
        
        JLabel lblName = new JLabel("Enter your name");
        panelCenter.add(lblName);
        
        txtName = new JTextField();
        panelCenter.add(txtName);
        txtName.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        panelCenter.add(lblNewLabel_1);
        
        txtPassword = new JPasswordField();
        panelCenter.add(txtPassword);
        txtPassword.setColumns(10);
        setVisible(true); // Show the window
        
        btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterWindow(currentWindow);
				currentWindow.setVisible(false);
			}
		});
        
		
    }
    
    
}
