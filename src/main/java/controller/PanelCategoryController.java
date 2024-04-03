package controller;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import dao.CategoryDAO;
import model.CategoryModel;
import util.MapUtil;
import views.menu.PanelCategory;


public class PanelCategoryController {
	private PanelCategory panelcategory;
	private CategoryDAO categoryDAO = new CategoryDAO();
	
	public PanelCategoryController(PanelCategory panelcategory) {
		this.panelcategory = panelcategory;
		ArrayList<CategoryModel> rowData = CategoryDAO.CategoryList();
		renderTable(rowData);
		addEvent();
	}
	
	private void renderTable(ArrayList<CategoryModel> rowData) {
		// TODO Auto-generated method stub
		DefaultTableModel model = new DefaultTableModel(); 
		String[] colName = {"CategoryID", "Name"};
		for(String x : colName) {
			model.addColumn(x);
		}	
		for(CategoryModel x : rowData) {
			Vector<String> row = new Vector<>();
			row.add(Integer.toString(x.getID()));
			row.add(x.getCategoryName());

			model.addRow(row);
		}
		panelcategory.getTable_Category().setModel(model);
	}
	private void addEvent() {
			
			panelcategory.getTable_Category().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		        public void valueChanged(ListSelectionEvent event){
		        	int rowSelect = panelcategory.getTable_Category().getSelectedRow();
		        	
		        	if(rowSelect != -1) {
		        		String id = MapUtil.convertObjectToString(panelcategory.getTable_Category().getValueAt(rowSelect, 0));
		        		String name = MapUtil.convertObjectToString(panelcategory.getTable_Category().getValueAt(rowSelect, 1));
	        		
		        		panelcategory.getInput_Category().setText(id);
		        		panelcategory.getInput_ID().setText(name);	    
		       
		        	}
		        }
			});
	}
}
