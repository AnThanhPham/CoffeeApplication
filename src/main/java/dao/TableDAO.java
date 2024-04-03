package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.PaymentModel;
import model.TableModel;

public class TableDAO extends DAO implements AbstractDAO<TableModel>{
	public TableDAO() {
		super();
	}
	public TableModel findByID(String id) {
    	TableModel res = null;
    	try {
    		String sql = "select * from customer where id = ? limit 1";
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
	
	@Override
	public void insert(TableModel t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(TableModel t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TableModel t) {
		// TODO Auto-generated method stub
		
	}

}
