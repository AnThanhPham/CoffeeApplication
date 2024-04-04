package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.BillDetailsModel;

public class BillDetailsDAO extends DAO implements AbstractDAO<BillDetailsModel> {
	private BillDAO billDao = new BillDAO();
	private ProductDAO productDao = new ProductDAO();
	
	public BillDetailsDAO(){
		super();
	}
	public BillDetailsModel findByID(String id) {
	    	BillDetailsModel res = null;
	    	try {
	    		String sql = "select * from billdetails where id = ? limit 1";
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ps.setString(1, id);
	    		ResultSet rs = ps.executeQuery();
	    		BillDetailsModel tmp = new BillDetailsModel();
	    		if(rs.next()) {
	    			tmp.setID(rs.getInt(1));
	    			tmp.setQuantityProduct(rs.getInt(2));
	    			tmp.setBill(billDao.findByID(Integer.toString(rs.getInt(3))));
	    			tmp.setProduct(productDao.findByID(rs.getInt(4)+""));
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
	public void insert(BillDetailsModel t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(BillDetailsModel t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(BillDetailsModel t) {
		// TODO Auto-generated method stub
		
	}
}
