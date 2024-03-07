package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.VerifyPassWordController;
import model.UserModel;

public class VerifyPassWordView extends JFrame {

	private JPanel contentPane;
	private JTextField txtCode;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JButton btnOke;
	private JButton btnCancel;
	private VerifyPassWordController verifyPassWordController;

	public VerifyPassWordView(UserModel user) {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setBounds(100, 100, 447, 183);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Verify Code");
		lblNewLabel.setBounds(5, 5, 440, 32);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBounds(5, 37, 453, 146);
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel_1 = new JLabel("Check your mail to get verify code");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 440, 35);
		panel.add(lblNewLabel_1);
		
		txtCode = new JTextField();
		txtCode.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtCode.setHorizontalAlignment(SwingConstants.CENTER);
		txtCode.setBackground(new Color(204, 255, 204));
		txtCode.setForeground(new Color(105, 105, 105));
		txtCode.setBounds(70, 46, 300, 42);
		panel.add(txtCode);
		txtCode.setColumns(10);
		
		btnOke = new JButton("OK");
		btnOke.setBackground(Color.WHITE);
		btnOke.setBorder(new LineBorder(new Color(127, 255, 0), 1, true));
		btnOke.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnOke.setBounds(122, 99, 78, 28);
		panel.add(btnOke);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnCancel.setBorder(new LineBorder(new Color(255, 0, 0), 1, true));
		btnCancel.setBounds(232, 99, 78, 28);
		panel.add(btnCancel);
		
		verifyPassWordController = new VerifyPassWordController(this,user);
	}
	
	public JTextField getTxtCode() {
		return txtCode;
	}
	public void setTxtCode(JTextField txtCode) {
		this.txtCode = txtCode;
	}
	public JButton getBtnOke() {
		return btnOke;
	}
	public void setBtnOke(JButton btnOke) {
		this.btnOke = btnOke;
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public VerifyPassWordController getVerifyPassWordController() {
		return verifyPassWordController;
	}

	public void setVerifyPassWordController(VerifyPassWordController verifyPassWordController) {
		this.verifyPassWordController = verifyPassWordController;
	}
	
}
