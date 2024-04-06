package controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.apache.pivot.wtk.Mouse;

import dao.BillDAO;
import dao.BillDetailsDAO;
import dao.CustomerDao;
import dao.PaymentDAO;
import dao.ProductDAO;
import dao.TableDAO;
import dao.UserDAO;
import model.BillDetailsModel;
import model.BillModel;
import model.CustomerModel;
import model.PaymentModel;
import model.ProductModel;
import model.TableModel;
import model.UserModel;
import util.ValidateUtils;
import views.menu.PanelBill;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class PanelBillController {
	private PanelBill panelBill;
	private BillDAO billDao = new BillDAO();
	private BillDetailsDAO billDetailsDao = new BillDetailsDAO();
	private ProductDAO productDao = new ProductDAO();
	private ArrayList<ProductModel> productList = billDao.findProductAll(); 
	private CustomerDao customerDao = new CustomerDao();
	private TableDAO tableDao = new TableDAO();
	private PaymentDAO paymentDao = new PaymentDAO();
	private UserDAO userDao = new UserDAO();
	
	public PanelBillController(PanelBill panelBill) {
		this.panelBill = panelBill;
		ArrayList<BillModel> rowData = billDao.findAll();	
		renderTable(rowData);
		UpdateEvent();
		addEvent();	
	}
	public void renderTable(ArrayList<BillModel> rowData) {
		DefaultTableModel model = new DefaultTableModel(); 
		String[] colName = {"BillID", "CustomerID", "UserID", "Date", "Price"};
		for(String x : colName) {
			model.addColumn(x);
		}
		
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
		    model.addRow(row);
		}
		panelBill.getTableBill().setModel(model);
	}
	
	public void addEvent() {
		//private Date utilDate;
		DisableInput();
		panelBill.getTableBill().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
		public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		panelBill.getAddBill().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				EnableInput();
				resetInput();
			}
		});
		
		panelBill.getSaveBill().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Bill_ID = panelBill.getBill_ID().getText();
				String Customer_ID = panelBill.getCustomer_ID().getText();
				String User_ID = panelBill.getUser_ID().getText();
				String Quantity = panelBill.getQuantity().getText();
				String Table_ID = panelBill.getTable_ID().getText();
				String Payment_ID = panelBill.getPayment_ID().getText();
				String Status = (String) panelBill.getStatus_item().getSelectedItem();
				String dateWork = panelBill.getDatetime().getText();
				
				BillModel tmp = new BillModel();
				//tmp.setID(Integer.parseInt(Bill_ID));
				/*
				Date currentDate = new Date(System.currentTimeMillis());
        	    java.sql.Date sqlDate2 = (java.sql.Date) new Date(currentDate.getTime());
			    tmp.setBillDate(sqlDate2); 
				*/
				int productIdx = (int) panelBill.getProducts_item().getSelectedIndex();
				ProductModel product = productDao.findByID( (productIdx+1) +  "") ;
				tmp.setBillTotal(Integer.parseInt(Quantity)*product.getPrice());
				
				tmp.setStatus(Status);
				
				CustomerModel cusDao = customerDao.findByID(Customer_ID);
				tmp.setCustomer(cusDao);
				tmp.setCustomerID(cusDao.getID());
				
				TableModel tablee = tableDao.findByID(Table_ID);
				tmp.setTable(tablee);
				tmp.setTableID(tablee.getID());
				
				UserModel user = userDao.findByID(User_ID);
				tmp.setUser(user);
				tmp.setUserID(user.getID());
				
				PaymentModel payment = paymentDao.findByID(Payment_ID);
				tmp.setPayment(payment);
				tmp.setPaymentID(payment.getID());
				
				
				try {
        			if(!ValidateUtils.checkEmptyAndNull(dateWork)) {
        				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        				java.util.Date utilDate = dateFormat.parse(dateWork);
        				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        				tmp.setBillDate(sqlDate);        				
        			}
        			else tmp.setBillDate(null);
				} catch (Exception e1) {
					e1.printStackTrace();
				}   
				
				StringBuilder messageError = new StringBuilder("");
				if(ValidateUtils.checkEmptyAndNull(Bill_ID)) {
        			// them moi
            		
            		if(validateForm(tmp, messageError)) {
            			tmp.setID(billDao.findAll().size()+1);
            			billDao.insert(tmp);
            			renderTable(billDao.findAll());            			
            		}else {
            			JOptionPane.showMessageDialog(panelBill, messageError.toString());
            		}
        		}else {
        			// chinh sua
        			tmp.setID(Integer.parseInt(Bill_ID));
        			if(validateForm2(tmp, messageError)) {
        				billDao.update(tmp);
        				renderTable(billDao.findAll());
        			}else {
        				JOptionPane.showMessageDialog(panelBill, messageError.toString());
        			}
        		}
				DisableInput();
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
		
	
	
	
	public boolean validateForm2(BillModel bill,StringBuilder res) {		
		if(ValidateUtils.checkEmptyAndNull(bill.getUser()+"")) {
			res.append("ID người dùng không được để trống\n");
			return false;
		}
		if(ValidateUtils.checkEmptyAndNull(bill.getPayment()+"")) {
			res.append("ID thanh toán không được để trống\n");
			return false;
		}
		return true;
	}
	public boolean validateForm(BillModel bill,StringBuilder res) {			
		if(!billDao.checkUser(bill.getUser().getID()+"")) {
			res.append("Người dùng không tồn tại\\n");
			return false;
		}
		if(!billDao.checkCustomer(bill.getCustomer().getID()+"")) {
			res.append("Khách không tồn tại\\n");
			return false;
		}
		if(!billDao.checkTable(bill.getTable().getID()+"")) {
			res.append("Bàn không tồn tại\\n");
			return false;
		}
		return true;
		
	}
	
		
	public void resetInput() {
		panelBill.getBill_ID().setText("");
		panelBill.getCustomer_ID().setText("");
		panelBill.getUser_ID().setText("");
		panelBill.getQuantity().setText("");
		panelBill.getTable_ID().setText("");
		panelBill.getPayment_ID().setText("");
	}
	public void EnableInput() {
		//panelBill.getBill_ID().setEnabled(true);
		panelBill.getCustomer_ID().setEnabled(true);
		panelBill.getUser_ID().setEnabled(true);
		panelBill.getQuantity().setEnabled(true);
		panelBill.getTable_ID().setEnabled(true);
		panelBill.getPayment_ID().setEnabled(true);
		
	}
	public void DisableInput() {
		panelBill.getBill_ID().setEnabled(false);
		panelBill.getCustomer_ID().setEnabled(false);
		panelBill.getUser_ID().setEnabled(false);
		panelBill.getQuantity().setEnabled(false);
		panelBill.getTable_ID().setEnabled(false);
		panelBill.getPayment_ID().setEnabled(false);
	}
	public void UpdateEvent() {
		// time run
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Runnable task = () -> {
            LocalDateTime current = LocalDateTime.now();
            String formatted = current.format(formatter);
            panelBill.getDatetime().setText(formatted);
        };
        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
        
        // set up product
        for(ProductModel item: productList) {
        	panelBill.getProducts_item().addItem(item.getName());
        }
        AutoCompleteDecorator.decorate(panelBill.getProducts_item());
	}
}
