package dao;

import java.util.*;
import java.sql.*;

import model.BillModel;
import model.CategoryModel;
import model.ProductModel;

public class ProductDAO extends DAO  {

	
	public void insert(ProductModel t) {
		// TODO Auto-generated method stub
		
	}

	
	public static void delete(ProductModel t) throws SQLException  {
		// TODO Auto-generated method stub
		
			Statement stm1= conn.createStatement();
			String sqlCommand= " delete from product where id = "+ t.getID();
			stm1.execute(sqlCommand);
		
		
		
	}

	
	public void update(ProductModel t) {
		// TODO Auto-generated method stub
		
	}
	public static List<ProductModel> selectAll() {
		new DAO();
		List<ProductModel> listResult= new ArrayList<ProductModel>();
		try {
			Statement stm= conn.createStatement();
			String sqlCommand= "select * from Product join Category on category.ID= Product.categoryID";
			ResultSet rs= stm.executeQuery(sqlCommand);
			
			
			while(rs.next()) {
				
				int id=rs.getInt(1);
				double price= rs.getDouble(2);
				String name= rs.getString(3);
				String des= rs.getString(4);
				String image= rs.getString(5);
				int categoryID= rs.getInt(6);
				int categoryId= rs.getInt( "category.ID");
				String categoryName= rs.getString("categoryName");
				String categoryDes= rs.getString("category.description");
				CategoryModel category= new CategoryModel(categoryId, categoryName, des);
				ProductModel in4= new ProductModel(id, price, name, des, image, category);
				listResult.add(in4);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listResult;
		
		
	
	}
}
