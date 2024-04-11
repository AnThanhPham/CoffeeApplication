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
	private JLabel Datetime;
	
	private JButton AddBill;
	private JButton DeleteBill;
	private JButton EditBill;
	private JButton DetailsBill;
	private JButton SaveBill;
	private JButton addBillProduct;
	private JButton editBillProduct;
	//page
	private JButton Page1;
	private JButton Page2;
	private JButton Page3; 
	private JButton PageBefore;
	private JButton PageNext; 
	private JButton PageFirst;
	private JButton FitterDay;
	
	private Font FLabel;
	private Font FLabelText;
	private Font FBtnBill;
	
	private JComboBox<String> FDay;
	private JComboBox<String> FMonth;
	private JComboBox<String> FYear;
	private JComboBox<String> Sort;
	private JComboBox<String> Status_item;
	private JComboBox<String> Products_item;
	
	private JTextField Bill_ID;
	private JTextField Customer_ID;
	private JTextField User_ID;
	private JTextField Quantity;
	private JTextField Table_ID;
	private JTextField Payment_ID;
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
		jlabel = new JLabel("Mã Hóa Đơn");
		jlabel.setBounds(60,50,100,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		Bill_ID = new JTextField();
		Bill_ID.setBounds(180,50,120,30);
		Bill_ID.setFont(FLabelText);
		add(Bill_ID);
		
		jlabel = new JLabel("Mã Khách Hàng");
		jlabel.setBounds(60,185,110,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		Customer_ID = new JTextField();
		Customer_ID.setBounds(180,185,120,30);
		Customer_ID.setFont(FLabelText);
		add(Customer_ID);
		
		jlabel = new JLabel("Mã Nhân Viên");
		jlabel.setBounds(60,140,100,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		User_ID = new JTextField();
		User_ID.setBounds(180,140,120,30);
		User_ID.setFont(FLabelText);
		add(User_ID);
		
		
		jlabel = new JLabel("Bàn số");
		jlabel.setBounds(60,230,60,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		Table_ID = new JTextField();
		Table_ID.setBounds(180,230,119,30);
		Table_ID.setFont(FLabelText);
		add(Table_ID);
		
		jlabel = new JLabel("Thanh Toán");
		jlabel.setBounds(340,140,90,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		Payment_ID = new JTextField();
		Payment_ID.setBounds(440,140,100,30);
		Payment_ID.setFont(FLabelText);
		add(Payment_ID);
		
		jlabel = new JLabel("Ngày Hóa Đơn"); // thời gian tạo hóa đơn + hiển thị
		jlabel.setBounds(60,95,120,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		Datetime = new JLabel("",SwingConstants.CENTER);
		Datetime.setBounds(180,95,120,30);
		Datetime.setBackground(Color.WHITE);
		Datetime.setOpaque(true);
		Datetime.setFont(FLabelText);
		add(Datetime);
		
		jlabel = new JLabel("Trạng Thái"); // thời gian tạo hóa đơn + hiển thị
		jlabel.setBounds(340,185,90,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		Status_item = new JComboBox<String>();
		Status_item.setBounds(440,185,100,30);
		Status_item.addItem("Done");
		Status_item.addItem("Waiting");
		add(Status_item);
		
		jlabel = new JLabel("Sản Phẩm"); // thời gian tạo hóa đơn + hiển thị
		jlabel.setBounds(340,50,70,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		Products_item = new JComboBox<String>();
		Products_item.setBounds(440,50,150,30);
		Products_item.setFont(FLabelText);
		add(Products_item);
		
		jlabel = new JLabel("Số lượng"); // dòng tổng tiền + hiển thị 
		jlabel.setBounds(340,95,70,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		Quantity = new JTextField();
		Quantity.setBounds(440,95,100,30);
		Quantity.setFont(FLabel);
		add(Quantity);
		
		addBillProduct = new JButton(); // thêm hóa đơn
		addBillProduct.setBounds(630,50,160,40);
		addBillProduct.setFont(FBtnBill);
		addBillProduct.setBackground(Color.ORANGE);
		addBillProduct.setOpaque(true);
		addBillProduct.setText("Thêm mặt hàng");
		add(addBillProduct);
		
		editBillProduct = new JButton(); // thêm hóa đơn
		editBillProduct.setBounds(630,105,160,40);
		editBillProduct.setFont(FBtnBill);
		editBillProduct.setBackground(Color.ORANGE);
		editBillProduct.setOpaque(true);
		editBillProduct.setText("Giỏ hàng");
		add(editBillProduct);
        
		AddBill = new JButton(); // thêm hóa đơn
		AddBill.setBounds(800,50,160,40);
		AddBill.setFont(FBtnBill);
		AddBill.setBackground(Color.GREEN);
		AddBill.setOpaque(true);
		AddBill.setText("Thêm Hóa Đơn");
		add(AddBill);
		
		DeleteBill = new JButton(); // xóa hóa đơn
		DeleteBill.setBounds(800,215,160,40);
		DeleteBill.setFont(FBtnBill);
		DeleteBill.setBackground(Color.RED);
		DeleteBill.setOpaque(true);
		DeleteBill.setText("Xóa Hóa Đơn");
		add(DeleteBill);
		
		EditBill = new JButton(); // hóa đơn chi tiết 
		EditBill.setBounds(800,105,160,40);
		EditBill.setFont(FBtnBill);
		EditBill.setBackground(Color.YELLOW);
		EditBill.setOpaque(true);
		EditBill.setText("Chỉnh Hóa Đơn");
		add(EditBill);
		
		DetailsBill = new JButton(); // hóa đơn chi tiết 
		DetailsBill.setBounds(800,160,160,40);
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
		jlabel = new JLabel("Thời gian : ");
		jlabel.setBounds(60,320,80,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		
		FDay = new JComboBox<String>();
		for(int i=0;i<=31;i++) {
			if(i<10) FDay.addItem("0"+String.valueOf(i));
			else FDay.addItem(String.valueOf(i));
		}
		FDay.setBounds(140,320,60,30);
		add(FDay);
		
		FMonth = new JComboBox<String>();
		for(int i=0;i<=12;i++) {
			if(i<10) FMonth.addItem("0"+String.valueOf(i));
			else FMonth.addItem(String.valueOf(i));
		}
		FMonth.setBounds(210,320,60,30);
		add(FMonth);
		
		FYear = new JComboBox<String>();
		FYear.addItem("0000");
		for(int i=2000;i<=2024;i++)
			FYear.addItem(String.valueOf(i));
		FYear.setBounds(280,320,60,30);
		add(FYear);
		
		FitterDay = new JButton("Lọc");
		FitterDay.setBounds(350,320,60,30);
		add(FitterDay);
		// lọc mã
		jlabel = new JLabel("Tìm Mã Hóa Đơn");
		jlabel.setBounds(440,320,120,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		jlabel = new JLabel("Sắp Xếp Theo ");
		jlabel.setBounds(687,320,110,30);
		jlabel.setFont(FLabel);
		add(jlabel);
		
		FindBillID = new JTextField();
		FindBillID.setBounds(560,320,100,30);
		FindBillID.setFont(FLabelText);
		add(FindBillID);
		
		Sort = new JComboBox<String>();
		Sort.setBounds(800,320,160,30);
		Sort.addItem("Mã hóa đơn mới nhất");
		Sort.addItem("Mã hóa đơn cũ nhất");
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
	
	
	public JLabel getDatetime() {
		return Datetime;
	}

	public void setDatetime(JLabel datetime) {
		Datetime = datetime;
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

	public JComboBox<String> getFitter() {
		return Sort;
	}

	public void setFitter(JComboBox<String> sort) {
		Sort = sort;
	}

	public JTextField getBill_ID() {
		return Bill_ID;
	}

	public void setBill_ID(JTextField bill_ID) {
		Bill_ID = bill_ID;
	}

	public JTextField getCustomer_ID() {
		return Customer_ID;
	}

	public void setCustomer_ID(JTextField customer_ID) {
		Customer_ID = customer_ID;
	}

	public JTextField getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(JTextField user_ID) {
		User_ID = user_ID;
	}

	public JTextField getQuantity() {
		return Quantity;
	}

	public void setQuantity(JTextField quantity) {
		Quantity = quantity;
	}

	public JTextField getTable_ID() {
		return Table_ID;
	}

	public void setTable_ID(JTextField table_ID) {
		Table_ID = table_ID;
	}

	public JTextField getPayment_ID() {
		return Payment_ID;
	}

	public void setPayment_ID(JTextField payment_ID) {
		Payment_ID = payment_ID;
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

	public JComboBox<String> getProducts_item() {
		return Products_item;
	}

	public void setProducts_item(JComboBox<String> products_item) {
		Products_item = products_item;
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

	public JButton getaddBillProduct() {
		return addBillProduct;
	}

	public void setaddBillProduct(JButton addBillProduct) {
		this.addBillProduct = addBillProduct;
	}

	public JButton geteditBillProduct() {
		return editBillProduct;
	}

	public void seteditBillProduct(JButton editBillProduct) {
		this.editBillProduct = editBillProduct;
	}

	public JButton getPageFirst() {
		return PageFirst;
	}

	public void setPageFirst(JButton pageFirst) {
		PageFirst = pageFirst;
	}

	public JButton getAddBillProduct() {
		return addBillProduct;
	}

	public void setAddBillProduct(JButton addBillProduct) {
		this.addBillProduct = addBillProduct;
	}

	public JButton getEditBillProduct() {
		return editBillProduct;
	}

	public void setEditBillProduct(JButton editBillProduct) {
		this.editBillProduct = editBillProduct;
	}

	public JButton getFitterDay() {
		return FitterDay;
	}

	public void setFitterDay(JButton fitterDay) {
		FitterDay = fitterDay;
	}

	public JComboBox<String> getSort() {
		return Sort;
	}

	public void setSort(JComboBox<String> sort) {
		Sort = sort;
	}
	
}
