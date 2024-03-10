package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import views.Login;


public class LoginController {
	private Login login;

	public LoginController(Login login) {
		this.login = login;
		addEvent();
	}

	private void addEvent() {	
		login.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent evt) {
				formWindowOpened(evt);
			}
		});
		
		login.getjPanel3().getSignUp().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login.getCard().show(login.getjPanel2(), "viewRegister");
			}
		});
		
		login.getjPanel4().getBack().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login.getCard().show(login.getjPanel2(), "viewLogin");
			}
		});
		
		
	}
	
	private void formWindowOpened(WindowEvent evt) {// GEN-FIRST:event_formWindowOpened
		for (double i = 0.0; i <= 1.0; i = i + 0.1) {
			String val = i + "";
			float f = Float.valueOf(val);
			login.setOpacity(f);
			try {
				Thread.sleep(50);
			} catch (Exception e) {

			}
		}
	}
}
