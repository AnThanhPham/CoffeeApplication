package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import model.BillModel;

public class BillDetailsDAO {
	/*
	  public BillModel findProductByPrice(String id) {
	    	BillModel res = null;
	    	try {
	    		String sql = "select * from bill where id = ? limit 1";
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ps.setString(1, id);
	    		ResultSet rs = ps.executeQuery();
	    		BillModel tmp = new BillModel();
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
	    		
					tmp.setID(rs.getInt(1));
					if(rs.getString(2) != null) {
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						java.util.Date utilDate = dateFormat.parse(rs.getString(2));
						java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
						tmp.setBillDate(sqlDate);
					}
					tmp.setBillTotal(rs.getFloat(3));
					tmp.setStatus(rs.getString(4));
					tmp.setCustomer(customerDao.findCustomerID(rs.getInt(5)));
					tmp.setUser(userDao.findByID(rs.getInt(6)+""));
					tmp.setTable(tableDao.findTableID(rs.getInt(7)));
					tmp.setPayment(paymentDao.findPaymentID(rs.getInt(8)));
	    		}
	    		if(tmp.getPro!=null) {
	    			res = tmp;
	    		}
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
	    	return res;
	    }
	 */
}
