package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import controller.ChangePasswordViewController;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePasswordView extends JFrame {

	private JPanel contentPane;
	private JTextField InputEmail;
	private JPasswordField OldPassword;
	private JPasswordField InputNewPassword;
	private JPasswordField InputConfirmPassword;
	private ChangePasswordViewController changePasswordViewController;
	private JButton btnChangePassword;

	public ChangePasswordView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 441, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblEmail.setBounds(30, 31, 66, 36);
		contentPane.add(lblEmail);
		
		InputEmail = new JTextField();
		InputEmail.setBounds(163, 33, 219, 36);
		contentPane.add(InputEmail);
		InputEmail.setColumns(10);
		
		JLabel lblOldPassword = new JLabel("Old Password");
		lblOldPassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblOldPassword.setBounds(30, 89, 128, 36);
		contentPane.add(lblOldPassword);
		
		OldPassword = new JPasswordField();
		OldPassword.setBounds(163, 91, 219, 36);
		contentPane.add(OldPassword);
		
		JLabel NewPassword = new JLabel("New Password");
		NewPassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
		NewPassword.setBounds(30, 142, 128, 36);
		contentPane.add(NewPassword);
		
		InputNewPassword = new JPasswordField();
		InputNewPassword.setBounds(163, 138, 219, 36);
		contentPane.add(InputNewPassword);
		
		JLabel ConfirmPass = new JLabel("Confirm Password");
		ConfirmPass.setFont(new Font("SansSerif", Font.PLAIN, 15));
		ConfirmPass.setBounds(30, 192, 128, 36);
		contentPane.add(ConfirmPass);
		
		InputConfirmPassword = new JPasswordField();
		InputConfirmPassword.setBounds(163, 192, 219, 36);
		contentPane.add(InputConfirmPassword);
		
		btnChangePassword = new JButton("Change");
		btnChangePassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnChangePassword.setBounds(139, 239, 146, 34);
		contentPane.add(btnChangePassword);
		
		changePasswordViewController = new ChangePasswordViewController(this);
	}
	public JTextField getInputEmail() {
		return InputEmail;
	}

	public void setInputEmail(JTextField inputEmail) {
		InputEmail = inputEmail;
	}

	public JPasswordField getOldPassword() {
		return OldPassword;
	}

	public void setOldPassword(JPasswordField oldPassword) {
		OldPassword = oldPassword;
	}

	public JPasswordField getInputNewPassword() {
		return InputNewPassword;
	}

	public void setInputNewPassword(JPasswordField inputNewPassword) {
		InputNewPassword = inputNewPassword;
	}

	public JPasswordField getInputConfirmPassword() {
		return InputConfirmPassword;
	}

	public void setInputConfirmPassword(JPasswordField inputConfirmPassword) {
		InputConfirmPassword = inputConfirmPassword;
	}

	public ChangePasswordViewController getChangePasswordViewController() {
		return changePasswordViewController;
	}

	public void setChangePasswordViewController(ChangePasswordViewController changePasswordViewController) {
		this.changePasswordViewController = changePasswordViewController;
	}

	public JButton getBtnChangePassword() {
		return btnChangePassword;
	}

	public void setBtnChangePassword(JButton btnChangePassword) {
		this.btnChangePassword = btnChangePassword;
	}
	
}
