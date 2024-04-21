package views.menu.Product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.lang.ModuleLayer.Controller;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.ProductDAO;
import model.ProductModel;
import util.ImageFilter;
import util.ValidateUtils;
import views.menu.PanelProduct;

public class insertUpdateDelete extends JFrame {
	JLabel topDialog = new JLabel();
	JLabel centerDialog = new JLabel();
	JPanel bottomDialog = new JPanel();
	//Cột trái
	JLabel idProduct= new JLabel("ID");
	JLabel nameProduct= new JLabel("Tên");
	JLabel priceProduct= new JLabel("Giá");
	JLabel desProduct= new JLabel("Mô Tả");
	JLabel imgProduct= new JLabel("Link Ảnh");
	JButton choseImage= new JButton("Chọn");
	// loại sp
	JLabel categoryProduct= new JLabel("Loại");
	 JComboBox<String> typeProduct= new JComboBox<>();
	
	public JComboBox<String> getTypeProduct() {
		return typeProduct;
	}
	// sound dialog
	JButton insert= new JButton("Thêm");
	JButton edit= new JButton("Sửa");
	JButton delete= new JButton("Xóa");
	JButton cancel= new JButton("Thoát");
	
	
	
	public JButton getInsert() {
		return insert;
	}
	public JButton getEdit() {
		return edit;
	}
	public JButton getDelete() {
		return delete;
	}
	public JButton getCancel() {
		return cancel;
	}
	int categoryId;
	public JTextField getNameField() {
		return nameField;
	}
	public void setNameField(JTextField nameField) {
		this.nameField = nameField;
	}
	public JTextField getPriceField() {
		return priceField;
	}
	public void setPriceField(JTextField priceField) {
		this.priceField = priceField;
	}
	String name;
	private JTextField nameField,priceField, idField,desField, imgField;
	private ProductModel model;
	public ProductModel getModel() {
		return model;
	}
	private TImage imgLabel;
	
