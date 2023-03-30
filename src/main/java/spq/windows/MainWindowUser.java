package spq.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Font;

public class MainWindowUser {

	private JFrame windowUser;
	private JTable tableProduct;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindowUser window = new MainWindowUser();
					window.windowUser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindowUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		GroupLayout gl_SouthPanel = new GroupLayout(SouthPanel);
		gl_SouthPanel.setHorizontalGroup(
			gl_SouthPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_SouthPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLogOut)
					.addContainerGap(341, Short.MAX_VALUE))
		);
		gl_SouthPanel.setVerticalGroup(
			gl_SouthPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_SouthPanel.createSequentialGroup()
					.addComponent(btnLogOut)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		SouthPanel.setLayout(gl_SouthPanel);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
