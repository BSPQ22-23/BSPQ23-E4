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

public class RegisterWindow extends JFrame {
	private JTextField txtName, txtPurse;
	private JPasswordField txtPassword;
	private JFrame currentWindow, previousWindow;

    public RegisterWindow(JFrame cw) {
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
        
		
    }
    
    
}
