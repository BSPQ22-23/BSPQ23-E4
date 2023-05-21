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

public class MainWindowAdmin extends JFrame{
	private static final long serialVersionUID = 1L;

	private JPanel contentpane;
	private JLabel labelName = new JLabel();
	private JLabel labelPassword = new JLabel();
	private JTextField textName = new JTextField();
	private JPasswordField textPassword = new JPasswordField();
	private JButton btnRegister = new JButton();
	private JButton btnLogOut = new JButton();
	private JButton btnSeeUsers= new JButton();

	public class JNumberTextField extends JTextField {
		private static final long serialVersionUID = 1L;
	}

	public MainWindowAdmin() {

		contentpane = new JPanel();
		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentpane);
		contentpane.setLayout(null);
		

		JLabel labelTitle = new JLabel("Insert the information of the new admin");
		labelTitle.setFont(new Font("Roboto", Font.BOLD, 40));
		labelTitle.setBounds(44, 35, 359, 50);
		contentpane.add(labelTitle);

		//GET USERNAME
		labelName.setText("Name:");
		labelName.setBounds(99, 175, 71, 20);
		labelName.setOpaque(true);
		labelName.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(labelName);
		
		labelPassword.setText("Password:");
		labelPassword.setBounds(86, 375, 120, 20);
		labelPassword.setOpaque(true);
		labelPassword.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(labelPassword);
		
		btnRegister.setForeground(SystemColor.text);
		btnRegister.setBackground(new Color(0, 51, 255));
		btnRegister.setBounds(227, 500, 190, 32);
		btnRegister.setText("Register new Admin");
		btnRegister.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(btnRegister);
		
		btnSeeUsers.setBackground(SystemColor.inactiveCaptionBorder);
		btnSeeUsers.setBounds(47, 500, 143, 32);
		btnSeeUsers.setText("See Users");
		btnSeeUsers.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(btnSeeUsers);
		
		btnLogOut.setBackground(SystemColor.inactiveCaptionBorder);
		btnLogOut.setBounds(47, 500, 143, 32);
		btnLogOut.setText("<< LogOut");
		btnLogOut.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(btnLogOut);
		
		textName.setBounds(214, 175, 143, 20);
		contentpane.add(textName);
		String username= textName.getText();

		textPassword.setBounds(214, 375, 143, 20);
		contentpane.add(textPassword);
		
		JButton btnRegisterAdminWindow = new JButton("Register Admin");
		btnRegisterAdminWindow.setBounds(99, 11, 107, 23);
		btnRegisterAdminWindow.setEnabled(false);
		contentpane.add(btnRegisterAdminWindow);
		
		JButton btnAddProductWindow = new JButton("Add new Product");
		btnAddProductWindow.setBounds(227, 11, 120, 23);
		contentpane.add(btnAddProductWindow);
		
		JButton btnViewClients = new JButton("ClientList");
		btnViewClients.setBounds(85, 438, 85, 21);
		contentpane.add(btnViewClients);
		String user_password=textPassword.getText();

		
		/**
		 * Button to register a new admin in the database using the method register user (with its rules and everything)
		 */
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String hostname="localhost";
				String  port = "8080";
				TheClient newclient= new TheClient(hostname, port);
				newclient.registerUser(textName.getText(),textPassword.getText(), 0, 1);
			}
		});
		
		/**
		 * Button to go to the window for adding new products in the database
		 */
		btnSeeUsers.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ClientTableWindow();
				dispose();
				
			}
		});
		btnAddProductWindow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AddProductWindow();
				dispose();
			}
		});
		
		/**
		 * Button to log out and return to the login window
		 */
		btnLogOut.addActionListener(new ActionListener() {
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
		setTitle("Register a new Admin");
	}
}
