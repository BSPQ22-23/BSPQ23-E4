package spq.windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import spq.client.TheClient;

import javax.swing.JButton;
import javax.swing.JTextField;

public class RegisterWindow extends JFrame {
	/*
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
        
		
    }*/
	private static final long serialVersionUID = 1L;

	private JPanel contentpane;
	private JLabel labelNombre = new JLabel();
	private JLabel labelPurse = new JLabel();
	private JLabel labelContrasenya = new JLabel();
	private JTextField textoNombre = new JTextField();
	private JTextField textoPurse = new JTextField();
	private JPasswordField textoContrasenya = new JPasswordField();
	private JButton btnRegister = new JButton();
	private JButton btnLogIn = new JButton();

	public class JNumberTextField extends JTextField {
		private static final long serialVersionUID = 1L;
	}

	public RegisterWindow() {

		contentpane = new JPanel();
		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentpane);
		contentpane.setLayout(null);
		

		JLabel labelTitle = new JLabel("Deusto-Computers");
		labelTitle.setFont(new Font("Roboto", Font.BOLD, 40));
		labelTitle.setBounds(44, 35, 359, 50);
		contentpane.add(labelTitle);
		//GET USERNAME
		labelNombre.setText("Name:");
		labelNombre.setBounds(99, 175, 71, 20);
		labelNombre.setOpaque(true);
		labelNombre.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(labelNombre);
				
		labelPurse.setText("Purse:");
		labelPurse.setBounds(107, 275, 99, 20);
		labelPurse.setOpaque(true);
		labelPurse.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(labelPurse);
		
		labelContrasenya.setText("Password:");
		labelContrasenya.setBounds(86, 375, 120, 20);
		labelContrasenya.setOpaque(true);
		labelContrasenya.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(labelContrasenya);
		
		btnRegister.setForeground(SystemColor.text);
		btnRegister.setBackground(new Color(0, 51, 255));
		btnRegister.setBounds(227, 500, 143, 32);
		btnRegister.setText("Register");
		btnRegister.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(btnRegister);
		
		btnLogIn.setBackground(SystemColor.inactiveCaptionBorder);
		btnLogIn.setBounds(47, 500, 143, 32);
		btnLogIn.setText("<< LogIn");
		btnLogIn.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(btnLogIn);

		textoNombre.setBounds(214, 175, 143, 20);
		contentpane.add(textoNombre);
		String username= textoNombre.getText();
		
		textoPurse.setBounds(214, 275, 143, 20);
		contentpane.add(textoPurse);

		textoContrasenya.setBounds(214, 375, 143, 20);
		contentpane.add(textoContrasenya);
		String user_password=textoContrasenya.getText();

		/**
		 * Button to register a new user in the database using the method register user (with its rules and everything)
		 */
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String hostname="localhost";
				String  port = "8080";
				double purse = Double.parseDouble(textoPurse.getText());
				DecimalFormat formato = new DecimalFormat("#.00");
				textoPurse.addKeyListener(new KeyAdapter() {
				    public void keyReleased(KeyEvent e) {
				        try {
				            textoPurse.setText(formato.format(purse));
				        } catch (NumberFormatException ex) {
				            // el texto ingresado no es un número válido
				        }
				    }
				});
				TheClient newclient= new TheClient(hostname, port);
				newclient.registerUser(textoNombre.getText(),textoContrasenya.getText(), purse, 0);
			}
		});
		
	
		/**
		 * Button to return to the main window
		 */
		btnLogIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new LoginWindow();
				dispose();
			
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(440, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Register as a new customer");
	}
    
}