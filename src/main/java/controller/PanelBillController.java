package controller;

import java.awt.Color;
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
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.sql.Date;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.swing.JButton;
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
	private ArrayList<ArrayList<BillModel>> AllPageInformation = new ArrayList<>();
	private int PageSize =5 ;
	private int PageNumber;
	
	public PanelBillController(PanelBill panelBill) {
		this.panelBill = panelBill;
		ArrayList<BillModel> rowData = billDao.findAll();	
		Pagination(rowData);
		renderTable(AllPageInformation.getFirst());
		addEventHeader();
		addEvent();	
		addPageButton();
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
		

		LinkedHashMap<String, String> ProductListTable = new LinkedHashMap<String, String>();
		panelBill.getAddProduct().addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ktra xem sản phẩm đã tồn tại chưa
				String productChoose = (String) panelBill.getProducts_item().getSelectedItem();
			    String QuantityProductChoose = panelBill.getQuantity().getText();
			    if(ValidateUtils.checkEmptyAndNull(QuantityProductChoose) || ValidateUtils.checkEmptyAndNull(productChoose)) {
			    	JOptionPane.showMessageDialog(panelBill, "Bạn chưa Nhập vào số lượng sản phẩm");
			    }
			    else {
			    	if(ProductListTable.containsKey(productChoose)) {
				    	int productChooseValue = Integer.parseInt(ProductListTable.get(productChoose)) + Integer.parseInt(QuantityProductChoose)  ;
				    	ProductListTable.replace(productChoose, productChooseValue+"");
				    }
				    else {
				    	ProductListTable.put(productChoose, QuantityProductChoose);
				    }
			    }
			    
			   // System.out.println(ProductList.size());
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
				
				DefaultTableModel model = new DefaultTableModel();
				String[] colName = {"ProductName","Quantity"};
				for(String x : colName) {
					model.addColumn(x);
				}
				
				for(Map.Entry<String, String> x: ProductListTable.entrySet()) {
					Vector<String> row = new Vector<>();
					row.add(x.getKey());
					row.add(x.getValue());
				    model.addRow(row);
				}
				tableCart.setModel(model);	
				
				JButton SaveTable = new JButton("Save");
				FrameProduct.add(SaveTable);
				
				LinkedHashMap<String, String> ProductListEdit = new LinkedHashMap<String, String>();
				SaveTable.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						    int counttableProduct = ProductListTable.size();
						    for(int i=0;i<counttableProduct;i++) {
						    	String ProductNameEdit = (String) tableCart.getValueAt(i, 0);
								String QuantityEdit = (String) tableCart.getValueAt(i, 1);
								if(ValidateUtils.checkEmptyAndNull(QuantityEdit) || ValidateUtils.checkEmptyAndNull(ProductNameEdit)) {
									JOptionPane.showMessageDialog(panelBill, "Không được để trống số liệu hàng thứ "+ (i+1));
								}
								else if(Integer.parseInt(QuantityEdit)==0) {
									continue;
								}
								else if(ValidateUtils.checkEmptyAndNull(QuantityEdit) && ValidateUtils.checkEmptyAndNull(ProductNameEdit)) {
									continue;
								}
								else ProductListEdit.put(ProductNameEdit,QuantityEdit);
						    }
						    ProductListTable.clear();
						    ProductListTable.putAll(ProductListEdit.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
						    
						    /*ProductListTable.forEach((key, value) -> {
						        System.out.println(key+" "+value);
						    });
						    */
					}
			
				});
				
				FrameProduct.setSize(500, 500);
				FrameProduct.setLocationRelativeTo(null);
				FrameProduct.setVisible(true);
			}
		});
		
		panelBill.getAddBill().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				EnableInput();
				resetInput();
				ProductListTable.clear();
			}
		});
		
		panelBill.getSaveBill().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuilder messageError = new StringBuilder("");
				double SumPrice = 0;
				String Bill_ID = panelBill.getBill_ID().getText();
				String Customer_ID = panelBill.getCustomer_ID().getText();
				String User_ID = panelBill.getUser_ID().getText();
				String Table_ID = panelBill.getTable_ID().getText();
				String Payment_ID = panelBill.getPayment_ID().getText();
				String Status = (String) panelBill.getStatus_item().getSelectedItem();
				String dateWork = panelBill.getDatetime().getText();
				
				// Lấy Bill 
                BillModel tmp = new BillModel();
				
				tmp.setStatus(Status);
				
				if(Customer_ID != null) {
					CustomerModel cusDao = customerDao.findByID(Customer_ID);
					tmp.setCustomer(cusDao);
					tmp.setCustomerID(cusDao.getID());
				}
				else {
					tmp.setCustomerID(0);
				}
				
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
				
				for(Entry<String, String> ele: ProductListTable.entrySet()) {
					SumPrice += Double.parseDouble(ele.getValue())* billDao.findProductByName(ele.getKey()).getPrice();
				}
				tmp.setBillTotal((float)SumPrice);
				if(ValidateUtils.checkEmptyAndNull(Bill_ID)) {
        			// them moi
            		
            		if(validateForm(tmp, messageError)) {
            			tmp.setID(billDao.findAll().getLast().getID()+1);
            			billDao.insert(tmp);
            			resetTable();
            			
            			// update table view
            			ArrayList<BillModel> AddrowData = billDao.findAll();	
            			Pagination(AddrowData);
            			renderTable(AllPageInformation.getFirst());
            			System.out.println(AllPageInformation.get(0).size());
            			//renderTable(billDao.findAll());            			
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
				// add BillDetails
				ArrayList<BillDetailsModel > BillDetaList = new ArrayList<BillDetailsModel>();
				ProductListTable.forEach((key,value)->{
					BillDetailsModel tmpBillDetails = new BillDetailsModel();
					tmpBillDetails.setQuantityProduct(Integer.parseInt(value));
					tmpBillDetails.setProduct(billDao.findProductByName(key));
					tmpBillDetails.setProductID(billDao.findProductByName(key).getID());
					BillDetaList.add(tmpBillDetails);
				});
				
				if(ValidateUtils.checkEmptyAndNull(Bill_ID)) {
        			for(int i=0;i<BillDetaList.size();i++) {
        				BillDetailsModel tmpDetails = BillDetaList.get(i);
        				if(validateFormBillDetails(tmpDetails, messageError)) {
                				tmpDetails.setID(billDetailsDao.findBillDetailsAll().getLast().getID()+1);
                				tmpDetails.setBill(tmp);
                				tmpDetails.setBillID(tmp.getID());
                    			billDetailsDao.insert(tmpDetails);         			
                		}else {
                			JOptionPane.showMessageDialog(panelBill, messageError.toString());
                		}
        			}
        		}else {
        			// chinh sua
        			for(int i=0;i<BillDetaList.size();i++) {
        				BillDetailsModel tmpDetails = BillDetaList.get(i);
        			//	tmp.setID(Integer.parseInt(Bill_ID));
        				if(validateFormBillDetails2(tmpDetails, messageError)) {
                				tmpDetails.setID(billDetailsDao.findBillDetailsAll().getLast().getID()+1);
                				tmpDetails.setBill(tmp);
                				tmpDetails.setBillID(tmp.getID());
                    			billDetailsDao.update(tmpDetails);
                    			//renderTable(billDetailsDao.findBillDetailsAll());           			
                		}else {
                			JOptionPane.showMessageDialog(panelBill, messageError.toString());
                		}
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
			res.append("Người dùng không tồn tại \n");
			return false;
		}
		if(!billDao.checkTable(bill.getTable().getID()+"")) {
			res.append("Bàn không tồn tại \n");
			return false;
		}
		return true;
		
	}
	
	public boolean validateFormBillDetails2(BillDetailsModel bill,StringBuilder res) {		
		if(ValidateUtils.checkEmptyAndNull(bill.getQuantityProduct()+"")) {
			res.append("Số lượng không được để trống\n");
			return false;
		}
		if(ValidateUtils.checkEmptyAndNull(bill.getProduct().getID()+"")) {
			res.append("ID sản phẩm không được để trống\n");
			return false;
		}
		return true;
	}
	public boolean validateFormBillDetails(BillDetailsModel bill,StringBuilder res) {			
		if(!billDao.checkProductName(bill.getProduct().getID()+"")) {
			res.append("Sản phẩm không tồn tại\\n");
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
	
	public void resetTable() {
		DefaultTableModel model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		String[] colName = {"BillID", "CustomerID", "UserID", "Date", "Price"};
		for(String x : colName) {
			model.addColumn(x);
		}
		model.setRowCount(0);
		panelBill.getTableBill().setModel(model);
	}
	
	public void addEventHeader() {
		// time run
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Runnable task = () -> {
            LocalDateTime current = LocalDateTime.now();
            String formatted = current.format(formatter);
            panelBill.getDatetime().setText(formatted);
        };
        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
       
        
        // set up product - danh sách sản phẩm 
        for(ProductModel item: productList) {
        	panelBill.getProducts_item().addItem(item.getName());
        }
        AutoCompleteDecorator.decorate(panelBill.getProducts_item());
        
        
	}
	
	public void Pagination(ArrayList<BillModel> rowData) {
		Collections.reverse(rowData);
		ArrayList<ArrayList<BillModel>> PageInformation = new ArrayList<>();
		
		int TableRowLength =  rowData.size();
		if(TableRowLength%PageSize == 0) {
			PageNumber = TableRowLength/PageSize;
		}else {
			PageNumber = TableRowLength/PageSize +1;
		}
		for(int i=0;i<PageNumber;i++) {
			ArrayList<BillModel> tmp = new ArrayList<BillModel>();
			PageInformation.add(tmp);
			for(int j=0;j<PageSize;j++) {
				if(i*PageSize+j < TableRowLength) {
					BillModel x = rowData.get(i*PageSize+j);
					PageInformation.get(i).add(x);
				}
				else break;
			}
		}
		AllPageInformation = PageInformation;
	}
	
	public void addPageButton() {
		panelBill.getPageFirst().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				renderTable(AllPageInformation.get(0));
				panelBill.getPage1().setText(String.valueOf(1));
				panelBill.getPage2().setText(String.valueOf(2));
				panelBill.getPage3().setText(String.valueOf(3));
			}
			
		});
		
		panelBill.getPage1().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int p1 = Integer.parseInt(panelBill.getPage1().getText());
				panelBill.getPage1().setBackground(Color.orange);
				panelBill.getPage2().setBackground(Color.white);
				panelBill.getPage3().setBackground(Color.white);
				if(p1 ==1) {
					renderTable(AllPageInformation.get(0));
				}
				else {
					if(AllPageInformation.size() < p1 ) {
						JOptionPane.showMessageDialog(panelBill, "Không có dữ liệu");
						resetTable();
					}
					else {
						renderTable(AllPageInformation.get(p1-1));
					}
				}
			}
		});
		
		panelBill.getPage2().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int p2 = Integer.parseInt(panelBill.getPage2().getText());
				panelBill.getPage1().setBackground(Color.white);
				panelBill.getPage2().setBackground(Color.orange);
				panelBill.getPage3().setBackground(Color.white);
				
				if(p2 == 2) {
					renderTable(AllPageInformation.get(1));
					
				}
				else {
					// 10 0-> 9
					if(AllPageInformation.size() < p2 ) {
						JOptionPane.showMessageDialog(panelBill, "Không có dữ liệu");
						resetTable();
					}
					else {
						renderTable(AllPageInformation.get(p2-1));
					}
				}
			}
		});
		
		panelBill.getPage3().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int p3 = Integer.parseInt(panelBill.getPage3().getText());
				panelBill.getPage1().setBackground(Color.white);
				panelBill.getPage2().setBackground(Color.white);
				panelBill.getPage3().setBackground(Color.orange);
				if(p3 == 3) {
					renderTable(AllPageInformation.get(2));
				}
				else {
					if(AllPageInformation.size() < p3 ) {
						JOptionPane.showMessageDialog(panelBill, "Không có dữ liệu");
						resetTable();
					}
					else {
						renderTable(AllPageInformation.get(p3-1));
					}
				}
			}
		});
		
		
		panelBill.getPageNext().addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				int p1 = Integer.parseInt(panelBill.getPage1().getText()); // lấy ra nhãn hiện tại
				int p2 = Integer.parseInt(panelBill.getPage2().getText());
				int p3 = Integer.parseInt(panelBill.getPage3().getText());
				
				String Background1 =  panelBill.getPage1().getBackground().toString();
				String Background2 =  panelBill.getPage2().getBackground().toString();
				String Background3 =  panelBill.getPage3().getBackground().toString();
			//	System.out.println(Background1+" 3"+Background2+" 3"+Background3);
			   
				int presentPage = 0 ;
				
			    if(Background1.equals("java.awt.Color[r=255,g=200,b=0]")) {
			    	presentPage = p1;
			    }
			    else if(Background2.equals("java.awt.Color[r=255,g=200,b=0]")) {
			    	presentPage = p2;
			    }
			    else if(Background3.equals("java.awt.Color[r=255,g=200,b=0]")) {
			    	presentPage = p3;
			    }
			    
			    // 3 next = 4
			   // System.out.println(presentPage);
				if(p3 >= PageNumber) { 
						panelBill.getPage1().setText(String.valueOf(PageNumber-2));
						panelBill.getPage2().setText(String.valueOf(PageNumber-1));
						panelBill.getPage3().setText(String.valueOf(PageNumber));
						if(presentPage==PageNumber) { // 10 - 10 vẫn phải cho data 11 - 10 mới in ra thông báo
							JOptionPane.showMessageDialog(panelBill, "Không có dữ liệu");
						}
						renderTable(AllPageInformation.get(presentPage));
						++presentPage;//9
						
					if(Background1.equals("java.awt.Color[r=255,g=200,b=0]")) {
						panelBill.getPage1().setBackground(Color.white);
				    	panelBill.getPage2().setBackground(Color.orange);
				    	panelBill.getPage3().setBackground(Color.white);
				    }
				    else if(Background2.equals("java.awt.Color[r=255,g=200,b=0]")) {
				    	panelBill.getPage1().setBackground(Color.white);
				    	panelBill.getPage2().setBackground(Color.white);
				    	panelBill.getPage3().setBackground(Color.orange);
				    }
				}
				else {
					p1+=1;
					panelBill.getPage1().setText(String.valueOf(p1));
					p2+=1;
					panelBill.getPage2().setText(String.valueOf(p2));
					p3+=1;
					panelBill.getPage3().setText(String.valueOf(p3));
					renderTable(AllPageInformation.get(presentPage));
				}
				
			}
		});
		
		// -2 -1 0 1
		panelBill.getPageBefore().addActionListener(new ActionListener() { // vẫn bị lùi âm
			@Override
			public void actionPerformed(ActionEvent e) {
				int p1 = Integer.parseInt(panelBill.getPage1().getText()); // lấy ra nhãn hiện tại
				int p2 = Integer.parseInt(panelBill.getPage2().getText());
				int p3 = Integer.parseInt(panelBill.getPage3().getText());
				
				String Background1 =  panelBill.getPage1().getBackground().toString();
				String Background2 =  panelBill.getPage2().getBackground().toString();
				String Background3 =  panelBill.getPage3().getBackground().toString();
			//	System.out.println(Background1+" 3"+Background2+" 3"+Background3);
			   
				int presentPage = 0 ;
				
			    if(Background1.equals("java.awt.Color[r=255,g=200,b=0]")) {
			    	presentPage = p1;
			    }
			    else if(Background2.equals("java.awt.Color[r=255,g=200,b=0]")) {
			    	presentPage = p2;
			    }
			    else if(Background3.equals("java.awt.Color[r=255,g=200,b=0]")) {
			    	presentPage = p3;
			    }
			    --presentPage; // phải giảm 1 trước để present = vị trí của mảng hiện t
			    // 3 next = 4
			    System.out.println(presentPage);
				if(p3 <=3) { 
						panelBill.getPage1().setText(String.valueOf(1));
						panelBill.getPage2().setText(String.valueOf(2));
						panelBill.getPage3().setText(String.valueOf(3));
						if(presentPage <1) { // 10 - 10 vẫn phải cho data 11 - 10 mới in ra thông báo
							JOptionPane.showMessageDialog(panelBill, "Không có dữ liệu");
						}
						--presentPage;
						renderTable(AllPageInformation.get(presentPage));
						;//9
						
					if(Background3.equals("java.awt.Color[r=255,g=200,b=0]")) {
						panelBill.getPage1().setBackground(Color.white);
				    	panelBill.getPage2().setBackground(Color.orange);
				    	panelBill.getPage3().setBackground(Color.white);
				    }
				    else if(Background2.equals("java.awt.Color[r=255,g=200,b=0]")) {
				    	panelBill.getPage1().setBackground(Color.orange);
				    	panelBill.getPage3().setBackground(Color.white);
				    	panelBill.getPage2().setBackground(Color.white);
				    }
				}
				else {
					p1-=1;
					panelBill.getPage1().setText(String.valueOf(p1));
					p2-=1;
					panelBill.getPage2().setText(String.valueOf(p2));
					p3-=1;
					panelBill.getPage3().setText(String.valueOf(p3));
					renderTable(AllPageInformation.get(--presentPage));
				}
			}
		});
	}
	
	/*
	public void addEventBody() {
		
	}
	*/
}
