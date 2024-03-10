package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dao.UserDAO;
import model.UserModel;
import util.Encryption;
import views.ChangePasswordView;
import views.Login;
import views.LoginViews;
import views.Main;
import views.VerifyPassWordView;

public class LoginViewController {
	private LoginViews loginView;
	private Login login;
	private UserDAO userDao = new UserDAO();

	public LoginViewController(LoginViews loginView,Login login) {
		this.loginView = loginView;
		this.login = login;
		addEvent();
	}

	private void addEvent() {
		loginView.getDisable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				disableMouseClicked(evt);
			}
		});
		
		loginView.getShow().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				showMouseClicked(evt);
			}
		});
		
		loginView.getClose().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				CloseMouseClicked(evt);
			}
		});
		
		loginView.getForgotPassword().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChangePasswordView changePasswordView = new ChangePasswordView();
				changePasswordView.setVisible(true);
			}
		});
		
		loginView.getButtonLogin().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = loginView.getInputUserName().getText();
				String password = String.valueOf(loginView.getInputPassWord().getPassword());
				password = Encryption.getSHAHash(password);
				
				UserModel user = userDao.findByUserNameAndPassword(userName, password);
				if(user != null) {
					System.out.println(user.getCode());
					if(!user.getCode().equals("")) {
						JOptionPane.showMessageDialog(loginView, "Tài khoản của bạn chưa xác nhận");
						VerifyPassWordView verifyPassWordView = new VerifyPassWordView(user);
						verifyPassWordView.setLocationRelativeTo(null);
						verifyPassWordView.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(loginView, "Đăng nhập thành công");
						login.dispose();
						Main main = new Main(user);
						main.setExtendedState(JFrame.MAXIMIZED_BOTH);
						main.getLabelWelcome().setText(user.getUserName());
						main.setVisible(true);
					}
				}else {
					JOptionPane.showMessageDialog(loginView, "Đăng nhập thất bại");
				}
			}
		});
	}
	
	private void CloseMouseClicked(MouseEvent evt) {
		System.exit(0);
	}
	
	private void disableMouseClicked(MouseEvent evt) {
		loginView.getInputPassWord().setEchoChar((char) 0);
		loginView.getDisable().setVisible(false);
		loginView.getDisable().setEnabled(false);
		loginView.getShow().setEnabled(true);
		loginView.getShow().setEnabled(true);
	}// event_disableMouseClicked

	private void showMouseClicked(MouseEvent evt) {
		loginView.getInputPassWord().setEchoChar((char) 8226);
		loginView.getDisable().setVisible(true);
		loginView.getDisable().setEnabled(true);
		loginView.getShow().setEnabled(false);
		loginView.getShow().setEnabled(false);
	}// event_showMouseClicked
	
}
