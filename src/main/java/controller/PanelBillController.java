package controller;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import views.menu.PanelBill;

public class PanelBillController {
	private PanelBill panelBill;

	public PanelBillController(PanelBill panelBill) {
		this.panelBill = panelBill;
		addEvent();
	}
	
	public void addEvent() {
		// add data - cần xem thêm
		panelBill.getAddBill().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5)); // 4 rows, 2 columns, 5px gap

				// Thêm các JLabel và JTextField
				panel.add(new JLabel("Mã Hóa Đơn:"));
				panel.add(new JTextField(20));

				panel.add(new JLabel("Mã Khách Hàng:"));
				panel.add(new JTextField(20));
				
				panel.add(new JLabel("Mã Nhân Viên:"));
				panel.add(new JTextField(20));
				
				LocalDateTime current = LocalDateTime.now();
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			    String formatted = current.format(formatter);
				panel.add(new JLabel("Ngày Hóa Đơn: "));
				panel.add(new JTextField(formatted,20));
				
				panel.add(new JLabel("Tổng Tiền:"));
				panel.add(new JTextField(20));
				
				int result = JOptionPane.showConfirmDialog(null, 
					    panel, 
					    "Nhập thông tin hóa đơn", 
					    JOptionPane.OK_CANCEL_OPTION);
				
				DefaultTableModel table = (DefaultTableModel) panelBill.getTableBill().getModel();
					if (result == JOptionPane.OK_OPTION) {
					    // Lấy dữ liệu từ các JTextField
					    String MaHD = ((JTextField) panel.getComponent(1)).getText();
					    String MaKH = ((JTextField) panel.getComponent(3)).getText();
					    String MaNV = ((JTextField) panel.getComponent(5)).getText();
					    String NgayHD = ((JTextField) panel.getComponent(7)).getText();
					    String TongTien = ((JTextField) panel.getComponent(9)).getText();
					    Object[] rowData= {MaHD,MaKH,MaNV,NgayHD,TongTien};
					    table.addRow(rowData);
					    table.fireTableDataChanged(); // update data table
					}
			}
		});
		
		
		// remote data
		panelBill.getDeleteBill().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectRow = panelBill.getTableBill().getSelectedRow();
				//panelBill.getTableBill().setEditingRow(selectRow);
				if(selectRow == -1)
					JOptionPane.showMessageDialog(panelBill, "Bạn chưa chọn dữ liệu muốn xóa");
				else {
					DefaultTableModel table = (DefaultTableModel) panelBill.getTableBill().getModel();
					table.removeRow(selectRow);
				}
			}
		});
		
		// hiển thị chi tiết
		panelBill.getDetailsBill().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectRow =  panelBill.getTableBill().getSelectedRow();
				//panelBill.getTableBill().setEditingRow(selectRow);
				
				String MaHD = (String) panelBill.getTableBill().getValueAt(selectRow, 0);
				String MaKH = (String) panelBill.getTableBill().getValueAt(selectRow, 1);
				String MaNV = (String) panelBill.getTableBill().getValueAt(selectRow, 2);
				String NgayHD = (String) panelBill.getTableBill().getValueAt(selectRow, 3);
				String TongTien = (String) panelBill.getTableBill().getValueAt(selectRow, 4);
				if(selectRow == -1)
					JOptionPane.showMessageDialog(panelBill, "Bạn chưa chọn dữ liệu muốn xem chi tiết");
				else {
					
					StringBuilder Information = new StringBuilder();
					Information.append(MaHD).append("\n");
					Information.append(MaKH).append("\n");
					Information.append(MaNV).append("\n");
					Information.append(NgayHD).append("\n");
					Information.append(TongTien).append("\n");
					JPanel panel = new JPanel();
					JOptionPane.showMessageDialog(panel, 
						Information.toString(),
					    "Chi Tiết Hóa Đơn",
					    JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		
		panelBill.getEditBill().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectRow = panelBill.getTableBill().getSelectedColumn();
				String MaHD = (String) panelBill.getTableBill().getValueAt(selectRow, 0);
				String MaKH = (String) panelBill.getTableBill().getValueAt(selectRow, 1); 
				String MaNV = (String) panelBill.getTableBill().getValueAt(selectRow, 2); 
				String NgayHD = (String) panelBill.getTableBill().getValueAt(selectRow, 3);
				String TongTien= (String) panelBill.getTableBill().getValueAt(selectRow, 4);
				
				JPanel panel = new JPanel(new GridLayout(5,2,5,5));
				
				panel.add(new JLabel("Mã Hóa Đơn"));
				panel.add(new JTextField(MaHD,20));
				
				panel.add(new JLabel("Mã Khách Hàng"));
				panel.add(new JTextField(MaKH,20));
				
				panel.add(new JLabel("Mã Nhân Viên"));
				panel.add(new JTextField(MaNV,20));
				
				panel.add(new JLabel("Ngày Hóa Đơn"));
				panel.add(new JTextField(NgayHD,20));
				
				panel.add(new JLabel("Tổng Tiền"));
				panel.add(new JTextField(TongTien,20));
				
				int result = JOptionPane.showConfirmDialog(null, 
					    panel, 
					    "Nhập thông tin hóa đơn", 
					    JOptionPane.OK_CANCEL_OPTION);
				
				DefaultTableModel table = (DefaultTableModel) panelBill.getTableBill().getModel();
				if (result == JOptionPane.OK_OPTION) {
				    // Lấy dữ liệu từ các JTextField
				    String MahdTable = ((JTextField) panel.getComponent(1)).getText();
				    String MakhTable = ((JTextField) panel.getComponent(3)).getText();
				    String ManvTable = ((JTextField) panel.getComponent(5)).getText();
				    String NgayhdTable = ((JTextField) panel.getComponent(7)).getText();
				    String TongtienTable = ((JTextField) panel.getComponent(9)).getText();
				    Object[] rowData= {MahdTable,MakhTable,ManvTable,NgayhdTable,TongtienTable};
				    table.addRow(rowData);
				    table.fireTableDataChanged(); // update data table
				}
			}
		});
	
	}
}
