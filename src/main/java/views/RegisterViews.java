package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.RegisterViewController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class RegisterViews extends JPanel {

	private JLabel Close2;
	private JLabel jLabel3_Register;
	private JLabel LabelUserName_Register;
	private JLabel LabelPassWord_Register;
	private JButton ButtonRegister;
	private JLabel LabelConfirmPassword;
	private RegisterViewController registerViewController;
	private JLabel LabelEmail_Register;
	private JTextField InputUserName_Register;
	private JTextField InputEmail_Register;
	private JPasswordField InputPassWord_Register;
	private JPasswordField InputConfirmPassword;
	private JLabel back;



	public RegisterViews() {
		init();
		setBackground(new java.awt.Color(25, 118, 211));
		setLayout(null);

		Close2.setFont(new java.awt.Font("Segoe UI", 0, 18)); 
		Close2.setForeground(new java.awt.Color(255, 255, 255));
		Close2.setHorizontalAlignment(SwingConstants.CENTER);
		Close2.setText("X");
		Close2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		add(Close2);

		jLabel3_Register.setFont(new java.awt.Font("Segoe UI", 0, 32)); 
		jLabel3_Register.setForeground(new java.awt.Color(255, 255, 255));
		jLabel3_Register.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel3_Register.setText("Register");
		add(jLabel3_Register);


		LabelUserName_Register.setFont(new java.awt.Font("Segoe UI", 0, 13)); 
		LabelUserName_Register.setForeground(new java.awt.Color(199, 226, 255));
		LabelUserName_Register.setText("Username");
		add(LabelUserName_Register);

		LabelPassWord_Register.setFont(new java.awt.Font("Segoe UI", 0, 13)); 
		LabelPassWord_Register.setForeground(new java.awt.Color(199, 226, 255));
		LabelPassWord_Register.setText("Password");
		add(LabelPassWord_Register);

		ButtonRegister.setBackground(new java.awt.Color(255, 255, 255));
		ButtonRegister.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		ButtonRegister.setForeground(new java.awt.Color(25, 118, 211));
		ButtonRegister.setText("Sign Up");
		ButtonRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		add(ButtonRegister);
		

		LabelConfirmPassword.setForeground(new Color(199, 226, 255));
		LabelConfirmPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		LabelConfirmPassword.setBounds(34, 288, 341, 18);
		add(LabelConfirmPassword);
		
		LabelEmail_Register = new JLabel();
		LabelEmail_Register.setText("Email");
		LabelEmail_Register.setForeground(new Color(199, 226, 255));
		LabelEmail_Register.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		LabelEmail_Register.setBounds(34, 146, 351, 18);
		add(LabelEmail_Register);
		
		
		InputUserName_Register = new JTextField();
		InputUserName_Register.setBounds(34, 105, 351, 30);
		add(InputUserName_Register);
		InputUserName_Register.setColumns(10);
		
		InputEmail_Register = new JTextField();
		InputEmail_Register.setColumns(10);
		InputEmail_Register.setBounds(34, 177, 351, 30);
		add(InputEmail_Register);
		
		
		InputPassWord_Register = new JPasswordField();
		InputPassWord_Register.setBounds(34, 247, 351, 30);
		add(InputPassWord_Register);
		
		InputConfirmPassword = new JPasswordField();
		InputConfirmPassword.setBounds(34, 318, 351, 30);
		add(InputConfirmPassword);
		
		registerViewController = new RegisterViewController(this);
		
		back = new JLabel("");
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.setIcon(new ImageIcon(RegisterViews.class.getResource("/icon/back2.png")));
		back.setBounds(0, 24, 51, 41);
		add(back);
	}
	
	void init() {
		Close2 = new JLabel();
		Close2.setBounds(380, 0, 40, 29);
		
		jLabel3_Register = new JLabel();
		jLabel3_Register.setBounds(53, 24, 322, 41);
		
		LabelUserName_Register = new JLabel();
		LabelUserName_Register.setBounds(34, 76, 351, 18);
		
		LabelPassWord_Register = new JLabel();
		LabelPassWord_Register.setBounds(34, 218, 341, 18);
		
		ButtonRegister = new JButton();
		ButtonRegister.setBounds(34, 372, 351, 40);
		
		LabelConfirmPassword = new JLabel();
		LabelConfirmPassword.setText("Confirm Password");
	}

	public JLabel getClose2() {
		return Close2;
	}

	public void setClose2(JLabel close2) {
		Close2 = close2;
	}

	public JLabel getjLabel3_Register() {
		return jLabel3_Register;
	}

	public void setjLabel3_Register(JLabel jLabel3_Register) {
		this.jLabel3_Register = jLabel3_Register;
	}

	public JLabel getLabelUserName_Register() {
		return LabelUserName_Register;
	}

	public void setLabelUserName_Register(JLabel labelUserName_Register) {
		LabelUserName_Register = labelUserName_Register;
	}

	public JTextField getInputUserName_Register() {
		return InputUserName_Register;
	}

	public void setInputUserName_Register(JTextField inputUserName_Register) {
		InputUserName_Register = inputUserName_Register;
	}

	public JLabel getLabelPassWord_Register() {
		return LabelPassWord_Register;
	}

	public void setLabelPassWord_Register(JLabel labelPassWord_Register) {
		LabelPassWord_Register = labelPassWord_Register;
	}

	public JPasswordField getInputPassWord_Register() {
		return InputPassWord_Register;
	}

	public void setInputPassWord_Register(JPasswordField inputPassWord_Register) {
		InputPassWord_Register = inputPassWord_Register;
	}

	public JButton getButtonRegister() {
		return ButtonRegister;
	}

	public void setButtonRegister(JButton buttonRegister) {
		ButtonRegister = buttonRegister;
	}

	public JLabel getLabelConfirmPassword() {
		return LabelConfirmPassword;
	}

	public void setLabelConfirmPassword(JLabel labelConfirmPassword) {
		LabelConfirmPassword = labelConfirmPassword;
	}

	public JPasswordField getInputConfirmPassword() {
		return InputConfirmPassword;
	}

	public void setInputConfirmPassword(JPasswordField inputConfirmPassword) {
		InputConfirmPassword = inputConfirmPassword;
	}

	public RegisterViewController getRegisterViewController() {
		return registerViewController;
	}

	public void setRegisterViewController(RegisterViewController registerViewController) {
		this.registerViewController = registerViewController;
	}

	public JTextField getInputEmail_Register() {
		return InputEmail_Register;
	}

	public void setInputEmail_Register(JTextField inputEmail_Register) {
		InputEmail_Register = inputEmail_Register;
	}

	public JLabel getBack() {
		return back;
	}

	public void setBack(JLabel back) {
		this.back = back;
	}
	
}
