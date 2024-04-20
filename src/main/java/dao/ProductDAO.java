package dao;

import java.util.*;
import java.sql.*;

import model.BillModel;
import model.CategoryModel;
import model.ProductModel;

public class ProductDAO extends DAO  {
	public static int selectCategoryId(String name) throws SQLException {
	    PreparedStatement stm = null;
	    ResultSet rs = null;
	    int categoryId = -1; // Giá trị mặc định nếu không tìm thấy id
	        //String sql = "SELECT id FROM category WHERE name = ? LIMIT 1";
        String sql = "SELECT id FROM category WHERE categoryName= N'" + name +"'";
	        stm = conn.prepareStatement(sql);
	        rs = stm.executeQuery();

	        if (rs.next()) {
	            categoryId = rs.getInt("id");
	        }
	     return categoryId;
	    
	}

	
	public static void insert(ProductModel t) throws SQLException {
		Statement stm= conn.createStatement();
		String sql= "insert into product(price,name,description,image,categoryId) value(" +
					t.getPrice() +
					",N'" + t.getName() + "',N'"+
					t.getDescription()+
					"',N'"+t.getImage()+"',"+
					t.getCategory().getID()+")";
		System.out.println(sql);
		stm.execute(sql);
		
		
		
		
//		Statement stm= conn.createStatement();
//		String sql= "insert into product(price,name,description,image,categoryId) value(" +
//					t.getPrice() +
//					",N'" + t.getName() + "',N'"+
//					t.getDescription()+
//					"',N'"+t.getImage()+"',"+
//					t.getCategory().getID()+")";
//		System.out.println(sql);
//		stm.execute(sql);
		


}
		

	
	public static void delete(ProductModel t) throws SQLException  {
		// TODO Auto-generated method stub
		
			Statement stm1= conn.createStatement();
			String sqlCommand= " delete from product where id = "+ t.getID();
			stm1.execute(sqlCommand);
		
	}

	
	public static void update(ProductModel t) throws SQLException {

		Statement stm1= conn.createStatement();
		 String sql="update product" +
				 	" SET "+
				 	"Name = N'" + t.getName()+ "'," + 
				 	"Price = "+ t.getPrice()+ " ," +
				 	"Description = N'" + t.getDescription()+"',"+
				 	"image= '" + t.getImage() +"'," +
				 	"categoryid = "+ t.getCategory().getID()+
				 	" where id= "+ t.getID()   ;
		 System.out.println("sql"+ sql);
		 stm1.execute(sql);                
		
		
	}
	
	public static List<ProductModel>  search(String t, String searchCategory) throws SQLException {
		List<ProductModel> listResult= new ArrayList<ProductModel>();
		Statement stm1= conn.createStatement();
		if(searchCategory.equals("Tất cả")) {
			searchCategory  = "%";
		} 
		String sql="select * from Product join Category on category.ID= Product.categoryID where name like '%"+ t + "%' and categoryName like N'"+ searchCategory +"'" ; 
		stm1.execute(sql);
		ResultSet rs= stm1.executeQuery(sql);
		
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
		
		
		return listResult;
		
	}
	
	public static List<String>  selectCategory() throws SQLException {
		List<String> listResult= new ArrayList<String>();
		Statement stm1= conn.createStatement();
		String sql="select categoryname from  category";
		stm1.execute(sql);
		ResultSet rs= stm1.executeQuery(sql);
		
		while(rs.next()) {
			String categoryName= rs.getString("categoryName");
			listResult.add(categoryName);
			
		}
		
		
		return listResult;
		
	}
//	
	
	
	
	
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
