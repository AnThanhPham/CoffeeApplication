package dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.CategoryModel;
import model.ProductModel;
import model.TableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.PaymentModel;
import model.TableModel;

public class TableDAO extends DAO {
	public TableDAO() {
		super();
	}
	public TableModel findByID(String id) {
    	TableModel res = null;
    	try {
    		String sql = "select * from tablee where id = ? limit 1";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, id);
    		ResultSet rs = ps.executeQuery();
    		TableModel tmp = new TableModel();
    		if(rs.next()) {
    			tmp.setID(rs.getInt(1));
    			tmp.setTableNumber(rs.getString(2));
    			tmp.setStatus(rs.getString(3));
    			tmp.setQuantityCustomer(rs.getInt(4));
    		}
    		if(Integer.toString(tmp.getID()) != null) {
    			res = tmp;
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return res;
    }
	
	public static void selectTable(TableModel t) throws SQLException {
		Statement stm= conn.createStatement();
		String sql= "select * from tablee where id = "+ t.getID();
		stm.execute(sql);

	}
	public static List<TableModel> selectAll() {
		new DAO();
		List<TableModel> listResult= new ArrayList<TableModel>();
		try {
			Statement stm= conn.createStatement();
			String sqlCommand= "select * from tablee";
			ResultSet rs= stm.executeQuery(sqlCommand);

			while(rs.next()) {
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String status= rs.getString(3);
				int quantityCustomer= rs.getInt(4);	
				listResult.add(new TableModel(id,name,status,quantityCustomer));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return listResult;	
	}
	public static void update(TableModel t) throws SQLException {

		Statement stm1;
		
			stm1 = conn.createStatement();
			 String sql="update tablee " +
					 	"set id= "+ t.getID()+ 
					 	",TableNumber = N'"+ t.getTableNumber()+"'" +
					 	",Status= N'"+ t.getStatus()+ "', " +
					 	"QuantityCustomer =" + t.getQuantityCustomer() +" "+
					 	" where id= "+ t.getID()   ;
			 System.out.println("sql"+ sql);
			stm1.execute(sql);             


	}

}
