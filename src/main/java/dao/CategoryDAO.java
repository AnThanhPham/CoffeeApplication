package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CategoryModel;
import model.CustomerModel;
import model.CategoryModel;

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
				tmp.setDescription(rs.getString(3));
				res.add(tmp);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}

	public void insert(CategoryModel t) {
		String sql = AbstractImpl.buildSqlInsertCategory(t);
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
	public void delete(CategoryModel t) {
		try {
    		String sql = "delete from category where id = ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setInt(1, t.getID());
    		ps.executeUpdate();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
	}

	@Override
	public void update(CategoryModel t) {
		try {
    		String sql = AbstractImpl.buildSqlUpdateCategory(t);
    		System.out.println(sql);
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.executeUpdate();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
	}
	 public CategoryModel findByID(String id) {
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
	    		if(Integer.valueOf(tmp.getID()) != null) {
	    			res = tmp;
	    		}
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
	    	return res;
	}
	 public boolean checkDuplicateName(String category){
	        boolean duplicate = false;
	        try {
				PreparedStatement ps = conn.prepareStatement("select ID from category where CategoryName=? limit 1");
				ps.setString(1, category);
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
	 public ArrayList<CategoryModel> findByFullname(String fullname) {
	    	ArrayList<CategoryModel> res = new ArrayList<>();
	    	try {
	    		String sql = "select * from category where categoryname like CONCAT('%',?,'%')";
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ps.setString(1, fullname);
	    		ResultSet rs = ps.executeQuery();
	    		while(rs.next()) {
	    			CategoryModel tmp = new CategoryModel();
	    			tmp.setID(rs.getInt(1));
	    			tmp.setCategoryName(rs.getString(2));
	    			tmp.setDescription(rs.getString(3));
	    			res.add(tmp);
	    		}
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
	    	return res;
	    }
	
	
}