package controller;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
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
import java.awt.Dialog;

import dao.BillDAO;
import dao.BillDetailsDAO;
import dao.CategoryDAO;
import dao.CustomerDao;
import dao.PanelDAO;
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
//import model.PanelModel;
import util.MapUtil;
import util.ValidateUtils;
import views.menu.PanelBill;
import views.menu.PanelProduct;
import views.menu.PanelShop;
import dao.DAO;

public class PanelShopController {

	PanelDAO panelDAO = new PanelDAO();
	private PanelShop panelShop;
	private BillDAO billDao = new BillDAO();
	private BillDetailsDAO billDetailsDao = new BillDetailsDAO();
	private UserDAO userDao = new UserDAO();
	private CustomerDao customerDao = new CustomerDao();
	private PaymentDAO paymentDao = new PaymentDAO();
	private TableDAO tableDao = new TableDAO();
	private CategoryDAO categoryDao = new CategoryDAO();
	private ProductDAO productDAO = new ProductDAO();
	private DefaultTableModel tableModel = new DefaultTableModel();
	private Vector<String> data;
	private ArrayList<ProductModel> list_1 = panelDAO.getInstance().findProductAll();
	private LinkedHashMap<String, String> List = new LinkedHashMap<String, String>();
	private float sumMoney;
	PanelBill panelBill;
	TableModel taModel = new TableModel();

	BillDetailsModel billDetailsModel = new BillDetailsModel();

	public PanelShopController(PanelShop panelShop) {
		this.panelShop = panelShop;

		addEventHeader();
//		addEventMyTable();
	}

	public void addEventHeader() {

		DisableInput();
		DefaultTableModel modelTable = new DefaultTableModel();

//		String column[] = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Tổng Tiền" };
//		for (String x : column) {
//			modelTable.addColumn(x);
//		}
		if (panelShop.getjTextFieldMaHD().getText() == "") {
			JOptionPane.showMessageDialog(panelShop, "Vui lòng thêm hóa đơn");
		}
		panelShop.getTable_Number().addItem("Chọn bàn");
		for (TableModel x : billDao.findTableByStatus("Available")) {
			panelShop.getTable_Number().addItem(x.getTableNumber());
		}

		for (PaymentModel x : billDao.findAllPayment()) {
			panelShop.getComBox().addItem(x.getPaymentName());
		}
		panelShop.getTenNV().addItem("Tên NV");
		for (UserModel x : billDao.findUserByRoleID("2")) {
			panelShop.getTenNV().addItem(x.getUserName());
		}

		// Thêm hóa đơn

		BillModel model = new BillModel();
		panelShop.getjButtonAdd().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				// reset toàn bộ bảng
				((DefaultTableModel) panelShop.getJtable().getModel()).setRowCount(0);
				// lấy mã id lớn nhất
//				int nextID = billDao.findAll().get(billDao.findAll().size() - 1).getID() + 1;
//				panelShop.getjTextFieldMaHD().setText(nextID + "");
				resetInput();
				EnableInput();
				JOptionPane.showMessageDialog(panelShop, "Vui lòng thêm sản phẩm ");
			}

		});
		// thêm sản phẩm

		panelShop.getjButtonThem().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (panelShop.getjTextFieldNgayLapHD().getText().isEmpty()) {
					JOptionPane.showMessageDialog(panelShop, "Vui lòng thêm hóa đơn trước khi thêm sản phẩm.");
					return;
				}

				panelShop.myTable();
				// Trả về thông tin sản phẩm
				panelShop.getjTable_1().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int selectIdx = panelShop.getjTable_1().getSelectedRow();
						if (selectIdx != -1) {
							String id = MapUtil.convertObjectToString(panelShop.getjTable_1().getValueAt(selectIdx, 0));
							String name = MapUtil
									.convertObjectToString(panelShop.getjTable_1().getValueAt(selectIdx, 1));
							String price = MapUtil
									.convertObjectToString(panelShop.getjTable_1().getValueAt(selectIdx, 2));

							panelShop.getjTextMaSP().setText(id);
							panelShop.getjTextTenSP().setText(name);
							panelShop.getjTextGia().setText(price);
							panelShop.getjText().setText("");

						}
					}

				});

				// thêm sản phẩm vào bảng chính
				panelShop.getjButton().addActionListener(new ActionListener() {
					float sumMoney = 0;// để trong lúc nhấn reset = 0

					@Override
					public void actionPerformed(ActionEvent e) {

						// TODO Auto-generated method stub
						String maSP = panelShop.getjTextMaSP().getText();
						String tenSP = panelShop.getjTextTenSP().getText();
						String giaSP = panelShop.getjTextGia().getText();
						String soLuong = panelShop.getjText().getText();

						if (checkQuantity(soLuong)) {
							JOptionPane.showMessageDialog(panelShop, "Thêm sản phẩm thành công");
						} else if (ValidateUtils.checkEmptyAndNull(soLuong)) {
							JOptionPane.showMessageDialog(panelShop, "Vui lòng điền đủ thông tin ");
						} else {
							JOptionPane.showMessageDialog(panelShop, "Số lượng sản phẩm không hợp lệ");
						}
						// add từng dòng về bảng chính
						data = new Vector<String>();
						data.add(maSP);
						data.add(tenSP);
						data.add(soLuong);
						data.add(giaSP);
						float sum = Integer.parseInt(soLuong) * Float.parseFloat(giaSP);
						data.add(sum + "");
						List.put(maSP, soLuong);
						sumMoney += sum;
						panelShop.getModel().addRow(data);
						panelShop.getTextTien().setText(sumMoney + "");

					}
				});
			}
		});

		// Xóa sản phẩm

		panelShop.getjButtonXoa().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DefaultTableModel tblModel = (DefaultTableModel) panelShop.getJtable().getModel();
				int idx = panelShop.getJtable().getSelectedRow();
				if (panelShop.getJtable().getSelectedRowCount() == 1) {
					String tong = panelShop.getTextTien().getText();
					String price = MapUtil.convertObjectToString(panelShop.getJtable().getValueAt(idx, 4));
					tblModel.removeRow(idx);// xóa trước thì không có idx
					float sum = Float.parseFloat(tong) - Float.parseFloat(price);
					panelShop.getTextTien().setText(String.valueOf(sum));
					JOptionPane.showMessageDialog(panelShop, "Xóa thành công");
				} else {
					if (panelShop.getJtable().getRowCount() == 0) {
						JOptionPane.showMessageDialog(panelShop, "Không có sản phẩm để xóa");
					} else {
						JOptionPane.showMessageDialog(panelShop, "Vui lòng chọn 1 sản phẩm để xóa");
					}

				}
			}
		});
