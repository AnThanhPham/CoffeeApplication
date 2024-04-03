package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.ProductModel;

public class ProductDAO extends DAO implements AbstractDAO<ProductModel>{
	public static ProductModel findProductID(int id) {
		ProductModel res = new ProductModel();
		try {
			String sql = "select * from product where ID = ? limit 1";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				res.setID(rs.getInt(1));
				res.setPrice(rs.getFloat(2));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}
	
	@Override
	public void insert(ProductModel t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ProductModel t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ProductModel t) {
		// TODO Auto-generated method stub
		
	}
	
}
