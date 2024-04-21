package controller;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import dao.ProductDAO;
import model.ProductModel;
import util.ValidateUtils;
import views.menu.PanelProduct;
import views.menu.Product.ItemProduct;
import views.menu.Product.PagePanel;
import views.menu.Product.insertUpdateDelete;

public class PanelProductController {
	private PanelProduct panelProduct;
	private List<ProductModel> listProduct;
	
	public PanelProductController(PanelProduct panelProduct) {
		this.panelProduct = panelProduct;
	}
	
	
	// Hàm reload
	public void reload(boolean isSelectAll) throws SQLException {
		if(isSelectAll==true) {
			try {
				List<String> categoryList= ProductDAO.selectCategory();
				String[] typeCategoryname = new String[categoryList.size()+1];
				typeCategoryname[0]= "Tất cả";
				for(int i=0; i<categoryList.size(); i++) {
					typeCategoryname[i+1]= categoryList.get(i);
					
				}
				panelProduct.getTypeProduct().setModel(new DefaultComboBoxModel<String>(typeCategoryname));
				
			} catch (SQLException e1) {
				
				
				e1.printStackTrace();
			}
			listProduct= ProductDAO.selectAll();
		}else
			listProduct= ProductDAO.search(panelProduct.getSearchTextField().getText(),panelProduct.getTypeProduct().getSelectedItem().toString());
		
			
		int amountPageNumber = ((listProduct.size()-1 )/ 8) +1 ;
		PagePanel[] pageNumber = new PagePanel[amountPageNumber + 1];
		
		panelProduct.getSouthCenterPanel().removeAll(); // trước khi gọi lại các trang ta xóa hết những thứ trước đi
		for (int i = 1; i <= amountPageNumber; i++) {
			pageNumber[i] = new PagePanel(i);
			panelProduct.getSouthCenterPanel().add(pageNumber[i]);
		}
	    showPage(1);
	    
	    
		
	}
	
	// Hàm show page
	
	public void showPage(int pageNumber) {
			
			panelProduct.getCenterCenterPanel().setLayout( new GridLayout(2, 4, 20, 20));
			panelProduct.getCenterCenterPanel().removeAll();
			ItemProduct[] itemProduct = new ItemProduct[8];
			for (int i = 0; i < 8; i++) {
				try {
					itemProduct[i] = new ItemProduct(listProduct.get(i + (pageNumber - 1) * 8));
				} catch (Exception e) {
					ProductModel empty = new ProductModel();
					itemProduct[i] = new ItemProduct(null);
					// TODO: handle exception
				}
				panelProduct.getCenterCenterPanel().add(itemProduct[i]);
			}
			panelProduct.getCenterCenterPanel().setVisible(false);
			panelProduct.getCenterCenterPanel().setVisible(true);
			
	
		}
	
	// Hàm validate checkPrice có hợp lệ không
	public static boolean checkPrice(String price) {
	    String regex = "^[0-9]\\d{0,18}$"; // Chuỗi số dương không có ký tự đặc biệt, có tối đa 19 chữ số (từ 1 đến 9999999999999999999)
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(price);
	    return matcher.matches();
	}
	//Hàm check rỗng ở file validateUtils
	public static void validateNamePriceProduct(insertUpdateDelete check) {
		// check xem name, gia co bỏ trống hay không
		if(ValidateUtils.checkEmptyAndNull(check.getNameField().getText())){
			JOptionPane.showMessageDialog(check, "Tên sản phẩm không được trống","Thông báo",JOptionPane.INFORMATION_MESSAGE);
			
			return ;
		}
		if(ValidateUtils.checkEmptyAndNull((String)check.getPriceField().getText())) {
			JOptionPane.showMessageDialog(check, "Giá sản phẩm không được trống","Thông báo",JOptionPane.INFORMATION_MESSAGE);
			return ;
		}
		if(checkPrice((String)check.getPriceField().getText())==false) {
			JOptionPane.showMessageDialog(check, "Giá phải là số");
			return ;
			
		}
		
	}
	
// Hàm add event
	
	public void addEvent() {
		// type category
		panelProduct.getTypeProduct().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					try {
						if(panelProduct.getTypeProduct().getSelectedIndex()==0) {
							reload(true);
						}else {
							reload(false);
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
		});
		// Nút insert
		panelProduct.getInsertProduct().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new insertUpdateDelete(new ProductModel(),true);
			}
		});
		
		
		// searchtext field
		panelProduct.getSearchTextField().addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
				try {
					 System.out.println(panelProduct.getSearchTextField().getText());
					 reload(false);
					
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
		
		// nút submit
		panelProduct.getSubmit().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					 System.out.println(panelProduct.getSearchTextField().getText());
					 //controller.reload(true);
					 reload(false);
					
				} catch (SQLException e1) {
					//JOptionPane.showMessageDialog(dialog,e.toString(),"Thông Báo", JOptionPane.MESSAGE_PROPERTY );
					System.out.println(e1.toString());
				}
				
			}
		});
		
		//responsive search text field
		panelProduct.getTopNorthPanel().addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

		
			@Override
			public void componentResized(ComponentEvent e) {
				int width = panelProduct.getCenterTop().getWidth();
				System.out.println("width" + width);
				// TODO Auto-generated method stub
				panelProduct.getSearchTextField().setPreferredSize(new Dimension(width / 2, 30));
				panelProduct.getSearchTextField().setVisible(false);
				panelProduct.getSearchTextField().setVisible(true);

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
	
	}
	
		
		

}
