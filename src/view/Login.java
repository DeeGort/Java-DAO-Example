package view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import model.db.Account;


public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField accountBox;
	private JPasswordField passwordBox;
	private JButton loginButton;


	public Account getAccount() {
		return new Account(accountBox.getText(), new String(passwordBox.getPassword()));
	}
	
	public JButton getLoginButton() {
		return loginButton;
	}

	public Login() {
		try {
			  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch(Exception e) {
			  System.out.println("Error setting native LAF: " + e);
			}
		
		setResizable(false);
		setBounds(100, 100, 280, 168);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel loginGroup = new JPanel();
		loginGroup.setBorder(BorderFactory.createTitledBorder("Database login"));
		loginGroup.setBounds(10, 11, 254, 96);
		contentPanel.add(loginGroup);
		loginGroup.setLayout(null);
		
		accountBox = new JTextField();
		accountBox.setBounds(98, 27, 146, 20);
		loginGroup.add(accountBox);
		accountBox.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Account");
		lblNewLabel.setBounds(10, 30, 88, 14);
		loginGroup.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 58, 88, 14);
		loginGroup.add(lblPassword);
		
		passwordBox = new JPasswordField();
		passwordBox.setBounds(98, 55, 146, 20);
		loginGroup.add(passwordBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				loginButton = new JButton("Login");
				{
					JButton cancelButton = new JButton("Cancel");
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							System.exit(0);
						}
					});
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}
				loginButton.setActionCommand("OK");
				buttonPane.add(loginButton);
				getRootPane().setDefaultButton(loginButton);
			}
		}
	}
}
