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

import controller.PanelBillController;
import model.BillModel;

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
	private JButton EditBill;
	
	private JButton Page1;
	private JButton Page2;
	private JButton Page3;
	private JButton PageNext;
	private JButton PageBefore;
	
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
	
	private PanelBillController panelBillController;
	
	public PanelBill() {
		setLayout(null);
		setBackground(new Color(255, 243, 199));
		
		FLabel = new Font("SansSerif", Font.BOLD, 14);
		FLabelText =  new Font("SansSerif", Font.PLAIN, 14);
		FBtnBill = new Font("SansSerif", Font.BOLD, 16);
		
		CreateHeader();
		CreateBody();
		
		panelBillController = new PanelBillController(this);

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
		AddBill.setBounds(550,70,180,50);
		AddBill.setFont(FBtnBill);
		AddBill.setBackground(Color.GREEN);
		AddBill.setOpaque(true);
		AddBill.setText("Thêm Hóa Đơn");
		add(AddBill);
		
		DeleteBill = new JButton(); // xóa hóa đơn
		DeleteBill.setBounds(550,130,180,50);
		DeleteBill.setFont(FBtnBill);
		DeleteBill.setBackground(Color.RED);
		DeleteBill.setOpaque(true);
		DeleteBill.setText("Xóa Hóa Đơn");
		add(DeleteBill);
		
		DetailsBill = new JButton(); // hóa đơn chi tiết 
		DetailsBill.setBounds(780,70,180,50);
		DetailsBill.setFont(FBtnBill);
		DetailsBill.setBackground(Color.YELLOW);
		DetailsBill.setOpaque(true);
		DetailsBill.setText("Chi Tiết Hóa Đơn");
		add(DetailsBill);
		
		EditBill = new JButton(); // hóa đơn chi tiết 
		EditBill.setBounds(780,130,180,50);
		EditBill.setFont(FBtnBill);
		EditBill.setBackground(Color.WHITE);
		EditBill.setOpaque(true);
		EditBill.setText("Chỉnh Sửa Hóa Đơn");
		add(EditBill);
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
		jlabel.setBounds(410,240,100,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		FBillForm = new JTextField();
		FBillForm.setBounds(500,241,100,30);
		FBillForm.setFont(FLabelText);
		add(FBillForm);
		
		jlabel = new JLabel("-",SwingConstants.CENTER);
		jlabel.setBounds(610,240,22,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		FBillTo = new JTextField();
		FBillTo.setBounds(642,241,100,30);
		FBillTo.setFont(FLabelText);
		add(FBillTo);
		
		// lọc mã
		jlabel = new JLabel("Mã HĐ");
		jlabel.setBounds(780,240,54,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		FMaHD = new JTextField() ;
		FMaHD.setBounds(844,241,116,30);
		FMaHD.setFont(FLabelText);
		add(FMaHD);
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 304, 884, 290);
		add(scrollPane);
		
		TableBill = new JTable();
		scrollPane.setViewportView(TableBill);
		TableBill.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 H\u00F3a \u0110\u01A1n", "M\u00E3 Kh\u00E1ch H\u00E0ng", "M\u00E3 Nh\u00E2n Vi\u00EAn", "Ng\u00E0y H\u00F3a \u0110\u01A1n", "T\u1ED5ng Ti\u1EC1n"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		TableBill.getColumnModel().getColumn(0).setPreferredWidth(110);
		TableBill.getColumnModel().getColumn(1).setPreferredWidth(110);
		TableBill.getColumnModel().getColumn(2).setPreferredWidth(110);
		TableBill.getColumnModel().getColumn(3).setPreferredWidth(110);
		TableBill.getColumnModel().getColumn(4).setPreferredWidth(110);
		TableBill.setRowHeight(20);
		
		
		// Page
		PageBefore = new JButton("Trang trước");
		PageBefore.setBounds(270, 640, 100, 30);
		add(PageBefore);
		
		Page1 = new JButton("1");
		Page1.setBounds(390, 640, 60, 30);
		add(Page1);
		
		Page2 = new JButton("2");
		Page2.setBounds(470, 640, 60, 30);
		add(Page2);
		
		Page3 = new JButton("3");
		Page3.setBounds(550, 640, 60, 30);
		add(Page3);
		
		PageNext = new JButton("Trang kế tiếp");
		PageNext.setBounds(630, 640, 100, 30);
		add(PageNext);
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
	 public JButton getEditBill() {
			return EditBill;
		}

	public void setEditBill(JButton editBill) {
			EditBill = editBill;
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
