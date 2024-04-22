package views.menu;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Menu;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import controller.PanelProductController;
import dao.ProductDAO;
import model.CategoryModel;
import model.ProductModel;
import util.ImageFilter;
import views.menu.Product.ItemProduct;
import views.menu.Product.PagePanel;
import views.menu.Product.TImage;
import views.menu.Product.insertUpdateDelete;

public class PanelProduct extends JPanel {
	private PanelProductController controller;

	JPanel centerCenterPanel = new JPanel();
	JPanel northCenterPanel = new JPanel();
	JPanel southCenterPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel eastPanel = new JPanel();
	JPanel westPanel = new JPanel();
	JTextField searchTextField = new JTextField();// Thanh tìm kiếm
	JButton submit = new JButton("Tìm"); // nuts submit
	JButton insertProduct = new JButton("Thêm sản phẩm"); // Them san pham
	JLabel searchLabel = new JLabel("Tìm kiếm");
	JLabel menu = new JLabel("Loại: ");


	JPanel leftTop = new JPanel(); 
	JPanel centerTop = new JPanel();
	JPanel rightTop = new JPanel();
	JPanel topNorthPanel = new JPanel();
	JPanel centerNorthPanel = new JPanel();

	
	String typeCategoryname[];
	 JComboBox<String> typeProduct= new JComboBox<>();
	private static PanelProduct ins;
//	List<ProductModel> listProduct = ProductDAO.selectAll();

	public static PanelProduct getIns() {
		return ins;
	}
	

	public PanelProduct() {
		controller= new PanelProductController(this);
		ins = this;
		// set layout cho panel chinh
		this.setLayout(new BorderLayout());
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(eastPanel, BorderLayout.EAST);
		this.add(westPanel, BorderLayout.WEST);

		// Set centerPanel
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(northCenterPanel, BorderLayout.NORTH);
		centerPanel.add(southCenterPanel, BorderLayout.SOUTH);
		centerPanel.add(centerCenterPanel);

		// SEt kich thuoc cho tung phan
		northCenterPanel.setPreferredSize(new Dimension(50, 50));// setsize cho boder layout
		southCenterPanel.setPreferredSize(new Dimension(50, 50));// setsize cho boder layout
		eastPanel.setPreferredSize(new Dimension(30, 30));// setsize cho boder layout
		westPanel.setPreferredSize(new Dimension(30, 30));// setsize cho boder layout
		// centerPanel.setBackground(Color.red);

		// set cac phan trong norrthPanel
		northCenterPanel.setLayout(new BorderLayout());
		northCenterPanel.add(topNorthPanel, BorderLayout.NORTH);
		northCenterPanel.add(centerNorthPanel);
		searchTextField.setPreferredSize(new Dimension(300, 30));

		// tao left right center cho topcenterpanel
		topNorthPanel.setLayout(new BorderLayout());
		topNorthPanel.add(centerTop);
		topNorthPanel.add(rightTop, BorderLayout.EAST);
		topNorthPanel.add(leftTop, BorderLayout.WEST);
		// centerTop.setBackground(Color.gray);
		
		centerTop.add(searchLabel);
		centerTop.add(searchTextField);
		
		centerTop.add(submit);
		rightTop.add(insertProduct);
		
		leftTop.add(menu);
		leftTop.add(typeProduct);
		controller.addEvent();
		
		
		

// southCenterpanel
		try {
			controller.reload(true);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public PanelProductController getController() {
		return controller;
	}





	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.add(new PanelProduct());
		jf.setSize(600, 400);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);

	}
	public JPanel getCenterCenterPanel() {
		return centerCenterPanel;
	}


	public JPanel getNorthCenterPanel() {
		return northCenterPanel;
	}


	public JPanel getSouthCenterPanel() {
		return southCenterPanel;
	}


	public JPanel getCenterPanel() {
		return centerPanel;
	}


	public JPanel getEastPanel() {
		return eastPanel;
	}


	public JPanel getWestPanel() {
		return westPanel;
	}


	public JTextField getSearchTextField() {
		return searchTextField;
	}


	public JButton getSubmit() {
		return submit;
	}


	public JButton getInsertProduct() {
		return insertProduct;
	}


	public JLabel getSearchLabel() {
		return searchLabel;
	}


	public JLabel getMenu() {
		return menu;
	}


	public String[] getTypeCategoryname() {
		return typeCategoryname;
	}


	public JComboBox<String> getTypeProduct() {
		return typeProduct;
	}
	
	public JPanel getLeftTop() {
		return leftTop;
	}


	public JPanel getCenterTop() {
		return centerTop;
	}


	public JPanel getRightTop() {
		return rightTop;
	}


	public JPanel getTopNorthPanel() {
		return topNorthPanel;
	}


	public JPanel getCenterNorthPanel() {
		return centerNorthPanel;
	}

//	public List<ProductModel> getListProduct() {
//		return listProduct;
//	}
}