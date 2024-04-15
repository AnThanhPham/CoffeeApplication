package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.Pageable;
import java.text.ParseException;
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
import javax.swing.JInternalFrame;
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

import com.mysql.cj.xdevapi.Table;

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
	private int ButtonPageNumber = 3;
	
	public PanelBillController(PanelBill panelBill) {
		this.panelBill = panelBill;
		ArrayList<BillModel> rowDataList = billDao.findAll();	
		Pagination(rowDataList);
		renderTable(AllPageInformation.getFirst());
		addEventHeader();
		addEvent();	
		addEventBody();
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
		
		
		// thêm sản phẩm vào giỏ hàng 
		LinkedHashMap<String, String> ProductListTable = new LinkedHashMap<String, String>();
		panelBill.getaddBillProduct().addActionListener(new ActionListener() {			
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
		
		// chỉnh sửa giỏ hàng
		panelBill.geteditBillProduct().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame FrameProduct = new JFrame();
				FrameProduct.setLayout(new FlowLayout());
				
				JScrollPane scrollPaneProduct = new JScrollPane();
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
				
				String dateWork = panelBill.getBill_Date().getText();
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
				
				
	           // System.out.println(dateWork);
				try {
        			if(!ValidateUtils.checkEmptyAndNull(dateWork)) {
        				SimpleDateFormat DateInput = new SimpleDateFormat("dd-MM-yyyy");
        	            SimpleDateFormat DateOutput = new SimpleDateFormat("yyyy-MM-dd");
        	            java.util.Date date = DateInput.parse(dateWork);
        	          ///  System.out.println(date);
        	            String outputParse = DateOutput.format(date);
        	            tmp.setBillDate(java.sql.Date.valueOf(outputParse));
        	          ///  System.out.println(outputParse);
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
			@Override
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
						Pagination(billDao.findAll());
						renderTable(AllPageInformation.get(0));
						resetInput();
					}
				}
			}
		});
		/*
		panelBill.getDetailsBill().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowSelect = panelBill.getTableBill().getSelectedRow();
				String id = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(rowSelect, 0));
        		String cusID = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(rowSelect, 1));
        		String userID = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(rowSelect, 2));
        		String date = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(rowSelect, 3));
        		String sumPrice = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(rowSelect, 4));
        		
				if(rowSelect!=-1) {
					BillModel tmpBill = billDao.findByID(id);
					ArrayList<BillDetailsModel> tmpBillDetail = billDetailsDao.findByBillID(id);
					//System.out.println(tmpBillDetail.size());
					JFrame FrameDetails = new JFrame();
					FrameDetails.set
					JDialog Details = new JDialog();
					Details.setLayout(new GridLayout(4,1));
					//Details.setSize(400, 600);
					Details.setLocationRelativeTo(null);
					JLabel rowBillId = new JLabel("Mã hóa đơn : "+id);
					Details.add(rowBillId);
					JLabel rowCusId = new JLabel("Mã Khách Hàng : "+cusID);
					Details.add(rowCusId);
					JLabel rowUserId = new JLabel("Mã Nhân Viên : "+userID);
					Details.add(rowUserId);
					JLabel rowDate = new JLabel("Ngày hóa đơn : "+date);
					Details.add(rowDate);
				    JLabel rowProductList = new JLabel("Sản Phẩm : ");
					Details.add(rowProductList);
					
					JScrollPane scrollPane = new JScrollPane(); 
					Details.add(scrollPane);

					JTable tableDetails = new JTable();
					scrollPane.setViewportView(tableDetails);
					
					DefaultTableModel DeftableDetails = new DefaultTableModel() {
					};
					String[] columnName = {"Tên sản phẩm","Số lượng","Thành Tiền"};
					for(String x: columnName) {
						DeftableDetails.addColumn(x);
						
					}
					
					for(BillDetailsModel x : tmpBillDetail) {
						Vector<String> rowdataDetails = new Vector<String>();
						rowdataDetails.add(x.getProduct().getName());
						rowdataDetails.add(x.getQuantityProduct()+"");
						rowdataDetails.add((x.getQuantityProduct()*x.getProduct().getPrice())+"");
						DeftableDetails.addRow(rowdataDetails);
					}
					tableDetails.setModel(DeftableDetails);
				
					
					Details.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(panelBill,"Bạn chưa chọn dữ liệu");
				}
			}
		});
		*/
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
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDateTime current = LocalDateTime.now();
        String formatted = current.format(formatter);
        panelBill.getBill_Date().setText(formatted);
	}
	public void EnableInput() {
		//panelBill.getBill_ID().setEnabled(true);
		panelBill.getCustomer_ID().setEnabled(true);
		panelBill.getUser_ID().setEnabled(true);
		panelBill.getQuantity().setEnabled(true);
		panelBill.getTable_ID().setEnabled(true);
		panelBill.getPayment_ID().setEnabled(true);	
		panelBill.getBill_Date().setEnabled(true);
	}
	public void DisableInput() {
		panelBill.getBill_ID().setEnabled(false);
		panelBill.getCustomer_ID().setEnabled(false);
		panelBill.getUser_ID().setEnabled(false);
		panelBill.getQuantity().setEnabled(false);
		panelBill.getTable_ID().setEnabled(false);
		panelBill.getPayment_ID().setEnabled(false);
		panelBill.getBill_Date().setEnabled(false);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        Runnable task = () -> {
            LocalDateTime current = LocalDateTime.now();
            String formatted = current.format(formatter);
            panelBill.getTodayDate().setText(formatted);
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
				renderButton();
			}
			
		});
		
		panelBill.getPage1().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int p1 = Integer.parseInt(panelBill.getPage1().getText());
				setColorPage1();
				renderTable(AllPageInformation.get(p1-1));
			}
		});
		
		panelBill.getPage2().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int p2 = Integer.parseInt(panelBill.getPage2().getText());
				setColorPage2();
				renderTable(AllPageInformation.get(p2-1));
			}
		});
		
		panelBill.getPage3().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int p3 = Integer.parseInt(panelBill.getPage3().getText());
				setColorPage3();
				renderTable(AllPageInformation.get(p3-1));
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
			   
				int CurrentPage = 0 ;
				
			    if(Background1.equals("java.awt.Color[r=255,g=200,b=0]")) {
			    	CurrentPage = p1;
			    }
			    else if(Background2.equals("java.awt.Color[r=255,g=200,b=0]")) {
			    	CurrentPage = p2;
			    }
			    else if(Background3.equals("java.awt.Color[r=255,g=200,b=0]")) {
			    	CurrentPage = p3;
			    }
			    
			    if(CurrentPage==PageNumber) {// 1 or 2
			    	if(CurrentPage==p1) {
			    		panelBill.getPage1().setText(p1+"");
			    		setColorPage1();
			    		renderTable(AllPageInformation.get(--CurrentPage));
			    	}
			    	else if (CurrentPage==p2){
			    		panelBill.getPage1().setText(p1+"");
			    		panelBill.getPage2().setText(p2+"");
			    		setColorPage2();
			    		renderTable(AllPageInformation.get(--CurrentPage));
			    	}
			    	JOptionPane.showMessageDialog(panelBill, "Không có dữ liệu");
			    }
			    
			    if(p3==PageNumber) {
			    	panelBill.getPage1().setText(p1+"");
			    	panelBill.getPage2().setText(p2+"");
			    	renderTable(AllPageInformation.get(p3-1));
			    	panelBill.getPage3().setText(p3+"");
			    	setColorPage3();
			    }
			    else {
			    	if(CurrentPage==p3 && CurrentPage != PageNumber) {
			    		p1+=ButtonPageNumber; //p1+=3
			    		p2+=ButtonPageNumber;
			    		p3+=ButtonPageNumber;
			    	    if(p2 > PageNumber ) {
			    	    	panelBill.getPage2().setVisible(false);
			    	    	panelBill.getPage3().setVisible(false);
			    	    }
			    	    else if(p2 == PageNumber && p3 > PageNumber) {
			    	    	panelBill.getPage3().setVisible(false);
			    	    }
			    	    panelBill.getPage1().setText(p1+"");
				    	panelBill.getPage2().setText(p2+"");
				    	renderTable(AllPageInformation.get(CurrentPage));
				    	panelBill.getPage3().setText(p3+"");
				    	setColorPage1();
			    	}
			    	
			    	if(CurrentPage ==p1) {
			    		renderTable(AllPageInformation.get(CurrentPage));
				    	setColorPage2();
			    	}
			    	if(CurrentPage ==p2) {
			    		renderTable(AllPageInformation.get(CurrentPage));
				    	setColorPage3();
			    	}
			    }
			}
		});
		
		panelBill.getPageBefore().addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				int p1 = Integer.parseInt(panelBill.getPage1().getText()); 
				int p2 = Integer.parseInt(panelBill.getPage2().getText());
				int p3 = Integer.parseInt(panelBill.getPage3().getText());
				
				String Background1 =  panelBill.getPage1().getBackground().toString();
				String Background2 =  panelBill.getPage2().getBackground().toString();
				String Background3 =  panelBill.getPage3().getBackground().toString();
			//	System.out.println(Background1+" 3"+Background2+" 3"+Background3);
			   
				int CurrentPage = 0 ;
				
			    if(Background1.equals("java.awt.Color[r=255,g=200,b=0]")) {
			    	CurrentPage = p1;
			    }
			    else if(Background2.equals("java.awt.Color[r=255,g=200,b=0]")) {
			    	CurrentPage = p2;
			    }
			    else if(Background3.equals("java.awt.Color[r=255,g=200,b=0]")) {
			    	CurrentPage = p3;
			    }
			    
			 // System.out.println(p1+" "+p2+" "+p3);
			    if(CurrentPage==1) {
			    	System.out.println(p1+" "+p2+" "+p3);
			    	renderButton();
			    	JOptionPane.showMessageDialog(panelBill, "Không có dữ liệu");
			    	
			    }
			    if(p1==PageNumber) {
			    	panelBill.getPage2().setVisible(true);
			    	panelBill.getPage3().setVisible(true);
			    	
			    	p1-=ButtonPageNumber;
			    	panelBill.getPage1().setText(p1+"");
			    	panelBill.getPage2().setText(p1+1+"");
			    	renderTable(AllPageInformation.get(p1+1));
			    	panelBill.getPage3().setText(p1+2+"");
			    	setColorPage3();
			    }
			    else {
			    	if(CurrentPage==p1 && CurrentPage != 1) {
			    		p1-=ButtonPageNumber;
				    	panelBill.getPage1().setText(p1+"");
			    		panelBill.getPage2().setText(p1+1+"");
			    		panelBill.getPage3().setText(p1+2+"");
			    		--CurrentPage;
			    		renderTable(AllPageInformation.get(--CurrentPage));
			    		setColorPage3();
			    	}
			    	
			    	if(CurrentPage==p2) {
			    		--CurrentPage;
			    		renderTable(AllPageInformation.get(--CurrentPage));
			    		setColorPage1();
				    	
			    	}
			    	
			    	if(CurrentPage==p3) {
			    		--CurrentPage;
			    		renderTable(AllPageInformation.get(--CurrentPage));
			    		setColorPage2();
			    	}
			    }
			}
		});
		
		
	}
	
	public void addEventBody() {
		panelBill.getFitter().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String day = panelBill.getFDay().getSelectedItem().toString();
				String  month = panelBill.getFMonth().getSelectedItem().toString();
				String  year = panelBill.getFYear().getSelectedItem().toString();
				String date = year+"-"+month+"-"+day;
				String id =  panelBill.getFindBillID().getText();
				
				if(day.equalsIgnoreCase("day") && month.equalsIgnoreCase("month")&& year.equalsIgnoreCase(year)) {
					if(!ValidateUtils.checkEmptyAndNull(id)) {
						ArrayList<BillModel> res = new ArrayList<BillModel>();
						
						for(BillModel x: billDao.findAll()) {
							String billID = String.valueOf(x.getID());
							if(billID.startsWith(id)) {
								res.add(x);
							}
						}
						if(res.size()==0) {
							JOptionPane.showMessageDialog(panelBill, "Không có dữ liệu mà bạn đang tìm");
						}
						sortdata(res);
					}
					else {
						sortdata(billDao.findAll());
					}
				}
				else {
					if(checkDate(day, month, year)) {
						if(!ValidateUtils.checkEmptyAndNull(id)) {
							ArrayList<BillModel> res = new ArrayList<BillModel>();
							
							for(BillModel x: billDao.findAll()) {
								String billID = String.valueOf(x.getID());
								String billdate = String.valueOf(x.getBillDate());
								if(billID.startsWith(id) && billdate.equals(date)) {
									res.add(x);
								}
								System.out.println(billdate+"  "+date);
							}
							if(res.size()==0) {
								JOptionPane.showMessageDialog(panelBill, "Không có dữ liệu mà bạn đang tìm");
								resetTable();
							}
							sortdata(res);
						}
						else {
                            ArrayList<BillModel> res = new ArrayList<BillModel>();
							
							for(BillModel x: billDao.findAll()) {
								String billdate = String.valueOf(x.getBillDate());
								if(billdate.equals(date)) {
									res.add(x);
								}
							//	System.out.println(billdate+"  "+date);
							}
							if(res.size()==0) {
								JOptionPane.showMessageDialog(panelBill, "Không có dữ liệu mà bạn đang tìm");
								resetTable();
							}
							sortdata(res);
						}
					}
					else {
							JOptionPane.showMessageDialog(panelBill, "Ngày tháng không hợp lệ");
					}
				}
			}
		});
		
	   panelBill.getRefreshFitter().addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			panelBill.getFDay().setSelectedIndex(0);
			panelBill.getFMonth().setSelectedIndex(0);
			panelBill.getFYear().setSelectedIndex(0);
			panelBill.getFindBillID().setText("");
			panelBill.getSort().setSelectedIndex(0);
			sortdata(billDao.findAll());
			Pagination(billDao.findAll());
			renderTable(AllPageInformation.get(0));
		  }
	    });
	}
	
	
	
	public boolean checkDate(String day, String month, String year) {
		int d = Integer.parseInt(day);
		int m = Integer.parseInt(month);
		int y = Integer.parseInt(year);
		
		if(y %4 ==0 || y %100 ==0) {
			if(m ==2 && d <=29) {
				return true;
			}
		}
		else {
			if(m ==2 && d <=28) {
				return true;
			}
		}
		
		if(m==4 || m==6 || m==9 || m==11 ) {
			if(d>=1 && d <=30)
			    return true;
		}
		if(m==1 || m==3 || m==5 || m==7 || m== 8 || m==10 || m ==12) {
			if(d>=1  && d <=31)
			    return true;
		}
			return false;
	}
	
	public void renderButton() {
		panelBill.getPage1().setText("1");
		panelBill.getPage2().setText("2");
		panelBill.getPage3().setText("3");
		panelBill.getPage1().setBackground(Color.orange);
		panelBill.getPage2().setBackground(Color.white);
		panelBill.getPage3().setBackground(Color.white);		
	}
	public void setColorPage1() {
		panelBill.getPage1().setBackground(Color.orange);
		panelBill.getPage2().setBackground(Color.white);
		panelBill.getPage3().setBackground(Color.white);
	}
	public void setColorPage2() {
		panelBill.getPage1().setBackground(Color.white);
		panelBill.getPage2().setBackground(Color.orange);
		panelBill.getPage3().setBackground(Color.white);
	}
	public void setColorPage3() {
		panelBill.getPage1().setBackground(Color.white);
		panelBill.getPage2().setBackground(Color.white);
		panelBill.getPage3().setBackground(Color.orange);
	}
	public void sortdata(ArrayList<BillModel> rowdata) {
		if(panelBill.getSort().getSelectedIndex()==1){
			ArrayList<BillModel> tmp = rowdata;
			Collections.reverse(tmp);
			Pagination(tmp);
       }
		else if(panelBill.getSort().getSelectedIndex()==0) {
			Pagination(rowdata);
		}
	//	else if(panelBill.gétS)
		if(AllPageInformation.size() ==1) {
			panelBill.getPage2().setVisible(false);
			panelBill.getPage3().setVisible(false);
		}
		else if(AllPageInformation.size() ==2) {
			panelBill.getPage2().setVisible(true);
			panelBill.getPage3().setVisible(false);
		}
		else {
			panelBill.getPage2().setVisible(true);
			panelBill.getPage3().setVisible(true);
		}
		renderTable(AllPageInformation.get(0));
		renderButton();
	}
}
