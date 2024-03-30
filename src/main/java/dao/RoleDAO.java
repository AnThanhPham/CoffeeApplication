package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	
	public static ArrayList<RoleModel> findAll(){
		ArrayList<RoleModel> res = new ArrayList<>();
		try {
			String sql = "select * from role";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				RoleModel tmp = new RoleModel();
				tmp.setRoleID(rs.getInt(1));
				tmp.setCreator(rs.getString(2));
				tmp.setRoleName(rs.getString(3));
				res.add(tmp);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}
	
	public static RoleModel findByRoleName(String roleName) {
		RoleModel res = new RoleModel();
		try {
			String sql = "select * from role where RoleName = ? limit 1";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, roleName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				res.setRoleID(rs.getInt(1));
				res.setCreator(rs.getString(2));
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

	@Override
	public void update(RoleModel t) {
		// TODO Auto-generated method stub
		
	}
}
