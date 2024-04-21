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
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ProductDAO;
import model.ProductModel;
import util.ImageFilter;
import util.ValidateUtils;
import views.menu.PanelProduct;

public class insertUpdateDelete extends JFrame {
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
	private JTextField nameField,priceField;
	public insertUpdateDelete(ProductModel model,boolean isInsert) {
		
		//JDialog dialog= new JDialog();
		JLabel topDialog = new JLabel();
		JLabel centerDialog = new JLabel();
		JPanel bottomDialog = new JPanel();
		this.setVisible(true);
		this.setMinimumSize(new Dimension(400,400));
		this.setLayout(new BorderLayout());	
		this.add(topDialog, BorderLayout.NORTH);
		this.add(bottomDialog, BorderLayout.SOUTH);
		this.add(centerDialog);
		
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
		
		JLabel idProduct= new JLabel("ID");
		idProduct.setFont(centerFont);
		JTextField idField= new JTextField(model.getID()+"");
		if(isInsert==false) idField.setEditable(false);//ko đc sửa
		
		JLabel nameProduct= new JLabel("Tên");
		nameProduct.setFont(centerFont);
		 nameField= new JTextField( model.getName());
		
		JLabel priceProduct= new JLabel("Giá");
		priceProduct.setFont(centerFont);
		 priceField= new JTextField((int)model.getPrice()+"");
		
		JLabel desProduct= new JLabel("Mô Tả");
		desProduct.setFont(centerFont);
		JTextField desField= new JTextField(model.getDescription());
		
		JLabel imgProduct= new JLabel("Link Ảnh");
		imgProduct.setFont(centerFont);
		JTextField imgField= new JTextField(model.getImage());
		JButton choseImage= new JButton("Chọn");
		
		
		JLabel categoryProduct= new JLabel("Loại");
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
		gbc.gridy= 5;
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
		centerDialog.add(imgField,gbc);
		
		
		
// sửa chọn link ảnh
		//gbc.weightx=0.0;
		gbc.gridx= 2;
		choseImage.setPreferredSize(new Dimension(20,10));
		
		
		centerDialog.add(choseImage,gbc);
		JFileChooser fc = new JFileChooser();
		choseImage.addMouseListener(new MouseListener() {
			
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
				fc.addChoosableFileFilter(new ImageFilter());
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal = fc.showOpenDialog(centerDialog);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            try {
						Files.copy(file.toPath(),new File("src/main/java/img/product/anh"+ model.getID() +".png").toPath(),StandardCopyOption.REPLACE_EXISTING);
						System.out.println(file.getAbsolutePath());
			            imgField.setText("src/main/java/img/product/anh"+ model.getID() +".png");
//			            imgField.setText(file.getAbsolutePath());
		
		            } catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            
		        } else {
		            System.out.println("Open command cancelled by user.");
		        }
			}
		});
		
		
		
		
		
		gbc.weightx=0.7;
		gbc.gridx=1;
		gbc.gridy= 5;
		String typeCategoryname[];
		 JComboBox<String> typeProduct= new JComboBox<>();
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
		JButton insert= new JButton("Thêm");
		insert.setFont(fontSouth);
		insert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//int categoryId;
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
							if(nameText.equalsIgnoreCase(x)==false) {
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
					
//					for(String x: ProductDAO.listName()) {
//						System.out.println("tên:"+ x+ "\n");
//					}
//					

//					model.setID(Integer.parseInt(idField.getText()));
					model.setPrice(Integer.parseInt(priceField.getText()));
					model.setName(nameField.getText());
					model.setDescription(desField.getText());
					model.setImage(imgField.getText());
				    categoryId= ProductDAO.selectCategoryId((String)typeProduct.getSelectedItem());// chuyển tên loại sp thành id loại sp;
					model.getCategory().setID(categoryId);
					
					
					System.out.println((String)typeProduct.getSelectedItem()+"\n");
					//model.getCategory().setCategoryName(typeProduct.getToolTipText());
					
					
					ProductDAO.insert(model);
					insertUpdateDelete.this.dispose();
					System.out.println("Mã ID"+ categoryId);
					JOptionPane.showMessageDialog(insertUpdateDelete.this, "Thêm thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
					PanelProduct.getIns().getController().reload(true);
				 
					}catch (Exception e1) {
					// TODO Auto-generated catch block
//					System.out.println(e1.getErrorCode());
					e1.printStackTrace();
//					System.out.println(e1.printStackTrace());
//					System.out.println("\n" + e1.getNextException());
					JOptionPane.showMessageDialog(insertUpdateDelete.this, "Thêm thất bại","Thông báo",JOptionPane.INFORMATION_MESSAGE);
					
					}
				


				
				
				
				System.out.println("Lỗi: "+(String)typeProduct.getSelectedItem()+"\n");
				System.out.println("Mã Loại:"+ categoryId);
				
				
				
			}
		});
		
		JButton edit= new JButton("Sửa");
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
		
		JButton delete= new JButton("Xóa");
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
		JButton cancel= new JButton("Thoát");
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
		
	}
		
						
	
}