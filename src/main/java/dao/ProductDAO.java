package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.ProductModel;

public class ProductDAO extends DAO implements AbstractDAO<ProductModel>{
	private static  CategoryDAO categoryDao = new CategoryDAO();
	
	public ProductDAO() {
		super();
	}
	public ProductModel findByID(String id) {
    	ProductModel res = null;
    	try {
    		String sql = "select * from product where id = ? limit 1";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, id);
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
