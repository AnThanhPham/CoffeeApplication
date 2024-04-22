
package views.menu;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import controller.PanelShopController;
import dao.PanelDAO;
import dao.UserDAO;
import model.PaymentModel;
import model.ProductModel;
import model.UserModel;
import util.MapUtil;
import views.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class PanelShop extends JPanel {

	private JLabel jLabelMaHD;
	private JTextField jTextFieldMaHD;
	private JLabel jLabelMaKH;
	private JTextField jTextFieldMaKH;
	private JComboBox<String> TenNV;
	private JLabel jLabelNgayLapHD;
	private JTextField jTextFieldNgayLapHD;
	private JTextField jTextMaSP;
	private JPanel jPanelHead;
	private JButton jButtonOK;
	private JButton jButton;
	private JPanel chiTietView;
	private JButton jButtonThem;
	private JLabel tenNV;
	private JButton jButtonSua;
	private JButton jButtonXoa;
	private JLabel jLabelBan;
	private JTextField jTextGia;
	private JComboBox<String> comBox;
	private JTextField textBan;
	private JTextField jText;
	private JTextField jTextTenSP;
	private JButton jButtonAdd;
	private DefaultTableModel model = new DefaultTableModel() {
		public boolean isCellEditable(int row,int column) {
			return false;
		}
	};
	private JTable jtable;
	private JTable jTable_1;
	private JTextField textTien;
//	private ArrayList<PanelModel> ds;
//	private PanelModel panelModel;
	private ArrayList<UserModel> list;
	private PanelDAO panelDAO;
	private ProductModel productModel = new ProductModel();
	private UserDAO userDAO = new UserDAO();
	private JComboBox<String>Table_Number;
	private List<ProductModel> li = panelDAO.getInstance().findProductAll();
	private DefaultTableModel tableModel = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private PanelShopController panelShopController  ;
	Font font_0 = new Font("Segoe UI", Font.PLAIN, 14);
	Font font_1 = new Font("Arials", Font.PLAIN, 9);

	public PanelShop() {

		HeaderPage();
		CenterPage();
		panelShopController = new PanelShopController(this);
	}

	public void HeaderPage() {

		list = userDAO.findAll();

		setLayout(null);
		setBackground(null);
		setBounds(new Rectangle(50, 0, 1700, 1000));

		jPanelHead = new JPanel(null);
		jPanelHead.setBackground(null);
		jPanelHead.setBounds(new Rectangle(59, 43, 1428, 150));
		jTextFieldMaHD = new JTextField();
		jTextFieldMaHD.setBounds(65, 0, 75, 30);
		jTextFieldMaHD.setHorizontalAlignment(JTextField.CENTER);
		jTextFieldMaHD.setPreferredSize(new Dimension(80, 30));
		jTextFieldMaHD.setEnabled(false);
		jPanelHead.add(jTextFieldMaHD);

		// Tao ma kh
		jLabelMaKH = new JLabel("Ma KH");
		jLabelMaKH.setFont(font_0);
		jLabelMaKH.setBounds(165, 0, 60, 30);
		jTextFieldMaKH = new JTextField();
		jTextFieldMaKH.setBounds(225, 0, 100, 30);
		jTextFieldMaKH.setHorizontalAlignment(JTextField.CENTER);
		jTextFieldMaKH.setEnabled(false);

		jPanelHead.add(jLabelMaKH);
		jPanelHead.add(jTextFieldMaKH);

//         //Tao ma nv
		tenNV = new JLabel("Tên NV");
		tenNV.setFont(font_0);
		tenNV.setBounds(347, -3, 50, 30);
		jPanelHead.add(tenNV);
		TenNV = new JComboBox<String>();
		TenNV.addItem("Tên NV");
		for (UserModel x : list) {
			TenNV.addItem(x.getUserName());
		}
		TenNV.setBounds(416, -1, 100, 30);
		jPanelHead.add(TenNV);
//
		// Tao ngay lap hoa don
		jLabelNgayLapHD = new JLabel("Ngày Lập Hóa Đơn");
		jLabelNgayLapHD.setFont(font_0);
		jLabelNgayLapHD.setBounds(0, 50, 140, 30);
		jTextFieldNgayLapHD = new JTextField();
//		jTextFieldNgayLapHD.setText(date+"");
		jTextFieldNgayLapHD.setBounds(140, 50, 185, 30);
		jPanelHead.add(jLabelNgayLapHD);
		jPanelHead.add(jTextFieldNgayLapHD);

		jLabelBan = new JLabel("Bàn Số");
		jLabelBan.setFont(font_0);
		jLabelBan.setBounds(347, 50, 60, 30);
		jPanelHead.add(jLabelBan);
		
		Table_Number = new JComboBox<String>();
		Table_Number.setBounds(416,50,100,30);
		Table_Number.setFont(font_0);
		jPanelHead.add(Table_Number);
//         //Button tao xac nhan 
		jButtonAdd = new JButton("Thêm Hóa Đơn");
		jButtonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		jButtonAdd.setFont(font_0);
		jButtonAdd.setHorizontalAlignment(JButton.CENTER);
		jButtonAdd.setBounds(540, 50, 170, 30);
		jButtonAdd.setBackground(Color.gray);
		jPanelHead.add(jButtonAdd);

		jButtonOK = new JButton("Xác Nhận");
		jButtonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		jButtonOK.setFont(font_0);

		jButtonOK.setBackground(Color.gray);
		jButtonOK.setBounds(760, 50, 170, 30);
		jPanelHead.add(jButtonOK);

		// tao duong ke
		JSeparator jsep = new JSeparator(SwingConstants.HORIZONTAL);
		jsep.setBounds(0, 120, 930, 3);
		jPanelHead.add(jsep);

		// Add phan head vao this
		this.add(jPanelHead);

		textTien = new JTextField();
		textTien.setBounds(620, 0, 100, 30);
		textTien.setHorizontalAlignment(JTextField.CENTER);
		jPanelHead.add(textTien);

		JLabel lblNewLabel = new JLabel("Tổng Tiền");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(540, 0, 77, 30);
		jPanelHead.add(lblNewLabel);
		
				// Tao ma hd
				jLabelMaHD = new JLabel("Mã HD");
				jLabelMaHD.setBounds(0, 0, 55, 30);
				jPanelHead.add(jLabelMaHD);
				jLabelMaHD.setFont(font_0);
				
				 comBox = new JComboBox();
				comBox.setBounds(830, 0, 100, 30);
				jPanelHead.add(comBox);
				
				JLabel lblNewLabel_1 = new JLabel("Thanh Toán");
				lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				lblNewLabel_1.setBounds(740, 0, 90, 30);
				jPanelHead.add(lblNewLabel_1);
	}

	public void CenterPage() {

		// Phan view chi tiet

		chiTietView = new JPanel(null);
		chiTietView.setVisible(true);
		chiTietView.setBackground(null);

		chiTietView.setBounds(30, 190, 1600, 600);
		
		// Tao Bang
		model.addColumn("Mã Sản Phẩm");
		model.addColumn("Tên Sản Phẩm");
		model.addColumn("Số Lượng");
		model.addColumn("Giá Mỗi Sản Phẩm");

		model.addColumn("Tổng Tiền");

		jtable = new JTable(model);
		// sắp xếp dữ liệu dựa trên model
//		TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model);
//		jtable.setRowSorter(rowSorter);

		// Chỉnh chiều rộng các cột
		TableColumnModel tableModel = jtable.getColumnModel();

		tableModel.getColumn(0).setPreferredWidth(50);
		tableModel.getColumn(1).setPreferredWidth(140);
		tableModel.getColumn(2).setPreferredWidth(30);
		tableModel.getColumn(3).setPreferredWidth(20);
		tableModel.getColumn(4).setPreferredWidth(50);

		// Căn giữa các giá trị
		DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
		centerRender.setHorizontalAlignment(JLabel.CENTER);

		tableModel.getColumn(0).setCellRenderer(centerRender);
		tableModel.getColumn(1).setCellRenderer(centerRender);
		tableModel.getColumn(2).setCellRenderer(centerRender);
		tableModel.getColumn(3).setCellRenderer(centerRender);
		tableModel.getColumn(4).setCellRenderer(centerRender);

		// Custom table
		jtable.setFocusable(false);
		jtable.setIntercellSpacing(new Dimension(0, 0));
		jtable.getTableHeader().setFont(font_0);
		jtable.getTableHeader().setOpaque(false);
		jtable.getTableHeader().setBackground(new Color(139, 122, 101));
		jtable.getTableHeader().setForeground(Color.WHITE);
		jtable.setRowHeight(30);
		jtable.setShowVerticalLines(true);
		jtable.setSelectionBackground(Color.DARK_GRAY);

		// add table vào Scroll
		JScrollPane jScroll = new JScrollPane(jtable);
		jScroll.setBounds(new Rectangle(31, 10, 935, 450));
		jScroll.setBackground(null);

		chiTietView.add(jScroll);
		add(chiTietView);

		jButtonThem = new JButton("Thêm Sản Phẩm");
		jButtonThem.setFont(font_0);
		jButtonThem.setVisible(true);
		jButtonThem.setBackground(Color.gray);
		jButtonThem.setBounds(new Rectangle(250, 500, 150, 30));
		chiTietView.add(jButtonThem);

		jButtonSua = new JButton("Sửa Sản Phẩm");
		jButtonSua.setFont(font_0);
		jButtonSua.setHorizontalAlignment(JButton.CENTER);
		jButtonSua.setBackground(Color.gray);
		jButtonSua.setBounds(new Rectangle(460, 500, 150, 30));
		chiTietView.add(jButtonSua);
	
		jButtonXoa = new JButton("Xoá Sản Phẩm");
		
		jButtonXoa.setFont(font_0);
		jButtonXoa.setHorizontalAlignment(jButtonXoa.CENTER);
		jButtonXoa.setBackground(Color.gray);
		jButtonXoa.setBounds(new Rectangle(670, 500, 150, 30));
		chiTietView.add(jButtonXoa);

	}

	public static void main(String args[]) {
		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(1000, 600);
		jf.setLocationRelativeTo(null);
		jf.getContentPane().add(new PanelShop());

	}

	public class MyTable extends DefaultTableModel {

		MyTable(Vector header, int i) {
			super(header, i);
		}

		@Override
		public boolean isCellEditable(int rowIndex, int mColIndex) {
			return false;
		}

	}

	public void myTable() {
		JDialog dialog = new JDialog();
		Font font_0 = new Font("Segoe UI", Font.PLAIN, 14);

		dialog.setSize(850, 650);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);

		jTable_1 = new JTable();
		JPanel jPanelTable = new JPanel();
		jPanelTable.setLayout(null);

		String[] colsName = { "Mã sản phẩm", "Tên sản phẩm", "Giá" };
		tableModel.setColumnIdentifiers(colsName);

		jTable_1.setFocusable(false);
		jTable_1.setIntercellSpacing(new Dimension(0, 0));
		jTable_1.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 14));
		jTable_1.getTableHeader().setOpaque(false);
		jTable_1.getTableHeader().setBackground(new Color(139, 122, 101));
		jTable_1.getTableHeader().setForeground(Color.WHITE);
		jTable_1.setRowHeight(50);
		jTable_1.setShowVerticalLines(true);
		jTable_1.setSelectionBackground(new Color(0xE6FFEC));

		JScrollPane scrollPane = new JScrollPane(jTable_1);
		scrollPane.setBounds(new Rectangle(370, 90, 400, 450));
		jPanelTable.add(scrollPane);

		dialog.getContentPane().add(jPanelTable);
		Vector data;
		for (ProductModel x : li) {
			data = new Vector();
			data.add(x.getID());
			data.add(x.getName());
			data.add(x.getPrice());
			tableModel.addRow(data);
		}
		jTable_1.setModel(tableModel);

		dialog.getContentPane().add(jPanelTable);

		JLabel jLabel = new JLabel("Mã Sản Phẩm");
		jLabel.setFont(font_0);
		jLabel.setBounds(50, 170, 100, 30);
		jTextMaSP = new JTextField();
		jTextMaSP.setBounds(new Rectangle(170, 170, 100, 30));
		jTextMaSP.setEditable(false);
		jTextMaSP.setHorizontalAlignment(JTextField.CENTER);
		jPanelTable.add(jLabel);
		jPanelTable.add(jTextMaSP);

		jLabel = new JLabel("Số Lượng");
		jLabel.setFont(font_0);
		jLabel.setBounds(50, 330, 70, 30);
		jPanelTable.add(jLabel);

		jText = new JTextField();

		jText.setHorizontalAlignment(JTextField.CENTER);
		jText.setBounds(new Rectangle(170, 330, 100, 30));
		jPanelTable.add(jText);

		jLabel = new JLabel("Tên Sản Phẩm");
		jLabel.setFont(font_0);
		jLabel.setBounds(50, 250, 100, 30);
		jPanelTable.add(jLabel);
		jTextTenSP = new JTextField();
		jTextTenSP.setHorizontalAlignment(JTextField.CENTER);
		jTextTenSP.setEditable(false);
		jTextTenSP.setBounds(new Rectangle(170, 250, 100, 30));
		jPanelTable.add(jTextTenSP);

		jLabel = new JLabel("Đơn Giá");
		jLabel.setFont(font_0);
		jLabel.setBounds(50, 410, 100, 30);
		jPanelTable.add(jLabel);
		jTextGia = new JTextField();
		jTextGia.setHorizontalAlignment(JTextField.CENTER);
		jTextGia.setEditable(false);
		jTextGia.setBounds(new Rectangle(170, 410, 100, 30));
		jPanelTable.add(jTextGia);

		jButton = new JButton("Thêm");
		jButton.setFont(font_0);
		jButton.setBackground(Color.GRAY);
		jButton.setBounds(105, 480, 100, 30);
		jPanelTable.add(jButton);
		dialog.getContentPane().add(jPanelTable);

	}

	public JLabel getjLabelMaHD() {
		return jLabelMaHD;
	}

	public void setjLabelMaHD(JLabel jLabelMaHD) {
		this.jLabelMaHD = jLabelMaHD;
	}

	public JTextField getjTextFieldMaHD() {
		return jTextFieldMaHD;
	}

	public void setjTextFieldMaHD(JTextField jTextFieldMaHD) {
		this.jTextFieldMaHD = jTextFieldMaHD;
	}

	public JLabel getjLabelMaKH() {
		return jLabelMaKH;
	}

	public void setjLabelMaKH(JLabel jLabelMaKH) {
		this.jLabelMaKH = jLabelMaKH;
	}

	public JTextField getjTextFieldMaKH() {
		return jTextFieldMaKH;
	}

	public void setjTextFieldMaKH(JTextField jTextFieldMaKH) {
		this.jTextFieldMaKH = jTextFieldMaKH;
	}

	public JComboBox<String> getTenNV() {
		return TenNV;
	}

	public void setTenNV(JComboBox<String> tenNV) {
		TenNV = tenNV;
	}

	public JLabel getjLabelNgayLapHD() {
		return jLabelNgayLapHD;
	}

	public void setjLabelNgayLapHD(JLabel jLabelNgayLapHD) {
		this.jLabelNgayLapHD = jLabelNgayLapHD;
	}

	public JTextField getjTextFieldNgayLapHD() {
		return jTextFieldNgayLapHD;
	}

	public void setjTextFieldNgayLapHD(JTextField jTextFieldNgayLapHD) {
		this.jTextFieldNgayLapHD = jTextFieldNgayLapHD;
	}

