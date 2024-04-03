package dao;

import java.beans.JavaBean;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import model.BillDetailsModel;
import model.BillModel;
import model.CustomerModel;
import model.PaymentModel;
import model.TableModel;
import model.UserModel;
import views.menu.PanelBill;

public class BillDAO extends DAO implements AbstractDAO<BillModel>{
	private UserDAO userDao = new UserDAO();
	private CustomerDAO customerDao = new CustomerDAO();
	private PaymentDAO paymentDAO = new PaymentDAO();
	private TableDAO tableDAO = new TableDAO();
	
	public static BillDAO getInstance(){
		return new BillDAO();
	}
	public BillDAO() {
		super();
	}
	// chỉ lấy truy vấn ở đây
	
	public ArrayList<BillModel> FindAll() {
		ArrayList<BillModel> data = new ArrayList<BillModel>();
		try {
			String sql = "select * from bill";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				BillModel billModel = new BillModel();
				
				billModel.setID(rs.getInt("ID"));
				if(rs.getString("BillDate") != null) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date utilDate = dateFormat.parse(rs.getString("BillDate"));
					java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
					billModel.setBillDate(sqlDate);
				}
				billModel.setBillTotal(rs.getFloat("BillTotal"));
				billModel.setStatus(rs.getString("status"));
				billModel.setCustomer(customerDao.findCustomerID(rs.getInt("CustomerID")));
				billModel.setUser(userDao.findByID(rs.getInt("UserID")+"")); //(rs.getInt("UserID"));
				billModel.setTable(TableDao.findByID(rs.getInt("TableID"));
				billModel.getPayment().setID(rs.getInt("PaymentID"));
				
			}
		}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
	    return data;
			
	}
	
	@Override
	public void insert(BillModel t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BillModel t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(BillModel t) {
		// TODO Auto-generated method stub
		
	}

}
