package views;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import controller.LoginController;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {
	private JLabel jLabel1;
	private JLabel jLabel12;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private LoginViews jPanel3;
	private RegisterViews jPanel4;
	private LoginController loginController;
	private CardLayout card;
	

	public Login() {
		initComponents();
		loginController = new LoginController(this);
	}

	private void initComponents() {
		jPanel1 = new JPanel();
		jPanel2 = new JPanel();
		jLabel12 = new JLabel();
		jLabel1 = new JLabel();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setUndecorated(true);
		getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		// panel 1
		jPanel1.setBackground(new java.awt.Color(255, 255, 255));
		jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 13)); 
		jLabel12.setForeground(new java.awt.Color(25, 118, 211));
		jLabel12.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel12.setText("Created By Junior Programmer");
		jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 500, -1));

		jLabel1.setIcon(new ImageIcon(getClass().getResource("/icon/bg-login.png"))); 
		jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 35, 500, 340));

		getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 440));
		
		// panel 2
		card = new CardLayout();
		jPanel2.setLayout(card);
		
		viewLogin();
		
		viewRegister();
		
		jPanel2.add(jPanel3,"viewLogin");
		jPanel2.add(jPanel4,"viewRegister");
		
		getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 420, 440));

		setSize(new java.awt.Dimension(916, 438));
		setLocationRelativeTo(null);
	}

	public void viewRegister() {
		jPanel4 = new RegisterViews();
		
	}

	public void viewLogin() {
		jPanel3 = new LoginViews();
		
	}

	public JLabel getjLabel1() {
		return jLabel1;
	}

	public void setjLabel1(JLabel jLabel1) {
		this.jLabel1 = jLabel1;
	}

	public JLabel getjLabel12() {
		return jLabel12;
	}

	public void setjLabel12(JLabel jLabel12) {
		this.jLabel12 = jLabel12;
	}

	public JPanel getjPanel1() {
		return jPanel1;
	}

	public void setjPanel1(JPanel jPanel1) {
		this.jPanel1 = jPanel1;
	}

	public JPanel getjPanel2() {
		return jPanel2;
	}

	public void setjPanel2(JPanel jPanel2) {
		this.jPanel2 = jPanel2;
	}

	public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	public CardLayout getCard() {
		return card;
	}

	public void setCard(CardLayout card) {
		this.card = card;
	}

	public LoginViews getjPanel3() {
		return jPanel3;
	}

	public void setjPanel3(LoginViews jPanel3) {
		this.jPanel3 = jPanel3;
	}

	public RegisterViews getjPanel4() {
		return jPanel4;
	}

	public void setjPanel4(RegisterViews jPanel4) {
		this.jPanel4 = jPanel4;
	}
}
