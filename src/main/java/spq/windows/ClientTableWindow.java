package spq.windows;

import java.awt.EventQueue;
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

    private JButton btnATRAS = new JButton("Atrás");
    private JPanel contentPane;
    private JButton btndelete= new JButton("delete");
    private JTable table;
    public ClientTableWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(220, 220, 220)); // Fondo gris claro
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        btnATRAS.setBounds(10, 10, 89, 23);
        contentPane.add(btnATRAS, BorderLayout.NORTH);
        
        String hostname = "localhost";
		String port = "8080";
		
		TheClient newclient = new TheClient(hostname, port);
		List<UserData> users = newclient.getAvailableUsers();
		 // Crear el modelo de la tabla
		
		String[] columnNames = { "Nombre", "Contraseña" };
        Object[][] data = new Object[users.size()][columnNames.length];
        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i).getName();
            data[i][1] = users.get(i).getPassword();
            
        }
        table = new JTable(data, columnNames);
        
        // Agregar la tabla a un JScrollPane para permitir el desplazamiento
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(table, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(440, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("See Users");
		
    }
   }