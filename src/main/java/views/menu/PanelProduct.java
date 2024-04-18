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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ProductDAO;
import model.CategoryModel;
import model.ProductModel;

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

	// CenterPanel
//	JLabel product1 = new JLabel();
//	JLabel nameProduct1 = new JLabel("Ten: Trà dâu");
//	JLabel priceProduct1 = new JLabel("Gia: 55.000");
//	JLabel descriptionPr1 = new JLabel("Sản phẩm là sự kết hợp từ dâu tây, trà nhài,..");
//	JPanel leftCenterpanel = new JPanel();

	
	String typeCategoryname[];
	
	 JComboBox<String> typeProduct= new JComboBox<>();
	 	
	 //JComboBox<String> typeProduct= new JComboBox<>(new String []{"cafe","banhngot","nước ép"});
//	JMenuBar outMenu = new JMenuBar();
//	JMenu menu1 = new JMenu("Menu");
//	String[] typeProduct = { "cafe", "nuoc ngot", "banh ngot", "Nuoc ep" };
	private static PanelProduct ins;
	List<ProductModel> listProduct = ProductDAO.selectAll();

	public static PanelProduct getIns() {
		return ins;
	}
	
	public void reLoad(boolean isSelectAll) throws SQLException {
		if(isSelectAll==true) {
			listProduct= ProductDAO.selectAll();
		}else
			listProduct= ProductDAO.search(searchTextField.getText(),typeProduct.getSelectedItem().toString());
		
		try {
			typeCategoryname= new String[ProductDAO.selectCategory().size()];
			for(int i=0; i<typeCategoryname.length; i++) {
				typeCategoryname[i]= ProductDAO.selectCategory().get(i);
				
			}
			typeProduct.setModel(new DefaultComboBoxModel<String>(typeCategoryname));
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
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
				itemProduct[i] = new ItemProduct(empty);
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
		centerTop.add(submit);
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					 listProduct = ProductDAO.search(searchTextField.getText(),typeProduct.getSelectedItem().toString());
					 System.out.println(searchTextField.getText());
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
					reLoad(false);
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
class ItemProduct extends JPanel {
	JLabel centerItem;
	JLabel southItem;

	ItemProduct(ProductModel model){
		centerItem = new TImage(new ImageIcon(model.getImage()));
		southItem = new JLabel();
		
//		ImageIcon image=new ImageIcon(model.getImage());
//		centerItem.setIcon(image);
		centerItem.setOpaque(true);
		centerItem.setBackground(Color.lightGray);
		
		southItem.setText("<html>"+"Tên: "+model.getName()+ "<br>"+"Giá: "+ model.getPrice()+"</html>");
		southItem.setForeground(Color.darkGray);
		southItem.setFont(new Font("Times New roman",Font.PLAIN,20));
		
		this.setLayout(new BorderLayout());
		this.add(centerItem);
		this.add(southItem,BorderLayout.SOUTH);
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				new insertUpdateDelete(model,false);
				
		}
		});
		
	
	}

}

