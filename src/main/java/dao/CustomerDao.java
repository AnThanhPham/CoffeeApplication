package dao;

import model.CustomerModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerDao extends DAO implements AbstractDAO<CustomerModel>{
	public CustomerDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static ArrayList<CustomerModel> CustomerList(){
		ArrayList<CustomerModel> res = new ArrayList<>();
		try {
			String sql = "select * from customer";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CustomerModel tmp = new CustomerModel();
				tmp.setID(rs.getInt(1));
				tmp.setFullName(rs.getString(2));
				tmp.setPhoneNumber(rs.getString(3));
				tmp.setAddress(rs.getString(4));
				tmp.setEmail(rs.getString(5));

				res.add(tmp);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}
	
	@Override
	public void insert(CustomerModel t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CustomerModel t) {
		try {
    		String sql = "delete from user where id = ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setInt(1, t.getID());
    		ps.executeUpdate();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
	}

	@Override
	public void update(CustomerModel t) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void update(CustomerModel t) {
//		try {
//    		String sql = AbstractImpl.buildSqlUpdateUser(t);
//    		System.out.println(sql);
//    		PreparedStatement ps = conn.prepareStatement(sql);
//    		ps.executeUpdate();
//    	}catch(Exception ex) {
//    		ex.printStackTrace();
//    	}

}
