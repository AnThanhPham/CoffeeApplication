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

import dao.ProductDAO;
import model.CategoryModel;
import model.ProductModel;
import util.ImageFilter;
import views.menu.Product.ItemProduct;
import views.menu.Product.PagePanel;
import views.menu.Product.TImage;
import views.menu.Product.insertUpdateDelete;

public class PanelProduct extends JPanel {
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

	
	String typeCategoryname[];
	
	 JComboBox<String> typeProduct= new JComboBox<>();
	private static PanelProduct ins;
	List<ProductModel> listProduct = ProductDAO.selectAll();

	public static PanelProduct getIns() {
		return ins;
	}
	
	public void reLoad(boolean isSelectAll) throws SQLException {
		if(isSelectAll==true) {
			try {
				List<String> categoryList= ProductDAO.selectCategory();
				typeCategoryname= new String[categoryList.size()+1];
				typeCategoryname[0]= "Tất cả";
				for(int i=0; i<categoryList.size(); i++) {
					typeCategoryname[i+1]= categoryList.get(i);
					
				}
				typeProduct.setModel(new DefaultComboBoxModel<String>(typeCategoryname));
				
			} catch (SQLException e1) {
				
				
				e1.printStackTrace();
			}
			listProduct= ProductDAO.selectAll();
		}else
			listProduct= ProductDAO.search(searchTextField.getText(),typeProduct.getSelectedItem().toString());
		
			
		int amountPageNumber = ((listProduct.size()-1 )/ 8) +1 ;
		PagePanel[] pageNumber = new PagePanel[amountPageNumber + 1];
		
		southCenterPanel.removeAll(); // trước khi gọi lại các trang ta xóa hết những thứ trước đi
		for (int i = 1; i <= amountPageNumber; i++) {
			pageNumber[i] = new PagePanel(i);
			southCenterPanel.add(pageNumber[i]);
		}
	    showPage(1);
		
	}
	

	public void showPage(int pageNumber) {
		
		centerCenterPanel.setLayout(new GridLayout(2, 4, 20, 20));
		centerCenterPanel.removeAll();
		ItemProduct[] itemProduct = new ItemProduct[8];
		for (int i = 0; i < 8; i++) {
			try {
				itemProduct[i] = new ItemProduct(listProduct.get(i + (pageNumber - 1) * 8));
			} catch (Exception e) {
				ProductModel empty = new ProductModel();
				itemProduct[i] = new ItemProduct(null);
				// TODO: handle exception
			}
			centerCenterPanel.add(itemProduct[i]);
		}
		centerCenterPanel.setVisible(false);
		centerCenterPanel.setVisible(true);

	}

	
	

	public PanelProduct() {
		
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
		JPanel topNorthPanel = new JPanel();
		JPanel centerNorthPanel = new JPanel();
		northCenterPanel.add(topNorthPanel, BorderLayout.NORTH);
		northCenterPanel.add(centerNorthPanel);
		searchTextField.setPreferredSize(new Dimension(300, 30));

		// tao left right center cho topcenterpanel
		JPanel leftTop = new JPanel(); 
		JPanel centerTop = new JPanel();
		JPanel rightTop = new JPanel();
		topNorthPanel.setLayout(new BorderLayout());
		topNorthPanel.add(centerTop);
		topNorthPanel.add(rightTop, BorderLayout.EAST);
		topNorthPanel.add(leftTop, BorderLayout.WEST);
		// centerTop.setBackground(Color.gray);
		
		centerTop.add(searchLabel);
		centerTop.add(searchTextField);
		searchTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
				try {
					 listProduct = ProductDAO.search(searchTextField.getText(),typeProduct.getSelectedItem().toString());
					 System.out.println(searchTextField.getText());
					 reLoad(true);
					 reLoad(false);
					
				} catch (SQLException e1) {
					//JOptionPane.showMessageDialog(dialog,e.toString(),"Thông Báo", JOptionPane.MESSAGE_PROPERTY );
					System.out.println(e1.toString());
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		centerTop.add(submit);
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					 listProduct = ProductDAO.search(searchTextField.getText(),typeProduct.getSelectedItem().toString());
					 System.out.println(searchTextField.getText());
					 reLoad(true);
					 reLoad(false);
					
				} catch (SQLException e1) {
					//JOptionPane.showMessageDialog(dialog,e.toString(),"Thông Báo", JOptionPane.MESSAGE_PROPERTY );
					System.out.println(e1.toString());
				}
				
			}
		});
		
		
		
		rightTop.add(insertProduct);
		insertProduct.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new insertUpdateDelete(new ProductModel(),true);
			}
		});
		
		
		leftTop.add(menu);
		leftTop.add(typeProduct);
		typeProduct.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(typeProduct.getSelectedIndex()==0) {
						reLoad(true);
					}else {
						reLoad(false);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		
		// leftTop.add(typeProduct);
//		for (int i = 0; i < typeProduct.length; i++) {
//			menu1.add(typeProduct[i]);
//		}
//		outMenu.add(menu1);
//		leftTop.add(outMenu);

		// responsive topNorthPane
		topNorthPanel.addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override

			public void componentResized(ComponentEvent e) {
				int width = centerTop.getWidth();
				System.out.println("width" + width);
				// TODO Auto-generated method stub
				searchTextField.setPreferredSize(new Dimension(width / 2, 30));
				searchTextField.setVisible(false);
				searchTextField.setVisible(true);

			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub

			}
		});

		// set centerCenterpanel
		showPage(1);

// southCenterpanel
		try {
			reLoad(true);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	

	}

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.add(new PanelProduct());
		jf.setSize(600, 400);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);

	}

}

// tao them class cho itemProduct





// Cat anh
