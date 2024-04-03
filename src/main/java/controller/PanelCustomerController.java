package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.CustomerDao;
import model.CustomerModel;
import util.MapUtil;
import util.ValidateUtils;
import views.menu.PanelCustomer;

public class PanelCustomerController{
	private PanelCustomer panelCustomer;
	private CustomerDao customerdao= new CustomerDao();
	
	public PanelCustomerController(PanelCustomer panelCustomer) {
		this.panelCustomer = panelCustomer;
		ArrayList<CustomerModel> rowData = CustomerDao.CustomerList();
		renderTable(rowData);
		addEvent();
	}

	private void renderTable(ArrayList<CustomerModel> rowData) {
		// TODO Auto-generated method stub
		DefaultTableModel model = new DefaultTableModel(); 
		String[] colName = {"CusomerID", "FullName", "PhoneNumber", "Address", "Email"};
		for(String x : colName) {
			model.addColumn(x);
		}	
		for(CustomerModel x : rowData) {
			Vector<String> row = new Vector<>();
			row.add(Integer.toString(x.getID()));
			row.add(x.getName());
			row.add(x.getPhone());
			row.add(x.getAddress());
			row.add(x.getEmail());

			model.addRow(row);
		}
		panelCustomer.getTable().setModel(model);
	}
	private void addEvent() {
			DisableInput();
			panelCustomer.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		        public void valueChanged(ListSelectionEvent event){
		        	int rowSelect = panelCustomer.getTable().getSelectedRow();
		        	
		        	if(rowSelect != -1) {
		        		String id = MapUtil.convertObjectToString(panelCustomer.getTable().getValueAt(rowSelect, 0));
		        		String name = MapUtil.convertObjectToString(panelCustomer.getTable().getValueAt(rowSelect, 1));
		        		String phone = MapUtil.convertObjectToString(panelCustomer.getTable().getValueAt(rowSelect, 2));
		        		String address = MapUtil.convertObjectToString(panelCustomer.getTable().getValueAt(rowSelect, 3));
		        		String Email = MapUtil.convertObjectToString(panelCustomer.getTable().getValueAt(rowSelect, 4));
		        		
		        		
		        		panelCustomer.getTextField_ID().setText(id);
		        		panelCustomer.getTextfield_Name().setText(name);
		        		panelCustomer.getTextfield_PN().setText(phone);
		        		panelCustomer.getTextfield_Address().setText(address);
		        		panelCustomer.getTextfield_Email().setText(Email);
		       
		        	}
		        }
			});
	       
	        
	        panelCustomer.getBtn_Add().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EnableInput();
					resetInput();
				}
			});
	        
			
	        panelCustomer.getBtn_Save().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String id = panelCustomer.getTextField_ID().getText();
	        		String fullName = panelCustomer.getTextfield_Name().getText();
	        		String phone =panelCustomer.getTextfield_PN().getText();
	        		String address = panelCustomer.getTextfield_Address().getText();
	        		String email = panelCustomer.getTextfield_Email().getText();
	        	
	        		
	        		CustomerModel tmp = new CustomerModel();
	        		tmp.setAddress(address);
	        		tmp.setName(fullName);
	        		tmp.setPhone(phone);
	        		tmp.setEmail(email);
	        		StringBuilder messageError = new StringBuilder("");
	        		
	        		if(ValidateUtils.checkEmptyAndNull(id)) {
	        			// them moi
	            		if(validateForm(tmp, messageError)) {
	            			customerdao.insert(tmp);
	            			renderTable(CustomerDao.CustomerList());            			
	            		}else {
	            			JOptionPane.showMessageDialog(panelCustomer, messageError.toString());
	            		}
	        		}else {
	        			// chinh sua
	        			tmp.setID(Integer.parseInt(id));
	        			if(validateForm2(tmp, messageError)) {
	        				customerdao.update(tmp);
	        				renderTable(CustomerDao.CustomerList());
	        			}else {
	        				JOptionPane.showMessageDialog(panelCustomer, messageError.toString());
	        			}
	        		}
				}
			});
			
			panelCustomer.getBtnFix().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int rowSelect = panelCustomer.getTable().getSelectedRow();
					if(rowSelect != -1) {
						EnableInput();					
					}
				}
			});
			
			
			panelCustomer.getBtnDelete().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int[] rowSelects = panelCustomer.getTable().getSelectedRows();
					if(rowSelects.length >0) {
						int result = JOptionPane.showConfirmDialog(panelCustomer, "Bạn có chắc chắn muốn xóa?");
						if(result == JOptionPane.OK_OPTION) {
							for(int x : rowSelects) {
								String id = MapUtil.convertObjectToString(panelCustomer.getTable().getValueAt(x, 0));
								CustomerModel customer =customerdao.findByID(id);
								customerdao.delete(customer);
							}
							renderTable(CustomerDao.CustomerList());
							resetInput();
						}
					}
				}
			});
		}

	public boolean validateForm(CustomerModel cus,StringBuilder res) {			
		if(ValidateUtils.checkEmptyAndNull(cus.getName())){
			res.append("Họ và tên không được để trống\n");
			return false;
		}
		
		if(ValidateUtils.checkEmptyAndNull(cus.getPhone())) {
			res.append("Phone không được để trống\n");
			return false;
		}
		
		if(ValidateUtils.checkEmptyAndNull(cus.getAddress())) {
			res.append("Địa chỉ không được để trống\n");
			return false;
		}
		
		
		if(ValidateUtils.checkEmptyAndNull(cus.getEmail())) {
			res.append("Email không được để trống\n");
			return false;
		}
		
		
		if(!ValidateUtils.checkEmail(cus.getEmail())) {
			res.append("Email không hợp lệ\n");		
			return false;
		}
		
		if(customerdao.checkDuplicateEmail(cus.getEmail())) {
			res.append("Email bạn nhập đã tồn tại\\n");
			return false;
		}
		return true;
	}
	public boolean validateForm2(CustomerModel Customer,StringBuilder res) {			
		if(ValidateUtils.checkEmptyAndNull(Customer.getName())) {
			res.append("Họ và tên không được để trống\n");
			return false;
		}
		
		if(ValidateUtils.checkEmptyAndNull(Customer.getPhone())) {
			res.append("Phone không được để trống\n");
			return false;
		}
		
		if(ValidateUtils.checkEmptyAndNull(Customer.getAddress())) {
			res.append("Địa chỉ không được để trống\n");
			return false;
		}
		
		if(ValidateUtils.checkEmptyAndNull(Customer.getEmail())) {
			res.append("Email không được để trống\n");
			return false;
		}
		
		return true;
	}
	
		
	public void resetInput() {
		// TODO Auto-generated method stub
		panelCustomer.getTextField_ID().setText("");
		panelCustomer.getTextfield_Name().setText("");
		panelCustomer.getTextfield_PN().setText("");
		panelCustomer.getTextfield_Address().setText("");
		panelCustomer.getTextfield_Email().setText("");
	}
	public void EnableInput() {
		panelCustomer.getTextfield_Address().setEnabled(true);
		panelCustomer.getTextfield_Name().setEnabled(true);
		panelCustomer.getTextfield_PN().setEnabled(true);
//		panelCustomer.getTextField_ID().setEnabled(true);
		panelCustomer.getTextfield_Email().setEnabled(true);
		
	}
	public void DisableInput() {
		panelCustomer.getTextfield_Address().setEnabled(false);
		panelCustomer.getTextField_ID().setEnabled(false);
		panelCustomer.getTextfield_PN().setEnabled(false);
		panelCustomer.getTextfield_Email().setEnabled(false);
		panelCustomer.getTextfield_Name().setEnabled(false);
	}
}
	

