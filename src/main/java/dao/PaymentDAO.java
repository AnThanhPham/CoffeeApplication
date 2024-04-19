package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import model.PaymentModel;

public class PaymentDAO extends DAO implements AbstractDAO<PaymentModel>{
	public PaymentModel findByID(String id) {
    	PaymentModel res = null;
    	try {
    		String sql = "select * from payment where id = ? limit 1";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, id);
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

	@Override
	public void insert(PaymentModel t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PaymentModel t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(PaymentModel t) {
		// TODO Auto-generated method stub
		
	}
	
}
