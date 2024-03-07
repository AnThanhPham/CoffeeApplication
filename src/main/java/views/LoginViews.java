package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.LoginViewController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginViews extends JPanel {

	private JLabel Close;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel LabelUserName;
	private JTextField InputUserName;
	private JLabel jLabel6;
	private JLabel LabelImageUser;
	private JLabel LabelPassWord;
	private JPasswordField InputPassWord;
	private JLabel jLabel9;
	private JLabel disable;
	private JLabel show;
	private JButton ButtonLogin;
	private JLabel SignUp;
	private JLabel jLabel14;
	private LoginViewController loginViewController;

	public LoginViews() {
		init();
		setBackground(new java.awt.Color(25, 118, 211));
		setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		Close.setFont(new java.awt.Font("Segoe UI", 0, 18)); 
		Close.setForeground(new java.awt.Color(255, 255, 255));
		Close.setHorizontalAlignment(SwingConstants.CENTER);
		Close.setText("X");
		Close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		add(Close, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 40, 29));

		jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
		jLabel3.setForeground(new java.awt.Color(255, 255, 255));
		jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel3.setText("Login");
		add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 47, 420, 41));

		jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
		jLabel4.setForeground(new java.awt.Color(255, 255, 255));
		jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel4.setText("Hello! Let's get started");
		add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 94, 420, -1));

		LabelUserName.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
		LabelUserName.setForeground(new java.awt.Color(199, 226, 255));
		LabelUserName.setText("Username");
		add(LabelUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 123, 341, -1));

		InputUserName.setFont(InputUserName.getFont().deriveFont(InputUserName.getFont().getSize() + 2f));
		InputUserName.setForeground(new java.awt.Color(255, 255, 255));
		InputUserName.setBorder(null);
		InputUserName.setBackground(new java.awt.Color(0, 0, 0, 1));
		add(InputUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 140, 240, 30));

		jLabel6.setForeground(new java.awt.Color(255, 255, 255));
		jLabel6.setText("_________________________________________");
		add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 147, 290, 39));

		LabelImageUser.setHorizontalAlignment(SwingConstants.CENTER);
		LabelImageUser.setIcon(new ImageIcon(getClass().getResource("/icon/icons8_user_20px_1.png"))); // NOI18N
		add(LabelImageUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 147, 40, 39));

		LabelPassWord.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
		LabelPassWord.setForeground(new java.awt.Color(199, 226, 255));
		LabelPassWord.setText("Password");
		add(LabelPassWord, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 192, 341, -1));

		InputPassWord.setFont(InputPassWord.getFont().deriveFont(InputPassWord.getFont().getSize() + 2f));
		InputPassWord.setForeground(new java.awt.Color(255, 255, 255));
		InputPassWord.setBorder(null);
		InputPassWord.setCaretColor(new java.awt.Color(255, 255, 255));
		InputPassWord.setBackground(new java.awt.Color(0, 0, 0, 1));
		add(InputPassWord, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 240, 30));

		jLabel9.setForeground(new java.awt.Color(255, 255, 255));
		jLabel9.setText("_________________________________________");
		add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 216, 290, 40));

		disable.setHorizontalAlignment(SwingConstants.CENTER);
		disable.setIcon(new ImageIcon(getClass().getResource("/icon/icons8_invisible_20px_1.png"))); // NOI18N
		disable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		add(disable, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 216, 40, 40));

		show.setHorizontalAlignment(SwingConstants.CENTER);
		show.setIcon(new ImageIcon(getClass().getResource("/icon/icons8_eye_20px_1.png"))); // NOI18N
		show.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		add(show, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 216, 40, 40));

		ButtonLogin.setBackground(new java.awt.Color(255, 255, 255));
		ButtonLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		ButtonLogin.setForeground(new java.awt.Color(25, 118, 211));
		ButtonLogin.setText("LOGIN");
		ButtonLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		add(ButtonLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 306, 341, 40));

		SignUp.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
		SignUp.setForeground(new java.awt.Color(255, 255, 255));
		SignUp.setText("Sign Up");
		SignUp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		add(SignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 357, 122, -1));

		jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
		jLabel14.setForeground(new java.awt.Color(199, 226, 255));
		jLabel14.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel14.setText("Don't have an account?");
		add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 357, 213, -1));
		
		loginViewController = new LoginViewController(this);
	}
	
	public void init() {
		Close = new JLabel();
		jLabel3 = new JLabel();
		jLabel4 = new JLabel();
		LabelUserName = new JLabel();
		InputUserName = new JTextField();
		jLabel6 = new JLabel(); 
		LabelImageUser = new JLabel();
		LabelPassWord = new JLabel();
		InputPassWord = new JPasswordField();
		jLabel9 = new JLabel();
		disable = new JLabel();
		show = new JLabel();
		ButtonLogin = new JButton();
		SignUp = new JLabel();
		jLabel14 = new JLabel();
	}

	public JLabel getClose() {
		return Close;
	}

	public void setClose(JLabel close) {
		Close = close;
	}

	public JLabel getjLabel3() {
		return jLabel3;
	}

	public void setjLabel3(JLabel jLabel3) {
		this.jLabel3 = jLabel3;
	}

	public JLabel getjLabel4() {
		return jLabel4;
	}

	public void setjLabel4(JLabel jLabel4) {
		this.jLabel4 = jLabel4;
	}

	public JLabel getLabelUserName() {
		return LabelUserName;
	}

	public void setLabelUserName(JLabel labelUserName) {
		LabelUserName = labelUserName;
	}

	public JTextField getInputUserName() {
		return InputUserName;
	}

	public void setInputUserName(JTextField inputUserName) {
		InputUserName = inputUserName;
	}

	public JLabel getjLabel6() {
		return jLabel6;
	}

	public void setjLabel6(JLabel jLabel6) {
		this.jLabel6 = jLabel6;
	}

	public JLabel getLabelImageUser() {
		return LabelImageUser;
	}

	public void setLabelImageUser(JLabel labelImageUser) {
		LabelImageUser = labelImageUser;
	}

	public JLabel getLabelPassWord() {
		return LabelPassWord;
	}

	public void setLabelPassWord(JLabel labelPassWord) {
		LabelPassWord = labelPassWord;
	}

	public JPasswordField getInputPassWord() {
		return InputPassWord;
	}

	public void setInputPassWord(JPasswordField inputPassWord) {
		InputPassWord = inputPassWord;
	}

	public JLabel getjLabel9() {
		return jLabel9;
	}

	public void setjLabel9(JLabel jLabel9) {
		this.jLabel9 = jLabel9;
	}

	public JLabel getDisable() {
		return disable;
	}

	public void setDisable(JLabel disable) {
		this.disable = disable;
	}

	public JLabel getShow() {
		return show;
	}

	public void setShow(JLabel show) {
		this.show = show;
	}

	public JButton getButtonLogin() {
		return ButtonLogin;
	}

	public void setButtonLogin(JButton buttonLogin) {
		ButtonLogin = buttonLogin;
	}

	public JLabel getSignUp() {
		return SignUp;
	}

	public void setSignUp(JLabel signUp) {
		SignUp = signUp;
	}

	public JLabel getjLabel14() {
		return jLabel14;
	}

	public void setjLabel14(JLabel jLabel14) {
		this.jLabel14 = jLabel14;
	}

	
}