//	public JButton getjButtonMaKH() {
//		return jButtonMaKH;
//	}
//
//	public void setjButtonMaKH(JButton jButtonMaKH) {
//		this.jButtonMaKH = jButtonMaKH;
//	}

	public JPanel getjPanelHead() {
		return jPanelHead;
	}

	public void setjPanelHead(JPanel jPanelHead) {
		this.jPanelHead = jPanelHead;
	}

	public JButton getjButtonOK() {
		return jButtonOK;
	}

	public void setjButtonOK(JButton jButtonOK) {
		this.jButtonOK = jButtonOK;
	}

	public JButton getjButtonThem() {
		return jButtonThem;
	}

	public void setjButtonThem(JButton jButtonThem) {
		this.jButtonThem = jButtonThem;
	}

	public JLabel getTenNV1() {
		return tenNV;
	}

	public void setTenNV(JLabel tenNV) {
		this.tenNV = tenNV;
	}

	public JButton getjButtonSua() {
		return jButtonSua;
	}

	public void setjButtonSua(JButton jButtonSua) {
		this.jButtonSua = jButtonSua;
	}

	public JButton getjButtonXoa() {
		return jButtonXoa;
	}

	public void setjButtonXoa(JButton jButtonXoa) {
		this.jButtonXoa = jButtonXoa;
	}
	

	public JComboBox<String> getTable_Number() {
		return Table_Number;
	}

	public void setTable_Number(JComboBox<String> table_Number) {
		Table_Number = table_Number;
	}

	public JLabel getjLabelBan() {
		return jLabelBan;
	}

	public void setjLabelBan(JLabel jLabelBan) {
		this.jLabelBan = jLabelBan;
	}

	public JTextField getTextBan() {
		return textBan;
	}

	public void setTextBan(JTextField textBan) {
		this.textBan = textBan;
	}

	public JButton getjButtonAdd() {
		return jButtonAdd;
	}

	public void setjButtonAdd(JButton jButtonAdd) {
		this.jButtonAdd = jButtonAdd;
	}

	public JTable getJtable() {
		return jtable;
	}

	public void setJtable(JTable jtable) {
		this.jtable = jtable;
	}

	public JTextField getjTextMaSP() {
		return jTextMaSP;
	}

	public void setjTextMaSP(JTextField jTextMaSP) {
		this.jTextMaSP = jTextMaSP;
	}

	public JButton getjButton() {
		return jButton;
	}

	public void setjButton(JButton jButton) {
		this.jButton = jButton;
	}

	public JTextField getjTextGia() {
		return jTextGia;
	}

	public void setjTextGia(JTextField jTextGia) {
		this.jTextGia = jTextGia;
	}

	public JTextField getjText() {
		return jText;
	}

	public void setjText(JTextField jText) {
		this.jText = jText;
	}

	public JTextField getjTextTenSP() {
		return jTextTenSP;
	}

	public void setjTextTenSP(JTextField jTextTenSP) {
		this.jTextTenSP = jTextTenSP;
	}

	public JComboBox<String> getComBox() {
		return comBox;
	}

	public void setComBox(JComboBox<String> comBox) {
		this.comBox = comBox;
	}

	public JTable getjTable_1() {
		return jTable_1;
	}

	public void setjTable_1(JTable jTable_1) {
		this.jTable_1 = jTable_1;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JTextField getTextTien() {
		return textTien;
	}

	public void setTextTien(JTextField textTien) {
		this.textTien = textTien;
	}
}
