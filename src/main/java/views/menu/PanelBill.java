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
import dao.BillDAO;
import model.BillDetailsModel;

import javax.swing.JScrollPane;

public class PanelBill extends JPanel {
	private JLabel jlabel;
	private JLabel TodayDate;
	
	private JButton AddBill;
	private JButton DeleteBill;
	private JButton EditBill;
	private JButton DetailsBill;
	private JButton SaveBill;
	//private JButton addBillProduct;
	private JButton Cart;
	//page
	private JButton Page1;
	private JButton Page2;
	private JButton Page3; 
	private JButton PageBefore;
	private JButton PageNext; 
	private JButton PageFirst;
	private JButton Fitter;
	private JButton RefreshFitter;
	
	private Font FLabel;
	private Font FLabelText;
	private Font FBtnBill;
	
	private JComboBox<String> FDay;
	private JComboBox<String> FMonth;
	private JComboBox<String> FYear;
	private JComboBox<String> Sort;
	private JComboBox<String> Status_item;
	
	private JTextField Bill_Date;
	private JTextField Bill_ID;
	private JTextField Customer_Phone;
	private JComboBox<String> User_Name;
	private JComboBox<String> Table_Number;
	private JComboBox<String> Payment_Name;
	private JTextField FindBillID;
	
	private JTable TableBill;
	private JScrollPane scrollPaneTable;
	
    private PanelBillController panelBillController;
	private BillDetailsModel billDetailsModel;
	private BillDAO billDao = new BillDAO();
	
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
		
		TodayDate = new JLabel();
		TodayDate.setBounds(60,10,237,30);
		TodayDate.setFont(FBtnBill);
		add(TodayDate);
		
