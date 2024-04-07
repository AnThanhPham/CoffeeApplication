package controller;

import java.awt.FlowLayout;
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
import java.util.LinkedHashMap;
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
import javax.swing.JScrollPane;
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
import util.MapUtil;
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
				/*
				 int rowSelect = panelBill.getTableBill().getSelectedRow();
				int columnSelect = panelBill.getTableBill().getSelectedColumn();
	        	panelBill.getTableBill().isCellEditable(rowSelect, columnSelect);
	        	if(rowSelect != -1) {
	        		
	        		String id = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(rowSelect, 0));
	        		String CusID = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(rowSelect, 1));
	        		String UserID = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(rowSelect, 2));
	        		//String DateTime  = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(rowSelect, 3));
	        		/*
	        		BillDetailsModel billdet = billDetailsDao.findByBillID(id);
	             	BillModel bill = billDao.findByID(id);
	        		panelBill.getBill_ID().setText(id);
	        		//panelBill.getDatetime().setText(DateTime);
	        		panelBill.getCustomer_ID().setText(CusID);
	        		panelBill.getUser_ID().setText(UserID);
	        		panelBill.getQuantity().setText(billdet.getQuantityProduct()+"");
	        	//	panelBill.getPayment_ID().setText(bill.getPayment().getID()+"");
	        		//panelBill.getTable_ID().setText(bill.getTable().getID()+"");
	        		panelBill.getStatus_item().setSelectedItem(bill.getStatus());
	        		//panelBill.getProducts_item().setSelectedItem(billdet.getProduct().getName());
				 */
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
				
				//System.out.println( Bill_ID + " "+Customer_ID+" "+User_ID+" "+Quantity+" "+Table_ID+" "+Payment_ID+" "+Status );
				BillModel tmp = new BillModel();
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
            			tmp.setID(billDao.findAll().getLast().getID()+1);
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
		
		panelBill.getEditBill().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowSelect = panelBill.getTableBill().getSelectedRow();
				if(rowSelect != -1) {
					EnableInput();					
				}
			}
		});
		
		panelBill.getDeleteBill().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] rowSelects = panelBill.getTableBill().getSelectedRows();
				if(rowSelects.length >0) {
					int result = JOptionPane.showConfirmDialog(panelBill, "Bạn có chắc chắn muốn xóa?");
					if(result == JOptionPane.OK_OPTION) {
						for(int x : rowSelects) {
							String id = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(x, 0));
							BillModel bill = billDao.findByID(id);
							ArrayList<BillDetailsModel> billdet = billDetailsDao.findByBillID(bill.getID()+"");
							for(BillDetailsModel tmp: billdet) {
								billDetailsDao.delete(tmp);
							}
							billDao.delete(bill);
						}
						renderTable(billDao.findAll());
						resetInput();
					}
				}
			}
		});
		
		LinkedHashMap<String, String> ProductList = new LinkedHashMap<String, String>();
		panelBill.getAddProduct().addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ktra xem sản phẩm đã tồn tại chưa
				String productChoose = (String) panelBill.getProducts_item().getSelectedItem();
			    String QuantityProductChoose = panelBill.getQuantity().getText();
			    if(ValidateUtils.checkEmptyAndNull(QuantityProductChoose)) {
			    	JOptionPane.showMessageDialog(panelBill, "Bạn chưa Nhập vào số lượng sản phẩm");
			    }
			    else {
			    	if(ProductList.containsKey(productChoose)) {
				    	int productChooseValue = Integer.parseInt(ProductList.get(productChoose)) + Integer.parseInt(QuantityProductChoose)  ;
				    	ProductList.replace(productChoose, productChooseValue+"");
				    }
				    else {
				    	 ProductList.put(productChoose, QuantityProductChoose);
				    }
			    }
			    
			    System.out.println(ProductList.size());
			}
		});
		
		panelBill.getEditProduct().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame FrameProduct = new JFrame();
				FrameProduct.setLayout(new FlowLayout());
				
				JScrollPane scrollPaneProduct = new JScrollPane();
				//scrollPaneProduct.setBounds(60, 340, 894, 290);
				FrameProduct.add(scrollPaneProduct);
				
				JTable tableCart = new JTable();
				scrollPaneProduct.setViewportView(tableCart);
				tableCart.setRowHeight(20);
				
				FrameProduct.setSize(500, 500);
				FrameProduct.setLocationRelativeTo(null);
				FrameProduct.setVisible(true);
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
