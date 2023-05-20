package spq.windows;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ClientTableWindow {

	private JFrame frame;
	private JScrollPane CentereScrollPane;
	private JTable tableClient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientTableWindow window = new ClientTableWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientTableWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CentereScrollPane = new JScrollPane();
		frame.getContentPane().add(CentereScrollPane, BorderLayout.CENTER);
		
		tableClient = new JTable();
		frame.getContentPane().add(tableClient, BorderLayout.NORTH);
		
        // Conectar a la base de datos y recuperar los clientes registrados
        try (Connection conn = DriverManager.getConnection("productsdb", "usuario", "contraseña")) {
            String sql = "SELECT nombre, email, telefono FROM clientes";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            
            // Obtener los metadatos de la consulta
            int columnCount = resultSet.getMetaData().getColumnCount();
            
            // Agregar las columnas al modelo de la tabla
            for (int i = 1; i <= columnCount; i++) {
             //   model.addColumn(resultSet.getMetaData().getColumnName(i));
            }
            
            // Agregar las filas al modelo de la tabla
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
               // model.addRow(row);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		
		
	}

}