class insertUpdateDelete extends JDialog {
	public insertUpdateDelete(ProductModel model,boolean isInsert) {
		
		JDialog dialog= new JDialog();
		JLabel topDialog = new JLabel();
		JLabel centerDialog = new JLabel();
		JPanel bottomDialog = new JPanel();
		dialog.setVisible(true);
		dialog.setMinimumSize(new Dimension(400,400));
		dialog.setLayout(new BorderLayout());	
		dialog.add(topDialog, BorderLayout.NORTH);
		dialog.add(bottomDialog, BorderLayout.SOUTH);
		dialog.add(centerDialog);
		
		// set top dialog
		if(isInsert==true) {
			topDialog.setText("Thêm sản phẩm");
			
		}else topDialog.setText("Chi tiết sản phẩm");
		topDialog.setVerticalAlignment(JLabel.CENTER);
		topDialog.setHorizontalAlignment(JLabel.CENTER);
		topDialog.setFont(new Font(Font.SANS_SERIF, Font.BOLD,18));
		topDialog.setPreferredSize(new Dimension(100,60));
		
		
		Font centerFont= new Font(Font.SANS_SERIF, Font.BOLD, 15);
		// set center dialog
		centerDialog.setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		JLabel idProduct= new JLabel("ID");
		idProduct.setFont(centerFont);
		JTextField idField= new JTextField(model.getID()+"");
		if(isInsert==false) idField.setEditable(false);//ko đc sửa
		JLabel nameProduct= new JLabel("Tên");
		nameProduct.setFont(centerFont);
		JTextField nameField= new JTextField( model.getName());
		JLabel priceProduct= new JLabel("Giá");
		priceProduct.setFont(centerFont);
		JTextField priceField= new JTextField(model.getPrice()+"");
		JLabel desProduct= new JLabel("Mô Tả");
		desProduct.setFont(centerFont);
		JTextField desField= new JTextField(model.getDescription());
		JLabel imgProduct= new JLabel("Link Ảnh");
		imgProduct.setFont(centerFont);
		JTextField imgField= new JTextField(model.getImage());
		JLabel categoryProduct= new JLabel("Loại");
		categoryProduct.setFont(centerFont);
		JTextField categoryField= new JTextField(model.getCategory().getID()+"");
	// cot trai	
		gbc.insets= new Insets(0, 10,0,10);   // set padding
		gbc.weightx= 0.3;  // set ti le cho ben trai ( con lai la ti le thua)
		gbc.ipadx=60;		// tang chieu rong cua 1 ô 
		gbc.ipady= 10;		// tang chieu cao của cả ô
		gbc.anchor= GridBagConstraints.LINE_START; // căn vị trí của component( tất cả các loại J)so với ô- cụ thể đây là căn đầu dòng
		gbc.gridx= 0;
		gbc.gridy= 0;
		centerDialog.add(idProduct,gbc);
		gbc.gridy= 1;
		centerDialog.add(nameProduct,gbc);
		gbc.gridy= 2;
		centerDialog.add(priceProduct,gbc);
		gbc.gridy= 3;
		centerDialog.add(desProduct,gbc);
		gbc.gridy= 4;
		centerDialog.add(imgProduct,gbc);
		gbc.gridy= 5;
		centerDialog.add(categoryProduct,gbc);
		
	// set size cho cot trai center
		
	// cot phai
		
		gbc.fill= GridBagConstraints.HORIZONTAL;  // lap đầy component trong ô
		gbc.ipadx= 160;   
		gbc.weightx= 0.7;
		
		gbc.gridx= 1;
		gbc.gridy= 0;
		centerDialog.add(idField,gbc);
		gbc.gridy= 1;
		centerDialog.add(nameField,gbc);
		gbc.gridy= 2;
		centerDialog.add(priceField,gbc);
		gbc.gridy= 3;
		centerDialog.add(desField,gbc);
		gbc.gridy= 4;
		centerDialog.add(imgField,gbc);
		gbc.gridy= 5;
		centerDialog.add(categoryField,gbc);
//set soundDialog
		Font fontSouth= new Font(Font.SANS_SERIF,Font.BOLD,14);
		bottomDialog.setPreferredSize(new Dimension(100,60));
		JButton insert= new JButton("Thêm");
		insert.setFont(fontSouth);
		insert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					model.setID(Integer.parseInt(idField.getText()));
					model.setPrice(Integer.parseInt(priceField.getText()));
					model.setName(nameField.getText());
					model.setDescription(desField.getText());
					model.setImage(imgField.getText());
					model.getCategory().setID(Integer.parseInt(categoryField.getText()));
					ProductDAO.insert(model);
					dialog.dispose();
					JOptionPane.showMessageDialog(dialog, "Thêm thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
					PanelProduct.getIns().reLoad(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					
					JOptionPane.showMessageDialog(dialog, "Thêm thất bại","Thông báo",JOptionPane.INFORMATION_MESSAGE);
				} 
				
				
			}
		});
		
		JButton edit= new JButton("Sửa");
		edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					model.setID(Integer.parseInt(idField.getText()));
					model.setPrice(Integer.parseInt(priceField.getText()));
					model.setName(nameField.getText());
					model.setDescription(desField.getText());
					model.setImage(imgField.getText());
					ProductDAO.update(model);
					dialog.dispose();
					JOptionPane.showMessageDialog(dialog, "Sửa thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
					PanelProduct.getIns().reLoad(true);
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(dialog, e2.toString(),"Thông báo",JOptionPane.INFORMATION_MESSAGE);

				}
				
			}
		});
		edit.setFont(fontSouth);
		
		JButton delete= new JButton("Xóa");
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ProductDAO.delete(model);
					dialog.dispose();
					JOptionPane.showMessageDialog(dialog, "Xóa thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
					PanelProduct.getIns().reLoad(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					
					JOptionPane.showMessageDialog(dialog, "Xóa thất bại","Thông báo",JOptionPane.INFORMATION_MESSAGE);
				} 
				
			}
		});
		delete.setFont(fontSouth);
		JButton cancel= new JButton("Thoát");
		cancel.setFont(fontSouth);
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result= JOptionPane.showConfirmDialog(dialog, "Bạn có chắc chắn thoát","Thông báo", JOptionPane.YES_NO_OPTION);
				if (result==JOptionPane.YES_OPTION) {
					dialog.dispose();
					
				}
				
			}
		});
		
		
		
		if(isInsert==true) {
			
			bottomDialog.add(insert);
			bottomDialog.add(cancel);
		}else {
			bottomDialog.add(edit);
			bottomDialog.add(delete);
			bottomDialog.add(cancel);
		}
		
		
		
	}
	
}

