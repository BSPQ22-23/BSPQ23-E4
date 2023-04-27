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

public class AddProductWindow extends JFrame{
	private static final long serialVersionUID = 1L;

	private JPanel contentpane;
	private JLabel labelNombre = new JLabel();
	private JLabel labelPrice = new JLabel();
	private JTextField textoNombre = new JTextField();
	private JTextField textoPrecio = new JTextField();
	private JButton btnAddProduct = new JButton();
	private JButton btnLogOut = new JButton();

	public class JNumberTextField extends JTextField {
		private static final long serialVersionUID = 1L;
	}

	public AddProductWindow() {

		contentpane = new JPanel();
		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentpane);
		contentpane.setLayout(null);
		

		JLabel labelTitle = new JLabel("Insert the information of the new product");
		labelTitle.setFont(new Font("Roboto", Font.BOLD, 40));
		labelTitle.setBounds(44, 35, 359, 50);
		contentpane.add(labelTitle);

		//GET USERNAME
		labelNombre.setText("Name:");
		labelNombre.setBounds(99, 175, 71, 20);
		labelNombre.setOpaque(true);
		labelNombre.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(labelNombre);
		
		labelPrice.setText("Price:");
		labelPrice.setBounds(86, 375, 120, 20);
		labelPrice.setOpaque(true);
		labelPrice.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(labelPrice);
		
		btnAddProduct.setForeground(SystemColor.text);
		btnAddProduct.setBackground(new Color(0, 51, 255));
		btnAddProduct.setBounds(227, 500, 190, 32);
		btnAddProduct.setText("Add Product");
		btnAddProduct.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(btnAddProduct);
		
		btnLogOut.setBackground(SystemColor.inactiveCaptionBorder);
		btnLogOut.setBounds(47, 500, 143, 32);
		btnLogOut.setText("<< LogOut");
		btnLogOut.setFont(new Font("Goudy Old Style", Font.BOLD, 16));
		contentpane.add(btnLogOut);

		textoNombre.setBounds(214, 175, 143, 20);
		contentpane.add(textoNombre);

		textoPrecio.setBounds(214, 375, 143, 20);
		contentpane.add(textoPrecio);
		
		JButton btnRegisterAdminWindow = new JButton("Register Admin");
		btnRegisterAdminWindow.setBounds(99, 11, 107, 23);
		contentpane.add(btnRegisterAdminWindow);
		
		JButton btnAddProductWindow = new JButton("Add new Product");
		btnAddProductWindow.setBounds(227, 11, 120, 23);
		btnAddProductWindow.setEnabled(false);
		contentpane.add(btnAddProductWindow);

		btnRegisterAdminWindow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MainWindowAdmin();
				dispose();
			}
		});
		
		btnAddProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String hostname="localhost";
				String  port = "8080";
				TheClient newclient= new TheClient(hostname, port);
				newclient.addProduct(textoNombre.getText(),Double.parseDouble(textoPrecio.getText()),false);
				System.out.println("Okay");
			}
		});
		
		
		btnAddProductWindow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MainWindowAdmin();
				dispose();
			}
		});
	

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
		setTitle("Add a new Product");
	}
}
