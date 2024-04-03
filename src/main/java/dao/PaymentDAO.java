package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.PaymentModel;

public class PaymentDAO extends DAO implements AbstractDAO<PaymentModel>{
	public static PaymentModel findPaymentID(int id) {
		PaymentModel res = new PaymentModel();
		try {
			String sql = "select * from payment where ID = ? limit 1";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				res.setID(rs.getInt(1));
				res.setPaymentName((rs.getString(2)));
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
