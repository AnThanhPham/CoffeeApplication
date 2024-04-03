package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CategoryModel;
import model.CustomerModel;

public class CategoryDAO extends DAO implements AbstractDAO<CategoryModel>{
	public CategoryDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static ArrayList<CategoryModel> CategoryList(){
		ArrayList<CategoryModel> res = new ArrayList<>();
		try {
			String sql = "select * from category";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CategoryModel tmp = new CategoryModel();
				tmp.setID(rs.getInt(1));
				tmp.setCategoryName(rs.getString(2));

				res.add(tmp);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}
	
	public static CategoryModel findByID(String id) {
		CategoryModel res = null;
    	try {
    		String sql = "select * from category where id = ? limit 1";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, id);
    		ResultSet rs = ps.executeQuery();
    		CategoryModel tmp = new CategoryModel();
    		if(rs.next()) {
    			tmp.setID(rs.getInt(1));
    			tmp.setCategoryName(rs.getString(2));
    			tmp.setDescription(rs.getString(3));
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
	public void insert(CategoryModel t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CategoryModel t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CategoryModel t) {
		// TODO Auto-generated method stub
		
	}
	
//	@Override
//	public void insert(CategoryModel t) {
//		String sql = AbstractImpl.buildSqlInsertCustomer(t);
//		System.out.println(sql);
//		try {
//			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//			ps.executeUpdate();
//			ResultSet rs = ps.getGeneratedKeys();
//			rs.first();
//			int cusID = rs.getInt(1);
//			t.setID(cusID);
//			ps.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void delete(CategoryModel t) {
//		try {
//    		String sql = "delete from category where id = ?";
//    		PreparedStatement ps = conn.prepareStatement(sql);
//    		ps.setInt(1, t.getID());
//    		ps.executeUpdate();
//    	}catch(Exception ex) {
//    		ex.printStackTrace();
//    	}
//	}
//
//	@Override
//	public void update(CategoryModel t) {
//		try {
//    		String sql = AbstractImpl.buildSqlUpdateCustomer(t);
//    		System.out.println(sql);
//    		PreparedStatement ps = conn.prepareStatement(sql);
//    		ps.executeUpdate();
//    	}catch(Exception ex) {
//    		ex.printStackTrace();
//    	}
//	}
	
	
}