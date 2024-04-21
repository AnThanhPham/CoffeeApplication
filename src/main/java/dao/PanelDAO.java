package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.BillDetailsModel;
import model.BillModel;
import model.PanelModel;
import model.UserModel;
import views.menu.PanelShop;

public class PanelDAO extends DAO implements AbstractDAO<PanelModel> {
	private CustomerDao customerDao = new CustomerDao();
	private PaymentDAO paymentDao = new PaymentDAO();
	private TableDAO tableDao = new TableDAO();
	private CategoryDAO categoryDao = new CategoryDAO();
	private UserDAO userDao = new UserDAO();
	private BillDetailsModel billDetailsModel = new BillDetailsModel();

	
	public static PanelDAO getInstance() {
		return new PanelDAO();
	}
	


	public static ArrayList<PanelModel> selectALL(){
		ArrayList<PanelModel> result = new ArrayList<PanelModel>();
		
		try {
			Statement st = conn.createStatement();
			String sql = "SELECT ID,Name,Price FROM product";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int idProduct = rs.getInt(1);
				String name = rs.getString(2);
				float price = rs.getFloat(3);
				PanelModel panelModel = new PanelModel(idProduct,name,price);
				result.add(panelModel);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
		
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
	
	@Override
	public void insert(PanelModel t) {
		// TODO Auto-generated method stub
//		String id = t.getPanelShop().getjTextMaSP().getText();
//		String quantity = t.getPanelShop().getjText().getText();
//	    try {
//	        
//	        
//	       
//	        Statement st = conn.createStatement();
//	        st.executeUpdate("insert into billDetails valuse ("+id+","+quantity+")");
//	        JOptionPane.showMessageDialog(null, "Thất bại");
//	        
//	      conn.close();
//	    } catch (SQLException e2) {
//	        e2.printStackTrace();
//	    }
		
		}

	@Override
	public void delete(PanelModel t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(PanelModel t) {
		// TODO Auto-generated method stub
		
	}
}
