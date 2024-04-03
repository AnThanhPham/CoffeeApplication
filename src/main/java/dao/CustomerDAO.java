package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.CustomerModel;

public class CustomerDAO extends DAO implements AbstractDAO<CustomerModel>{
	public static CustomerModel findCustomerID(int id) {
		CustomerModel res = new CustomerModel();
		try {
			String sql = "select * from customer where ID = ? limit 1";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				res.setID(rs.getInt(1));
				res.setName(rs.getString(2));
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CustomerModel t) {
		// TODO Auto-generated method stub
		
	}
}
