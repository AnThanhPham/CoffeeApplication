package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.BillDetailsModel;
import model.BillModel;
import model.ProductModel;

public class BillDetailsDAO extends DAO implements AbstractDAO<BillDetailsModel> {
	private BillDAO billDao = new BillDAO();
	private ProductDAO productDao = new ProductDAO();
	
	public BillDetailsDAO(){
		super();
	}
	public ArrayList<BillDetailsModel> findByBillID(String id) {
		ArrayList<BillDetailsModel> res = new ArrayList<BillDetailsModel>();
	    	try {
	    		String sql = "select * from billdetails where Billid = ?";
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ps.setString(1, id);
	    		ResultSet rs = ps.executeQuery();
	    		while(rs.next()) {
	    			BillDetailsModel tmp = new BillDetailsModel();
	    			tmp.setID(rs.getInt(1));
	    			tmp.setQuantityProduct(rs.getInt(2));
	    			tmp.setBill(billDao.findByID(Integer.toString(rs.getInt(3))));
	    			tmp.setProduct(productDao.findByID(rs.getInt(4)+""));
	    			res.add(tmp);
	    		}
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
	    	return res;
	    }
	public BillDetailsModel findByProductID(String id) {
		BillDetailsModel res = null;
	    	try {
	    		String sql = "select * from billdetails where ProductID in (select id from product where CategoryID = ?)";
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
	public void deletefromProduct(BillDetailsModel t) {
		try {		
			String deleteBillDetailSql = "delete from billdetails where ProductID in (select id from product where CategoryID = ?)";
	        PreparedStatement deleteBillDetailPs = conn.prepareStatement(deleteBillDetailSql);
	        deleteBillDetailPs.setInt(1, t.getID());
	        deleteBillDetailPs.executeUpdate();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
	}
	
	public ArrayList<BillDetailsModel> findBillDetailsAll() {
		ArrayList<BillDetailsModel> data = new ArrayList<BillDetailsModel>();
		try {
			String sql = "select * from billdetails";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				BillDetailsModel billdet = new BillDetailsModel();
				
				billdet.setID(rs.getInt(1));
				billdet.setQuantityProduct(rs.getInt(2));
				billdet.setBill(billDao.findByID(rs.getString(3)));
				billdet.setProduct(productDao.findByID(rs.getString(4)));
				data.add(billdet);
			}
		}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
	    return data;
			
	}
	
	@Override
	public void insert(BillDetailsModel t) {
		String sql = AbstractImpl.buildSqlInsertBillDetails(t);
		System.out.println(sql);
		try {
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.first();
			int ID = rs.getInt(1);
			t.setID(ID);
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(BillDetailsModel t) {
		try {
    		String sql = "delete from billdetails where id = ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setInt(1, t.getID());
    		ps.executeUpdate();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
		
	}
	


	@Override
	public void update(BillDetailsModel t) {
		try {
    		String sql = AbstractImpl.buildSqlUpdateBillDetails(t);
    		System.out.println(sql);
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.executeUpdate();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
		
	}
}
