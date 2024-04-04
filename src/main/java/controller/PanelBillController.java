package controller;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.apache.pivot.wtk.Mouse;

import dao.BillDAO;
import dao.BillDetailsDAO;
import dao.ProductDAO;
import model.BillDetailsModel;
import model.BillModel;
import model.ProductModel;
import views.menu.PanelBill;

public class PanelBillController {
	private PanelBill panelBill;
	private BillDAO billDao = new BillDAO();
	private BillDetailsDAO billDetailsDao = new BillDetailsDAO();
	private ProductDAO productDao = new ProductDAO();
	
	public PanelBillController(PanelBill panelBill) {
		this.panelBill = panelBill;
		ArrayList<BillModel> rowData = billDao.findAll();
		renderTable(rowData);
		addLabel();
		addEvent();
	}
	
	public void addEvent() {
		// add data - cần xem thêm
		
		panelBill.getAddBill().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5)); // 4 rows, 2 columns, 5px gap

				// Thêm các JLabel và JTextField
				panel.add(new JLabel("Mã Hóa Đơn:"));
				panel.add(new JTextField((billDao.CountID()+1)+"",20));

				panel.add(new JLabel("Mã Khách Hàng:"));
				panel.add(new JTextField(20));
				
				panel.add(new JLabel("Mã Nhân Viên:"));
				panel.add(new JTextField(20));
				
				LocalDateTime current = LocalDateTime.now();
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			    String formatted = current.format(formatter);
				panel.add(new JLabel("Ngày Hóa Đơn: "));
				panel.add(new JTextField(formatted,20));
				
				panel.add(new JLabel("Mã Sản Phẩm:"));
				panel.add(new JTextField(20));
				
				panel.add(new JLabel("Số Lượng :"));
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
					    String MaSP = ((JTextField) panel.getComponent(9)).getText();
					    String NhapSoLuong = ((JTextField) panel.getComponent(11)).getText();
					    // cần query ra số tiền sản phẩm
					   
					    BillDetailsModel billDetails = billDetailsDao.findByID(MaSP);
					    ProductModel product = productDao.findByID(billDetails.getProduct().getID()+"");
					    int soluong = Integer.parseInt(NhapSoLuong); 
					    
					    System.out.println(soluong);
					    System.out.println(product.getPrice());
					    System.out.println(product.getID());
					    Vector<String> rowData = new Vector<String>();
					    rowData.add(MaHD);
					    rowData.add(MaKH);
					    rowData.add(MaNV);
					    rowData.add(NgayHD);
					    rowData.add(soluong*product.getPrice()+"");
					    table.addRow(rowData);
					    table.fireTableDataChanged(); // update data table
					}
			}
		});
		
		
		// remote data
		panelBill.getDeleteBill().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  if (e.getClickCount() == 1 && SwingUtilities.isLeftMouseButton(e)) {
					  int selectRow = panelBill.getTableBill().getSelectedRow();
						//panelBill.getTableBill().setEditingRow(selectRow);
					  if(selectRow >=0) {
					  DefaultTableModel table = (DefaultTableModel) panelBill.getTableBill().getModel();
					  table.removeRow(selectRow);
					  }
					  else {
						  JOptionPane.showMessageDialog(panelBill, "Bạn chưa chọn dữ liệu muốn xóa");
		                }
				  }
			}
		});
		
		// hiển thị chi tiết
		panelBill.getDetailsBill().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  if (e.getClickCount() == 1 && SwingUtilities.isLeftMouseButton(e)) {
					  int selectRow =  panelBill.getTableBill().getSelectedRow();
					  if(selectRow >=0) {
						  
						String MaHD = (String) panelBill.getTableBill().getValueAt(selectRow, 0);
						String MaKH = (String) panelBill.getTableBill().getValueAt(selectRow, 1);
						String MaNV = (String) panelBill.getTableBill().getValueAt(selectRow, 2);
						String NgayHD = (String) panelBill.getTableBill().getValueAt(selectRow, 3);
						String TongTien = (String) panelBill.getTableBill().getValueAt(selectRow, 4);
							
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
				 else {
							JOptionPane.showMessageDialog(panelBill, "Bạn chưa chọn dữ liệu muốn xem chi tiết");
					  }
			  }
			}
		});
		
		// chinh sua hoa don
		panelBill.getEditBill().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  if (e.getClickCount() == 1 && SwingUtilities.isLeftMouseButton(e)) {
					  int selectRow = panelBill.getTableBill().getSelectedRow();
						
					    if(selectRow >=0) {
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
						    table.removeRow(selectRow);
						    table.insertRow(selectRow, rowData);
						    table.fireTableDataChanged(); // update data table
						}
					  }
					    else {
							JOptionPane.showMessageDialog(panelBill, "Bạn chưa chọn dữ liệu muốn chỉnh sửa");
					  }
				  }
			}
		});
	
	}
	
	public void addLabel() {
		panelBill.getMaHD().setText(billDao.CountID()+"");
	}
	
	public void renderTable(ArrayList<BillModel> rowData) {
		for(BillModel x: rowData) {
			Vector<String> row = new Vector<>();
			row.add(Integer.toString(x.getID()));
			row.add(Integer.toString(x.getCustomer().getID()));
			row.add(Integer.toString(x.getUser().getID()));
			if(x.getBillDate() != null) {
				SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy", new java.util.Locale("vi", "VN"));
				String formattedDate = outputDateFormat.format(x.getBillDate());
				row.add(formattedDate);	
			} else {
				row.add(null);
			}
			row.add(Float.toString(x.getBillTotal()));
			
			DefaultTableModel table = (DefaultTableModel) panelBill.getTableBill().getModel();
		    table.addRow(row);
		    table.fireTableDataChanged();
		}
	}
}
