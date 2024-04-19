package dao;

import model.CustomerModel;
import views.menu.PanelCustomer;
import model.CustomerModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				tmp.setName(rs.getString(2));
				tmp.setPhone(rs.getString(3));
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
		String sql = AbstractImpl.buildSqlInsertCustomer(t);
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
	public void delete(CustomerModel t) {
		try {
    		String sql = "delete from customer where id = ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setInt(1, t.getID());
    		ps.executeUpdate();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
	}

	@Override
	public void update(CustomerModel t) {
		try {
    		String sql = AbstractImpl.buildSqlUpdateCustomer(t);
    		System.out.println(sql);
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.executeUpdate();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
	}
	
	 public boolean checkDuplicateEmail(String customer){
	        boolean duplicate = false;
	        try {
				PreparedStatement ps = conn.prepareStatement("select ID from customer where Email=? limit 1");
				ps.setString(1, customer);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
				    duplicate = true;
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        return duplicate;
	    }

	 public CustomerModel findByID(String id) {
	    	CustomerModel res = null;
	    	try {
	    		String sql = "select * from customer where id = ? limit 1";
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ps.setString(1, id);
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

	 public ArrayList<CustomerModel> findByFullname(String fullname) {
	    	ArrayList<CustomerModel> res = new ArrayList<>();
	    	try {
	    		String sql = "select * from customer where name like CONCAT('%',?,'%')";
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ps.setString(1, fullname);
	    		ResultSet rs = ps.executeQuery();
	    		while(rs.next()) {
	    			CustomerModel tmp = new CustomerModel();
	    			tmp.setID(rs.getInt(1));
	    			tmp.setName(rs.getString(2));
	    			tmp.setEmail(rs.getString(5));
	    			tmp.setPhone(rs.getString(3));
	    			tmp.setAddress(rs.getString(4));
	    			res.add(tmp);
	    		}
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
	    	return res;
	    }
}


