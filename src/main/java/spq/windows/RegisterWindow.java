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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;



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
	private JLabel labelDNI = new JLabel();
	private JLabel labelNombre = new JLabel();
	private JLabel labelApellido = new JLabel();
	private JLabel labelEdad = new JLabel();
	private JLabel labelEmail = new JLabel();
	private JLabel labelContrasenya = new JLabel();
	private JLabel labelConfirmarContrasenya = new JLabel();
	private JTextField textoDNI = new JTextField();
	private JTextField textoNombre = new JTextField();
	private JTextField textoApellido = new JTextField();
	private JTextField textoEdad = new JTextField();
	private JTextField textoEmail = new JTextField();
	private JPasswordField textoContrasenya = new JPasswordField();
	private JPasswordField textoConfirmarContrasenya = new JPasswordField();
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

		labelDNI.setText("DNI:");
		labelDNI.setOpaque(true);
		labelDNI.setBounds(108, 124, 44, 20);
		labelDNI.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(labelDNI, BorderLayout.SOUTH);
		
		labelNombre.setText("Name:");
		labelNombre.setBounds(99, 175, 71, 20);
		labelNombre.setOpaque(true);
		labelNombre.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(labelNombre);
		
		labelApellido.setText("Surname:");
		labelApellido.setBounds(98, 225, 61, 20);
		labelApellido.setOpaque(true);
		labelApellido.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(labelApellido);
				
		labelEdad.setText(" Age:");
		labelEdad.setBounds(107, 275, 99, 20);
		labelEdad.setOpaque(true);
		labelEdad.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(labelEdad);
		
		labelEmail.setText("Email:");
		labelEmail.setBounds(104, 325, 71, 20);
		labelEmail.setOpaque(true);
		labelEmail.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(labelEmail);
		
		labelContrasenya.setText("Password:");
		labelContrasenya.setBounds(86, 375, 120, 20);
		labelContrasenya.setOpaque(true);
		labelContrasenya.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(labelContrasenya);
		
		labelConfirmarContrasenya.setText("Confirm password:");
		labelConfirmarContrasenya.setBounds(50, 425, 156, 20);
		labelConfirmarContrasenya.setOpaque(true);
		labelConfirmarContrasenya.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(labelConfirmarContrasenya);
		
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

		textoDNI.setBounds(214, 125, 143, 20);
		contentpane.add(textoDNI);

		textoNombre.setBounds(214, 175, 143, 20);
		contentpane.add(textoNombre);

		textoApellido.setBounds(216, 225, 143, 20);
		contentpane.add(textoApellido);
		
		textoEdad.setBounds(214, 275, 143, 20);
		contentpane.add(textoEdad);

		textoEmail.setBounds(214, 325, 143, 20);
		contentpane.add(textoEmail);

		textoContrasenya.setBounds(214, 375, 143, 20);
		contentpane.add(textoContrasenya);

		textoConfirmarContrasenya.setBounds(214, 425, 143, 20);
		contentpane.add(textoConfirmarContrasenya);

		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		
	

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
