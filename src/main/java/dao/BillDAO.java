package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.BillModel;

public class BillDAO extends DAO implements AbstractDAO<BillModel>{
	public BillDAO() {
		super();
	}
	/*
	public static BillModel getRoleByID(int id) {
		BillModel res = new BillModel();
		
		try {
			String sql = "select * from role where RoleID = ? limit 1";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				//res.setRoleID(rs.getInt(1));
				//res.setRoleName(rs.getString(3));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}
*/
	@Override
	public void insert(BillModel t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BillModel t) {
		// TODO Auto-generated method stub
		
	}
}
