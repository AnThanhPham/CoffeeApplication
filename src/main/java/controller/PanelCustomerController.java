package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.google.protobuf.StringValue;
import com.google.protobuf.Value;

import dao.CustomerDao;
import model.CustomerModel;
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
		renderListID();
		addEvent();
	}
	public void renderListID() {
		Object[] listID= customerdao.CustomerList().stream().map(itemCustomer->itemCustomer.getID()).toArray();
		listID = Stream.concat(Stream.of("Ma Khach Hang"), Stream.of(listID)).toArray();
		panelCustomer.getComboBox_MaKH().setModel(new DefaultComboBoxModel<>(listID));
		ArrayList<CustomerModel> rowData = CustomerDao.CustomerList();		
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
	        		tmp.setAddress(capitalizeFirstLetter(address));
	        		tmp.setName(capitalizeFirstLetter(fullName));
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
			
//			panelCustomer.getTextField_Find().addKeyListener(new KeyAdapter() {
//				public void keyReleased(KeyEvent e) {
//					JTextField textField = (JTextField) e.getSource();
//					String text = textField.getText();
//					renderTableByFullName(text);
//				}
//
//				public void keyTyped(KeyEvent e) {
//				}
//
//				public void keyPressed(KeyEvent e) {
//				}
//			});
//			panelCustomer.getComboBox_MaKH().addActionListener(new ActionListener() {
//		        @SuppressWarnings("unchecked")
//				@Override
//		        public void actionPerformed(ActionEvent e) {
//					JComboBox<Object> comboBox = (JComboBox<Object>) e.getSource();
//		            String selectedID = (String) comboBox.getSelectedItem().toString();
//
//		            if (!("Ma Khach Hang").equals(selectedID)) {
//		                ArrayList<CustomerModel> filteredData = new ArrayList<>();
//		                // Lọc dữ liệu tương ứng với ID được chọn
//		                for (CustomerModel customer : CustomerDao.CustomerList()) {
//		                    if (selectedID.equals(Integer.toString(customer.getID()))) {
//		                        filteredData.add(customer);
//		                    }
//		                }
//		                renderTable(filteredData);
//		            } else {
//		                renderTable(CustomerDao.CustomerList());
//		            }
//		        }
//		    });
					
			panelCustomer.getTextField_Find().addKeyListener(new KeyAdapter() {
			    public void keyReleased(KeyEvent e) {
			        JTextField textField = (JTextField) e.getSource();
			        String text = textField.getText();
			        String selectedID = (String) panelCustomer.getComboBox_MaKH().getSelectedItem().toString();
			        if (!("Ma Khach Hang").equals(selectedID)) {
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

			panelCustomer.getComboBox_MaKH().addActionListener(new ActionListener() {
			    @SuppressWarnings("unchecked")
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        JComboBox<Object> comboBox = (JComboBox<Object>) e.getSource();
			        String selectedID = (String) comboBox.getSelectedItem().toString();

			        if (!("Ma Khach Hang").equals(selectedID)) {
			            ArrayList<CustomerModel> filteredData = new ArrayList<>();
			            // Lọc dữ liệu tương ứng với ID được chọn
			            for (CustomerModel customer : CustomerDao.CustomerList()) {
			                if (selectedID.equals(Integer.toString(customer.getID()))) {
			                    filteredData.add(customer);
			                }
			            }
			            renderTable(filteredData);
			        } else {
			            renderTable(CustomerDao.CustomerList());
			        }
			    }
			});

			panelCustomer.getTextField_Find().addFocusListener(new FocusListener() {
				
				@Override
				public void focusGained(FocusEvent e) {
			        JTextField field = (JTextField) e.getSource();
			        if (field.getText().equals("Tìm kiếm tên khách hàng")) {
			            field.setText("");
			        }
			    }
			    
			    @Override
			    public void focusLost(FocusEvent e) {
			        JTextField field = (JTextField) e.getSource();
			        if (field.getText().isEmpty()) {
			            field.setText("Tìm kiếm tên khách hàng");
			            field.setForeground(Color.GRAY);
			        }
			    }
			});
			panelCustomer.getTextField_Find().setText("Tìm kiếm tên khách hàng");
			panelCustomer.getTextField_Find().setForeground(Color.GRAY);
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
		
		if(!ValidateUtils.checkPhoneNumber(cus.getPhone())) {
			res.append("Số điện thoại không hợp lệ\n");
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
	public void renderTableByFullName(String text) {
		ArrayList<CustomerModel> rowData = new ArrayList<>();
		if(text.equals("") || text == null) {
			rowData = customerdao.CustomerList();
		}else {
			rowData =customerdao.findByFullname(text);					
		}
		renderTable(rowData);
	}
	public String capitalizeFirstLetter(String str) {
	    String NameList[] = str.split(" ");
	    String res="";
	   
	    for (String x : NameList) {
	        String tmp = x.substring(0,1);
	        String tam = x.substring(1);
	        res += tmp.toUpperCase() + tam + " ";
	    }
	    return res.trim(); 
	}

	public void renderTableByFullNameAndID(String text, String selectedID) {
	    ArrayList<CustomerModel> filteredData = new ArrayList<>();
	    for (CustomerModel customer : CustomerDao.CustomerList()) {
	        if (selectedID.equals(Integer.toString(customer.getID())) && customer.getName().toLowerCase().contains(text.toLowerCase())) {
	            filteredData.add(customer);
	        }
	    }
	    renderTable(filteredData);
	}


}
	

