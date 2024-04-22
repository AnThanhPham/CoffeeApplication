package dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Statement;

import model.CategoryModel;
import model.ProductModel;


public class ProductDAO extends DAO  {
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
    			tmp.setPrice((int)rs.getFloat(2));
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
	
	public ProductModel findByIDCategory(String id) {
    	ProductModel res = null;
    	try {
    		String sql = "select * from product where CategoryID = ? limit 1";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, id);
    		ResultSet rs = ps.executeQuery();
    		ProductModel tmp = new ProductModel();
    		if(rs.next()) {
    			tmp.setID(rs.getInt(1));
    			tmp.setPrice((int)rs.getFloat(2));
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
	public void deletefromCategory(ProductModel t) {
		try {		
			String deleteProductSql = "delete from product where CategoryID = ?";
	        PreparedStatement deleteProductPs = conn.prepareStatement(deleteProductSql);
	        deleteProductPs.setInt(1, t.getID());
	        deleteProductPs.executeUpdate();
    		
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
	}
	
	// Hàm lấy ra list tên sản phẩm
	public static List<String>  listName() throws SQLException {
		List<String> listResult= new ArrayList<String>();
		try {
	        java.sql.Statement stm1 = conn.createStatement();
	        String sql = "select name from Product";
	        ResultSet rs = stm1.executeQuery(sql);

	        while (rs.next()) {
	            listResult.add(rs.getString("name"));
	        }
	        rs.close();
	        stm1.close();
	    } catch (SQLException e) {
	        e.printStackTrace(); // hoặc log.error("Error occurred:", e);
	    }
		return listResult;
	}
	
	
	public static String selectCategoryName(int id) throws SQLException {
	    PreparedStatement stm = null;
	    ResultSet rs = null;
	    String name="" ;
        String sql = "SELECT product.id, categoryName FROM category join product on product.categoryid= category.id WHERE product.id= " + id;
	        stm = conn.prepareStatement(sql);
	        rs = stm.executeQuery();

	        if (rs.next()) {
	            name = rs.getString("categoryName");
	        }
	     return name;

	}
	
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
	
	public static void insert(ProductModel t) throws SQLException, IOException {
		// TODO Auto-generated method stub

			new DAO();

			java.sql.Statement stm= conn.createStatement(); stm= conn.createStatement();
			String sql= "insert into product(price,name,description,image,categoryId) value(" +
						t.getPrice() +
						",N'" + t.getName() + "',N'"+
						t.getDescription()+
						"',N'"+t.getImage()+"',"+
						t.getCategory().getID()+")";
			System.out.println(sql);
			stm.execute(sql);
			updatePathImgAll();
	}
	
	
	public static void delete(ProductModel t) throws SQLException  {
		// TODO Auto-generated method stub
		
			java.sql.Statement stm1= conn.createStatement();
			String sqlCommand= " delete from product where id = "+ t.getID();
			stm1.execute(sqlCommand);
	}


	public static void update(ProductModel t) throws SQLException, IOException {

		java.sql.Statement stm1= conn.createStatement();
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
		 updatePathImgAll();
	}
	public static void updatePathImgAll() throws IOException, SQLException {
		java.sql.Statement stm= conn.createStatement();
		String sqlCommand= "select * from Product where  image not like concat('%anh',id,'.png')";
		ResultSet rs= stm.executeQuery(sqlCommand);
		ArrayList<ProductModel> wait = new ArrayList<ProductModel>();
		while(rs.next()) {
			int id=rs.getInt(1);
			String oldimage= rs.getString(5);
			ProductModel model = new ProductModel();
			model.setID(id);
			model.setImage(oldimage);
			wait.add(model);
		}
		for(ProductModel model : wait ) {
			int id = model.getID();
			String oldimage = model.getImage();
			String _newimage = "src/main/java/img/product/anh"+id+".png";
			Files.copy(new File(oldimage).toPath(),new File(_newimage).toPath(),StandardCopyOption.REPLACE_EXISTING);
			String sql="update product SET image= '" + _newimage +"' where id= "+ id  ;
			stm.execute(sql);
			
		}
			
	} 
	
	
	public static List<ProductModel>  search(String t, String searchCategory) throws SQLException {
		List<ProductModel> listResult= new ArrayList<ProductModel>();
		java.sql.Statement stm1= conn.createStatement();
		if(searchCategory.equals("Tất cả")) {
			searchCategory  = "%";
		} 
		String sql="select * from Product join Category on category.ID= Product.categoryID where name like '%"+ t + "%' and categoryName like N'"+ searchCategory +"'" ; 
		ResultSet rs= stm1.executeQuery(sql);

		while(rs.next()) {

			int id=rs.getInt(1);
			int price= (int) rs.getDouble(2);
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
		java.sql.Statement stm1= conn.createStatement();
		String sql="select categoryname from  category";
		stm1.execute(sql);
		ResultSet rs= stm1.executeQuery(sql);

		while(rs.next()) {
			String categoryName= rs.getString("categoryName");
			listResult.add(categoryName);

		}


		return listResult;

	}
	
	public static List<ProductModel> selectAll() {
		new DAO();
		List<ProductModel> listResult= new ArrayList<ProductModel>();
		try {
			java.sql.Statement stm= conn.createStatement();
			String sqlCommand= "select * from Product join Category on category.ID= Product.categoryID";
			ResultSet rs= stm.executeQuery(sqlCommand);
			
			
			while(rs.next()) {
				
				int id=rs.getInt(1);
				int price= (int) rs.getDouble(2);
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