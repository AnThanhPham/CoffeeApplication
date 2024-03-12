package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dao.UserDAO;
import model.UserModel;
import util.Encryption;
import views.Login;
import views.Main;
import views.VerifyPassWordView;


public class LoginController {
	private Login login;
	private UserDAO userDao = new UserDAO();

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
		
		login.getjPanel3().getButtonLogin().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = login.getjPanel3().getInputUserName().getText();
				String password = String.valueOf(login.getjPanel3().getInputPassWord().getPassword());
				password = Encryption.getSHAHash(password);
				
				UserModel user = userDao.findByUserNameAndPassword(userName, password);
				if(user != null) {
					System.out.println(user.getCode());
					if(!user.getCode().equals("")) {
						JOptionPane.showMessageDialog(login.getjPanel3(), "Tài khoản của bạn chưa xác nhận");
						VerifyPassWordView verifyPassWordView = new VerifyPassWordView(user);
						verifyPassWordView.setLocationRelativeTo(null);
						verifyPassWordView.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(login.getjPanel3(), "Đăng nhập thành công");
						login.dispose();
						Main main = new Main(user);
						main.setVisible(true);
					}
				}else {
					JOptionPane.showMessageDialog(login, "Đăng nhập thất bại");
				}
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
