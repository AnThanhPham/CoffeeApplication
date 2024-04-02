package views.menu;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import controller.PanelCustomerController;
import model.CustomerModel;

import javax.swing.JTextField;
import java.awt.Label;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import java.awt.List;
import java.awt.Choice;
import java.awt.Scrollbar;
import java.awt.TextArea;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import java.awt.Component;
import java.awt.Desktop.Action;

import javax.swing.Box;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JList;

public class PanelCustomer extends JPanel {
	private CustomerModel model;
	private JTextField textField_Ma;
	private JTextField textfield_Name;
	private JTextField textfield_Address;
	private Label label_PN;
	private Label label_Email;
	private JTextField textfield_PN;
	private JTextField textfield_Email;
	private JTable cus_Table;
	private Label label_KH;
	private Label label_Name;
	private Label label_Address;
	private JComboBox comboBox_MaKH;
	DefaultTableModel modeltable;
	private PanelCustomerController panelCustomerController;
	private JButton btn_Add;
	private JButton btnFix;
	private JButton btnDelete;
	private JButton btn_Save;
	public PanelCustomer() {
		setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		setBackground(Color.WHITE);
		setBounds(287, 0, 1063, 826);
		setLayout(null);
		
		setSize(new Dimension(2, 2));
		setBackground(new Color(250, 235, 215));
		setBounds(287, 0, 1063, 826);
		setLayout(null);
		
		textField_Ma = new JTextField();
		textField_Ma.setBounds(202, 45, 199, 31);
		add(textField_Ma);
		textField_Ma.setColumns(10);
		
		label_KH = new Label("CustomerID");
		label_KH.setBounds(46, 24, 144, 46);
		label_KH.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		add(label_KH);
		
		label_Name = new Label("FullName");
		label_Name.setBounds(46, 96, 101, 31);
		label_Name.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		add(label_Name);
		
		label_Address = new Label("Address");
		label_Address.setBounds(46, 154, 72, 31);
		label_Address.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		add(label_Address);
		
		textfield_Name = new JTextField();
		textfield_Name.setBounds(202, 101, 199, 31);
		textfield_Name.setColumns(10);
		add(textfield_Name);
		
		textfield_Address = new JTextField();
		textfield_Address.setBounds(202, 154, 199, 31);
		textfield_Address.setColumns(10);
		add(textfield_Address);
		
		label_PN = new Label("PhoneNumber");
		label_PN.setBounds(456, 24, 144, 46);
		label_PN.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		add(label_PN);
		
		label_Email = new Label("Email");
		label_Email.setBounds(456, 81, 46, 46);
		label_Email.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		add(label_Email);
		
		textfield_PN = new JTextField();
		textfield_PN.setBounds(606, 45, 199, 31);
		textfield_PN.setColumns(10);
		add(textfield_PN);
		
		textfield_Email = new JTextField();
		textfield_Email.setBounds(606, 96, 199, 31);
		textfield_Email.setColumns(10);
		add(textfield_Email);
		
		btn_Add = new JButton("Add");
		btn_Add.setBounds(873, 36, 129, 48);
		btn_Add.setBackground(new Color(0, 191, 255));
		btn_Add.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		add(btn_Add);
		
	
		
		btnFix = new JButton("Modify");
		btnFix.setBounds(873, 124, 129, 48);
		btnFix.setBackground(new Color(0, 191, 255));
		btnFix.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		add(btnFix);

		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(873, 208, 129, 48);
		btnDelete.setBackground(new Color(0, 191, 255));
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		add(btnDelete);

		
		btn_Save = new JButton("Save");
		btn_Save.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		btn_Save.setBackground(new Color(0, 191, 255));
		btn_Save.setBounds(676, 208, 129, 48);
		add(btn_Save);

		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(160, 276, 107, -3);
		add(spinner);
		
		comboBox_MaKH = new JComboBox();
		comboBox_MaKH.setBounds(46, 221, 140, 31);
		add(comboBox_MaKH);
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 275, 1063, 22);
		add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 325, 846, 434);
		add(scrollPane);
		
		cus_Table = new JTable();
		cus_Table.setFont(new Font("SansSerif", Font.PLAIN, 14));