	public insertUpdateDelete(ProductModel model,boolean isInsert) {
		this.model=model;
		//JDialog dialog= new JDialog();
		
		this.setVisible(true);
		this.setMinimumSize(new Dimension(500,600));
		this.setLayout(new BorderLayout());	
		this.add(topDialog, BorderLayout.NORTH);
		this.add(bottomDialog, BorderLayout.SOUTH);
		this.add(centerDialog);
		this.setResizable(false);
		
		// set top dialog
		if(isInsert==true) {
			topDialog.setText("Thêm sản phẩm");
			
		}else {
			topDialog.setText("Chi tiết sản phẩm");
		}
		
		
		
		topDialog.setVerticalAlignment(JLabel.CENTER);
		topDialog.setHorizontalAlignment(JLabel.CENTER);
		topDialog.setFont(new Font(Font.SANS_SERIF, Font.BOLD,18));
		topDialog.setPreferredSize(new Dimension(100,60));
		
		
		Font centerFont= new Font(Font.SANS_SERIF, Font.BOLD, 15);
		// set center dialog
		centerDialog.setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		
		
		idProduct.setFont(centerFont);
		idField= new JTextField(model.getID()+"");
		if(isInsert==false) idField.setEditable(false);//ko đc sửa
		
		
		nameProduct.setFont(centerFont);
		 nameField= new JTextField( model.getName());
		
		
		priceProduct.setFont(centerFont);
		 priceField= new JTextField((int)model.getPrice()+"");
		
		
		desProduct.setFont(centerFont);
		 desField= new JTextField(model.getDescription());
		
		imgProduct.setFont(centerFont);
		 imgField= new JTextField(model.getImage());
		
		imgLabel = new TImage(new ImageIcon(model.getImage()));
		
		
		categoryProduct.setFont(centerFont);
		//JTextField categoryField= new JTextField(model.getCategory().getID()+"");
		//JComboBox<int>categoryField= new Produc 
		
	// cot trai	
		gbc.insets= new Insets(0, 10,0,10);   // set padding
		gbc.weightx= 0.3;  // set ti le cho ben trai ( con lai la ti le thua)
		gbc.ipadx=60;		// tang chieu rong cua 1 ô 
		gbc.ipady= 10;		// tang chieu cao của cả ô
		gbc.anchor= GridBagConstraints.LINE_START; // căn vị trí của component( tất cả các loại J)so với ô- cụ thể đây là căn đầu dòng
		gbc.gridx= 0;
		gbc.gridy= 0;
		//centerDialog.add(idProduct,gbc);
		gbc.gridy= 1;
		centerDialog.add(nameProduct,gbc);
		gbc.gridy= 2;
		centerDialog.add(priceProduct,gbc);
		gbc.gridy= 3;
		centerDialog.add(desProduct,gbc);
		gbc.gridy= 4;
		centerDialog.add(imgProduct,gbc);
		gbc.gridy= 6;
		centerDialog.add(categoryProduct,gbc);
		
	// set size cho cot trai center
		
	// cot phai
		
		gbc.fill= GridBagConstraints.HORIZONTAL;  // lap đầy component trong ô
		gbc.ipadx= 160;   
		gbc.weightx= 0.7;
		
		gbc.gridx= 1;
		gbc.gridy= 0;
		idField.setEnabled(false);
		//centerDialog.add(idField,gbc);
		gbc.gridy= 1;
		centerDialog.add(nameField,gbc);
		gbc.gridy= 2;
		centerDialog.add(priceField,gbc);
		gbc.gridy= 3;
		centerDialog.add(desField,gbc);
		gbc.gridy= 4;
		gbc.insets.top=10;
		//centerDialog.add(imgField,gbc);
		imgLabel.setPreferredSize(new Dimension(100,100));
		imgLabel.setOpaque(true);
		centerDialog.add(imgLabel,gbc);
		
		
		
		
// sửa chọn link ảnh
		//gbc.weightx=0.0;
		//gbc.gridx= 2;
		gbc.insets.top=5;
		gbc.gridx=1;
		gbc.gridy=5;
		choseImage.setPreferredSize(new Dimension(20,10));
		
		
		centerDialog.add(choseImage,gbc);
		
		
		
		
		
		
		gbc.weightx=0.7;
		gbc.gridx=1;
		//gbc.gridy= 5;
		gbc.gridy= 6;
		String typeCategoryname[];
		
		 typeProduct.setBackground(Color.white);
		List<ProductModel> listProduct = ProductDAO.selectAll();
					List<String> categoryList;
					try {
						categoryList = ProductDAO.selectCategory();
						typeCategoryname= new String[categoryList.size()];
						for(int i=0; i<categoryList.size(); i++) {
							typeCategoryname[i]= categoryList.get(i);
							
						}
						typeProduct.setModel(new DefaultComboBoxModel<String>(typeCategoryname));
						
						
						if(isInsert==false) {
							try {
								
								name= ProductDAO.selectCategoryName(model.getID());
								typeProduct.setSelectedItem(name);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
						
						centerDialog.add(typeProduct,gbc);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
				
		
		
		
		
		
		
//set soundDialog
		Font fontSouth= new Font(Font.SANS_SERIF,Font.BOLD,14);
		bottomDialog.setPreferredSize(new Dimension(100,60));
		insert.setFont(fontSouth);
	
		edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertUpdateDelete check= insertUpdateDelete.this;
				String nameText= check.getNameField().getText();
				try {
					
					if(ValidateUtils.checkEmptyAndNull(check.getNameField().getText())){
						JOptionPane.showMessageDialog(check, "Tên sản phẩm không được trống","Thông báo",JOptionPane.INFORMATION_MESSAGE);
						return ;
					}
					if(ValidateUtils.checkEmptyAndNull((String)check.getPriceField().getText())) {
						JOptionPane.showMessageDialog(check, "Giá sản phẩm không được trống","Thông báo",JOptionPane.INFORMATION_MESSAGE);
						
						return; 
					}
					if(PanelProduct.getIns().getController().checkPrice((String)check.getPriceField().getText())== false) {
						JOptionPane.showMessageDialog(check, "Giá phải là số dương");
						return ;
					}
					try {
						List<String> productList = ProductDAO.listName();
						for(String x: productList) {
							if(nameText.equalsIgnoreCase(x)==false|| nameText.equalsIgnoreCase(model.getName()) ) {
								
								continue;
							}else {
								JOptionPane.showMessageDialog(check, "Tên sản phẩm đã tồn tại");
								return ;
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					model.setID(Integer.parseInt(idField.getText()));
					model.setPrice(Integer.parseInt(priceField.getText()));
					model.setName(nameField.getText());
					model.setDescription(desField.getText());
					model.setImage(imgField.getText());
					categoryId= ProductDAO.selectCategoryId((String)typeProduct.getSelectedItem());// chuyển tên loại sp thành id loại sp;
					model.getCategory().setID(categoryId);
					
					ProductDAO.update(model);
					insertUpdateDelete.this.dispose();
					JOptionPane.showMessageDialog(insertUpdateDelete.this, "Sửa thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
					PanelProduct.getIns().getController().reload(true);
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(insertUpdateDelete.this, e2.toString(),"Thông báo",JOptionPane.INFORMATION_MESSAGE);

				}
				
			}
		});
		edit.setFont(fontSouth);
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ProductDAO.delete(model);
					insertUpdateDelete.this.dispose();
					JOptionPane.showMessageDialog(insertUpdateDelete.this, "Xóa thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
					PanelProduct.getIns().getController().reload(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					
					JOptionPane.showMessageDialog(insertUpdateDelete.this, "Xóa thất bại","Thông báo",JOptionPane.INFORMATION_MESSAGE);
				} 
				
			}
		});
		delete.setFont(fontSouth);
		cancel.setFont(fontSouth);
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result= JOptionPane.showConfirmDialog(insertUpdateDelete.this, "Bạn có chắc chắn thoát","Thông báo", JOptionPane.YES_NO_OPTION);
				if (result==JOptionPane.YES_OPTION) {
					insertUpdateDelete.this.dispose();
					
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
		PanelProduct.getIns().getController().addEventDialog(insertUpdateDelete.this);
		this.setVisible(false);
		this.setVisible(true);
	}
	public void setImgLabel(TImage imgLabel) {
		this.imgLabel = imgLabel;
	}
	public JLabel getTopDialog() {
		return topDialog;
	}
	public JLabel getCenterDialog() {
		return centerDialog;
	}
	public JPanel getBottomDialog() {
		return bottomDialog;
	}
	public JLabel getIdProduct() {
		return idProduct;
	}
	public JLabel getNameProduct() {
		return nameProduct;
	}
	public JLabel getPriceProduct() {
		return priceProduct;
	}
	public JLabel getDesProduct() {
		return desProduct;
	}
	public JLabel getImgProduct() {
		return imgProduct;
	}
	public JButton getChoseImage() {
		return choseImage;
	}
	public JLabel getCategoryProduct() {
		return categoryProduct;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public String getName() {
		return name;
	}
	public JTextField getIdField() {
		return idField;
	}
	public JTextField getDesField() {
		return desField;
	}
	public JTextField getImgField() {
		return imgField;
	}
	public TImage getImgLabel() {
		return imgLabel;
	}
		
						
	
}