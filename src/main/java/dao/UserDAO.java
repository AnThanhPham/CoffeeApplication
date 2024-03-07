package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.UserModel;

public class UserDAO extends DAO implements AbstractDAO<UserModel> {
	public UserDAO() {
		super();
	}

	@Override
	public void insert(UserModel t) {
		String sql = "insert into user(username,password,roleID,code,email) values (?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, t.getUserName());
			ps.setString(2, t.getPassword());
			ps.setInt(3, 1);
			ps.setString(4, t.getCode());
			ps.setString(5, t.getEmail());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.first();
			int userID = rs.getInt(1);
			t.setID(userID);
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean checkDuplicateCode(String code) {
		boolean duplicate = false;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from user where code=? limit 1");
			ps.setString(1, code);
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
	
	public void doneVerify(int userID){
        try {
			PreparedStatement ps = conn.prepareStatement("update user set code='', status='Verified' where ID=? limit 1");
			ps.setInt(1, userID);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public boolean verifyCodeWithUser(int userID, String code){
    	boolean verify = false;
        try {
			PreparedStatement p = conn.prepareStatement("select * from user where ID=? and code=? limit 1");
			p.setInt(1, userID);
			p.setString(2, code);
			ResultSet r = p.executeQuery();
			if (r.next()) {
			    verify = true;
			}
			r.close();
			p.close();
		} catch (SQLException e) {
			verify = false;
			e.printStackTrace();
		}
        return verify;
    }
    
    public UserModel findByUserNameAndPassword(String username,String password) {
    	UserModel res = new UserModel();
    	try {
    		String sql = "select * from user where username = ? and password = ? and status = 'Verified' limit 1";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, username);
    		ps.setString(2, password);
    		ResultSet rs = ps.executeQuery();
    		if(rs.next()) {
    			res.setID(rs.getInt(1));
    			res.setUserName(rs.getString(2));
    			res.setPhone(rs.getString(3));
    			res.setAddress(rs.getString(4));
    			res.setFullName(rs.getString(8));
    			res.setRole(RoleDAO.getRoleByID(rs.getInt(9)));
    			res.setEmail(rs.getString(12));
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return res;
    }

	@Override
	public void delete(UserModel t) {

	}

}
