package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TreeMap;

import model.BillModel;
import model.CategoryModel;
import model.CustomerModel;
import model.PaymentModel;
import model.ProductModel;
import model.TableModel;
import model.UserModel;

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
	
	public PaymentModel findPaymentByName(String name) {
    	PaymentModel res = null;
    	try {
    		String sql = "select * from payment where PaymentName = ? limit 1";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, name);
    		ResultSet rs = ps.executeQuery();
    		PaymentModel tmp = new PaymentModel();
    		if(rs.next()) {
    			tmp.setID(rs.getInt(1));
    			tmp.setPaymentName(rs.getString(2));
    		}
    		if(Integer.toString(tmp.getID()) != null) {
    			res = tmp;
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return res;
    }
	public ArrayList<PaymentModel> findAllPayment() {
		ArrayList<PaymentModel> res = new ArrayList<PaymentModel>();
    	try {
    		String sql = "select * from payment";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			PaymentModel tmp = new PaymentModel();
    			tmp.setID(rs.getInt(1));
    			tmp.setPaymentName(rs.getString(2));
    			res.add(tmp);
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return res;
    }
	
	 public ArrayList<TableModel> findTableByStatus(String status) {
		 ArrayList<TableModel> res = new ArrayList<TableModel>();
	    	try {
	    		String sql = "select * from tablee where Status = ?";
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ps.setString(1, status);
	    		ResultSet rs = ps.executeQuery();
	    		
	    		while(rs.next()) {
	    			TableModel tmp = new TableModel();
	    			tmp.setID(rs.getInt(1));
	    			tmp.setTableNumber(rs.getString(2));
	    			tmp.setStatus(rs.getString(3));
	    			tmp.setQuantityCustomer(rs.getInt(4));
	    			res.add(tmp);
	    		}
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
	    	return res;
	    }
	 
	 public ArrayList<TableModel> findTableAll() {
		 ArrayList<TableModel> res = new ArrayList<TableModel>();
	    	try {
	    		String sql = "select * from tablee";
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ResultSet rs = ps.executeQuery();
	    		
	    		while(rs.next()) {
	    			TableModel tmp = new TableModel();
	    			tmp.setID(rs.getInt(1));
	    			tmp.setTableNumber(rs.getString(2));
	    			tmp.setStatus(rs.getString(3));
	    			tmp.setQuantityCustomer(rs.getInt(4));
	    			res.add(tmp);
	    		}
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
	    	return res;
	    }
	 
	 public ArrayList<UserModel> findUserByRoleID(String roleid) {
	    	ArrayList<UserModel> res = new ArrayList<>();
	    	try {
	    		String sql = "select * from user where roleid = ?";
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ps.setString(1, roleid);
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
	 
	 public UserModel findUserByUserName(String UserName) {
	    	UserModel res = null;
	    	try {
	    		String sql = "select * from user where UserName = ? limit 1";
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ps.setString(1, UserName);
	    		ResultSet rs = ps.executeQuery();
	    		UserModel tmp = new UserModel();
	    		if(rs.next()) {
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
	    		}
	    		if(tmp.getID()!=null) {
	    			res = tmp;
	    		}
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
	    	return res;
	    }
	 
	 public TableModel findTableByNumber(String number) {
	    	TableModel res = null;
	    	try {
	    		String sql = "select * from tablee where TableNumber = ? limit 1";
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ps.setString(1, number);
	    		ResultSet rs = ps.executeQuery();
	    		TableModel tmp = new TableModel();
	    		if(rs.next()) {
	    			tmp.setID(rs.getInt(1));
	    			tmp.setTableNumber(rs.getString(2));
	    			tmp.setStatus(rs.getString(3));
	    			tmp.setQuantityCustomer(rs.getInt(4));
	    		}
	    		if(Integer.toString(tmp.getID()) != null) {
	    			res = tmp;
	    		}
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
	    	return res;
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
	public ArrayList<BillModel> findByDate(String day) {
		ArrayList<BillModel> res = new ArrayList<BillModel>();
    	try {
    		String sql = "select * from bill where BillDate = ? ";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, day);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()) {
    			BillModel tmp = new BillModel();
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
				res.add(tmp);
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return res;
	}
	
	public static ArrayList<CategoryModel> findCategoryAll(){
		ArrayList<CategoryModel> res = new ArrayList<>();
		try {
			String sql = "select * from category";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CategoryModel tmp = new CategoryModel();
				tmp.setID(rs.getInt(1));
				tmp.setCategoryName(rs.getString(2));
				tmp.setDescription(rs.getString(3));
				res.add(tmp);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}
	

	public ArrayList<ProductModel> findProductByCategoryId(String id){
		ArrayList<ProductModel> res = new ArrayList<ProductModel>();
		try {
			String sql = "select * from product where categoryid = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				ProductModel product = new ProductModel();
				
				product.setID(rs.getInt(1));
				product.setPrice(rs.getFloat(2));
				product.setName(rs.getString(3));
				product.setDescription(rs.getString(4));
				product.setImage(rs.getString(5));
				product.setCategory(categoryDao.findByID(rs.getInt(6)+""));
				res.add(product);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}
	
	 public BillModel findByID(String id) {
	    	BillModel res = null;
	    	try {
	    		String sql = "select * from bill where id = ? limit 1";
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
	 
	 public CustomerModel findCusByPhone(String phone) {
	    	CustomerModel res = null;
	    	try {
	    		String sql = "select * from customer where phone = ? limit 1";
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ps.setString(1, phone);
	    		ResultSet rs = ps.executeQuery();
	    		CustomerModel tmp = new CustomerModel();
	    		if(rs.next()) {
	    			tmp.setID(rs.getInt(1));
	    			tmp.setName(rs.getString(2));
	    			tmp.setPhone(rs.getString(3));
	    			tmp.setAddress(rs.getString(4));	    			
	    			tmp.setEmail(rs.getString(5));
	    		}
	    		if(Integer.valueOf(tmp.getID()) != null) {
	    			res = tmp;
	    		}
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
	    	return res;
	    }
	 
	 public ProductModel findProductByName(String name) {
	    	ProductModel res = null;
	    	try {
	    		String sql = "select * from product where name = ? limit 1";
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ps.setString(1, name);
	    		ResultSet rs = ps.executeQuery();
	    		ProductModel tmp = new ProductModel();
	    		if(rs.next()) {
	    			tmp.setID(rs.getInt(1));
	    			tmp.setPrice(rs.getFloat(2));
	    			tmp.setName(rs.getString(3));
	    			tmp.setDescription(rs.getString(4));
	    			tmp.setImage(rs.getString(5));
	    			tmp.setCategory(categoryDao.findByID(rs.getInt(6)+""));
	    		}
	    		if(Integer.toString(tmp.getID())!=null) {
	    			res = tmp;
	    		}
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
	    	return res;
	    }
	 /*
	 public ArrayList<ProductModel> findProductByFullName(String name) {
		 ArrayList<ProductModel> res = null;
	    	try {
	    		String sql = "select * from product where name like CONCAT('%',?,'%')"; 
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ps.setString(1, name);
	    		ResultSet rs = ps.executeQuery();
	    		ProductModel tmp = new ProductModel();
	    		while(rs.next()) {
	    			tmp.setID(rs.getInt(1));
	    			tmp.setPrice(rs.getFloat(2));
	    			tmp.setName(rs.getString(3));
	    			tmp.setDescription(rs.getString(4));
	    			tmp.setImage(rs.getString(5));
	    			tmp.setCategory(categoryDao.findByID(rs.getInt(6)+""));
	    			res.add(tmp);
	    		}
	    		
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
	    	return res;
	    }
	 */
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
	 public boolean checkProductName(String id) {
		 boolean result = false;
		 try {
				PreparedStatement ps = conn.prepareStatement("select Name from product where id=? limit 1");
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
	 
	 public TreeMap<Integer,Double> FitterDataByYear(String year){
		 TreeMap<Integer,Double> res = new TreeMap<Integer,Double>();
		 try {
			 String sql = "select *  from bill  where year(BillDate) = ?   ";
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ps.setString(1, year);
	    		ResultSet rs = ps.executeQuery();
	    		while(rs.next()) {
	    			BillModel tmp = new BillModel();
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
					Double sumPrice = (double) rs.getFloat(3);
					
					Integer key = tmp.getBillDate().getMonth()+1;
					//System.out.println(tmp.getBillDate());
					if(res.containsKey(key)){
						res.replace(key, res.get(key)+sumPrice);
					}
					else res.put(key, sumPrice);
					//System.out.println(key+"    "+res.get(key));
					
	    		}
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
		 return res;
	 }
	
	 public void changePrice(Float price,Integer id ) {
	    	try {
	    		String sql = "update bill set BillTotal = ? where id = ?";
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ps.setFloat(1, price);
	    		ps.setInt(2,id);
	    		ps.executeUpdate();
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
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
			int BillID = rs.getInt(1);
			t.setID(BillID);
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
