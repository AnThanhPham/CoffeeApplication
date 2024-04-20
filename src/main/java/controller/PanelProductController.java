package controller;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import dao.ProductDAO;
import model.ProductModel;
import views.menu.PanelProduct;
import views.menu.Product.ItemProduct;
import views.menu.Product.PagePanel;

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
	
		
		

}
