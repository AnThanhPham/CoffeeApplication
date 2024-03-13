package views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.MainController;
import model.UserModel;
import views.menu.PanelBill;
import views.menu.PanelCategory;
import views.menu.PanelCustomer;
import views.menu.PanelEmployee;
import views.menu.PanelOrderTable;
import views.menu.PanelProduct;
import views.menu.PanelShop;
import views.menu.PanelStatistical;

public class Main extends JFrame {

	private JPanel pnlMenu;
	private JButton btnShop;
	private JButton btnManageProduct;
	private JButton btnManageCustomer;
	private JButton btnBill;
	private JButton btnCategories;
	private JButton btnStatistical;
	private JButton btnNewButton;
	private PanelCategory panelCategory;
	private PanelStatistical panelStatistical;
	private PanelOrderTable panelOrderTable;
	private PanelEmployee panelEmployee;
	private PanelBill panelBill;
	private PanelShop panelShop;
	private PanelProduct panelProduct;
	private PanelCustomer panelCustomer;
	private JPanel panelCard;
	private MainController mainController;
	private JButton btnManageEmployee;
	private JButton btnOrderTable;
	private CardLayout cardLayout;
	private JMenuItem logout;
	private JMenuItem changePass;
	private JMenuBar menuBar;
	private JMenu option;
	

