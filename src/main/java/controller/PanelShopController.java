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
import java.util.List;
import java.util.Vector;

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
import dao.TableDAO;
import dao.UserDAO;
import model.BillDetailsModel;
import model.BillModel;
import model.CustomerModel;
import model.PaymentModel;
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
	private CustomerDao customerDao = new CustomerDao();
	private CategoryDAO categoryDao = new CategoryDAO();
	private TableDAO tableDao = new TableDAO();
	private PaymentDAO paymentDao = new PaymentDAO();
	private UserDAO userDao = new UserDAO();
	UserModel userModel = new UserModel();
	// private ArrayList<PanelModel> list = panelDAO.getInstance().selectALL();
	private DefaultTableModel tableModel = new DefaultTableModel();
	private Vector<String> data;
	BillDAO billDAO = new BillDAO();
	BillModel billModel = new BillModel();
	private ArrayList<BillModel> list_1 = panelDAO.getInstance().findAll();
	private float sumMoney;
	TableModel taModel = new TableModel();
//	private PanelModel panelModel;
	BillDetailsModel billDetailsModel = new BillDetailsModel();

	public PanelShopController(PanelShop panelShop) {
		this.panelShop = panelShop;

		addEventHeader();
//		addEventMyTable();
	}

	public void addEventHeader() {
		DisableInput();
		DefaultTableModel modelTable = new DefaultTableModel();

		String column[] = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Tổng Tiền" };
		for (String x : column) {
			modelTable.addColumn(x);
		}
		if (panelShop.getjTextFieldMaHD().getText() == "") {
			JOptionPane.showMessageDialog(panelShop, "Vui lòng thêm hóa đơn");
		}

		// Thêm hóa đơn
		panelShop.getjButtonAdd().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				// reset toàn bộ bảng
				((DefaultTableModel) panelShop.getJtable().getModel()).setRowCount(0);
				// lấy mã id lớn nhất

//				int maHD = 0;
//				for (BillModel x : list_1) {
//					maHD = x.getID();
//					System.out.println(maHD + "   ");
//				}
//				panelShop.getjTextFieldMaHD().setText(maHD + "");
				resetInput();
				EnableInput();
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

						if (!maSP.isEmpty() && !tenSP.isEmpty() && !giaSP.isEmpty() && !soLuong.isEmpty()) {
							try {
								double gia = Double.parseDouble(giaSP);
								int quantity = Integer.parseInt(soLuong);
								JOptionPane.showMessageDialog(panelShop, "Thêm sản phẩm thành công");
							} catch (NumberFormatException e2) {
								JOptionPane.showMessageDialog(panelShop, "Số lượng phải là số");
							}
						} else {
							JOptionPane.showMessageDialog(panelShop, "Vui lòng điền đầy đủ thông tin sản phẩm");
						}
						// add từng dòng về bảng chính
						data = new Vector<String>();
						data.add(maSP);
						data.add(tenSP);
						data.add(soLuong);
						data.add(giaSP);
						float sum = Integer.parseInt(soLuong) * Float.parseFloat(giaSP);
						data.add(sum + "");
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
					tblModel.removeRow(panelShop.getJtable().getSelectedRow());// xóa trước thì không có idx
					float sum = Float.parseFloat(tong) - Float.parseFloat(price);
					panelShop.getTextTien().setText(sum + "");
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

				int idx = panelShop.getJtable().getSelectedRow();
				if (panelShop.getJtable().getSelectedRowCount() == 1) {

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
//				if (panelShop.getjTextFieldMaHD().getText().isEmpty()) {
//					JOptionPane.showMessageDialog(panelShop, "Vui lòng thêm hóa đơn trước khi xác nhận");
//					return;
//				}
//				if (panelShop.getJtable().getModel().getRowCount() == 0) {
//					JOptionPane.showMessageDialog(panelShop, "Không có sản phẩm để xác nhận");
//			
				StringBuilder messageError = new StringBuilder("");
				float SumPrice = 0;
				String Bill_ID = panelShop.getjTextFieldMaHD().getText();
			
		//		String User_ID = (String)panelShop.getTenNV().getSelectedItem();
				String Table_ID = panelShop.getTextBan().getText();

                BillModel tmp = new BillModel();
				

				taModel = tableDao.findByID(Table_ID);
				tmp.setTable(taModel);
				tmp.setTableID(taModel.getID());
				
//				 userModel = userDao.findByID(User_ID);
//				tmp.setUser( userModel);
//				tmp.setUserID( userModel.getID());
				
//			
					billDetailsModel.setQuantityProduct(Integer.parseInt(panelShop.getjText().getText()));
					
					billDetailsModel.setProductID(Integer.parseInt(panelShop.getjTextMaSP().getText()));
					billDetailsModel.setBillID(Integer.parseInt(panelShop.getjTextFieldMaHD().getText()));
					panelDAO.insert(billDetailsModel);

	         

			
			}
		});
	}

//	
	public void resetInput() {
		panelShop.getjTextFieldMaKH().setText("");
		panelShop.getTextBan().setText("");
		panelShop.getTenNV().setSelectedItem("Tên NV");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDateTime current = LocalDateTime.now();
		String formatted = current.format(formatter);
		panelShop.getjTextFieldNgayLapHD().setText(formatted);
		panelShop.getTextTien().setText("");
	}

	public void EnableInput() {
		panelShop.getjTextFieldMaKH().setEnabled(true);
		panelShop.getTextBan().setEnabled(true);
		panelShop.getjTextFieldNgayLapHD().setEnabled(true);
		panelShop.getTenNV().setEnabled(true);
		// panelShop.getTextTien().setEnabled(true);
//			panelShop.getjTextFieldMaHD().setEnabled(true);

	}

	public void DisableInput() {
		panelShop.getjTextFieldMaKH().setEnabled(false);
		panelShop.getTextBan().setEnabled(false);
		panelShop.getjTextFieldNgayLapHD().setEnabled(false);
		panelShop.getTenNV().setEnabled(false);
		panelShop.getjTextFieldMaHD().setEnabled(false);
		panelShop.getTextTien().setEnabled(false);
	}

}
