package spq.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;

import spq.client.TheClient;
import spq.jdo.Product;
import spq.jdo.User;
import spq.serialization.ProductData;
import spq.serialization.SaleData;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

public class MainWindowUser extends JFrame {

	private JFrame windowUser;
	private JTable tableProduct;

	/**
	 * Create the application.
	 */
	public MainWindowUser(User u) {
		initialize(u);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User u) {
		windowUser = new JFrame();
		windowUser.setBounds(100, 100, 670, 399);
		windowUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel CenterPanel = new JPanel();
		CenterPanel.setBackground(new Color(255, 235, 205));
		windowUser.getContentPane().add(CenterPanel, BorderLayout.CENTER);

		tableProduct = new JTable();
		tableProduct.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Name", "Price", "Available" }
		/*
		 * ------------With these changes, the table will display the three additional columns we have added, and each row will contain the corresponding product data.-------
		 * 
		 * 
		 * List<ProductData> products = newclient.getAvailableProducts();
		 * ProductTableModel model = (ProductTableModel) tableProduct.getModel(); for
		 * (ProductData prod : products) { Object[] rowData = {prod.getName(),
		 * prod.getPrice(), prod.isAvailable()}; model.addRow(rowData); }
		 * 
		 */
		));

		tableProduct.setForeground(new Color(192, 192, 192));
		CenterPanel.add(tableProduct);
		DefaultTableModel def = new DefaultTableModel();
		def.addColumn(u);

		JPanel SouthPanel = new JPanel();
		SouthPanel.setBackground(new Color(255, 255, 240));
		windowUser.getContentPane().add(SouthPanel, BorderLayout.SOUTH);

		JButton btnLogOut = new JButton("LogOut");
		btnLogOut.setBackground(Color.RED);
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnLogOut.setForeground(Color.BLACK);
		JButton btnchangePassword = new JButton("Change Password");
		btnchangePassword.setBackground(Color.ORANGE);
		btnchangePassword.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnchangePassword.setForeground(Color.BLACK);
		
		//BTN UPDATEPURSE
		JButton btnUP = new JButton("UPDATEPURSE");
		btnUP.setBackground(Color.GREEN);
		btnUP.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnUP.setForeground(Color.BLACK);
		
		JButton btnSeeSales = new JButton("See Sales");
		btnSeeSales.setBackground(Color.ORANGE);
		btnSeeSales.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnSeeSales.setForeground(Color.BLACK);
		GroupLayout gl_SouthPanel = new GroupLayout(SouthPanel);
		gl_SouthPanel.setHorizontalGroup(
			gl_SouthPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_SouthPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLogOut)
					.addComponent(btnUP)
					.addPreferredGap(ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
					.addComponent(btnSeeSales)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnchangePassword)
					.addGap(24))
		);
		gl_SouthPanel.setVerticalGroup(
			gl_SouthPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_SouthPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_SouthPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnchangePassword, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogOut)
						.addComponent(btnUP)
						.addComponent(btnSeeSales))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		SouthPanel.setLayout(gl_SouthPanel);
		
	
		
		

		JPanel NorthPanel = new JPanel();
		NorthPanel.setBackground(new Color(255, 235, 205));
		windowUser.getContentPane().add(NorthPanel, BorderLayout.NORTH);

		JLabel lblTitle = new JLabel("Welcome ");
		lblTitle.setFont(new Font("Kunstler Script", Font.PLAIN, 24));
		GroupLayout gl_NorthPanel = new GroupLayout(NorthPanel);
		gl_NorthPanel
				.setHorizontalGroup(gl_NorthPanel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_NorthPanel.createSequentialGroup().addContainerGap(255, Short.MAX_VALUE)
								.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
								.addGap(233)));
		gl_NorthPanel.setVerticalGroup(gl_NorthPanel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_NorthPanel.createSequentialGroup().addContainerGap(14, Short.MAX_VALUE).addComponent(lblTitle)
						.addContainerGap()));
		NorthPanel.setLayout(gl_NorthPanel);

		/**
		 * Button that will make an option pane appear where you will have to write the new password for the next time that you will want to log in as this user
		 */
		
		btnchangePassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * // Solicita al usuario que ingrese su nueva contraseña String newPassword =
				 * JOptionPane.showInputDialog("Introduce la nueva contraseña: ");
				 * 
				 * // Actualiza la contraseña del usuario actual try { // Creo que el metodo se
				 * haria asi, me falta en la parte de BBDD String hostname = "localhost"; String
				 * port = "8080"; TheClient newclient = new TheClient(hostname, port);
				 * newclient.changeUserPassword(u, newPassword);
				 * JOptionPane.showMessageDialog(null,
				 * "La contraseña se ha actualizado correctamente."); } catch (Exception ex) {
				 * JOptionPane.showMessageDialog(null,
				 * "Se produjo un error al actualizar la contraseña: " + ex.getMessage()); }
				 */

				String newPassword = JOptionPane.showInputDialog("Introduce the new Password: ");
				User neu = new User(u.getName(), newPassword, u.getPurse(), 0);
				String hostname = "localhost";
				String port = "8080";

				TheClient newclient = new TheClient(hostname, port);
				// newclient.changeUserPassword(u, newPassword);
				newclient.deleteUser(u);
				newclient.registerUser(neu.getName(), neu.getPassword(), neu.getPurse(), 0);
				new LoginWindow();
				dispose();
			}

		});
		
		btnUP.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String amount = JOptionPane.showInputDialog("Introduce the amount u wanna add: ");
				String hostname = "localhost";
				String port = "8080";

				TheClient newclient = new TheClient(hostname, port);
				newclient.updatePurse(u,Double.parseDouble(amount),u.getPurse());
				dispose();
				
			}
		});
		
		btnSeeSales.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	String hostname = "localhost";
				String port = "8080";

				TheClient newclient = new TheClient(hostname, port);
		        List<SaleData> sales = newclient.getSales();
		        if (sales != null) {
		            StringBuilder message = new StringBuilder();
		            /*if (sales.isEmpty()) {
		            	message.append("There are no sales");
		            } else {*/
		            message.append("Products bought by user: ").append(u.getName()).append("\n");
		            int cont = 0;
	            	for (SaleData sale : sales) {
	            		if (sale.getBuyer().equals(u.getName())) {
			                message.append("Product: ").append(sale.getNameProduct()).append("\n");
			                message.append("Price: ").append(sale.getPriceProduct()).append("\n");
			                message.append("--------------------------------------\n");
			                cont++;
	            		}
		            }
	            	if (cont == 0) {
	            		message.append("This user has no sales");
	            	}
		            //}
		            
		            JOptionPane.showMessageDialog(null, message.toString(), "Sales", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            JOptionPane.showMessageDialog(null, "Error retrieving sales", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});


		String hostname = "localhost";
		String port = "8080";

		TheClient newclient = new TheClient(hostname, port);
		List<ProductData> products = newclient.getAvailableProducts();
		for (ProductData prod : products) {

			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(7, 0, 0, 0));

			// System.out.println(car.getMatriculation_number());
			panel.add(new JLabel(prod.getName()));
			JLabel name = new JLabel(prod.getName());
			double p = prod.getPrice();
			String pricetext = Double.toString(p);

			panel.add(new JLabel(pricetext));
			JLabel priceText = (new JLabel(pricetext));

			JLabel price = new JLabel(pricetext);
			JButton buyButton = new JButton("BUY!");
			/**
			 * Button that will appear once for every product available to buy
			 * When clicked and if it is possible for the user, the product will be bought and the product won't be able anymore
			 */
			buyButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String hostname = "localhost";
					String port = "8080";
					boolean a = false;
					TheClient newclient = new TheClient(hostname, port);
					a = newclient.buyProduct(u, Double.parseDouble(priceText.getText()), name.getText());
					if (a = true) {
						buyButton.setEnabled(false);
						Product p = new Product(prod.getName(), prod.getPrice(), prod.isAvailable());
						newclient.addSale(u.getName(), p);
					}
					
				}
			});

			panel.add(buyButton);
			panel.setBorder(BorderFactory.createLineBorder(Color.black));
			CenterPanel.add(panel);
			CenterPanel.updateUI();

		}
		
		/*List<SaleData> sales = newclient.getSalesUser(u);
		for (SaleData s : sales) {

			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(7, 0, 0, 0));

			// System.out.println(car.getMatriculation_number());
			panel.add(new JLabel(s.getNameProduct()));
			double p = s.getPriceProduct();
			String pricetext = Double.toString(p);

			panel.add(new JLabel(pricetext));
			panel.setBorder(BorderFactory.createLineBorder(Color.red));
			CenterPanel.add(panel);
			CenterPanel.updateUI();

		}*/

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
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		windowUser.setVisible(b);
	}
}