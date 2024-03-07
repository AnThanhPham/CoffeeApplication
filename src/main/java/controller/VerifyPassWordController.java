package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.UserDAO;
import model.UserModel;
import views.VerifyPassWordView;

public class VerifyPassWordController {
	private VerifyPassWordView verifyPassWordView;
	private UserModel user;
	private UserDAO userDao;

	public VerifyPassWordController(VerifyPassWordView verifyPassWordView,UserModel user) {
		this.verifyPassWordView = verifyPassWordView;
		this.user = user;
		userDao = new UserDAO();
		addEvent();
	}

	private void addEvent() {
		verifyPassWordView.getBtnCancel().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verifyPassWordView.dispose();
			}
		});
		
		verifyPassWordView.getBtnOke().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String code = verifyPassWordView.getTxtCode().getText();
				System.out.println(user.getCode());
				if(userDao.verifyCodeWithUser(user.getID(), code)) {
					userDao.doneVerify(user.getID());
					JOptionPane.showMessageDialog(verifyPassWordView, "Thành công");
					verifyPassWordView.dispose();
				}else {
					System.out.println("Không khớp");
				}
			}
		});
	}

	public VerifyPassWordView getVerifyPassWordView() {
		return verifyPassWordView;
	}

	public void setVerifyPassWordView(VerifyPassWordView verifyPassWordView) {
		this.verifyPassWordView = verifyPassWordView;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
	
	
}
