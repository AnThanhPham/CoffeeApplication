package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;
import java.util.stream.Stream;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.RoleDAO;
import dao.UserDAO;
import model.RoleModel;
import model.UserModel;
import util.Encryption;
import util.ImageFilter;
import util.MapUtil;
import util.ValidateUtils;
import views.menu.PanelEmployee;

public class PanelEmployeeController {
	private PanelEmployee panelEmployee;
	private UserDAO userDao = new UserDAO();
	private RoleDAO roleDao = new RoleDAO();

	public PanelEmployeeController(PanelEmployee panelEmployee) {
		this.panelEmployee = panelEmployee;
		ArrayList<UserModel> rowData = userDao.findAll();
		renderListRole();
		renderTable(rowData);
		addEvent();
	}
	
	public void renderListRole() {
		Object[] listRole= roleDao.findAll().stream().map(itemRoleModel -> itemRoleModel.getRoleName()).toArray();
		listRole = Stream.concat(Stream.of("Chọn"), Stream.of(listRole)).toArray();
		panelEmployee.getComboBox().setModel(new DefaultComboBoxModel<>(listRole));
	}
	
	public void renderTable(ArrayList<UserModel> rowData) {
		DefaultTableModel model = new DefaultTableModel(); 
		String[] colName = {"ID", "Fullname", "Email", "Username", "Password", "Phone", "Address", "Datework","Role","Gender"};
		for(String x : colName) {
			model.addColumn(x);
		}
		for(UserModel x : rowData) {
			Vector<String> row = new Vector<>();
			row.add(x.getFullName());
			row.add(x.getEmail());
			row.add(x.getUserName());
			row.add(x.getPassword());
			row.add(x.getPhone());
			row.add(x.getAddress());
			if(x.getDateWork() != null) {
				SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy", new java.util.Locale("vi", "VN"));
				String formattedDate = outputDateFormat.format(x.getDateWork());
				row.add(formattedDate);				
			}else {
				row.add(null);
			}
			row.add(x.getRole().getRoleName());
			row.add(x.getGender());
			model.addRow(row);
		}
		panelEmployee.getTable().setModel(model);
	}

