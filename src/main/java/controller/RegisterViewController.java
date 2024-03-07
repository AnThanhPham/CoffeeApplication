package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Random;

import dao.UserDAO;
import model.UserModel;
import util.Email;
import util.Encryption;
import util.ValidateUtils;
import views.RegisterViews;
import views.VerifyPassWordView;

public class RegisterViewController {
	private RegisterViews registerView;
	private UserDAO userDao = new UserDAO();
//	private VerifyPassWordView verifyPassWordView;

	public RegisterViewController(RegisterViews registerView) {
		this.registerView = registerView;
		addEvent();
	}

	private void addEvent() {
		registerView.getClose2().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				CloseMouseClicked(evt);
			}
		});
		
		registerView.getButtonRegister().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = registerView.getInputUserName_Register().getText();
				String email = registerView.getInputEmail_Register().getText();
				String password = String.valueOf(registerView.getInputPassWord_Register().getPassword());
				String confirmPass = String.valueOf(registerView.getInputConfirmPassword().getPassword());
				password = Encryption.getSHAHash(password);
				confirmPass = Encryption.getSHAHash(confirmPass);
				
				if(validateForm(userName, email, password, confirmPass)) {
					UserModel user = new UserModel();
					user.setUserName(userName);
					user.setPassword(password);
					user.setEmail(email);
					user.setCode(generateVerifyCode());		
					userDao.insert(user);
					
					Email.sendEmail(user.getEmail(), "Mã xác thực tài khoản", user.getCode());
					
					VerifyPassWordView verifyPassWordView = new VerifyPassWordView(user);
					verifyPassWordView.setLocationRelativeTo(null);
					verifyPassWordView.setVisible(true);
				}
			}
		});
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
	
	public boolean validateForm(String userName,String email,String password,String confirmPass) {
		if(ValidateUtils.checkNotEmptyAndNotNull(userName) && ValidateUtils.checkNotEmptyAndNotNull(email) 
				&& ValidateUtils.checkNotEmptyAndNotNull(password) && ValidateUtils.checkNotEmptyAndNotNull(confirmPass)) {
			if(ValidateUtils.checkEmail(email)) {
				if(checkPassword(password, confirmPass)) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}
	
	public boolean checkPassword(String password,String confirmPass) {
		if(password.equals(confirmPass)) {
			return true;
		}else {
			return false;
		}
	}
	
	private void CloseMouseClicked(MouseEvent evt) {
		System.exit(0);
	}
}
