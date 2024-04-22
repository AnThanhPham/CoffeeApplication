package views.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.stream.Stream;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

import controller.PanelEmployeeController;
import dao.RoleDAO;
import util.table.TableCustom;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class PanelEmployee extends JPanel {
	private JTextField Input_IdEmployee;
	private JTextField Input_FullName;
	private JTextField Input_Phone;
	private JTextField Input_Address;
	private JComponent lblNewLabel_1_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1_1;
	private JLabel lblNewLabel_1_1_1_1;
	private JLabel lblNewLabel_1_1_1_1_1;
	private JRadioButton RadioButtonMale;
	private JRadioButton RadioButtonFemale;
	private JLabel lblNewLabel_2;
	private JTextField Input_UserName;
	private JLabel lblNewLabel_3;
	private JTextField Input_Password;
	private JLabel lblNewLabel_4;
	private JButton btnAdd;
	private JButton btnSave;
	private JButton btnEdit;
	private JTextField Input_Search;
	private JButton btnSearch;
	private JTable table;
	private PanelEmployeeController panelEmployeeController;
	private ButtonGroup bgGender;
	private JButton btnShowImage;
	private JLabel labelFrameImage;
	private JComboBox comboBox;
	private JTextField InputEmail;
	private JTextField Input_DateWork;
	private JLabel lblEmail;
	private JComponent lblDateWork;

	public PanelEmployee() {
		setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		setBackground(Color.WHITE);
		setBounds(287, 0, 1063, 826);
		setLayout(null);
		
		lblNewLabel = new JLabel("Thông tin:");
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblNewLabel.setBounds(21, 0, 129, 49);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Mã nhân viên:");
		lblNewLabel_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(31, 60, 119, 41);
		add(lblNewLabel_1);
		
		Input_IdEmployee = new JTextField();
		Input_IdEmployee.setBounds(149, 60, 203, 41);
		add(Input_IdEmployee);
		Input_IdEmployee.setEnabled(false);
		Input_IdEmployee.setColumns(10);
		
		lblNewLabel_1_1 = new JLabel("Họ và tên:");
		lblNewLabel_1_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(31, 117, 119, 41);
		add(lblNewLabel_1_1);
		
		Input_FullName = new JTextField();
		Input_FullName.setColumns(10);
		Input_FullName.setBounds(149, 120, 203, 41);
		add(Input_FullName);
		
		lblNewLabel_1_1_1 = new JLabel("Số điện thoại:");
		lblNewLabel_1_1_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(31, 169, 119, 41);
		add(lblNewLabel_1_1_1);
		
		Input_Phone = new JTextField();
		Input_Phone.setColumns(10);
		Input_Phone.setBounds(149, 172, 203, 41);
		add(Input_Phone);
		
		lblNewLabel_1_1_1_1 = new JLabel("Địa chỉ:");
		lblNewLabel_1_1_1_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_1_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1.setBounds(31, 223, 119, 41);
		add(lblNewLabel_1_1_1_1);
		
		Input_Address = new JTextField();
		Input_Address.setColumns(10);
		Input_Address.setBounds(149, 226, 203, 41);
		add(Input_Address);
		
		lblNewLabel_1_1_1_1_1 = new JLabel("Giới tính:");
		lblNewLabel_1_1_1_1_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_1_1_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1.setBounds(31, 278, 119, 41);
		add(lblNewLabel_1_1_1_1_1);
		
		RadioButtonMale = new JRadioButton("Nam");
		RadioButtonMale.setBackground(Color.WHITE);
		RadioButtonMale.setFont(new Font("SansSerif", Font.PLAIN, 16));
		RadioButtonMale.setForeground(new Color(0, 128, 0));
		RadioButtonMale.setBounds(147, 287, 91, 23);
		add(RadioButtonMale);
		
		RadioButtonFemale = new JRadioButton("Nữ");
		RadioButtonFemale.setForeground(new Color(0, 128, 0));
		RadioButtonFemale.setFont(new Font("SansSerif", Font.PLAIN, 16));
		RadioButtonFemale.setBackground(Color.WHITE);
		RadioButtonFemale.setBounds(240, 287, 91, 23);
		add(RadioButtonFemale);
		
		bgGender=new ButtonGroup();    
		bgGender.add(RadioButtonMale);
		bgGender.add(RadioButtonFemale);    
		
		lblNewLabel_2 = new JLabel("Tài khoản:");
		lblNewLabel_2.setForeground(new Color(0, 128, 0));
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(728, 60, 119, 41);
		add(lblNewLabel_2);
		
		Input_UserName = new JTextField();
		Input_UserName.setColumns(10);
		Input_UserName.setBounds(830, 63, 203, 41);
		add(Input_UserName);
		
		lblNewLabel_3 = new JLabel("Mật khẩu:");
		lblNewLabel_3.setForeground(new Color(0, 128, 0));
		lblNewLabel_3.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(728, 117, 119, 41);
		add(lblNewLabel_3);
		
		Input_Password = new JTextField();
		Input_Password.setColumns(10);
		Input_Password.setBounds(830, 120, 203, 41);
		add(Input_Password);
		
		lblNewLabel_4 = new JLabel("Chức vụ:");
		lblNewLabel_4.setForeground(new Color(0, 128, 0));
		lblNewLabel_4.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(728, 169, 119, 41);
		add(lblNewLabel_4);
		
		btnAdd = new JButton("Thêm");
		btnAdd.setIcon(new ImageIcon(PanelEmployee.class.getResource("/icon/Add@0.3x.png")));
		btnAdd.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAdd.setBackground(new Color(169, 169, 169));
		btnAdd.setForeground(new Color(0, 128, 0));
		btnAdd.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnAdd.setBounds(31, 357, 91, 35);
		add(btnAdd);
		
		btnSave = new JButton("Lưu");
		btnSave.setIcon(new ImageIcon(PanelEmployee.class.getResource("/icon/Save@0.2x.png")));
		btnSave.setForeground(new Color(0, 128, 0));
		btnSave.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnSave.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnSave.setBackground(new Color(169, 169, 169));
		btnSave.setBounds(147, 357, 91, 35);
		add(btnSave);
		
		btnEdit = new JButton("Sửa");
		btnEdit.setIcon(new ImageIcon(PanelEmployee.class.getResource("/icon/Edit@0.3x.png")));
		btnEdit.setForeground(new Color(0, 128, 0));
		btnEdit.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnEdit.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnEdit.setBackground(new Color(169, 169, 169));
		btnEdit.setBounds(261, 357, 91, 35);
		add(btnEdit);
		
		Input_Search = new JTextField();
		Input_Search.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(211, 211, 211), new Color(220, 220, 220)));
		Input_Search.setBounds(649, 357, 278, 33);
		add(Input_Search);
		Input_Search.setColumns(10);
		
		btnSearch = new JButton("Tìm kiếm");
		btnSearch.setBorder(new CompoundBorder());
		btnSearch.setBackground(new Color(220, 220, 220));
		btnSearch.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnSearch.setBounds(950, 357, 83, 30);
		add(btnSearch);
		
		
		table = new JTable();
		table.setFont(new Font("SansSerif", Font.PLAIN, 14));
		table.setRowHeight(40);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setFont(new Font("SansSerif", Font.PLAIN, 14));
		scrollPane.setBounds(31, 430, 1002, 385);
		add(scrollPane);
		TableCustom.apply(scrollPane, TableCustom.TableType.MULTI_LINE);
		
		
		btnShowImage = new JButton("Chọn hình ảnh");
		btnShowImage.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnShowImage.setBorder(new CompoundBorder());
		btnShowImage.setBackground(new Color(220, 220, 220));
		btnShowImage.setBounds(466, 284, 108, 30);
		add(btnShowImage);
		
		labelFrameImage = new JLabel("");
		labelFrameImage.setBackground(SystemColor.menu);
		labelFrameImage.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		labelFrameImage.setBounds(442, 60, 160, 200);
		add(labelFrameImage);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("SansSerif", Font.PLAIN, 14));
		comboBox.setBounds(830, 178, 97, 29);
		add(comboBox);
		
		
		lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(0, 128, 0));
		lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblEmail.setBounds(728, 223, 119, 41);
		add(lblEmail);
		
		InputEmail = new JTextField();
		InputEmail.setColumns(10);
		InputEmail.setBounds(830, 223, 203, 41);
		add(InputEmail);
		
		lblDateWork = new JLabel("Ngày làm:");
		lblDateWork.setForeground(new Color(0, 128, 0));
		lblDateWork.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblDateWork.setBounds(728, 278, 119, 41);
		add(lblDateWork);
		
		Input_DateWork = new JTextField();
		Input_DateWork.setColumns(10);
		Input_DateWork.setBounds(830, 278, 203, 41);
		add(Input_DateWork);
		
		panelEmployeeController = new PanelEmployeeController(this);
	}
	
	

	public JTextField getInputEmail() {
		return InputEmail;
	}



	public void setInputEmail(JTextField inputEmail) {
		InputEmail = inputEmail;
	}



	public JTextField getInput_DateWork() {
		return Input_DateWork;
	}



	public void setInput_DateWork(JTextField input_DateWork) {
		Input_DateWork = input_DateWork;
	}



	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTextField getInput_IdEmployee() {
		return Input_IdEmployee;
	}

	public void setInput_IdEmployee(JTextField input_IdEmployee) {
		Input_IdEmployee = input_IdEmployee;
	}

	public JTextField getInput_FullName() {
		return Input_FullName;
	}

	public void setInput_FullName(JTextField input_FullName) {
		Input_FullName = input_FullName;
	}

	public JTextField getInput_Phone() {
		return Input_Phone;
	}

	public void setInput_Phone(JTextField input_Phone) {
		Input_Phone = input_Phone;
	}

	public JTextField getInput_Address() {
		return Input_Address;
	}

	public void setInput_Address(JTextField input_Address) {
		Input_Address = input_Address;
	}

	public JRadioButton getRadioButtonMale() {
		return RadioButtonMale;
	}

	public void setRadioButtonMale(JRadioButton radioButtonMale) {
		RadioButtonMale = radioButtonMale;
	}

	public JRadioButton getRadioButtonFemale() {
		return RadioButtonFemale;
	}

	public void setRadioButtonFemale(JRadioButton radioButtonFemale) {
		RadioButtonFemale = radioButtonFemale;
	}

	public JTextField getInput_UserName() {
		return Input_UserName;
	}

	public void setInput_UserName(JTextField input_UserName) {
		Input_UserName = input_UserName;
	}

	public JTextField getInput_Password() {
		return Input_Password;
	}

	public void setInput_Password(JTextField input_Password) {
		Input_Password = input_Password;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public JButton getBtnEdit() {
		return btnEdit;
	}

	public void setBtnEdit(JButton btnEdit) {
		this.btnEdit = btnEdit;
	}

	public JTextField getInput_Search() {
		return Input_Search;
	}

	public void setInput_Search(JTextField input_Search) {
		Input_Search = input_Search;
	}

	public JButton getBtnSearch() {
		return btnSearch;
	}

	public void setBtnSearch(JButton btnSearch) {
		this.btnSearch = btnSearch;
	}

	public ButtonGroup getBgGender() {
		return bgGender;
	}

	public void setBgGender(ButtonGroup bgGender) {
		this.bgGender = bgGender;
	}

	public JButton getBtnShowImage() {
		return btnShowImage;
	}

	public void setBtnShowImage(JButton btnShowImage) {
		this.btnShowImage = btnShowImage;
	}

	public JLabel getLabelFrameImage() {
		return labelFrameImage;
	}

	public void setLabelFrameImage(JLabel labelFrameImage) {
		this.labelFrameImage = labelFrameImage;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}
}
