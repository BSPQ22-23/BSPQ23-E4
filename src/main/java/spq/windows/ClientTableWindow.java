package spq.windows;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import spq.client.TheClient;
import spq.serialization.UserData;

import javax.swing.JScrollPane;



public class ClientTableWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private JButton btnBACK = new JButton("Back");
    private JPanel contentPane;
    private JButton btndelete= new JButton("delete");
    private JTable table;
    public ClientTableWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(220, 220, 220)); // Light gray background
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        btnBACK.setBounds(10, 10, 89, 23);
        contentPane.add(btnBACK, BorderLayout.NORTH);
        
        String hostname = "localhost";
		String port = "8080";
		
		TheClient newclient = new TheClient(hostname, port);
		List<UserData> users = newclient.getAvailableUsers();
		 // Creating the model of the table
		
		String[] columnNames = { "Name", "Password" };
        Object[][] data = new Object[users.size()][columnNames.length];
        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i).getName();
            data[i][1] = users.get(i).getPassword();
            
            
        }
        table = new JTable(data, columnNames);
        
        // Add table to a JScrollPane to allow scrolling
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(table, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(440, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("See Users");
		
		btnBACK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AddProductWindow();
				dispose();
			}
		});
		
    }
   }