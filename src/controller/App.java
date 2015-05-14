package controller;
import java.awt.EventQueue;

import view.Login;
import view.Window;

public class App {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login login = new Login();
					login.setVisible(true);
					login.getLoginButton().addActionListener(e ->
					{
						Window frame = new Window();
						frame.setAccout(login.getAccount());
						frame.setVisible(true);
						login.setVisible(false);
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
