package controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.sql.Date;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.BillDAO;
import dao.BillDetailsDAO;
import dao.CustomerDao;
import dao.CategoryDAO;
import dao.PaymentDAO;
import dao.TableDAO;
import dao.UserDAO;
import model.BillDetailsModel;
import model.BillModel;
import model.CategoryModel;
import model.CustomerModel;
import model.PaymentModel;
import model.ProductModel;
import model.TableModel;
import model.UserModel;
import util.MapUtil;
import util.ValidateUtils;
import views.menu.PanelBill;

public class PanelBillController {
	private PanelBill panelBill;
	private BillDAO billDao = new BillDAO();
	private BillDetailsDAO billDetailsDao = new BillDetailsDAO();
	private CustomerDao customerDao = new CustomerDao();
	private CategoryDAO categoryDao = new CategoryDAO();
	private ArrayList<ArrayList<BillModel>> AllPageInformation = new ArrayList<>();
	private UserDAO userDao = new UserDAO();
	private int PageSize =5 ;
	private int PageNumber;
	private int ButtonPageNumber = 3;
	private LinkedHashMap<String, String> cartList = new LinkedHashMap<String, String>(); 
	
	public PanelBillController(PanelBill panelBill) {
		this.panelBill = panelBill;
		UpdateComboboxHeader();
		updatePrice();
		ArrayList<BillModel> rowDataList = billDao.findAll();	
		Pagination(rowDataList);
		renderTable(AllPageInformation.get(0));
		addEventHeader();
		addEvent();	
		FindID();
		addEventBody();
		addPageButton();
	}
	public void UpdateComboboxHeader() {
		
		panelBill.getUser_Name().addItem("Chọn nhân viên");
		for(UserModel x: billDao.findUserByRoleID("2")){
			panelBill.getUser_Name().addItem(x.getUserName());
		}
		
		for(PaymentModel x: billDao.findAllPayment()) {
			panelBill.getPayment_Name().addItem(x.getPaymentName());
		}
		
		panelBill.getStatus_item().addItem("Chọn Trạng Thái");
		LinkedHashSet<String> status = new LinkedHashSet<String>();
		for(BillModel x: billDao.findAll()) {
			status.add(x.getStatus());
		}
		for(String x: status) {
			panelBill.getStatus_item().addItem(x);
		}
		
		for(TableModel x: billDao.findTableByStatus("Available")) {
			panelBill.getTable_Number().addItem(x.getTableNumber());
		}
		
		
	}
	public void renderTable(ArrayList<BillModel> rowData) {
		DefaultTableModel model = new DefaultTableModel() {
			public boolean 	isCellEditable(int row, int column) {
				return false;
			}
		}; 
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
			row.add(x.getBillTotal()+"");
		    model.addRow(row);
		}
		panelBill.getTableBill().setModel(model);
	}
	
	public void updatePrice() {
		for(BillModel x: billDao.findAll()) {
			ArrayList<BillDetailsModel> res = billDetailsDao.findByBillID(x.getID()+"");
			float sum =0;
					for(BillDetailsModel tmp: res) {
						sum+=tmp.getQuantityProduct()*tmp.getProduct().getPrice();
					}
				billDao.changePrice(sum, x.getID());
		}
	}
	
	public void addEvent() {
		//private Date utilDate;
		DisableInput();
		
		panelBill.getTableBill().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
		public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				 
				int rowSelect = panelBill.getTableBill().getSelectedRow();
	        	if(rowSelect != -1) {
	        		
	        		String id = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(rowSelect, 0));
	        		String CusPhone = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(rowSelect, 1));
	        		String UserID = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(rowSelect, 2));
	        		String DateTime  = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(rowSelect, 3));
	        		
	             	BillModel bill = billDao.findByID(id);
	        		panelBill.getBill_ID().setText(id);
	        		panelBill.getBill_Date().setText(DateTime);
	        		panelBill.getCustomer_Phone().setText(bill.getCustomer().getPhone());
	        		panelBill.getUser_Name().setSelectedItem(bill.getUser().getUserName());
	        		panelBill.getPayment_Name().setSelectedItem(bill.getPayment().getPaymentName());
	        		
	        		panelBill.getTable_Number().removeAllItems();
	        		for(TableModel x: billDao.findTableAll()) {
	        			panelBill.getTable_Number().addItem(x.getTableNumber());
	        			//System.out.println(x.getTableNumber());
	        		}
	        		
	        		panelBill.getTable_Number().setSelectedItem(bill.getTable().getTableNumber());
	        		
	        		panelBill.getStatus_item().setSelectedItem(bill.getStatus());
	        	}
	        	
			}
		});
		
		
		panelBill.getCart().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				 int rowSelect = panelBill.getTableBill().getSelectedRow();
				 
				 JFrame frameCart = new JFrame();
				 frameCart.setLayout(new FlowLayout());
			     
				 JLabel CategoryLabel = new JLabel("                                          Loại sản phẩm ");
				 JComboBox<String> CategoryList = new JComboBox<String>();
				 CategoryList.addItem("Chọn loại sản phẩm ");
				 for(CategoryModel item: categoryDao.CategoryList()) {
					 CategoryList.addItem(item.getCategoryName());
			        }
				 JLabel white = new JLabel("                              ");
				 JLabel productLabel = new JLabel("Tên sản phẩm ");
				 JComboBox<String> productList = new JComboBox<String>();
				 productList.addItem("Chọn 1 sản phẩm ");
				 
				 for(ProductModel item: billDao.findProductAll()) 
					    productList.addItem(item.getName());
				 
				 CategoryList.addActionListener(new ActionListener() {
					    @Override
					    public void actionPerformed(ActionEvent e) {
					        productList.removeAllItems();       
					        int idx= CategoryList.getSelectedIndex();
					        productList.addItem("Chọn 1 sản phẩm ");
					        if ( idx == 0) {
					        	for (ProductModel item : billDao.findProductAll()) {
					                productList.addItem(item.getName());
					            }
					            
					        } else {
					            for (ProductModel item : billDao.findProductByCategoryId(idx+"")) {
					                productList.addItem(item.getName());
					            }
					        }
					    }
					});

				 JLabel QuantityLabel = new JLabel("Số lượng ");
				 JTextField QuantityProduct = new JTextField(10);
 				 JButton saveData = new JButton("Thêm vào giỏ hàng");
				 frameCart.add(CategoryLabel);
				 frameCart.add(CategoryList);
				 frameCart.add(white);
				 frameCart.add(productLabel);
				 frameCart.add(productList);
				 frameCart.add(QuantityLabel);
				 frameCart.add(QuantityProduct);
				 frameCart.add(saveData);
					
				 JScrollPane scrollPaneCart = new JScrollPane();
					frameCart.add(scrollPaneCart);
					
					
					JTable tableCart = new JTable();
					scrollPaneCart.setViewportView(tableCart);
					tableCart.setRowHeight(20);
					
					DefaultTableModel model = new DefaultTableModel();
					//String[] colName = {"ProductName","","Quantity","",""};
					String[] colName = {"ProductName","Quantity",};
					for(String x : colName) {
						model.addColumn(x);
					}
					 tableCart.setModel(model);
					 
				 if(rowSelect !=-1) {
					 
					 String id = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(rowSelect, 0));
					 ArrayList<BillDetailsModel>  billdet = billDetailsDao.findByBillID(id);
					 for(BillDetailsModel x: billdet) {
						 cartList.put(x.getProduct().getName(), x.getQuantityProduct()+"");
					 }
				 }
				 else {
					 }
					
					
					saveData.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String ProductName =  productList.getSelectedItem().toString();
						//	System.out.println(productIdx);
								if( productList.getSelectedIndex() != 0 && (QuantityProduct.getText() !="" && Integer.parseInt(QuantityProduct.getText()) !=0)) {
									Integer Quantity = Integer.parseInt(QuantityProduct.getText());
									
									if(cartList.containsKey(ProductName)) {
										Integer QuantityUpdate = Integer.parseInt(cartList.get(ProductName)) + Quantity;
										cartList.replace(ProductName,QuantityUpdate  +"");
									}
									else 	{
										cartList.put(ProductName,Quantity+"");
									}
									model.setRowCount(0);
									for(Entry<String, String> x : cartList.entrySet()) {
										// model.addRow(new Object[] { x.getKey(),"+",x.getValue(),"-","delete"});
										 model.addRow(new Object[] { x.getKey(),x.getValue()});
									}
									
								}
								else {
									JOptionPane.showMessageDialog(panelBill, "Sản phẩm hoặc số lượng không hợp lệ");
								}
						}
					});  
					for(Entry<String, String> x : cartList.entrySet()) {
						// model.addRow(new Object[] { x.getKey(),"+",x.getValue(),"-","delete"});
						 model.addRow(new Object[] { x.getKey(),x.getValue()});
					}
				 frameCart.setSize(500, 500);
				frameCart.setLocationRelativeTo(null);
				frameCart.setVisible(true);
				
			}
		});
		
		panelBill.getAddBill().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				EnableInput();
				resetInput();
				panelBill.getTableBill().clearSelection();
				cartList.clear();
				
				panelBill.getTable_Number().removeAllItems();
				//panelBill.getTable_Number().addItem("Chọn số bàn");
				for(TableModel x: billDao.findTableByStatus("Available")) {
					panelBill.getTable_Number().addItem(x.getTableNumber());
				}
			}
		});

		
		panelBill.getCheckNumberCustomer().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String Customer_Phone = panelBill.getCustomer_Phone().getText();
				
				if(!ValidateUtils.checkEmptyAndNull(Customer_Phone)) {
					if(billDao.findCusByPhone(Customer_Phone).getID() != 0) {
						Integer cusID = billDao.findCusByPhone(Customer_Phone).getID();
						CustomerModel cusDao = customerDao.findByID(cusID+"");
						JOptionPane.showMessageDialog(panelBill, "Khách Hàng đã tồn tại");
					}
					else {
						//System.out.println(2222);
						JFrame cusFrame= new JFrame();
						cusFrame.setLayout(new FlowLayout());
						//JPanel cusPanel = new JPanel();
						//cusPanel.setLayout(new GridLayout(4,2,5,2));
						
						JLabel CusNameLabel = new JLabel("       Tên khách Hàng    ");
					    JTextField CusName = new JTextField(20);
					    JLabel CheckCusNameLabel = new JLabel("                                                                                     ");
					    CheckCusNameLabel.setForeground(Color.red);
					    
					    JLabel CusPhoneLabel = new JLabel("             Sđt khách Hàng");
					    JTextField CusPhone = new JTextField(20);
					    CusPhone.setText(Customer_Phone);
					    JLabel CheckCusPhoneLabel = new JLabel("                                                                                             ");
					    CheckCusPhoneLabel.setForeground(Color.red);
					    
					    JLabel CusAddressLabel = new JLabel("     Địa Chỉ khách Hàng");
					    JTextField CusAddress = new JTextField(20); 
					    JLabel CheckCusAddressLabel = new JLabel("                                                                                                    ");
					    CheckCusAddressLabel.setForeground(Color.red);
					    
					    JLabel CusEmailLabel = new JLabel("     Email khách Hàng   ");
					    JTextField CusEmail = new JTextField(20);
					    JLabel CheckCusEmailLabel = new JLabel("                                                                                                      ");
					    CheckCusEmailLabel.setForeground(Color.red);
					    
					    JButton saveCus = new JButton("Thêm Thông Tin Khách Hàng");
					    
					    cusFrame.add(CusNameLabel);
					    cusFrame.add(CusName);
					    cusFrame.add(CheckCusNameLabel);
					    
					    cusFrame.add(CusPhoneLabel);
					    cusFrame.add(CusPhone);
					    cusFrame.add(CheckCusPhoneLabel);
					    
					    cusFrame.add(CusAddressLabel);
					    cusFrame.add(CusAddress);
					    cusFrame.add(CheckCusAddressLabel);
					    
					    cusFrame.add(CusEmailLabel);
					    cusFrame.add(CusEmail);
					    cusFrame.add(CheckCusEmailLabel);
					    
					    cusFrame.add(saveCus);
					    
					    saveCus.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								CustomerModel Custmp = new CustomerModel();
								String Cus_Name = CusName.getText();
								String Cus_Phone = CusPhone.getText();
								String Cus_Address = CusAddress.getText();
								String Cus_Email = CusEmail.getText();
								
								Custmp.setID(customerDao.CustomerList().get(customerDao.CustomerList().size()-1).getID()+1);
					
								
								if(!ValidateUtils.checkEmptyAndNull(Cus_Email)) {
									if(ValidateUtils.checkEmail(Cus_Email)) {
										Custmp.setEmail(Cus_Email);
										CheckCusEmailLabel.setText("");
										
										if(!ValidateUtils.checkEmptyAndNull(Cus_Name)) {
											String editname =  EditCustomerName(Cus_Name);
											Custmp.setName(editname);
											CheckCusNameLabel.setText("");
											
											if(!ValidateUtils.checkEmptyAndNull(Cus_Phone)) {
												if(isPhoneNumberValid(Customer_Phone)) {
													Custmp.setPhone(Cus_Phone);
													CheckCusPhoneLabel.setText("");
													
													if(!ValidateUtils.checkEmptyAndNull(Cus_Address)) {
														String editAddress =  EditCustomerName(Cus_Address);
														Custmp.setAddress(editAddress);
														CheckCusAddressLabel.setText("");
														//System.out.println(editname);
														//System.out.println(Cus_Phone);
														//System.out.println(editAddress);
														//System.out.println(Cus_Email);
														
														customerDao.insert(Custmp);
														if(customerDao.findByID(Custmp.getID()+"").getID() !=0) {
															JOptionPane.showMessageDialog(panelBill, "Lưu thông tin khách hàng thành công");
															
															CheckCusPhoneLabel.setText("");
															CheckCusAddressLabel.setText("");
															CheckCusEmailLabel.setText("");
															CheckCusNameLabel.setText("");
														}
														else JOptionPane.showMessageDialog(panelBill, "Không thể lưu thông tin khách hàng");
													}else CheckCusAddressLabel.setText("              Địa chỉ không được để trống                                                            ");
													
												}
													
												else  CheckCusPhoneLabel.setText("           Sđt không hợp lệ                                                            ");
											}else CheckCusPhoneLabel.setText("            Sđt không được để trống                                                            ");
										} else CheckCusNameLabel.setText("            Tên không được để trống                                                            ");						
									}
									else CheckCusEmailLabel.setText("            Email không hợp lệ                                                            ");
								}
								else CheckCusEmailLabel.setText("                Email không được để trống                                                            ");
								
								
								
							}
						});
						cusFrame.setSize(400, 400);
						cusFrame.setLocationRelativeTo(null);
						cusFrame.setVisible(true);
					}
				}
				else {
					JOptionPane.showMessageDialog(panelBill, "Số điện thoại khách hàng không được để trống");
				}
			}
		});
		panelBill.getSaveBill().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuilder messageError = new StringBuilder("");
				float SumPrice = 0;
				String Bill_ID = panelBill.getBill_ID().getText();
				String Customer_Phone = panelBill.getCustomer_Phone().getText();
				String User_Name = panelBill.getUser_Name().getSelectedItem().toString();
				String Table_Number = panelBill.getTable_Number().getSelectedItem().toString();
				String Payment_Name = panelBill.getPayment_Name().getSelectedItem().toString();
				String Status = (String) panelBill.getStatus_item().getSelectedItem();
				
				String dateWork = panelBill.getBill_Date().getText();
				// Lấy Bill 
                BillModel tmp = new BillModel();
				
				tmp.setStatus(Status);
				
				CustomerModel cusDao = billDao.findCusByPhone(Customer_Phone);
				tmp.setCustomer(cusDao);
				tmp.setCustomerID(cusDao.getID());
				
				TableModel tablee = billDao.findTableByNumber(Table_Number);
				tmp.setTable(tablee);
				tmp.setTableID(tablee.getID());
				
				UserModel user = billDao.findUserByUserName(User_Name);
				tmp.setUser(user);
				tmp.setUserID(user.getID());
				
				PaymentModel payment = billDao.findPaymentByName(Payment_Name);
				tmp.setPayment(payment);
				tmp.setPaymentID(payment.getID());
				
				
	           // System.out.println(dateWork);
				try {
        			if(!ValidateUtils.checkEmptyAndNull(dateWork)) {
        				SimpleDateFormat DateInput = new SimpleDateFormat("dd-MM-yyyy");
        	            SimpleDateFormat DateOutput = new SimpleDateFormat("yyyy-MM-dd");
        	            java.util.Date date = DateInput.parse(dateWork);
        	        
        	            String outputParse = DateOutput.format(date);
        	            tmp.setBillDate(java.sql.Date.valueOf(outputParse));
        	        
        			}
        			else tmp.setBillDate(null);
				} catch (Exception e1) {
					e1.printStackTrace();
				}   
				
				// add BillDetails
				
				ArrayList<BillDetailsModel > BillDetaList = new ArrayList<BillDetailsModel>();
				cartList.forEach((key,value)->{
					BillDetailsModel tmpBillDetails = new BillDetailsModel();
					tmpBillDetails.setQuantityProduct(Integer.parseInt(value));
					tmpBillDetails.setProduct(billDao.findProductByName(key));
					tmpBillDetails.setProductID(billDao.findProductByName(key).getID());
					BillDetaList.add(tmpBillDetails);
				});

				
				if(ValidateUtils.checkEmptyAndNull(Bill_ID)) {
        			// them moi
					int nextID = billDao.findAll().get(billDao.findAll().size()-1).getID()+1;
					//System.out.println(nextID);
            		if(validateForm(tmp, messageError)) {
            			tmp.setID(nextID);
            			if(cartList.size() ==0 )
    					    JOptionPane.showMessageDialog(panelBill, "Hóa đơn chưa có sản phẩm ");
    					else
    					{
            			billDao.insert(tmp);
            			
            			for(int i=0;i<BillDetaList.size();i++) {
            				if(validateFormBillDetails(BillDetaList.get(i), messageError)) {
            					int nextDetaID = billDetailsDao.findBillDetailsAll().get(billDetailsDao.findBillDetailsAll().size()-1).getID()+1;
            					BillDetaList.get(i).setID(nextDetaID);
            					BillDetaList.get(i).setBill(tmp); 
            					BillDetaList.get(i).setBillID(nextID);
            					if(cartList.size() ==0 )
            					    JOptionPane.showMessageDialog(panelBill, "Hóa đơn chưa có sản phẩm ");
            					else billDetailsDao.insert(BillDetaList.get(i));
                    			SumPrice += BillDetaList.get(i).getProduct().getPrice()*BillDetaList.get(i).getQuantityProduct();
                    		//	System.out.println(BillDetaList.get(i).getBill().getID() +"   "+BillDetaList.get(i).getBillID());
                    			
                    		}else {
                    			JOptionPane.showMessageDialog(panelBill, messageError.toString());
                    		}
            			}
    					tmp.setBillTotal(SumPrice);
            			billDao.changePrice(SumPrice,nextID);
            			Pagination(billDao.findAll());
        				renderTable(AllPageInformation.get(0));
    					}		
            		}else {
            			JOptionPane.showMessageDialog(panelBill, messageError.toString());
            		}
        		}else {
        			// chinh sua
        			for(BillDetailsModel x: billDetailsDao.findByBillID(Bill_ID)) {
        				billDetailsDao.delete(x);
        			}
        			for(int i=0;i<BillDetaList.size();i++) {
        				if(validateFormBillDetails2(BillDetaList.get(i), messageError)) {
        					int nextDetaID = billDetailsDao.findBillDetailsAll().get(billDetailsDao.findBillDetailsAll().size()-1).getID()+1;
        					BillDetaList.get(i).setID(nextDetaID);
        					BillDetaList.get(i).setBill(tmp);
        					BillDetaList.get(i).setBillID(Integer.parseInt(Bill_ID));
        					if(cartList.size() ==0 )
        					    JOptionPane.showMessageDialog(panelBill, "Hóa đơn chưa có sản phẩm ");
        					else billDetailsDao.insert(BillDetaList.get(i));
                			SumPrice += BillDetaList.get(i).getProduct().getPrice()*BillDetaList.get(i).getQuantityProduct();
                			//System.out.println("222222222211");
                    		       			
                		}else {
                			JOptionPane.showMessageDialog(panelBill, messageError.toString());
                		}
        			}
        			tmp.setBillTotal(SumPrice);
        			//System.out.println(SumPrice+"         d");
        			if(validateForm2(tmp, messageError)) {
        				tmp.setID(Integer.parseInt(Bill_ID));
        				if(cartList.size() ==0 )
    					    JOptionPane.showMessageDialog(panelBill, "Hóa đơn chưa có sản phẩm ");
    					else {
    						billDao.update(tmp);
        				    billDao.changePrice(SumPrice,Integer.parseInt(Bill_ID));
    					}
        			}else {
        				JOptionPane.showMessageDialog(panelBill, messageError.toString());
        			}
        		}
				
				
				
			
			}
		});
		
		panelBill.getEditBill().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowSelect = panelBill.getTableBill().getSelectedRow();
				if(rowSelect != -1) {
					EnableInput();	
					panelBill.getCustomer_Phone().setEnabled(false);
					panelBill.getCheckNumberCustomer().setEnabled(false);
					cartList.clear();
					String id = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(rowSelect, 0));
					for(BillDetailsModel x: billDetailsDao.findByBillID(id)) {
						cartList.put(x.getProduct().getName(), x.getQuantityProduct()+"");
					}
				}
				else JOptionPane.showMessageDialog(panelBill,"Bạn chưa chọn hóa đơn để chỉnh sửa");
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
						renderButton();
					}
				}
			}
			
		});
		
		panelBill.getDetailsBill().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int rowSelect = panelBill.getTableBill().getSelectedRow();
        			if(rowSelect!=-1) {
        				String id = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(rowSelect, 0));
                		String cusID = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(rowSelect, 1));
                		String userID = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(rowSelect, 2));
                		String date = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(rowSelect, 3));
                		String sumPrice = MapUtil.convertObjectToString(panelBill.getTableBill().getValueAt(rowSelect, 4));
                		
                		
    					ArrayList<BillDetailsModel> tmpBillDetail = billDetailsDao.findByBillID(id);
    					
    					
    					JDialog dialogBill = new JDialog();
    					dialogBill.setLayout(new BorderLayout());
    					JPanel panelBillHeader = new JPanel();
    					panelBillHeader.setLayout(new GridLayout(13,1));
    					JLabel white = new JLabel();
    				    panelBillHeader.add( white);
    				    
    					JLabel rowBillId = new JLabel("    Mã hóa đơn : "+id);
    					panelBillHeader.add(rowBillId);
    					JLabel white1 = new JLabel();
    				    panelBillHeader.add( white1);
    				    
    					JLabel rowCusId = new JLabel("    Tên Khách Hàng : "+customerDao.findByID(cusID).getName());
    					panelBillHeader.add(rowCusId);
    					JLabel white2 = new JLabel();
    				    panelBillHeader.add( white2);
    				    
    					JLabel rowUserId = new JLabel("    Tên Nhân Viên : "+userDao.findByID(userID).getUserName());
    					panelBillHeader.add(rowUserId);
    					JLabel white3 = new JLabel();
    				    panelBillHeader.add( white3);
    				    
    					JLabel rowDate = new JLabel("    Ngày hóa đơn : "+date);
    					panelBillHeader.add(rowDate);
    					JLabel white4 = new JLabel();
    				    panelBillHeader.add( white4);
    				    
    				    JLabel rowPrice = new JLabel("    Giá trị hóa đơn : "+ sumPrice);
    				    panelBillHeader.add(rowPrice);
    				    JLabel white5 = new JLabel();
    				    panelBillHeader.add( white5);
    				    
    				    JLabel cart = new JLabel("     Danh sách mặt hàng : ");
    				    panelBillHeader.add(cart);
    				    JLabel white6 = new JLabel();
    				    panelBillHeader.add( white6);
    					dialogBill.add(panelBillHeader,BorderLayout.NORTH);
    					
    					DefaultTableModel model =  new DefaultTableModel() {};
    					String column[] = {"Hàng hóa", "Số Lượng","Đơn giá", "Thành tiền"};
    					for(String x: column) {
    						model.addColumn(x);
    					}
    					
    					JScrollPane scrollPaneCart = new JScrollPane();
    					dialogBill.add(scrollPaneCart,BorderLayout.CENTER);
    					
    					JTable tableCart = new JTable();
    					scrollPaneCart.setViewportView(tableCart);
    					tableCart.setRowHeight(20);
    					
    					 tableCart.setModel(model);
    					 
    					 for(BillDetailsModel x: tmpBillDetail) {
    						 Vector<String> detaData = new Vector<String>();
    						 detaData.add(x.getProduct().getName());
    						 detaData.add(x.getQuantityProduct()+"");
    						 detaData.add(x.getProduct().getPrice()+"");
    						 detaData.add(x.getProduct().getPrice()*x.getQuantityProduct()+"");
    						 model.addRow(detaData);
    					 }
    					dialogBill.setSize(500, 700);
    					dialogBill.setLocationRelativeTo(null);
    					dialogBill.setVisible(true);
    				}
        			else JOptionPane.showMessageDialog(panelBill,"Bạn chưa chọn hóa đơn để xem chi tiết");
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
		panelBill.getCustomer_Phone().setText("");
		panelBill.getUser_Name().setSelectedIndex(0);
		panelBill.getTable_Number().setSelectedIndex(0);
		panelBill.getPayment_Name().setSelectedIndex(0);
		panelBill.getStatus_item().setSelectedIndex(0);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDateTime current = LocalDateTime.now();
        String formatted = current.format(formatter);
        panelBill.getBill_Date().setText(formatted);
	}
	public void EnableInput() {
		//panelBill.getBill_ID().setEnabled(true);
		panelBill.getCustomer_Phone().setEnabled(true);
		panelBill.getUser_Name().setEnabled(true);
		panelBill.getTable_Number().setEnabled(true);
		panelBill.getPayment_Name().setEnabled(true);	
		panelBill.getBill_Date().setEnabled(true);
		panelBill.getStatus_item().setEnabled(true);
		panelBill.getCheckNumberCustomer().setEnabled(true);
	}
	public void DisableInput() {
		panelBill.getBill_ID().setEnabled(false);
		panelBill.getCustomer_Phone().setEnabled(false);
		panelBill.getUser_Name().setEnabled(false);
		panelBill.getTable_Number().setEnabled(false);
		panelBill.getPayment_Name().setEnabled(false);
		panelBill.getBill_Date().setEnabled(false);
		panelBill.getStatus_item().setEnabled(false);
		panelBill.getCheckNumberCustomer().setEnabled(false);
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
		if(PageNumber==1) {
			panelBill.getPage2().setVisible(false);
			panelBill.getPage3().setVisible(false);
		}
		else if(PageNumber==2) {
			panelBill.getPage2().setVisible(true);
			panelBill.getPage3().setVisible(false);
		}
		else {
			panelBill.getPage3().setVisible(true);
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
			    	panelBill.getPage3().setText(p3+"");
			    	if(CurrentPage ==p1) {
			    		renderTable(AllPageInformation.get(CurrentPage));
				    	setColorPage2();
			    	}
			    	if(CurrentPage ==p2) {
			    		renderTable(AllPageInformation.get(CurrentPage));
				    	setColorPage3();
			    	}
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
			    	//System.out.println(p1+" "+p2+" "+p3);
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
	public void FindID() {
		panelBill.getFindBillID().addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				String id =  panelBill.getFindBillID().getText();
				
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
					Pagination(res);
					renderTable(AllPageInformation.get(0));
			   }
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
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
							//	System.out.println(billdate+"  "+date);
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
		// tăng dan theo ngay
		else if(panelBill.getSort().getSelectedIndex()==2) {
			ArrayList<BillModel> tmp = rowdata;
			Collections.sort(tmp, new Comparator<BillModel>() {
				@Override
				public int compare(BillModel o1, BillModel o2) {
					// TODO Auto-generated method stub
					return o1.getBillDate().compareTo(o2.getBillDate());
				}
	        });
			Pagination(tmp);
		}
		// giam dan
		else if(panelBill.getSort().getSelectedIndex()==3) {
			ArrayList<BillModel> tmp = rowdata;
			Collections.sort(tmp, new Comparator<BillModel>() {
				@Override
				public int compare(BillModel o1, BillModel o2) {
					// TODO Auto-generated method stub
					return o2.getBillDate().compareTo(o1.getBillDate());
				}
	        });
			Pagination(tmp);
		}
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
	
	public String EditCustomerName(String name) {
		String NameList[] = name.split(" ");
		String res ="";
		for(String x: NameList) {
			String head = x.substring(0,1);
			
			String tail = x.substring(1);
			
			res+= head.toUpperCase() + tail.toLowerCase() +" ";
		}
		return res.trim();
	}
	
	public boolean isPhoneNumberValid(String phoneNumber) {
		  // Biểu thức chính quy để kiểm tra số điện thoại Việt Nam
		  String regex = "(\\+84|0)[3|5|7|8|9]{1}(-|\\s)?\\d{3}(-|\\s)?\\d{3}(-|\\s)?\\d{2}";

		  // Khởi tạo đối tượng Matcher
		  Matcher matcher = Pattern.compile(regex).matcher(phoneNumber);

		  // Trả về kết quả kiểm tra
		  return matcher.matches();
		}
}

