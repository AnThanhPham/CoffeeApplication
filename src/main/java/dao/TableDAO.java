package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.PaymentModel;
import model.TableModel;

public class TableDAO extends DAO implements AbstractDAO<TableModel>{
	public TableDAO() {
		super();
	}
	public static TableModel findTableID(int id) {
		TableModel res = new TableModel();
		try {
			String sql = "select * from tablee where ID = ? limit 1";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				res.setID(rs.getInt(1));
				res.setTableNumber(rs.getString(3));
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