		jlabel = new JLabel("Mã Hóa Đơn");
		jlabel.setBounds(60,50,100,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		Bill_ID = new JTextField();
		Bill_ID.setBounds(170,50,140,30);
		Bill_ID.setFont(FLabelText);
		add(Bill_ID);
		
		jlabel = new JLabel("Sđt Khách Hàng");
		jlabel.setBounds(325,95,120,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		Customer_Phone = new JTextField();
		Customer_Phone.setBounds(445,95,145,30);
		Customer_Phone.setFont(FLabelText);
		add(Customer_Phone);
		
		jlabel = new JLabel("Tên Nhân Viên");
		jlabel.setBounds(325,50,120,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		User_Name = new JComboBox<String>();
		User_Name.setBounds(445,50,145,30);
		User_Name.setFont(FLabelText);
		add(User_Name);
		
		
		jlabel = new JLabel("Bàn số");
		jlabel.setBounds(60,185,60,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		Table_Number = new JComboBox<String>();
		Table_Number.setBounds(170,185,140,30);
		Table_Number.setFont(FLabelText);
		add(Table_Number);
		
		jlabel = new JLabel("Thanh Toán");
		jlabel.setBounds(60,140,100,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		Payment_Name = new JComboBox<String>();
		Payment_Name.setBounds(170,140,140,30);
		Payment_Name.setFont(FLabelText);
		add(Payment_Name);
		
		jlabel = new JLabel("Ngày Hóa Đơn"); // thời gian tạo hóa đơn + hiển thị
		jlabel.setBounds(60,95,110,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		Bill_Date = new JTextField();
		Bill_Date.setBounds(170,95,140,30);
		Bill_Date.setBackground(Color.WHITE);
		Bill_Date.setOpaque(true);
		Bill_Date.setFont(FLabelText);
		add(Bill_Date);
		
		jlabel = new JLabel("Trạng Thái"); // thời gian tạo hóa đơn + hiển thị
		jlabel.setBounds(325,140,95,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		Status_item = new JComboBox<String>();
		Status_item.setBounds(445,140,145,30);
		add(Status_item);

		
		Cart = new JButton(); // thêm hóa đơn
		Cart.setBounds(630,50,160,40);
		Cart.setFont(FBtnBill);
		Cart.setBackground(Color.ORANGE);
		Cart.setOpaque(true);
		Cart.setText("Giỏ hàng");
		add(Cart);
        
		AddBill = new JButton(); // thêm hóa đơn
		AddBill.setBounds(800,50,160,40);
		AddBill.setFont(FBtnBill);
		AddBill.setBackground(Color.GREEN);
		AddBill.setOpaque(true);
		AddBill.setText("Thêm Hóa Đơn");
		add(AddBill);
		
		DeleteBill = new JButton(); // xóa hóa đơn
		DeleteBill.setBounds(800,160,160,40);
		DeleteBill.setFont(FBtnBill);
		DeleteBill.setBackground(Color.RED);
		DeleteBill.setOpaque(true);
		DeleteBill.setText("Xóa Hóa Đơn");
		add(DeleteBill);
		
		EditBill = new JButton(); // hóa đơn chi tiết 
		EditBill.setBounds(630,105,160,40);
		EditBill.setFont(FBtnBill);
		EditBill.setBackground(Color.YELLOW);
		EditBill.setOpaque(true);
		EditBill.setText("Chỉnh Hóa Đơn");
		add(EditBill);
		
		DetailsBill = new JButton(); // hóa đơn chi tiết 
		DetailsBill.setBounds(800,105,160,40);
		DetailsBill.setFont(FBtnBill);
		DetailsBill.setBackground(Color.BLUE);
		DetailsBill.setOpaque(true);
		DetailsBill.setText("Chi Tiết Hóa Đơn");
		add(DetailsBill);
		
		SaveBill = new JButton(); // hóa đơn chi tiết 
		SaveBill.setBounds(630,160,160,40);
		SaveBill.setFont(FBtnBill);
		SaveBill.setBackground(Color.LIGHT_GRAY);
		SaveBill.setOpaque(true);
		SaveBill.setText("Lưu Hóa Đơn");
		add(SaveBill);
	}

	@Override
	protected void paintComponent(Graphics g) {
	        super.paintComponent(g);

	        Graphics g2 = (Graphics) g;

	        // Vẽ đường thẳng ngăn cách giữa 2 phần
	        g2.drawLine(60, 290, 1180, 290);
	    }
	 
	public void CreateBody() {
		// time
		jlabel = new JLabel("Thời gian ");
		jlabel.setBounds(60,320,75,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		
		FDay = new JComboBox<String>();
		FDay.addItem("day");
		for(int i=1;i<=31;i++) {
			if(i<10) FDay.addItem("0"+String.valueOf(i));
			else FDay.addItem(String.valueOf(i));
		}
		FDay.setBounds(135,320,55,30);
		add(FDay);
		
		FMonth = new JComboBox<String>();
		FMonth.addItem("month");
		for(int i=1;i<=12;i++) {
			if(i<10) FMonth.addItem("0"+String.valueOf(i));
			else FMonth.addItem(String.valueOf(i));
		}
		FMonth.setBounds(200,320,70,30);
		add(FMonth);
		
		FYear = new JComboBox<String>();
		FYear.addItem("year");
		for(int i=2020;i<=2024;i++)
			FYear.addItem(String.valueOf(i));
		FYear.setBounds(275,320,70,30);
		add(FYear);
		
		Fitter = new JButton("Lọc");
		Fitter.setBounds(885,300,75,30);
		add(Fitter);
		
		RefreshFitter = new JButton("Làm mới");
		RefreshFitter.setBounds(885,335,75,30);
		add(RefreshFitter);
		// lọc mã
		jlabel = new JLabel("Tìm Mã Hóa Đơn");
		jlabel.setBounds(360,320,120,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		jlabel = new JLabel("Sắp Xếp Theo ");
		jlabel.setBounds(614,320,110,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		FindBillID = new JTextField();
		FindBillID.setBounds(490,320,100,30);
		FindBillID.setFont(FLabelText);
		add(FindBillID);
		
		Sort = new JComboBox<String>();
		Sort.setBounds(715,320,150,30);
		Sort.addItem("Mã hóa đơn mới nhất");
		Sort.addItem("Mã hóa đơn cũ nhất");
		Sort.addItem("Ngày mới nhất");
		Sort.addItem("Ngày Cũ nhất");
		add(Sort);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 380, 900, 290);
		add(scrollPane);
		
		TableBill = new JTable();
		scrollPane.setViewportView(TableBill);
		TableBill.setRowHeight(20);
		
		JTableHeader header = TableBill.getTableHeader();
		header.setReorderingAllowed(false);
		
		// Page
		PageBefore = new JButton("Trang trước");
		PageBefore.setBounds(310, 687, 100, 30);
		add(PageBefore);
		
		PageFirst = new JButton("Trang Đầu");
		PageFirst.setBounds(210, 687, 90, 30);
		add(PageFirst);
		
		Page1 = new JButton("1");
		Page1.setBounds(430, 687, 60, 30);
		Page1.setBackground(Color.orange);
		add(Page1);
		
		Page2 = new JButton("2");
		Page2.setBounds(505, 687, 60, 30);
		add(Page2);
		
		Page3 = new JButton("3");
		Page3.setBounds(575, 687, 60, 30);
		add(Page3);
		
		PageNext = new JButton("Trang kế tiếp");
		PageNext.setBounds(660, 687, 100, 30);
		add(PageNext);
		
	}
	
	
	public JButton getRefreshFitter() {
		return RefreshFitter;
	}

	public void setRefreshFitter(JButton refreshFitter) {
		RefreshFitter = refreshFitter;
	}

	public JTextField getBill_Date() {
		return Bill_Date;
	}

	public void setBill_Date(JTextField datetime) {
		Bill_Date = datetime;
	}

	public JButton getPage1() {
		return Page1;
	}

	public void setPage1(JButton page1) {
		Page1 = page1;
	}

	public JButton getPage2() {
		return Page2;
	}

	public void setPage2(JButton page2) {
		Page2 = page2;
	}

	public JButton getPage3() {
		return Page3;
	}

	public void setPage3(JButton page3) {
		Page3 = page3;
	}

	public JButton getPageNext() {
		return PageNext;
	}

	public void setPageNext(JButton pageNext) {
		PageNext = pageNext;
	}

	public JButton getPageBefore() {
		return PageBefore;
	}

	public void setPageBefore(JButton pageBefore) {
		PageBefore = pageBefore;
	}

	public JTextField getBill_ID() {
		return Bill_ID;
	}

	public void setBill_ID(JTextField bill_ID) {
		Bill_ID = bill_ID;
	}

	public JTextField getFindBillID() {
		return FindBillID;
	}

	public void setFindBillID(JTextField findBillID) {
		FindBillID = findBillID;
	}

	public PanelBillController getPanelBillController() {
		return panelBillController;
	}

	public void setPanelBillController(PanelBillController panelBillController) {
		this.panelBillController = panelBillController;
	}

	public BillDetailsModel getBillDetailsModel() {
		return billDetailsModel;
	}

	public void setBillDetailsModel(BillDetailsModel billDetailsModel) {
		this.billDetailsModel = billDetailsModel;
	}

	public JLabel getJlabel() {
		return jlabel;
	}
	public void setJlabel(JLabel jlabel) {
		this.jlabel = jlabel;
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
	public JComboBox<String> getStatus_item() {
		return Status_item;
	}

	public void setStatus_item(JComboBox<String> status_item) {
		Status_item = status_item;
	}

	public JButton getDetailsBill() {
		return DetailsBill;
	}

	public void setDetailsBill(JButton detailsBill) {
		DetailsBill = detailsBill;
	}

	public BillDAO getBillDao() {
		return billDao;
	}

	public void setBillDao(BillDAO billDao) {
		this.billDao = billDao;
	}

	public JButton getSaveBill() {
		return SaveBill;
	}

	public void setSaveBill(JButton saveBill) {
		SaveBill = saveBill;
	}

	public JButton getPageFirst() {
		return PageFirst;
	}

	public JButton getFitter() {
		return Fitter;
	}

	public void setFitter(JButton fitter) {
		Fitter = fitter;
	}

	public JComboBox<String> getSort() {
		return Sort;
	}

	public void setSort(JComboBox<String> sort) {
		Sort = sort;
	}

	public JLabel getTodayDate() {
		return TodayDate;
	}

	public void setTodayDate(JLabel todayDate) {
		TodayDate = todayDate;
	}

	public JButton getCart() {
		return Cart;
	}

	public void setCart(JButton cart) {
		Cart = cart;
	}

	public void setPageFirst(JButton pageFirst) {
		PageFirst = pageFirst;
	}

	public JTextField getCustomer_Phone() {
		return Customer_Phone;
	}

	public void setCustomer_Phone(JTextField customer_Phone) {
		Customer_Phone = customer_Phone;
	}

	public JComboBox<String> getUser_Name() {
		return User_Name;
	}

	public void setUser_Name(JComboBox<String> user_Name) {
		User_Name = user_Name;
	}

	public JComboBox<String> getTable_Number() {
		return  Table_Number;
	}

	public void setTable_Number(JComboBox<String> Table_Number) {
		Table_Number = Table_Number;
	}

	public JComboBox<String> getPayment_Name() {
		return Payment_Name;
	}

	public void setPayment_Name(JComboBox<String> payment_Name) {
		Payment_Name = payment_Name;
	}
	
	
}
