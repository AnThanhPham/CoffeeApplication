package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.JOptionPane;

import dao.UserDAO;
import model.UserModel;
import util.Email;
import util.Encryption;
import views.ChangePasswordView;
import views.VerifyPassWordView;

public class ChangePasswordViewController {
	private ChangePasswordView changePasswordView;
	private UserDAO userDao = new UserDAO();

	public ChangePasswordViewController(ChangePasswordView changePasswordView) {
		this.changePasswordView = changePasswordView;
		addEvent();
	}

	private void addEvent() {
		changePasswordView.getBtnChangePassword().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = changePasswordView.getInputEmail().getText();
				String oldPass = String.valueOf(changePasswordView.getOldPassword().getPassword());
				String newPass = String.valueOf(changePasswordView.getInputNewPassword().getPassword());
				String confPass = String.valueOf(changePasswordView.getInputConfirmPassword().getPassword());
				
				oldPass = Encryption.getSHAHash(oldPass);
				
				if(userDao.checkEmailAndOldPassWord(email, oldPass)) {
					newPass = Encryption.getSHAHash(newPass);
					confPass = Encryption.getSHAHash(confPass);
					
					if(checkPassword(newPass, confPass)) {
						String code = generateVerifyCode();
						UserModel user = userDao.findByEmail(email);
						user.setCode(code);
						user.setPassword(newPass);
						userDao.insertCode(user.getID(), code);
						Email.sendEmail(email, "Mã xác thực tài khoản", code);
						
						VerifyPassWordView verifyPassWordView = new VerifyPassWordView(user);
						verifyPassWordView.setLocationRelativeTo(null);
						verifyPassWordView.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(changePasswordView, "Mật khẩu không khớp");
					}
				}else {
					JOptionPane.showMessageDialog(changePasswordView, "Email hoặc mật khẩu không đúng");
				}
			}
		});
	}
	
	public boolean checkPassword(String password,String confirmPass) {
		if(password.equals(confirmPass)) {
			return true;
		}else {
			return false;
		}
	}
	
	private String generateVerifyCode(){
        DecimalFormat df = new DecimalFormat("000000");
        Random ran = new Random();
        String code = df.format(ran.nextInt(1000000));  //  Random from 0 to 999999
        while (userDao.checkDuplicateCode(code)) {
            code = df.format(ran.nextInt(1000000));
        }
        return code;
    }
	
}
