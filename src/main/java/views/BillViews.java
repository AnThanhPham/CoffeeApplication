package views;

import javax.swing.JFrame;
import javax.swing.JTable;
import model.BillModel;

public class BillViews extends JFrame{
	private JTable jtable;
	private BillModel bill;
	
	public BillViews() {
		this.bill = new BillModel();
		this.CreateView();
		this.setVisible(true);
	}
	
	public void CreateView() {
		String[] ColumnNames = {"Mã Hóa Đơn","Mã Khách Hàng","Mã Nhân Viên","Ngày Tạo Hóa Đơn","Tổng Tiền"};
		
 		jtable = new JTable();
	}
}
