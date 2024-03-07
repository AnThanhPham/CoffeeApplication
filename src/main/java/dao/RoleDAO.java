package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.RoleModel;

public class RoleDAO extends DAO implements AbstractDAO<RoleModel>{
	public RoleDAO() {
		super();
	}
	
	public static RoleModel getRoleByID(int id) {
		RoleModel res = new RoleModel();
		try {
			String sql = "select * from role where RoleID = ? limit 1";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				res.setRoleID(rs.getInt(1));
				res.setRoleName(rs.getString(3));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}

	@Override
	public void insert(RoleModel t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(RoleModel t) {
		// TODO Auto-generated method stub
		
	}
}
