package views.menu;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.*;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class PanelBill extends JPanel {
	private JLabel jlabel;
	private JLabel MaHD;
	private JLabel MaKH;
	private JLabel MaNV;
	private JLabel NgayHD;
	private JLabel TongTien;
	
	private JButton AddBill;
	private JButton DeleteBill;
	private JButton DetailsBill;
	
	private Font FLabel;
	private Font FLabelText;
	private Font FBtnBill;
	
	private JComboBox<String> FDay;
	private JComboBox<String> FMonth;
	private JComboBox<String> FYear;
	
	private JTextField FBillForm;
	private JTextField FBillTo;
	private JTextField FMaHD;
	
	private JTable TableBill;
	private JScrollPane scrollPaneTable;
	
	public PanelBill() {
		setLayout(null);
		setBackground(new Color(255, 243, 199));
		
		FLabel = new Font("SansSerif", Font.BOLD, 14);
		FLabelText =  new Font("SansSerif", Font.PLAIN, 14);
		FBtnBill = new Font("SansSerif", Font.BOLD, 16);
		
		CreateHeader();
		CreateBody();
	}
	public void CreateHeader() {
		jlabel = new JLabel("Mã HĐ");
		jlabel.setBounds(70,70,60,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		MaHD = new JLabel("0",SwingConstants.CENTER);
		MaHD.setBounds(130,70,70,30);
		MaHD.setFont(FLabelText);
		MaHD.setBackground(Color.WHITE);
		MaHD.setOpaque(true);
		add(MaHD);
		
		jlabel = new JLabel("Mã KH");
		jlabel.setBounds(210,70,60,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		MaKH = new JLabel("0",SwingConstants.CENTER);
		MaKH.setBounds(270,70,70,30);
		MaKH.setFont(FLabelText);
		MaKH.setBackground(Color.WHITE);
		MaKH.setOpaque(true);
		add(MaKH);
		
		jlabel = new JLabel("Mã NV");
		jlabel.setBounds(350,70,60,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		MaNV = new JLabel("0",SwingConstants.CENTER);
		MaNV.setBounds(410,70,70,30);
		MaNV.setFont(FLabelText);
		MaNV.setBackground(Color.WHITE);
		MaNV.setOpaque(true);
		add(MaNV);
		
		jlabel = new JLabel("Ngày HĐ"); // thời gian tạo hóa đơn + hiển thị
		jlabel.setBounds(70,110,100,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		NgayHD = new JLabel("  0-0-0 00::00::00.0");
		NgayHD.setBounds(170,110,310,30);
		NgayHD.setFont(FLabelText);
		NgayHD.setBackground(Color.WHITE);
		NgayHD.setOpaque(true);
		add(NgayHD);

		jlabel = new JLabel("Tổng Tiền"); // dòng tổng tiền + hiển thị 
		jlabel.setBounds(70,150,100,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		TongTien = new JLabel("   0.0");
		TongTien.setBounds(170,150,310,30);
		TongTien.setFont(FLabelText);
		TongTien.setBackground(Color.WHITE);
		TongTien.setOpaque(true);
		add(TongTien);
		
		AddBill = new JButton(); // thêm hóa đơn
		AddBill.setBounds(650,70,200,50);
		AddBill.setFont(FBtnBill);
		AddBill.setBackground(Color.GREEN);
		AddBill.setOpaque(true);
		AddBill.setText("Thêm Hóa Đơn");
		add(AddBill);
		
		DeleteBill = new JButton(); // xóa hóa đơn
		DeleteBill.setBounds(650,130,200,50);
		DeleteBill.setFont(FBtnBill);
		DeleteBill.setBackground(Color.RED);
		DeleteBill.setOpaque(true);
		DeleteBill.setText("Xóa Hóa Đơn");
		add(DeleteBill);
		
		DetailsBill = new JButton(); // hóa đơn chi tiết 
		DetailsBill.setBounds(900,70,200,50);
		DetailsBill.setFont(FBtnBill);
		DetailsBill.setBackground(Color.YELLOW);
		DetailsBill.setOpaque(true);
		DetailsBill.setText("Chi Tiết Hóa Đơn");
		add(DetailsBill);
		
	}
	
	 @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);

	        Graphics g2 = (Graphics) g;

	        // Vẽ đường thẳng ngăn cách giữa 2 phần
	        g2.drawLine(70, 210, 1170, 210);
	    }
	 
	public void CreateBody() {
		// time
		jlabel = new JLabel("Thời gian : ");
		jlabel.setBounds(70,240,80,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		FDay = new JComboBox<String>();
		for(int i=1;i<=31;i++) {
			if(i<10) FDay.addItem("0"+String.valueOf(i));
			else FDay.addItem(String.valueOf(i));
		}
		FDay.setBounds(150,240,60,30);
		add(FDay);
		
		FMonth = new JComboBox<String>();
		for(int i=1;i<=12;i++) {
			if(i<10) FMonth.addItem("0"+String.valueOf(i));
			else FMonth.addItem(String.valueOf(i));
		}
		FMonth.setBounds(220,240,60,30);
		add(FMonth);
		
		FYear = new JComboBox<String>();
		for(int i=2000;i<=2024;i++)
			FYear.addItem(String.valueOf(i));
		FYear.setBounds(290,240,60,30);
		add(FYear);
		
		// Giá
		jlabel = new JLabel("Giá (VNĐ):");
		jlabel.setBounds(450,240,100,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		FBillForm = new JTextField();
		FBillForm.setBounds(550,240,100,30);
		FBillForm.setFont(FLabelText);
		add(FBillForm);
		
		jlabel = new JLabel("-",SwingConstants.CENTER);
		jlabel.setBounds(650,240,30,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		FBillTo = new JTextField();
		FBillTo.setBounds(680,240,100,30);
		FBillTo.setFont(FLabelText);
		add(FBillTo);
		
		// lọc mã
		jlabel = new JLabel("Mã HĐ");
		jlabel.setBounds(850,240,70,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		FMaHD = new JTextField() ;
		FMaHD.setBounds(920,240,150,30);
		FMaHD.setFont(FLabelText);
		add(FMaHD);
		
		scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(70, 352, 894, 275);
		add(scrollPaneTable);
		
		TableBill = new JTable();
		scrollPaneTable.setViewportView(TableBill);
		TableBill.setColumnSelectionAllowed(true);
		TableBill.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
					"Mã Hóa Đơn", "Mã Khách Hàng", "Mã Nhân Viên", "Ngày Hóa Đơn", "Tổng Tiền"
					//"Mã Hóa Đơn", "Mã Khách Hàng", "Mã Nhân Viên", "Ngày Hóa Đơn", "Tổng Tiền"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		TableBill.getColumnModel().getColumn(0).setPreferredWidth(100);
		TableBill.getColumnModel().getColumn(1).setPreferredWidth(100);
		TableBill.getColumnModel().getColumn(2).setPreferredWidth(100);
		TableBill.getColumnModel().getColumn(3).setPreferredWidth(100);
		TableBill.getColumnModel().getColumn(4).setPreferredWidth(100);
	}
	
	public JLabel getJlabel() {
		return jlabel;
	}
	public void setJlabel(JLabel jlabel) {
		this.jlabel = jlabel;
	}
	public JLabel getMaHD() {
		return MaHD;
	}
	public void setMaHD(JLabel maHD) {
		MaHD = maHD;
	}
	public JLabel getMaKH() {
		return MaKH;
	}
	public void setMaKH(JLabel maKH) {
		MaKH = maKH;
	}
	public JLabel getMaNV() {
		return MaNV;
	}
	public void setMaNV(JLabel maNV) {
		MaNV = maNV;
	}
	public JLabel getNgayHD() {
		return NgayHD;
	}
	public void setNgayHD(JLabel ngayHD) {
		NgayHD = ngayHD;
	}
	public JLabel getTongTien() {
		return TongTien;
	}
	public void setTongTien(JLabel tongTien) {
		TongTien = tongTien;
	}
	public JButton getAddBill() {
		return AddBill;
	}
	public void setAddBill(JButton addBill) {
		AddBill = addBill;
	}
	public JButton getDeleteBill() {
		return DeleteBill;
	}
	public void setDeleteBill(JButton deleteBill) {
		DeleteBill = deleteBill;
	}
	public JButton getDetailsBill() {
		return DetailsBill;
	}
	public void setDetailsBill(JButton detailsBill) {
		DetailsBill = detailsBill;
	}
	public Font getFLabel() {
		return FLabel;
	}
	public void setFLabel(Font fLabel) {
		FLabel = fLabel;
	}
	public Font getFLabelText() {
		return FLabelText;
	}
	public void setFLabelText(Font fLabelText) {
		FLabelText = fLabelText;
	}
	public Font getFBtnBill() {
		return FBtnBill;
	}
	public void setFBtnBill(Font fBtnBill) {
		FBtnBill = fBtnBill;
	}
	public JComboBox<String> getFDay() {
		return FDay;
	}
	public void setFDay(JComboBox<String> fDay) {
		FDay = fDay;
	}
	public JComboBox<String> getFMonth() {
		return FMonth;
	}
	public void setFMonth(JComboBox<String> fMonth) {
		FMonth = fMonth;
	}
	public JComboBox<String> getFYear() {
		return FYear;
	}
	public void setFYear(JComboBox<String> fYear) {
		FYear = fYear;
	}
	public JTextField getFBillForm() {
		return FBillForm;
	}
	public void setFBillForm(JTextField fBillForm) {
		FBillForm = fBillForm;
	}
	public JTextField getFBillTo() {
		return FBillTo;
	}
	public void setFBillTo(JTextField fBillTo) {
		FBillTo = fBillTo;
	}
	public JTextField getFMaHD() {
		return FMaHD;
	}
	public void setFMaHD(JTextField fMaHD) {
		FMaHD = fMaHD;
	}
	public JTable getTableBill() {
		return TableBill;
	}
	public void setTableBill(JTable tableBill) {
		TableBill = tableBill;
	}
	public JScrollPane getScrollPaneTable() {
		return scrollPaneTable;
	}
	public void setScrollPaneTable(JScrollPane scrollPaneTable) {
		this.scrollPaneTable = scrollPaneTable;
	}
	
	
}