	void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(1366,900));
		setLocationRelativeTo(null);

		getContentPane().setLayout(null);
		getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());

		pnlMenu = new JPanel();
		pnlMenu.setBounds(0, 0, 286, 826);
		pnlMenu.setBackground(new java.awt.Color(239, 238, 244));
		pnlMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(239, 238, 244)));
		getContentPane().add(pnlMenu);
		pnlMenu.setLayout(null);

		btnShop = new JButton("New button");
		btnShop.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnShop.setBounds(0, 71, 286, 66);
		btnShop.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnShop.setForeground(new java.awt.Color(128, 128, 131));
		btnShop.setIcon(new ImageIcon(Main.class.getResource("/icon/Shop@0.5x.png"))); // NOI18N
		btnShop.setText("Bán hàng");
		btnShop.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		btnShop.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		btnShop.setIconTextGap(25);
		pnlMenu.add(btnShop);

		btnManageProduct = new JButton("Quản lí sản phẩm");
		btnManageProduct.setIcon(new ImageIcon(Main.class.getResource("/icon/Product@0.5x.png")));
		btnManageProduct.setIconTextGap(25);
		btnManageProduct.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnManageProduct.setHorizontalAlignment(SwingConstants.LEFT);
		btnManageProduct.setForeground(new Color(128, 128, 131));
		btnManageProduct.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnManageProduct.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnManageProduct.setBounds(0, 158, 286, 66);
		pnlMenu.add(btnManageProduct);

		btnManageCustomer = new JButton("Quản lí khách hàng");
		btnManageCustomer.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnManageCustomer.setIcon(new ImageIcon(Main.class.getResource("/icon/Customer.png")));
		btnManageCustomer.setIconTextGap(25);
		btnManageCustomer.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnManageCustomer.setHorizontalAlignment(SwingConstants.LEFT);
		btnManageCustomer.setForeground(new Color(128, 128, 131));
		btnManageCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnManageCustomer.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnManageCustomer.setBounds(0, 248, 286, 66);
		pnlMenu.add(btnManageCustomer);

		btnBill = new JButton("Hóa đơn");
		btnBill.setIcon(new ImageIcon(Main.class.getResource("/icon/Bill@0.5x.png")));
		btnBill.setIconTextGap(25);
		btnBill.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnBill.setHorizontalAlignment(SwingConstants.LEFT);
		btnBill.setForeground(new Color(128, 128, 131));
		btnBill.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBill.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnBill.setBounds(0, 337, 286, 66);
		pnlMenu.add(btnBill);

		btnCategories = new JButton("Loại");
		btnCategories.setIcon(new ImageIcon(Main.class.getResource("/icon/Diversity@0.5x.png")));
		btnCategories.setIconTextGap(25);
		btnCategories.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnCategories.setHorizontalAlignment(SwingConstants.LEFT);
		btnCategories.setForeground(new Color(128, 128, 131));
		btnCategories.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCategories.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnCategories.setBounds(0, 427, 286, 66);
		pnlMenu.add(btnCategories);

		btnStatistical = new JButton("Thống kê");
		btnStatistical.setIcon(new ImageIcon(Main.class.getResource("/icon/Analytics@0.3x.png")));
		btnStatistical.setIconTextGap(25);
		btnStatistical.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnStatistical.setHorizontalAlignment(SwingConstants.LEFT);
		btnStatistical.setForeground(new Color(128, 128, 131));
		btnStatistical.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnStatistical.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnStatistical.setBounds(0, 514, 286, 66);
		pnlMenu.add(btnStatistical);

		btnOrderTable = new JButton("Đặt bàn");
		btnOrderTable.setIcon(new ImageIcon(Main.class.getResource("/icon/Table 2@0.5x.png")));
		btnOrderTable.setIconTextGap(25);
		btnOrderTable.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnOrderTable.setHorizontalAlignment(SwingConstants.LEFT);
		btnOrderTable.setForeground(new Color(128, 128, 131));
		btnOrderTable.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnOrderTable.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnOrderTable.setBounds(0, 605, 286, 66);
		pnlMenu.add(btnOrderTable);

		btnManageEmployee = new JButton("Quản lí nhân viên");
		btnManageEmployee.setIcon(new ImageIcon(Main.class.getResource("/icon/Permanent Job@0.5x.png")));
		btnManageEmployee.setIconTextGap(25);
		btnManageEmployee.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnManageEmployee.setHorizontalAlignment(SwingConstants.LEFT);
		btnManageEmployee.setForeground(new Color(128, 128, 131));
		btnManageEmployee.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnManageEmployee.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnManageEmployee.setBounds(0, 693, 286, 66);
		pnlMenu.add(btnManageEmployee);

		panelCard = new JPanel();
		panelCard.setBackground(Color.WHITE);
		panelCard.setBounds(287, 0, 1063, 826);
		getContentPane().add(panelCard);
		cardLayout = new CardLayout();
		panelCard.setLayout(cardLayout);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		option = new JMenu("OPTION");
		option.setIcon(new ImageIcon(Main.class.getResource("/icon/Option 3@0.3x.png")));
		option.setFont(new Font("SansSerif", Font.BOLD, 14));
		menuBar.add(option);
		
		logout = new JMenuItem("Log Out");
		logout.setIcon(new ImageIcon(Main.class.getResource("/icon/Open Pane@0.2x.png")));
		logout.setFont(new Font("SansSerif", Font.PLAIN, 12));
		logout.setBorder(null);
		option.add(logout);
		
		changePass = new JMenuItem("Change Password");
		changePass.setIcon(new ImageIcon(Main.class.getResource("/icon/Password Reset@0.2x.png")));
		changePass.setFont(new Font("SansSerif", Font.PLAIN, 12));
		changePass.setBorder(null);
		option.add(changePass);
	}
	
	public Main(UserModel user) {
		init();
		
		viewPanelShop();

		viewPanelProduct();

		viewPanelCustomer();

		viewPanelBill();

		viewPanelCategory();

		viewPanelStatistical();

		viewPanelOrderTable();

		viewPanelEmployee();

		panelCard.add(panelShop, "panelShop");
		panelCard.add(panelProduct, "panelProduct");
		panelCard.add(panelCustomer, "panelCustomer");
		panelCard.add(panelBill, "panelBill");
		panelCard.add(panelCategory, "panelCategory");
		panelCard.add(panelStatistical, "panelStatistical");
		panelCard.add(panelOrderTable, "panelOrderTable");
		panelCard.add(panelEmployee, "panelEmployee");
		
		mainController = new MainController(this,user);
	}
	
	public JMenuItem getChangePass() {
		return changePass;
	}

	public void setChangePass(JMenuItem changePass) {
		this.changePass = changePass;
	}

	public JMenuItem getLogout() {
		return logout;
	}

	public void setLogout(JMenuItem logout) {
		this.logout = logout;
	}

	public void viewPanelShop() {
		panelShop = new PanelShop();
	}

	public void viewPanelCategory() {
		panelCategory = new PanelCategory();
	}

	public void viewPanelBill() {
		panelBill = new PanelBill();
	}

	public void viewPanelStatistical() {
		panelStatistical = new PanelStatistical();
		
	}

	public void viewPanelProduct() {
		panelProduct = new PanelProduct();
	}

	public void viewPanelOrderTable() {
		panelOrderTable = new PanelOrderTable();
	}

	public void viewPanelEmployee() {
		panelEmployee = new PanelEmployee();
	}

	public void viewPanelCustomer() {
		panelCustomer = new PanelCustomer();
	}

	public JButton getBtnShop() {
		return btnShop;
	}

	public void setBtnShop(JButton btnShop) {
		this.btnShop = btnShop;
	}

	public JButton getBtnManageProduct() {
		return btnManageProduct;
	}

	public void setBtnManageProduct(JButton btnManageProduct) {
		this.btnManageProduct = btnManageProduct;
	}

	public JButton getBtnManageCustomer() {
		return btnManageCustomer;
	}

	public void setBtnManageCustomer(JButton btnManageCustomer) {
		this.btnManageCustomer = btnManageCustomer;
	}

	public JButton getBtnBill() {
		return btnBill;
	}

	public void setBtnBill(JButton btnBill) {
		this.btnBill = btnBill;
	}

	public JButton getBtnCategories() {
		return btnCategories;
	}

	public void setBtnCategories(JButton btnCategories) {
		this.btnCategories = btnCategories;
	}

	public JButton getBtnStatistical() {
		return btnStatistical;
	}

	public void setBtnStatistical(JButton btnStatistical) {
		this.btnStatistical = btnStatistical;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}

	public JPanel getPanelCard() {
		return panelCard;
	}

	public void setPanelCard(JPanel panelCard) {
		this.panelCard = panelCard;
	}

	public JButton getBtnManageEmployee() {
		return btnManageEmployee;
	}

	public void setBtnManageEmployee(JButton btnManageEmployee) {
		this.btnManageEmployee = btnManageEmployee;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	public JButton getBtnOrderTable() {
		return btnOrderTable;
	}

	public void setBtnOrderTable(JButton btnOrderTable) {
		this.btnOrderTable = btnOrderTable;
	}

	public JPanel getPnlMenu() {
		return pnlMenu;
	}

	public void setPnlMenu(JPanel pnlMenu) {
		this.pnlMenu = pnlMenu;
	}
}
