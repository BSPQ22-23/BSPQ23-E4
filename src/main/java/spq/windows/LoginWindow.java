package spq.windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import spq.client.TheClient;
import spq.jdo.User;

import javax.swing.JButton;
import javax.swing.JTextField;

public class LoginWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	static Properties p;

	private JPanel contentpane;
	private JLabel labelUser = new JLabel();
	private JLabel labelPassword = new JLabel();
	private JTextField textUser = new JTextField();
	private JPasswordField textPassword = new JPasswordField();
	private JButton btnLogIn = new JButton();
	private JButton btnRegister = new JButton();

	public LoginWindow() {

		contentpane = new JPanel();
		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentpane);
		contentpane.setLayout(null);

		JLabel labelTitle = new JLabel("Deusto-Computers");
		labelTitle.setFont(new Font("Roboto", Font.BOLD, 40));
		labelTitle.setBounds(49, 33, 390, 50);
		contentpane.add(labelTitle);

		labelUser.setText("Usuario:");
		labelUser.setOpaque(true);
		labelUser.setBounds(85, 125, 55, 20);
		labelUser.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(labelUser, BorderLayout.SOUTH);

		textUser.setBounds(179, 125, 169, 20);
		contentpane.add(textUser);

		labelPassword.setText("Password:");
		labelPassword.setBounds(65, 175, 89, 20);
		labelPassword.setOpaque(true);
		labelPassword.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(labelPassword);

		textPassword.setBounds(179, 175, 169, 20);
		contentpane.add(textPassword);

		btnLogIn.setForeground(SystemColor.text);
		btnLogIn.setBackground(new Color(0, 51, 255));
		btnLogIn.setBounds(262, 243, 121, 32);
		btnLogIn.setText("Login");
		btnLogIn.setFont(new Font("Goudy Old Style", Font.BOLD | Font.ITALIC, 16));
		contentpane.add(btnLogIn);

		btnRegister.setBackground(SystemColor.inactiveCaptionBorder);
		btnRegister.setBounds(49, 243, 121, 32);
		btnRegister.setText("Register");
		btnRegister.setFont(new Font("Goudy Old Style", Font.BOLD | Font.ITALIC, 16));
		contentpane.add(btnRegister);
		
		/**
		 * Button to log in the app. You will need an existing username and its correct password.
		 * Depending on the type of the user, admin or user, you will go to a different window.
		 */

		btnLogIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String hostname="localhost";
				String  port = "8080";
				
				TheClient newclient= new TheClient(hostname, port);
				newclient.loginUser(textUser.getText(),textPassword.getText());
				User u = newclient.loginUser(textUser.getText(),textPassword.getText());;
				
				if (newclient.loginUser(textUser.getText(),textPassword.getText()).getType()==0) {
					//new MainWindowUser(u);
					dispose();
					MainWindowUser w_user=new MainWindowUser(u);
					w_user.setVisible(true);
					
				}else if(newclient.loginUser(textUser.getText(),textPassword.getText()).getType()==1){
					dispose();
					MainWindowAdmin w_admin=new MainWindowAdmin();
					w_admin.setVisible(true);
					
				}
				
			}

		});

		/**
		 * Button that will take you to the window to register as a user
		 */
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new RegisterWindow();
				dispose();

			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(527, 372);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("DeustoComputers");

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
