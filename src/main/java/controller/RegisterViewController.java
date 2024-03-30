package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.JOptionPane;

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
				StringBuilder messageError = new StringBuilder("");
				
				if(validateForm(userName, email, password, confirmPass,messageError)) {
					UserModel user = new UserModel();
					password = Encryption.getSHAHash(password);
					confirmPass = Encryption.getSHAHash(confirmPass);
					user.setUserName(userName);
					user.setPassword(password);
					user.setEmail(email);
					user.setCode(generateVerifyCode());	
					user.setRoleID(2);
					Date currentDate = new Date(System.currentTimeMillis());
            	    java.sql.Date sqlDate2 = new java.sql.Date(currentDate.getTime());
            		user.setCreateDate(sqlDate2);
					userDao.insert(user);
					
					Email.sendEmail(user.getEmail(), "Mã xác thực tài khoản", user.getCode());
					
					VerifyPassWordView verifyPassWordView = new VerifyPassWordView(user);
					verifyPassWordView.setLocationRelativeTo(null);
					verifyPassWordView.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(registerView, messageError.toString());
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
	
	public boolean validateForm(String userName,String email,String password,String confirmPass,StringBuilder res) {	
		if(ValidateUtils.checkEmptyAndNull(userName)) {
			res.append("Username không được để trống\n");
			return false;
		}
		
		if(ValidateUtils.checkEmptyAndNull(email)) {
			res.append("Email không được để trống\n");
			return false;
		}
		
		if(ValidateUtils.checkEmptyAndNull(password)) {
			res.append("Password không được để trống\n");
			return false;
		}
		
		if(ValidateUtils.checkEmptyAndNull(confirmPass)) {
			res.append("Password nhập lại không được để trống\n");
			return false;
		}
		
		if(!ValidateUtils.checkEmail(email)) {
			res.append("Email không hợp lệ\n");		
			return false;
		}
		
		if(!checkPassword(password, confirmPass)) {
			res.append("Password nhập lại không khớp\n");
			return false;
		}
		
		if(userDao.checkDuplicateUser(userName)) {
			res.append("UserName bạn nhập đã tồn tại\n");
			return false;
		}
		
		if(userDao.checkDuplicateEmail(email)) {
			res.append("Email bạn nhập đã tồn tại\\n");
			return false;
		}
		
		return true;
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
