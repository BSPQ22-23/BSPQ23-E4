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
		windowUser.getContentPane().add(SouthPanel, BorderLayout.SOUTH);
		
		JButton btnLogOut = new JButton("LogOut");
		btnLogOut.setBackground(Color.RED);
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnLogOut.setForeground(Color.BLACK);
		JButton btnchangePassword = new JButton("Change Password");
		btnchangePassword.setBackground(Color.RED);
		btnchangePassword.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnchangePassword.setForeground(Color.BLACK);
		GroupLayout gl_SouthPanel = new GroupLayout(SouthPanel);
		gl_SouthPanel.setHorizontalGroup(
			gl_SouthPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_SouthPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLogOut)
					.addGap(33)
					.addComponent(btnchangePassword)
					.addContainerGap(211, Short.MAX_VALUE))
		);
		gl_SouthPanel.setVerticalGroup(
			gl_SouthPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_SouthPanel.createSequentialGroup()
					.addGroup(gl_SouthPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogOut)
						.addComponent(btnchangePassword))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		SouthPanel.setLayout(gl_SouthPanel);
		
		btnchangePassword.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	String newPassword = JOptionPane.showInputDialog("Introduce the new Password: ");
		    	String hostname="localhost";
				String  port = "8080";
				
				TheClient newclient= new TheClient(hostname, port);
				newclient.changeUserPassword(u, newPassword);
		    }
		});
		
		
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		windowUser.setVisible(b);
	}
}