//Sửa số lượng sản phẩm 
		panelShop.getjButtonSua().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				float sum = 0;
				int idx = panelShop.getJtable().getSelectedRow();
				if (panelShop.getJtable().getSelectedRowCount() == 1) {
					String value = (String) JOptionPane.showInputDialog(panelShop, "Nhập số lượng muốn thay đổi",
							"Thông báo", JOptionPane.PLAIN_MESSAGE);
					if (checkQuantity(value)) {
						panelShop.getJtable().setValueAt(value, idx, 2);
						JOptionPane.showMessageDialog(panelShop, "Sửa thành công");
						panelShop.getJtable().clearSelection();
					} else {
						JOptionPane.showMessageDialog(panelShop, "Số lượng không hợp lệ vui lòng nhập lại");

					}

					for (int i = 0; i < panelShop.getJtable().getModel().getRowCount(); i++) {
						float quantity = Float.parseFloat(panelShop.getJtable().getValueAt(i, 2).toString());
						float price = Float.parseFloat(panelShop.getJtable().getValueAt(i, 3).toString());
						float sum_row = quantity * price;
						panelShop.getJtable().setValueAt(sum_row, i, 4);
						sum += sum_row;
					}

					panelShop.getTextTien().setText(String.valueOf(sum));

				} else {
					if (panelShop.getJtable().getRowCount() == 0) {
						JOptionPane.showMessageDialog(panelShop, "Không có sản phẩm để sửa");
					} else {
						JOptionPane.showMessageDialog(panelShop, "Vui lòng chọn 1 sản phẩm để sửa số lượng");
					}

				}
			}
		});
		// Xác nhận thêm vào database
		panelShop.getjButtonOK().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (panelShop.getJtable().getModel().getRowCount() == 0) {
					JOptionPane.showMessageDialog(panelShop, "Vui lòng thêm sản phẩm trước khi xác nhận");
					return;
				}
				System.out.println(panelShop.getTenNV().getItemAt(0));
				
				if (ValidateUtils.checkEmptyAndNull(panelShop.getjTextFieldMaKH().getText())|| panelShop.getTenNV().getItemAt(0).equals("Tên NV")|| panelShop.getComBox().getItemAt(0).equals("Chọn bàn")) {
					JOptionPane.showMessageDialog(panelShop, "Vui lòng điền đủ thông tin");
					return;
				}
				StringBuilder messageError = new StringBuilder("");
				String Bill_ID = panelShop.getjTextFieldMaKH().getText();
				String User_Name = panelShop.getTenNV().getSelectedItem().toString();
				String Table_Number = panelShop.getTable_Number().getSelectedItem().toString();
				String Cus_ID = panelShop.getjTextFieldMaKH().getText();
				String Payment_Name = panelShop.getComBox().getSelectedItem().toString();
				String dateWork = panelShop.getjTextFieldNgayLapHD().getText();

				BillModel tmp = new BillModel();

				CustomerModel cusDao = customerDao.findByID(Cus_ID);
				tmp.setCustomer(cusDao);
				tmp.setCustomerID(cusDao.getID());

				TableModel tablee = billDao.findTableByNumber(Table_Number);
				tmp.setTable(tablee);
				tmp.setTableID(tablee.getID());

				UserModel user = billDao.findUserByUserName(User_Name);
				tmp.setUser(user);
				tmp.setUserID(user.getID());

				tmp.setBillTotal(Float.parseFloat(panelShop.getTextTien().getText()));
				PaymentModel payment = billDao.findPaymentByName(Payment_Name);
				tmp.setPayment(payment);
				tmp.setPaymentID(payment.getID());
				try {
					if (!ValidateUtils.checkEmptyAndNull(dateWork)) {
						SimpleDateFormat DateInput = new SimpleDateFormat("dd-MM-yyyy");
						SimpleDateFormat DateOutput = new SimpleDateFormat("yyyy-MM-dd");
						java.util.Date date = DateInput.parse(dateWork);

						String outputParse = DateOutput.format(date);
						tmp.setBillDate(java.sql.Date.valueOf(outputParse));

					} else
						tmp.setBillDate(null);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				billDao.insert(tmp);
//add vào billdetails
				ArrayList<BillDetailsModel> BillDetaList = new ArrayList<BillDetailsModel>();
				List.forEach((key, value) -> {
					BillDetailsModel tmpBillDetails = new BillDetailsModel();
					tmpBillDetails.setQuantityProduct(Integer.parseInt(value));
					tmpBillDetails.setProduct(billDao.findProductByName(key));
					tmpBillDetails.setProductID(billDao.findProductByName(key).getID());
					BillDetaList.add(tmpBillDetails);
				});

				// add vào billdetails

				for (int i = 0; i < panelShop.getJtable().getModel().getRowCount(); i++) {
					BillDetailsModel billDetailsModel = new BillDetailsModel();// tạo đối tượng mới
					billDetailsModel.setID(billDetailsModel.getID());

					String quantity = panelShop.getJtable().getValueAt(i, 2).toString();
					billDetailsModel.setQuantityProduct(Integer.parseInt(quantity));
					billDetailsModel.setBillID(tmp.getID());
					String id_Product = panelShop.getJtable().getValueAt(i, 0).toString();
					billDetailsModel.setProductID(Integer.parseInt(id_Product));
					billDetailsDao.insert(billDetailsModel);
				}
				JOptionPane.showMessageDialog(panelShop, "Xác nhận thành công");
			}

		});
	}

	public void resetInput() {
		panelShop.getjTextFieldMaKH().setText("");
		panelShop.getTable_Number().setSelectedItem("Chọn bàn");
		panelShop.getTenNV().setSelectedItem("Tên NV");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDateTime current = LocalDateTime.now();
		String formatted = current.format(formatter);
		panelShop.getjTextFieldNgayLapHD().setText(formatted);
		panelShop.getComBox().setSelectedItem("Cash");
		panelShop.getTextTien().setText("");
	}

	public void EnableInput() {
		panelShop.getjTextFieldMaKH().setEnabled(true);
		panelShop.getTable_Number().setEnabled(true);
		// panelShop.getjTextFieldNgayLapHD().setEnabled(true);
		panelShop.getComBox().setEnabled(true);
		panelShop.getTenNV().setEnabled(true);

	}

	public void DisableInput() {
		panelShop.getjTextFieldMaKH().setEnabled(false);

		panelShop.getjTextFieldNgayLapHD().setEnabled(false);
		panelShop.getTable_Number().setEnabled(false);
		panelShop.getTenNV().setEnabled(false);
		panelShop.getjTextFieldMaHD().setEnabled(false);
		panelShop.getTextTien().setEnabled(false);
		panelShop.getComBox().setEnabled(false);
	}

	public static boolean checkQuantity(String quantity) {
		String regex = "^[0-9]\\d{0,18}$"; // Chuỗi số dương không có ký tự đặc biệt, có tối đa 19 chữ số (từ 1 đến
											// 9999999999999999999)
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(quantity);
		return matcher.matches();
	}

}
