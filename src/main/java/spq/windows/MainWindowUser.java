package spq.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;

import spq.client.TheClient;
import spq.jdo.User;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainWindowUser {

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
				// Solicita al usuario que ingrese su nueva contraseña
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
				}
			}
		});
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		windowUser.setVisible(b);
	}
}