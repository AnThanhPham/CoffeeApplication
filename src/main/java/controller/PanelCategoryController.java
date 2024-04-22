package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;
import java.util.stream.Stream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import dao.CategoryDAO;
import dao.CustomerDao;
import model.CategoryModel;
import model.CustomerModel;
import util.MapUtil;
import util.ValidateUtils;
import views.menu.PanelCategory;


public class PanelCategoryController {
	private PanelCategory panelcategory;
	private CategoryDAO categoryDAO = new CategoryDAO();
	
	public PanelCategoryController(PanelCategory panelcategory) {
		this.panelcategory = panelcategory;
		ArrayList<CategoryModel> rowData = CategoryDAO.CategoryList();
		renderTable(rowData);
		renderListID();
		addEvent();
	}
	
	public void renderListID() {
		Object[] listID= categoryDAO.CategoryList().stream().map(itemCustomer->itemCustomer.getID()).toArray();
		listID = Stream.concat(Stream.of("Mã loại"), Stream.of(listID)).toArray();
		panelcategory.getComboBox_Ma().setModel(new DefaultComboBoxModel<>(listID));
		ArrayList<CustomerModel> rowData = CustomerDao.CustomerList();		
	}
	
	private void renderTable(ArrayList<CategoryModel> rowData) {
		// TODO Auto-generated method stub
		DefaultTableModel model = new DefaultTableModel(); 
		String[] colName = {"CategoryID", "Name","Description"};
		for(String x : colName) {
			model.addColumn(x);
		}	
		for(CategoryModel x : rowData) {
			Vector<String> row = new Vector<>();
			row.add(Integer.toString(x.getID()));
			row.add(x.getCategoryName());
			row.add(x.getDescription());
			
			model.addRow(row);
		}
		panelcategory.getTable_Category().setModel(model);
	}
	private void addEvent() {
			DisableInput();
			panelcategory.getTable_Category().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		        public void valueChanged(ListSelectionEvent event){
		        	int rowSelect = panelcategory.getTable_Category().getSelectedRow();
		        	
		        	if(rowSelect != -1) {
		        		String id = MapUtil.convertObjectToString(panelcategory.getTable_Category().getValueAt(rowSelect, 0));
		        		String name = MapUtil.convertObjectToString(panelcategory.getTable_Category().getValueAt(rowSelect, 1));
		        		String description = MapUtil.convertObjectToString(panelcategory.getTable_Category().getValueAt(rowSelect, 2));
		        		
		        		panelcategory.getInput_Category().setText(name);
		        		panelcategory.getInput_ID().setText(id);	
		        		panelcategory.getTextArea_Des().setText(description);
		       
		        	}
		        }
			});
			
			panelcategory.getBtn_Add().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EnableInput();
					resetInput();
				}
			});
	        
			
	        panelcategory.getBtn_Save().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String id = panelcategory.getInput_ID().getText();
	        		String categoryName = panelcategory.getInput_Category().getText();
	        		String description = panelcategory.getTextArea_Des().getText();
	        		
	        		CategoryModel tmp = new CategoryModel();
	        		tmp.setCategoryName(capitalizeFirstLetter(categoryName));  
	        		tmp.setDescription(description);
	        		
	        		StringBuilder messageError = new StringBuilder("");
	        		
	        		if(ValidateUtils.checkEmptyAndNull(id)) {
	        			// them moi
	            		if(validateForm(tmp, messageError)) {
	            			categoryDAO.insert(tmp);
	            			renderTable(categoryDAO.CategoryList());            			
	            		}else {
	            			JOptionPane.showMessageDialog(panelcategory, messageError.toString());
	            		}
	        		}else {
	        			// chinh sua
	        			tmp.setID(Integer.parseInt(id));
	        			if(validateForm2(tmp, messageError)) {
	        				categoryDAO.update(tmp);
	        				renderTable(categoryDAO.CategoryList());
	        			}else {
	        				JOptionPane.showMessageDialog(panelcategory, messageError.toString());
	        			}
	        		}
				}
			});
			
			panelcategory.getBtnFix().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int rowSelect = panelcategory.getTable_Category().getSelectedRow();
					if(rowSelect != -1) {
						EnableInput();					
					}
				}
			});
			
			
			panelcategory.getBtnDelete().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int[] rowSelects = panelcategory.getTable_Category().getSelectedRows();
					if(rowSelects.length >0) {
						int result = JOptionPane.showConfirmDialog(panelcategory, "Bạn có chắc chắn muốn xóa?");
						if(result == JOptionPane.OK_OPTION) {
							for(int x : rowSelects) {
								String id = MapUtil.convertObjectToString(panelcategory.getTable_Category().getValueAt(x, 0));
								CategoryModel category =categoryDAO.findByID(id);
								categoryDAO.delete(category);
							}
							renderTable(CategoryDAO.CategoryList());
							resetInput();
						}
					}
				}
			});
			
			panelcategory.getInput_Find().addKeyListener(new KeyAdapter() {
			    public void keyReleased(KeyEvent e) {
			        JTextField textField = (JTextField) e.getSource();
			        String text = textField.getText();
			        String selectedID = (String) panelcategory.getComboBox_Ma().getSelectedItem().toString();
			        if (!("Mã loại").equals(selectedID)) {
			            renderTableByFullNameAndID(text, selectedID);
			        } else {
			            renderTableByFullName(text);
			        }
			    }

			    public void keyTyped(KeyEvent e) {
			    }

			    public void keyPressed(KeyEvent e) {
			    }
			});

			panelcategory.getComboBox_Ma().addActionListener(new ActionListener() {
			    @SuppressWarnings("unchecked")
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        JComboBox<Object> comboBox = (JComboBox<Object>) e.getSource();
			        String selectedID = (String) comboBox.getSelectedItem().toString();

			        if (!("Mã loại").equals(selectedID)) {
			            ArrayList<CategoryModel> filteredData = new ArrayList<>();
			            // Lọc dữ liệu tương ứng với ID được chọn
			            for (CategoryModel category : categoryDAO.CategoryList()) {
			                if (selectedID.equals(Integer.toString(category.getID()))) {
			                    filteredData.add(category);
			                }
			            }
			            renderTable(filteredData);
			        } else {
			            renderTable(categoryDAO.CategoryList());
			        }
			    }
			});
			panelcategory.getBtn_Search().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String text = panelcategory.getInput_Find().getText();
					renderTableByFullName(text);
				}
			});
			
			panelcategory.getInput_Find().addFocusListener(new FocusListener() {
				
				@Override
				public void focusGained(FocusEvent e) {
			        JTextField field = (JTextField) e.getSource();
			        if (field.getText().equals("Tìm kiếm loại theo tên")) {
			            field.setText("");
			        }
			    }
			    
			    @Override
			    public void focusLost(FocusEvent e) {
			        JTextField field = (JTextField) e.getSource();
			        if (field.getText().isEmpty()) {
			            field.setText("Tìm kiếm loại theo tên");
			            field.setForeground(Color.GRAY);
			        }
			    }
			});
			panelcategory.getInput_Find().setText("Tìm kiếm loại theo tên");
			panelcategory.getInput_Find().setForeground(Color.GRAY);

	}
	
	public boolean validateForm(CategoryModel category,StringBuilder res) {			
		if(ValidateUtils.checkEmptyAndNull(category.getCategoryName())){
			res.append("Tên loại không được để trống\n");
			return false;
		}
		
		if(categoryDAO.checkDuplicateName(category.getCategoryName())) {
			res.append("Loại bạn nhập đã tồn tại\\n");
			return false;
		}
		return true;
	}
	
	public boolean validateForm2(CategoryModel category,StringBuilder res) {			
		if(ValidateUtils.checkEmptyAndNull(category.getCategoryName())){
			res.append("Tên loại không được để trống\n");
			return false;
		}
		
		return true;
	}
	public void resetInput() {
		// TODO Auto-generated method stub
		panelcategory.getInput_ID().setText("");
		panelcategory.getInput_Category().setText("");
		panelcategory.getTextArea_Des().setText("");

	}
	public void EnableInput() {
		panelcategory.getInput_Category().setEnabled(true);
		panelcategory.getTextArea_Des().setEnabled(true);
		
	}
	public void DisableInput() {
		panelcategory.getInput_Category().setEnabled(false);
		panelcategory.getInput_ID().setEnabled(false);
		panelcategory.getTextArea_Des().setEnabled(false);
	}
	public void renderTableByFullName(String text) {
		ArrayList<CategoryModel> rowData = new ArrayList<>();
		if(text.equals("") || text == null) {
			rowData = categoryDAO.CategoryList();
		}else {
			rowData =categoryDAO.findByFullname(text);					
		}
		renderTable(rowData);
	}
	public void renderTableByFullNameAndID(String text, String selectedID) {
	    ArrayList<CategoryModel> filteredData = new ArrayList<>();
	    for (CategoryModel category : categoryDAO.CategoryList()) {
	        if (selectedID.equals(Integer.toString(category.getID())) && category.getCategoryName().toLowerCase().contains(text.toLowerCase())) {
	            filteredData.add(category);
	        }
	    }
	    renderTable(filteredData);
	}
	public String capitalizeFirstLetter(String str) {
	    String NameList[] = str.split(" ");
	    String res="";
	   
	    for (String x : NameList) {
	        String tmp = x.substring(0,1);
	        String tam = x.substring(1);
	        res += tmp.toUpperCase() + tam.toLowerCase() + " ";
	    }
	    return res.trim(); 
	}
}
