package views.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

import controller.PanelCategoryController;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class PanelCategory extends JPanel {
	private PanelCategory category;
	private Label label_ID;
	private Label label_Catagory;
	private JTextField input_Category;
	private JTextField input_ID;
	private JButton btn_Add;
	private JButton btnFix;
	private JButton btnDelete;
	private JButton btn_Save;
	private JTextField input_Find;
	private JTable table_Category;
	private PanelCategoryController panelcategorycustomer;
	private JComboBox comboBox_Ma;
	private JButton btn_Search;
	private Label label_des;
	private JTextArea textArea_Des;

	public PanelCategory() {
		setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		setBackground(Color.WHITE);
		setBounds(287, 0, 1063, 826);
		setLayout(null);
		
		
		label_ID = new Label("ID:");
		label_ID.setForeground(new Color(0, 128, 0));
		label_ID.setBounds(45, 29, 40, 46);
		label_ID.setFont(new Font("SansSerif", Font.PLAIN, 16));
		add(label_ID);
		
		label_Catagory = new Label("Loại:");
		label_Catagory.setForeground(new Color(0, 128, 0));
		label_Catagory.setBounds(45, 111, 91, 46);
		label_Catagory.setFont(new Font("SansSerif", Font.PLAIN, 16));
		add(label_Catagory);
		
		label_des = new Label("Mô tả:");
		label_des.setForeground(new Color(0, 128, 0));
		label_des.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_des.setBounds(45, 200, 91, 46);
		add(label_des);
		
		input_Category = new JTextField();
		input_Category.setBounds(174, 119, 330, 36);
		add(input_Category);
		input_Category.setColumns(10);
		
		input_ID = new JTextField();
		input_ID.setBounds(174, 33, 330, 36);
		add(input_ID);
		input_ID.setColumns(10);
		
		btn_Add = new JButton("Thêm");
		btn_Add.setIcon(new ImageIcon(PanelEmployee.class.getResource("/icon/Add@0.3x.png")));
		btn_Add.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_Add.setBounds(151, 416, 91, 46);
		btn_Add.setBackground(Color.WHITE);
		btn_Add.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		add(btn_Add);
		
	
		
		btnFix = new JButton("Sửa");
		btnFix.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnFix.setIcon(new ImageIcon(PanelEmployee.class.getResource("/icon/Edit@0.3x.png")));
		btnFix.setBounds(266, 416, 107, 46);
		btnFix.setBackground(Color.WHITE);
		btnFix.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		add(btnFix);

		
		btnDelete = new JButton("Xóa");
		btnDelete.setIcon(new ImageIcon(PanelEmployee.class.getResource("/icon/Trash@0.3x.png")));
		btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnDelete.setBounds(397, 415, 107, 46);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		add(btnDelete);

		
		btn_Save = new JButton("Lưu");
		btn_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Save.setIcon(new ImageIcon(PanelEmployee.class.getResource("/icon/Save@0.2x.png")));
		btn_Save.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_Save.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		btn_Save.setBackground(Color.WHITE);
		btn_Save.setBounds(30, 416, 91, 46);
		add(btn_Save);
		
		comboBox_Ma = new JComboBox();
		comboBox_Ma.setBounds(30, 495, 160, 36);
		add(comboBox_Ma);
		
		btn_Search = new JButton("Find");
		btn_Search.setBounds(438, 495, 65, 36);
		add(btn_Search);
		
		
		input_Find = new JTextField();
		input_Find.setBounds(217, 495, 211, 36);
		add(input_Find);
		input_Find.setColumns(10);
		
		textArea_Des = new JTextArea();
		textArea_Des.setBounds(174, 216, 330, 148);
		add(textArea_Des);
		textArea_Des.setLineWrap(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(551, 29, 452, 567);
		add(scrollPane);
		
		table_Category = new JTable();
		table_Category.setFont(new Font("SansSerif", Font.PLAIN, 14));
		scrollPane.setViewportView(table_Category);
		table_Category.setRowHeight(50);

		panelcategorycustomer = new PanelCategoryController(this);
		
	}

	public JTextArea getTextArea_Des() {
		return textArea_Des;
	}

	public void setTextArea_Des(JTextArea textArea_Des) {
		this.textArea_Des = textArea_Des;
	}

	public JButton getBtn_Search() {
		return btn_Search;
	}

	public void setBtn_Search(JButton btn_Search) {
		this.btn_Search = btn_Search;
	}

	public JComboBox getComboBox_Ma() {
		return comboBox_Ma;
	}

	public void setComboBox_Ma(JComboBox comboBox_Ma) {
		this.comboBox_Ma = comboBox_Ma;
	}

	public Label getLabel_des() {
		return label_des;
	}

	public void setLabel_des(Label label_des) {
		this.label_des = label_des;
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

	public JTextField getInput_Category() {
		return input_Category;
	}

	public void setInput_Category(JTextField input_Category) {
		this.input_Category = input_Category;
	}

	public JTextField getInput_ID() {
		return input_ID;
	}

	public void setInput_ID(JTextField input_ID) {
		this.input_ID = input_ID;
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
