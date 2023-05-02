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
import spq.serialitazion.ProductData;

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

public class MainWindowUser extends JFrame{

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
		windowUser.setBounds(100, 100, 450, 300);
		windowUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel CenterPanel = new JPanel();
		windowUser.getContentPane().add(CenterPanel, BorderLayout.CENTER);

		tableProduct = new JTable();
		CenterPanel.add(tableProduct);

		JPanel SouthPanel = new JPanel();
		SouthPanel.setBackground(Color.WHITE);
		windowUser.getContentPane().add(SouthPanel, BorderLayout.SOUTH);

		JButton btnLogOut = new JButton("LogOut");
		btnLogOut.setBackground(Color.RED);
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnLogOut.setForeground(Color.BLACK);
		JButton btnchangePassword = new JButton("Change Password");
		btnchangePassword.setBackground(Color.ORANGE);
		btnchangePassword.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnchangePassword.setForeground(Color.BLACK);
		GroupLayout gl_SouthPanel = new GroupLayout(SouthPanel);
		gl_SouthPanel
				.setHorizontalGroup(gl_SouthPanel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_SouthPanel.createSequentialGroup().addContainerGap().addComponent(btnLogOut)
								.addPreferredGap(ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
								.addComponent(btnchangePassword).addGap(24)));
		gl_SouthPanel.setVerticalGroup(gl_SouthPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_SouthPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_SouthPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnchangePassword, GroupLayout.PREFERRED_SIZE, 21,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLogOut))
						.addContainerGap(22, Short.MAX_VALUE)));
		SouthPanel.setLayout(gl_SouthPanel);

		btnchangePassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*// Solicita al usuario que ingrese su nueva contraseña
				String newPassword = JOptionPane.showInputDialog("Introduce la nueva contraseña: ");

				// Actualiza la contraseña del usuario actual
				try {
					// Creo que el metodo se haria asi, me falta en la parte de BBDD
					String hostname = "localhost";
					String port = "8080";
					TheClient newclient = new TheClient(hostname, port);
					newclient.changeUserPassword(u, newPassword);
					JOptionPane.showMessageDialog(null, "La contraseña se ha actualizado correctamente.");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Se produjo un error al actualizar la contraseña: " + ex.getMessage());
				}*/
				
				String newPassword = JOptionPane.showInputDialog("Introduce the new Password: ");
		    	User neu = new User(u.getName(), newPassword, u.getPurse(), 0);
		    	String hostname="localhost";
				String  port = "8080";
				
				TheClient newclient= new TheClient(hostname, port);
				//newclient.changeUserPassword(u, newPassword);
				newclient.deleteUser(u);
				newclient.registerUser(neu.getName(), neu.getPassword(), neu.getPurse(), 0);
				new LoginWindow();
				dispose();
		    }
			
		});
		
		
		
		String hostname="localhost";
		String  port = "8080";
		
		TheClient newclient= new TheClient(hostname, port);
		List<ProductData> products = newclient.getAvailableProducts();
		for(ProductData prod: products) {
			
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(7,0,0,0));
			
			//System.out.println(car.getMatriculation_number());
			panel.add(new JLabel(prod.getName()));
			JLabel name=new JLabel(prod.getName());
			double p = prod.getPrice();
			String pricetext = Double.toString(p);
			
			panel.add(new JLabel(pricetext));
			JLabel priceText=(new JLabel(pricetext));
			
			JLabel price = new JLabel(pricetext);
			JButton buyButton = new JButton("BUY!");
			buyButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String hostname="localhost";
					String  port = "8080";
					boolean a = false;
					TheClient newclient= new TheClient(hostname, port);
					a=newclient.buyProduct(u,Double.parseDouble(priceText.getText()), name.getText());
					if (a=true) {
						buyButton.setEnabled(false);
					}
				}
			});
			
			
			panel.add(buyButton);
			panel.setBorder(BorderFactory.createLineBorder(Color.black));
			CenterPanel.add(panel);
			CenterPanel.updateUI();
		
		}
		
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