	private void addEvent() {
		DisableInput();
		panelEmployee.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	int rowSelect = panelEmployee.getTable().getSelectedRow();
	        	
	        	if(rowSelect != -1) {
	        		String id = MapUtil.convertObjectToString(panelEmployee.getTable().getValueAt(rowSelect, 0));
	        		String fullName = MapUtil.convertObjectToString(panelEmployee.getTable().getValueAt(rowSelect, 1));
	        		String Email = MapUtil.convertObjectToString(panelEmployee.getTable().getValueAt(rowSelect, 2));
	        		String userName = MapUtil.convertObjectToString(panelEmployee.getTable().getValueAt(rowSelect, 3));
	        		String password = MapUtil.convertObjectToString(panelEmployee.getTable().getValueAt(rowSelect, 4));
	        		String phone = MapUtil.convertObjectToString(panelEmployee.getTable().getValueAt(rowSelect, 5));
	        		String address = MapUtil.convertObjectToString(panelEmployee.getTable().getValueAt(rowSelect, 6));
	        		String dateWork = MapUtil.convertObjectToString(panelEmployee.getTable().getValueAt(rowSelect, 7));
	        		String role = MapUtil.convertObjectToString(panelEmployee.getTable().getValueAt(rowSelect, 8));
	        		String gender = MapUtil.convertObjectToString(panelEmployee.getTable().getValueAt(rowSelect, 9));
	        		String pathFile = userDao.findByID(id).getImage();
	        		
	        		panelEmployee.getInput_IdEmployee().setText(id);
	        		panelEmployee.getInput_FullName().setText(fullName);
	        		panelEmployee.getInput_UserName().setText(userName);
	        		panelEmployee.getInput_Password().setText(password);
	        		panelEmployee.getInput_Phone().setText(phone);
	        		panelEmployee.getInput_Address().setText(address);
	        		panelEmployee.getComboBox().setSelectedItem(role);
	        		panelEmployee.getInput_FullName().setText(fullName);
	        		panelEmployee.getInput_DateWork().setText(dateWork);
	        		panelEmployee.getInputEmail().setText(Email);
	        		if(gender != null) {
	        			if(gender.equals("Female")) {
		        			panelEmployee.getRadioButtonFemale().setSelected(true);
		        		}else {
		        			panelEmployee.getRadioButtonMale().setSelected(true);
		        		}
	        		}
	        		if(pathFile!=null) {
	        			File file = new File(pathFile);
	        			if(file!= null) {
	        				showImage(file);
	        			}
	        		}else {
	        			// don't set image in another selected row table
	        			panelEmployee.getLabelFrameImage().setIcon(null);
	        		}
	        	}
	        }
	    });
		
		panelEmployee.getInput_Search().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				JTextField textField = (JTextField) e.getSource();
				String text = textField.getText();
				renderTableByFullName(text);
			}

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
			}
		});
		
		panelEmployee.getBtnSearch().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = panelEmployee.getInput_Search().getText();
				renderTableByFullName(text);
			}
		});
		
		
		JFileChooser fc = new JFileChooser();
		
		panelEmployee.getBtnShowImage().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.addChoosableFileFilter(new ImageFilter());
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal = fc.showOpenDialog(panelEmployee);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            System.out.println(file.getAbsolutePath());
	            	showImage(file);
		        } else {
		            System.out.println("Open command cancelled by user.");
		        }
			}
		});
		
		panelEmployee.getBtnAdd().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnableInput();
				resetInput();
			}
		});
		
		panelEmployee.getBtnSave().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = panelEmployee.getInput_IdEmployee().getText();
        		String fullName = panelEmployee.getInput_FullName().getText();
        		String userName = panelEmployee.getInput_UserName().getText();
        		String password = panelEmployee.getInput_Password().getText();
        		String phone = panelEmployee.getInput_Phone().getText();
        		String address = panelEmployee.getInput_Address().getText();
        		String role = panelEmployee.getComboBox().getSelectedItem().toString().equals("Chọn") ? "Employee" : panelEmployee.getComboBox().getSelectedItem().toString();
        		String email = panelEmployee.getInputEmail().getText();
        		String dateWork = panelEmployee.getInput_DateWork().getText();
        		String gender = "";
        		Enumeration<AbstractButton> elements = panelEmployee.getBgGender().getElements();
        		while (elements.hasMoreElements()) 
        	    {
        	          AbstractButton button = (AbstractButton)elements.nextElement();
        	          if (button.isSelected())     
        	          {
        	            gender = button.getText().equals("Nam") ? "Male" : "Female";
        	          }
        	    }
        		
        		UserModel tmp = new UserModel();
        		tmp.setAddress(address);
        		tmp.setUserName(userName);
        		tmp.setFullName(fullName);
        		tmp.setPhone(phone);
        		RoleModel roledao = roleDao.findByRoleName(role); 
        		tmp.setRole(roledao);
        		tmp.setRoleID(roledao.getRoleID());
        		tmp.setGender(gender); 
        		if(!ValidateUtils.checkEmptyAndNull(password)) {
        			tmp.setPassword(Encryption.getSHAHash(password));
            		tmp.setEmail(email);
        		}
        		try {
        			if(!ValidateUtils.checkEmptyAndNull(dateWork)) {
        				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        				java.util.Date utilDate = dateFormat.parse(dateWork);
        				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        				tmp.setDateWork(sqlDate);        				
        			}
				} catch (Exception e1) {
					e1.printStackTrace();
				}    	
        		
        		File file = fc.getSelectedFile();
        		if(file != null) {
        			String fileName = convertPathFileToDB(file.getAbsolutePath());
        			tmp.setImage(fileName);
        			System.out.println(fileName);
        		}
        		StringBuilder messageError = new StringBuilder("");
        		if(ValidateUtils.checkEmptyAndNull(id)) {
        			// them moi
        			Date currentDate = new Date(System.currentTimeMillis());
            	    java.sql.Date sqlDate2 = new java.sql.Date(currentDate.getTime());
            		tmp.setCreateDate(sqlDate2); 
            		tmp.setCode(generateVerifyCode());
            		if(validateForm(tmp, messageError)) {
            			userDao.insert(tmp);
            			renderTable(userDao.findAll());            			
            		}else {
            			JOptionPane.showMessageDialog(panelEmployee, messageError.toString());
            		}
        		}else {
        			// chinh sua
        			tmp.setID(Integer.parseInt(id));
        			if(validateForm2(tmp, messageError)) {
        				userDao.update(tmp);
        				renderTable(userDao.findAll());
        			}else {
        				JOptionPane.showMessageDialog(panelEmployee, messageError.toString());
        			}
        		}
			}
		});
		
		panelEmployee.getBtnEdit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowSelect = panelEmployee.getTable().getSelectedRow();
				if(rowSelect != -1) {
					EnableInput();					
				}
			}
		});
		
		panelEmployee.getBtnDelete().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] rowSelects = panelEmployee.getTable().getSelectedRows();
				if(rowSelects.length >0) {
					int result = JOptionPane.showConfirmDialog(panelEmployee, "Bạn có chắc chắn muốn xóa?");
					if(result == JOptionPane.OK_OPTION) {
						for(int x : rowSelects) {
							String id = MapUtil.convertObjectToString(panelEmployee.getTable().getValueAt(x, 0));
							UserModel user = userDao.findByID(id);
							userDao.delete(user);
						}
						renderTable(userDao.findAll());
						resetInput();
					}
				}
			}
		});
	}
	
	private String generateVerifyCode(){
        DecimalFormat df = new DecimalFormat("000000");
        Random ran = new Random();
        String code = df.format(ran.nextInt(1000000));  //  Random from 0 to 999999
        while (userDao.checkDuplicateCode(code)) {
            code = df.format(ran.nextInt(1000000));
        }
        return code;
    }
	
	public boolean validateForm2(UserModel user,StringBuilder res) {			
		if(ValidateUtils.checkEmptyAndNull(user.getFullName())) {
			res.append("Họ và tên không được để trống\n");
			return false;
		}
		
		if(ValidateUtils.checkEmptyAndNull(user.getPhone())) {
			res.append("Phone không được để trống\n");
			return false;
		}
		
		if(ValidateUtils.checkEmptyAndNull(user.getAddress())) {
			res.append("Địa chỉ không được để trống\n");
			return false;
		}
		
		if(ValidateUtils.checkEmptyAndNull(user.getGender())) {
			res.append("Gender không được để trống\n");
			return false;
		}
		
		if(ValidateUtils.checkEmptyAndNull(user.getUserName())) {
			res.append("Username không được để trống\n");
			return false;
		}
		
		if(ValidateUtils.checkEmptyAndNull(user.getPassword())) {
			res.append("Password không được để trống\n");
			return false;
		}
		
		if(ValidateUtils.checkEmptyAndNull(user.getEmail())) {
			res.append("Email không được để trống\n");
			return false;
		}
		
		return true;
	}
	
	public boolean validateForm(UserModel user,StringBuilder res) {			
		if(ValidateUtils.checkEmptyAndNull(user.getFullName())) {
			res.append("Họ và tên không được để trống\n");
			return false;
		}
		
		if(ValidateUtils.checkEmptyAndNull(user.getPhone())) {
			res.append("Phone không được để trống\n");
			return false;
		}
		
		if(ValidateUtils.checkEmptyAndNull(user.getAddress())) {
			res.append("Địa chỉ không được để trống\n");
			return false;
		}
		
		if(ValidateUtils.checkEmptyAndNull(user.getGender())) {
			res.append("Gender không được để trống\n");
			return false;
		}
		
		if(ValidateUtils.checkEmptyAndNull(user.getUserName())) {
			res.append("Username không được để trống\n");
			return false;
		}
		
		if(ValidateUtils.checkEmptyAndNull(user.getPassword())) {
			res.append("Password không được để trống\n");
			return false;
		}
		
		if(userDao.checkDuplicateUser(user.getUserName())) {
			res.append("UserName bạn nhập đã tồn tại\n");
			return false;
		}
		
		if(ValidateUtils.checkEmptyAndNull(user.getEmail())) {
			res.append("Email không được để trống\n");
			return false;
		}
		
		
		if(!ValidateUtils.checkEmail(user.getEmail())) {
			res.append("Email không hợp lệ\n");		
			return false;
		}
		
		if(userDao.checkDuplicateEmail(user.getEmail())) {
			res.append("Email bạn nhập đã tồn tại\\n");
			return false;
		}
		
		return true;
	}
	
	public String convertPathFileToDB(String pathFile) {
		String[] words = pathFile.split("\\\\");
		StringBuilder res = new StringBuilder("");
		for(int i=0;i<words.length;i++) {
			res.append(words[i]);
			if(i!=words.length-1) {
				res.append("\\\\");
			}
		}
		return res.toString();
	}
	
	public void showImage(File file) {
		try {
			ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath()); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(160, 200, Image.SCALE_DEFAULT); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			panelEmployee.getLabelFrameImage().setIcon(imageIcon);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resetInput() {
		panelEmployee.getInput_IdEmployee().setText("");
		panelEmployee.getInput_FullName().setText("");
		panelEmployee.getInput_UserName().setText("");
		panelEmployee.getInput_Password().setText("");
		panelEmployee.getInput_Phone().setText("");
		panelEmployee.getInput_Address().setText("");
		panelEmployee.getComboBox().setSelectedIndex(0);
		panelEmployee.getTable().setRowSelectionAllowed(false);
		panelEmployee.getBgGender().clearSelection();
		panelEmployee.getInputEmail().setText("");
		panelEmployee.getInput_DateWork().setText("");
		// don't set image in another selected row table
		panelEmployee.getLabelFrameImage().setIcon(null);
	}
	
	public void DisableInput() {
		panelEmployee.getInput_Address().setEnabled(false);
		panelEmployee.getInput_FullName().setEnabled(false);
		panelEmployee.getInput_Password().setEnabled(false);
		panelEmployee.getInput_Phone().setEnabled(false);
		panelEmployee.getInput_UserName().setEnabled(false);
		panelEmployee.getInput_DateWork().setEnabled(false);
		panelEmployee.getInputEmail().setEnabled(false);
		
		Enumeration<AbstractButton> elements = panelEmployee.getBgGender().getElements();
		while (elements.hasMoreElements()) 
	    {
	          AbstractButton button = (AbstractButton)elements.nextElement();
	          if (button.isEnabled())     
	          {
	            button.setEnabled(false); 
	          }
	    }
		panelEmployee.getComboBox().setEnabled(false);
	}
	
	public void EnableInput() {
		panelEmployee.getInput_Address().setEnabled(true);
		panelEmployee.getInput_FullName().setEnabled(true);
		panelEmployee.getInput_Password().setEnabled(true);
		panelEmployee.getInput_Phone().setEnabled(true);
		panelEmployee.getInput_UserName().setEnabled(true);
		panelEmployee.getInput_DateWork().setEnabled(true);
		panelEmployee.getInputEmail().setEnabled(true);
		
		Enumeration<AbstractButton> elements = panelEmployee.getBgGender().getElements();
		while (elements.hasMoreElements()) 
	    {
	          AbstractButton button = (AbstractButton)elements.nextElement();
	          if (!button.isEnabled())     
	          {
	            button.setEnabled(true); 
	          }
	    }
		panelEmployee.getComboBox().setEnabled(true);
	}
	
	public void renderTableByFullName(String text) {
		ArrayList<UserModel> rowData = new ArrayList<>();
		if(text.equals("") || text == null) {
			rowData = userDao.findAll();
		}else {
			rowData = userDao.findByFullname(text);					
		}
		renderTable(rowData);
	}
}
