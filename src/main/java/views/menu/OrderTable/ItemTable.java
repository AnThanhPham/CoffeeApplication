package views.menu.OrderTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.TableDAO;
import model.TableModel;


public class ItemTable extends JButton {
	 
 	TableModel tableModel;
 	
	public TableModel getTableModel() {
		return tableModel;
	}

	public JLabel getTableName() {
		return tableName;
	}

	JLabel tableName;

	public ItemTable( TableModel model){
		tableModel= model;
		
		tableName = new JLabel();
		
		
		tableName.setText("Bàn "+ model.getID());
		tableName.setForeground(Color.white);
		tableName.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,22));
		tableName.setHorizontalAlignment(JButton.CENTER);
		tableName.setVerticalAlignment(JButton.CENTER);
		this.setLayout(new BorderLayout());
		
		
		
		
		this.add(tableName);
	
		
		if( model.getStatus().equals("Full")) {
			this.setBackground(new Color(0x438BF7));
			
			
		}else {
			this.setBackground(Color.lightGray);
		}
		
	
	}
}
