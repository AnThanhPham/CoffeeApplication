package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.TableDAO;
import model.TableModel;
import views.menu.PanelOrderTable;
import views.menu.OrderTable.ItemTable;
import views.menu.Product.ItemProduct;

public class PanelTableController {
	
	
	PanelOrderTable panelOrderTable;
	public PanelTableController(PanelOrderTable panelOrderTable) {
		this.panelOrderTable=panelOrderTable;
		
	
	}
	// Ham reload
	public void reload() {
		java.util.List<TableModel> listTable= TableDAO.selectAll();
		ItemTable[] ItemTable = new ItemTable[listTable.size()+1];
		panelOrderTable.getCenterPanel().removeAll();
		for (int i = 0; i < listTable.size(); i++) {
			ItemTable[i]= new ItemTable( listTable.get(i));
			panelOrderTable.getCenterPanel().add(ItemTable[i]);
			// add event
			this.addEventItemTable(ItemTable[i]);
		}
	}
	
	
	public void addEventItemTable(ItemTable itemTable) {
		// hàm status bàn
		
	itemTable.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result= JOptionPane.showConfirmDialog(null, "Bạn có muốn chuyển trạng thái không","Thông báo", JOptionPane.YES_NO_OPTION);
				if (result==JOptionPane.YES_OPTION) {
					if(itemTable.getTableModel().getStatus().equals("Full")) {
						itemTable.getTableModel().setStatus("Available");
						itemTable.setBackground(Color.LIGHT_GRAY);
						System.out.println(itemTable.getTableModel().getStatus());
					}else {
						itemTable.getTableModel().setStatus("Full");
						itemTable.setBackground(new Color(0x438BF7));
					}
				}
				 try {
					 TableDAO daoUpdate= new TableDAO();
					 daoUpdate.update(itemTable.getTableModel());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
	}
		
}