class PagePanel extends JLabel {	
	private int pageNumber;

	public PagePanel(int pageNumber) {
		this.setHorizontalAlignment(CENTER);
		this.setForeground(Color.WHITE);
		this.pageNumber = pageNumber;
		this.setText(pageNumber + "");
		// TODO Auto-generated constructor stub
		setOpaque(false);// TRUE THI HIEN BACHGROUND MAC DINH
		this.setPreferredSize(new Dimension(30, 30));

		// add su kien khi an pagenumber

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				PanelProduct.getIns().showPage(pageNumber);

			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.gray);
		g.fillOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
		super.paintComponent(g);

	}
}

// Cat anh
class TImage extends JLabel {
	private final ImageIcon srcIcon;
	private final ImageIcon cloneIcon;

	public TImage(ImageIcon icon) {
		srcIcon = icon;
		cloneIcon = new ImageIcon(srcIcon.getImage());
		this.setIcon(cloneIcon);
		this.setVerticalAlignment(JLabel.CENTER);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				// resize
				int wight1 = Math.max(1, getWidth());
				int height1 = Math.max(1, (int) (getWidth() / 1.0 / srcIcon.getIconWidth() * srcIcon.getIconHeight()));
				int width2 = Math.max(1, (int) (getHeight() / 1.0 / srcIcon.getIconHeight() * srcIcon.getIconWidth()));
				int height2 = Math.max(1, getHeight());
				cloneIcon.setImage(srcIcon.getImage().getScaledInstance(Math.min(wight1, width2),
						Math.min(height1, height2), Image.SCALE_DEFAULT));

			}

			@Override
			public void componentMoved(ComponentEvent e) {

			}

			@Override
			public void componentShown(ComponentEvent e) {

			}

			@Override
			public void componentHidden(ComponentEvent e) {

			}
		});
	}

	public ImageIcon getSrcIcon() {
		return srcIcon;
	}

	public ImageIcon getCloneIcon() {
		return cloneIcon;
	}
}