//		
		scrollPane.setViewportView(cus_Table);
		
		panelCustomerController =new PanelCustomerController(this);
	}

	public CustomerModel getModel() {
		return model;
	}

	public void setModel(CustomerModel model) {
		this.model = model;
	}

	public JTextField getTextField_ID() {
		return textField_Ma;
	}

	public void setTextField_Ma(JTextField textField_Ma) {
		this.textField_Ma = textField_Ma;
	}

	public JTextField getTextfield_Name() {
		return textfield_Name;
	}

	public void setTextfield_Name(JTextField textfield_Name) {
		this.textfield_Name = textfield_Name;
	}

	public JTextField getTextfield_Address() {
		return textfield_Address;
	}

	public void setTextfield_Address(JTextField textfield_Address) {
		this.textfield_Address = textfield_Address;
	}

	public Label getLabel_PN() {
		return label_PN;
	}

	public void setLabel_PN(Label label_PN) {
		this.label_PN = label_PN;
	}

	public Label getlabel_Email() {
		return label_Email;
	}

	public void setlabel_Email(Label label_Email) {
		this.label_Email = label_Email;
	}

	public JTextField getTextfield_PN() {
		return textfield_PN;
	}

	public void setTextfield_PN(JTextField textfield_PN) {
		this.textfield_PN = textfield_PN;
	}

	public JTextField getTextfield_Email() {
		return textfield_Email;
	}

	public void setTextfield_Email(JTextField textfield_Email) {
		this.textfield_Email = textfield_Email;
	}

	public Label getLabel_KH() {
		return label_KH;
	}

	public void setLabel_KH(Label label_KH) {
		this.label_KH = label_KH;
	}

	public Label getLabel_Name() {
		return label_Name;
	}

	public void setLabel_Name(Label label_Name) {
		this.label_Name = label_Name;
	}

	public Label getLabel_Address() {
		return label_Address;
	}

	public void setLabel_Address(Label label_Address) {
		this.label_Address = label_Address;
	}

	public JComboBox getComboBox_MaKH() {
		return comboBox_MaKH;
	}

	public void setComboBox_MaKH(JComboBox comboBox_MaKH) {
		this.comboBox_MaKH = comboBox_MaKH;
	}

	public DefaultTableModel getModeltable() {
		return modeltable;
	}

	public void setModeltable(DefaultTableModel modeltable) {
		this.modeltable = modeltable;
	}
	public JTable getTable() {
		return cus_Table;
	}

	public void setTable(JTable table) {
		this.cus_Table = table;
	}

	public JButton getBtn_Add() {
		return btn_Add;
	}

	public void setBtn_Add(JButton btn_Add) {
		this.btn_Add = btn_Add;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JButton getBtn_Save() {
		return btn_Save;
	}

	public void setBtn_Save(JButton btn_Save) {
		this.btn_Save = btn_Save;
	}

	public JButton getBtnFix() {
		return btnFix;
	}

	public void setBtnFix(JButton btnFix) {
		this.btnFix = btnFix;
	}
	
	
	
	
	
	
 
//	public void Save_KH(){
//		int maKH = Integer.valueOf(this.textField_Ma.getText());
//		String Ho=this.textfield_Email.getText();
//		String Ten=this.textfield_Name.getText();
//		String address=this.textfield_Address.getText();
//		String PhoneNumber=this.textfield_PN.getText();
//		String email=this.textfield_Email.getText();
//		
//		Customer cus= new Customer(maKH, Ho, Ten, address, PhoneNumber, email);
//		this.final_Save(cus);
//	}
//	public void final_Save(Customer cus) {
//		DefaultTableModel model=(DefaultTableModel) table.getModel();
//		this.model.insert(cus);
//	}
//
//
//	public void Modify_KH(KhachHang kh) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void Delete_KH(KhachHang kh) {
//		// TODO Auto-generated method stub
//		
//	}
}