package views.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.PanelCategoryController;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class PanelCategory extends JPanel {
	private PanelCategory category;
	private Label label_ID;
	private Label label_Catagory;
	private JTextField input_ID;
	private JTextField input_Category;
	private JButton btn_Add;
	private JButton btnFix;
	private JButton btnDelete;
	private JButton btn_Save;
	private JTextField input_Find;
	private JTable table_Category;
	private PanelCategoryController panelcategorycustomer;

	public PanelCategory() {
		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		setBounds(287, 0, 1063, 826);
		
		label_ID = new Label("ID");
		label_ID.setBounds(56, 41, 40, 46);
		label_ID.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		add(label_ID);
		
		label_Catagory = new Label("Category");
		label_Catagory.setBounds(55, 132, 91, 46);
		label_Catagory.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		add(label_Catagory);
		
		input_ID = new JTextField();
		input_ID.setBounds(173, 152, 330, 36);
		add(input_ID);
		input_ID.setColumns(10);
		
		input_Category = new JTextField();
		input_Category.setBounds(174, 54, 160, 36);
		add(input_Category);
		input_Category.setColumns(10);
		
		btn_Add = new JButton("Add");
		btn_Add.setBounds(146, 245, 75, 48);
		btn_Add.setBackground(Color.WHITE);
		btn_Add.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		add(btn_Add);
		
	
		
		btnFix = new JButton("Modify");
		btnFix.setBounds(242, 247, 107, 46);
		btnFix.setBackground(Color.WHITE);
		btnFix.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		add(btnFix);

		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(374, 245, 129, 48);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		add(btnDelete);

		
		btn_Save = new JButton("Save");
		btn_Save.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		btn_Save.setBackground(Color.WHITE);
		btn_Save.setBounds(30, 247, 91, 46);
		add(btn_Save);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(30, 368, 160, 36);
		add(comboBox);
		
		JButton btnNewButton = new JButton("Find");
		btnNewButton.setBounds(441, 368, 65, 36);
		add(btnNewButton);
		
		input_Find = new JTextField();
		input_Find.setBounds(210, 368, 211, 36);
		add(input_Find);
		input_Find.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(551, 29, 452, 567);
		add(scrollPane);
		
		table_Category = new JTable();
		table_Category.setFont(new Font("SansSerif", Font.PLAIN, 14));
		scrollPane.setViewportView(table_Category);
		table_Category.setRowHeight(50);
		
		
		panelcategorycustomer = new PanelCategoryController(this);
	}

	public PanelCategory getCategory() {
		return category;
	}

	public void setCategory(PanelCategory category) {
		this.category = category;
	}

	public Label getLabel_ID() {
		return label_ID;
	}

	public void setLabel_ID(Label label_ID) {
		this.label_ID = label_ID;
	}

	public Label getLabel_Catagory() {
		return label_Catagory;
	}

	public void setLabel_Catagory(Label label_Catagory) {
		this.label_Catagory = label_Catagory;
	}

	public JTextField getInput_ID() {
		return input_ID;
	}

	public void setInput_ID(JTextField input_ID) {
		this.input_ID = input_ID;
	}

	public JTextField getInput_Category() {
		return input_Category;
	}

	public void setInput_Category(JTextField input_Category) {
		this.input_Category = input_Category;
	}

	public JButton getBtn_Add() {
		return btn_Add;
	}

	public void setBtn_Add(JButton btn_Add) {
		this.btn_Add = btn_Add;
	}

	public JButton getBtnFix() {
		return btnFix;
	}

	public void setBtnFix(JButton btnFix) {
		this.btnFix = btnFix;
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

	public JTextField getInput_Find() {
		return input_Find;
	}

	public void setInput_Find(JTextField input_Find) {
		this.input_Find = input_Find;
	}

	public JTable getTable_Category() {
		return table_Category;
	}

	public void setTable_Category(JTable table_Category) {
		this.table_Category = table_Category;
	}
}
