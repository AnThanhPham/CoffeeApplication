package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.PanelShopController;
import model.BillDetailsModel;
import model.BillModel;

import model.ProductModel;
import model.UserModel;
import views.menu.PanelShop;

public class PanelDAO extends DAO implements AbstractDAO<BillDetailsModel> {
	private CustomerDao customerDao = new CustomerDao();
	private PaymentDAO paymentDao = new PaymentDAO();
	private TableDAO tableDao = new TableDAO();
	private CategoryDAO categoryDao = new CategoryDAO();
	private UserDAO userDao = new UserDAO();

	private BillDetailsModel billDetailsModel = new BillDetailsModel();
	
	
	public static PanelDAO getInstance() {
		return new PanelDAO();
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

	public ArrayList<UserModel> findUser(String roleID){
		ArrayList<UserModel> res = new ArrayList<>();
    	try {
    		String sql = "select * from user where roleID = ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, roleID);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()) {
    			UserModel tmp = new UserModel();
    			tmp.setID(rs.getInt(1));
    			tmp.setUserName(rs.getString(2));
    			tmp.setPhone(rs.getString(3));
    			tmp.setAddress(rs.getString(4));
    			if(rs.getString(5)!=null) {
    				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    				java.util.Date utilDate = dateFormat.parse(rs.getString(5));
    				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    				tmp.setDateWork(sqlDate);    				
    			}
    			tmp.setPassword(rs.getString(7));
    			tmp.setFullName(rs.getString(8));
    			tmp.setRole(RoleDAO.getRoleByID(rs.getInt(9)));
    			tmp.setCode(rs.getString(10));
    			tmp.setStatus(rs.getString(11));
    			tmp.setEmail(rs.getString(12));
    			tmp.setGender(rs.getString(13));
    			tmp.setImage(rs.getString(14));
    			res.add(tmp);
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return res;
	}
	public BillModel findUserIDByName(String name) {
    	BillModel res = null;
    	try {
    		String sql = "SELECT * FROM bill WHERE UserID = (SELECT UserID FROM user WHERE UserName = ?) LIMIT 1";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, name);
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
	@Override
	public void insert(BillDetailsModel t) {
		// TODO Auto-generated method stub
		   String sql = AbstractImpl.buildSqlInsertBillDetails(t);
		    System.out.println(sql);
		    try {
		        PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		        ps.executeUpdate();
		        ResultSet rs = ps.getGeneratedKeys();
		        if (rs.next()) {
		            int ID = rs.getInt(1);
		            t.setID(ID);
		            int quantity = t.getQuantityProduct(); 
		            t.setQuantityProduct(quantity);
		            int BillID = rs.getInt(3);
		            t.setBillID(BillID);  ;
		            int productID = t.getProductID(); 
		            t.setProductID(productID);
		        }
		        ps.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		
	}

	

	@Override
	public void delete(BillDetailsModel t) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void update(BillDetailsModel t) {
		// TODO Auto-generated method stub
		
	}




}
	

	