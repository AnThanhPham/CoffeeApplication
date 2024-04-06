package dao;

import java.beans.JavaBean;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import model.BillDetailsModel;
import model.BillModel;
import model.CustomerModel;
import model.PaymentModel;
import model.ProductModel;
import model.TableModel;
import model.BillModel;
import model.BillModel;
import views.menu.PanelBill;

public class BillDAO extends DAO implements AbstractDAO<BillModel>{
	private UserDAO userDao = new UserDAO();
	private CustomerDao customerDao = new CustomerDao();
	private PaymentDAO paymentDao = new PaymentDAO();
	private TableDAO tableDao = new TableDAO();
	private CategoryDAO categoryDao = new CategoryDAO();
	
	public static BillDAO getInstance(){
		return new BillDAO();
	}
	public BillDAO() {
		super();
	}
	// chỉ lấy truy vấn ở đây
	
	public ArrayList<BillModel> findAll() {
		ArrayList<BillModel> data = new ArrayList<BillModel>();
		try {
			String sql = "select * from bill";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				BillModel billModel = new BillModel();
				
				billModel.setID(rs.getInt(1));
				if(rs.getString(2) != null) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date utilDate = dateFormat.parse(rs.getString(2));
					java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
					billModel.setBillDate(sqlDate);
				}
				billModel.setBillTotal(rs.getFloat(3));
				billModel.setStatus(rs.getString(4));
				billModel.setCustomer(customerDao.findByID(rs.getInt(5)+""));
				billModel.setUser(userDao.findByID(rs.getInt(6)+""));
				billModel.setTable(tableDao.findByID(rs.getInt(7)+""));
				billModel.setPayment(paymentDao.findByID(rs.getInt(8)+""));
				data.add(billModel);
			}
		}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
	    return data;
			
	}
	
	public ArrayList<ProductModel> findProductAll() {
		ArrayList<ProductModel> data = new ArrayList<ProductModel>();
		try {
			String sql = "select * from product";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				ProductModel product = new ProductModel();
				
				product.setID(rs.getInt(1));
				product.setPrice(rs.getFloat(2));
				product.setName(rs.getString(3));
				product.setDescription(rs.getString(4));
				product.setImage(rs.getString(5));
				product.setCategory(categoryDao.findByID(rs.getInt(6)+""));
				data.add(product);
			}
		}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
	    return data;
			
	}
	
	 public BillModel findByID(String id) {
	    	BillModel res = null;
	    	try {
	    		String sql = "select * from user where id = ? limit 1";
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ps.setString(1, id);
	    		ResultSet rs = ps.executeQuery();
	    		BillModel tmp = new BillModel();
	    		if(rs.next()) {
	    			tmp.setID(rs.getInt(1));
					if(rs.getString(2) != null) {
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						java.util.Date utilDate = dateFormat.parse(rs.getString(2));
						java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
						tmp.setBillDate(sqlDate);
					}
					tmp.setBillTotal(rs.getFloat(3));
					tmp.setStatus(rs.getString(4));
					tmp.setCustomer(customerDao.findByID(rs.getInt(5)+""));
					tmp.setUser(userDao.findByID(rs.getInt(6)+""));
					tmp.setTable(tableDao.findByID(rs.getInt(7)+""));
					tmp.setPayment(paymentDao.findByID(rs.getInt(8)+""));
	    		}
	    		if(Integer.toString(tmp.getID()) !=null) {
	    			res = tmp;
	    		}
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
	    	return res;
	 }
	 
	 public boolean checkUser(String id) {
		 boolean result = false;
		 try {
				PreparedStatement ps = conn.prepareStatement("select ID from user where id=? limit 1");
				ps.setString(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
				    result = true;
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 return result;
	 }
	 
	 public boolean checkTable(String id) {
		 boolean result = false;
		 try {
				PreparedStatement ps = conn.prepareStatement("select ID from tablee where id=? limit 1");
				ps.setString(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
				    result = true;
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 return result;
	 }
	 
	 public boolean checkPayment(String id) {
		 boolean result = false;
		 try {
				PreparedStatement ps = conn.prepareStatement("select ID from payment where id=? limit 1");
				ps.setString(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
				    result = true;
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 return result;
	 }
	 
	 public boolean checkCustomer(String id) {
		 boolean result = false;
		 try {
				PreparedStatement ps = conn.prepareStatement("select ID from customer where id=? limit 1");
				ps.setString(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
				    result = true;
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 return result;
	 }
	 
	@Override
	public void insert(BillModel t) {
		String sql = AbstractImpl.buildSqlInsertBill(t);
		System.out.println(sql);
		try {
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.first();
			int cusID = rs.getInt(1);
			t.setID(cusID);
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(BillModel t) {
		try {
    		String sql = "delete from bill where id = ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setInt(1, t.getID());
    		ps.executeUpdate();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
		
	}

	@Override
	public void update(BillModel t) {
		try {
    		String sql = AbstractImpl.buildSqlUpdateBill(t);
    		System.out.println(sql);
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.executeUpdate();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
		
	}

}
