package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.UserModel;

public class UserDAO extends DAO implements AbstractDAO<UserModel> {
	public UserDAO() {
		super();
	}
	
	public ArrayList<UserModel> findAll(){
		ArrayList<UserModel> res = new ArrayList<>();
    	try {
    		String sql = "select * from user";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()) {
    			UserModel tmp = new UserModel();
    			tmp.setID(rs.getInt(1));
    			tmp.setUserName(rs.getString(2));
    			tmp.setPhone(rs.getString(3));
    			tmp.setAddress(rs.getString(4));
    			if(rs.getString(5)!=null) {
    				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    				java.util.Date utilDate = dateFormat.parse(rs.getString(5));
    				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    				tmp.setDateWork(sqlDate);    				
    			}
    			tmp.setPassword(rs.getString(7));
    			tmp.setFullName(rs.getString(8));
    			tmp.setRole(RoleDAO.getRoleByID(rs.getInt(9)));
    			tmp.setCode(rs.getString(10));
    			tmp.setStatus(rs.getString(11));
    			tmp.setEmail(rs.getString(12));
    			tmp.setGender(rs.getString(13));
    			tmp.setImage(rs.getString(14));
    			res.add(tmp);
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return res;
	}
	
	@Override
	public void insert(UserModel t) {
		String sql = AbstractImpl.buildSqlInsertUser(t);
		System.out.println(sql);
		try {
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
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
	
	public boolean checkDuplicateUser(String user){
        boolean duplicate = false;
        try {
			PreparedStatement ps = conn.prepareStatement("select ID from user where UserName=? limit 1");
			ps.setString(1, user);
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

    public boolean checkDuplicateEmail(String user){
        boolean duplicate = false;
        try {
			PreparedStatement ps = conn.prepareStatement("select ID from user where Email=? limit 1");
			ps.setString(1, user);
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
	
	public void insertCode(int userID,String code) {
		try {
			PreparedStatement ps = conn.prepareStatement("update user set code= ? where ID=? limit 1");
			ps.setString(1, code);
			ps.setInt(2, userID);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
    	UserModel res = null;
    	try {
    		String sql = "select * from user where username = ? and password = ? limit 1";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, username);
    		ps.setString(2, password);
    		ResultSet rs = ps.executeQuery();
    		UserModel tmp = new UserModel();
    		if(rs.next()) {
    			tmp.setID(rs.getInt(1));
    			tmp.setUserName(rs.getString(2));
    			tmp.setPhone(rs.getString(3));
    			tmp.setAddress(rs.getString(4));
    			tmp.setFullName(rs.getString(8));
    			tmp.setRole(RoleDAO.getRoleByID(rs.getInt(9)));
    			tmp.setEmail(rs.getString(12));
    			tmp.setCode(rs.getString(10));
    			tmp.setStatus(rs.getString(11));
    		}
    		if(tmp.getID()!=null) {
    			res = tmp;
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return res;
    }
    
    public UserModel findByEmail(String email) {
    	UserModel res = null;
    	try {
    		String sql = "select * from user where email = ? limit 1";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, email);
    		ResultSet rs = ps.executeQuery();
    		UserModel tmp = new UserModel();
    		if(rs.next()) {
    			tmp.setID(rs.getInt(1));
    			tmp.setUserName(rs.getString(2));
    			tmp.setPhone(rs.getString(3));
    			tmp.setAddress(rs.getString(4));
    			if(rs.getString(5)!=null) {
    				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    				java.util.Date utilDate = dateFormat.parse(rs.getString(5));
    				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    				tmp.setDateWork(sqlDate);    				
    			}
    			tmp.setPassword(rs.getString(7));
    			tmp.setFullName(rs.getString(8));
    			tmp.setRole(RoleDAO.getRoleByID(rs.getInt(9)));
    			tmp.setCode(rs.getString(10));
    			tmp.setStatus(rs.getString(11));
    			tmp.setEmail(rs.getString(12));
    			tmp.setGender(rs.getString(13));
    			tmp.setImage(rs.getString(14));
    		}
    		if(tmp.getID()!=null) {
    			res = tmp;
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return res;
    }
    
    public UserModel findByID(String id) {
    	UserModel res = null;
    	try {
    		String sql = "select * from user where id = ? limit 1";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, id);
    		ResultSet rs = ps.executeQuery();
    		UserModel tmp = new UserModel();
    		if(rs.next()) {
    			tmp.setID(rs.getInt(1));
    			tmp.setUserName(rs.getString(2));
    			tmp.setPhone(rs.getString(3));
    			tmp.setAddress(rs.getString(4));
    			if(rs.getString(5)!=null) {
    				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    				java.util.Date utilDate = dateFormat.parse(rs.getString(5));
    				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    				tmp.setDateWork(sqlDate);    				
    			}
    			tmp.setPassword(rs.getString(7));
    			tmp.setFullName(rs.getString(8));
    			tmp.setRole(RoleDAO.getRoleByID(rs.getInt(9)));
    			tmp.setCode(rs.getString(10));
    			tmp.setStatus(rs.getString(11));
    			tmp.setEmail(rs.getString(12));
    			tmp.setGender(rs.getString(13));
    			tmp.setImage(rs.getString(14));
    		}
    		if(tmp.getID()!=null) {
    			res = tmp;
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return res;
    }
    
    public ArrayList<UserModel> findByFullname(String fullname) {
    	ArrayList<UserModel> res = new ArrayList<>();
    	try {
    		String sql = "select * from user where fullname like CONCAT('%',?,'%')";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, fullname);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()) {
    			UserModel tmp = new UserModel();
    			tmp.setID(rs.getInt(1));
    			tmp.setUserName(rs.getString(2));
    			tmp.setPhone(rs.getString(3));
    			tmp.setAddress(rs.getString(4));
    			if(rs.getString(5)!=null) {
    				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    				java.util.Date utilDate = dateFormat.parse(rs.getString(5));
    				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    				tmp.setDateWork(sqlDate);    				
    			}
    			tmp.setPassword(rs.getString(7));
    			tmp.setFullName(rs.getString(8));
    			tmp.setRole(RoleDAO.getRoleByID(rs.getInt(9)));
    			tmp.setCode(rs.getString(10));
    			tmp.setStatus(rs.getString(11));
    			tmp.setEmail(rs.getString(12));
    			tmp.setGender(rs.getString(13));
    			tmp.setImage(rs.getString(14));
    			res.add(tmp);
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return res;
    }

    public boolean checkEmailAndOldPassWord(String email,String oldPassword) {
    	try {
    		String sql = "select * from user where email = ? and password = ? limit 1";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, email);
    		ps.setString(2, oldPassword);
    		ResultSet rs = ps.executeQuery();
    		if(rs.next()) {
    			return true;
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return false;
    }
    
    public void changePassword(String email,String newPass) {
    	try {
    		String sql = "update user set password = ? where email = ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, newPass);
    		ps.setString(2, email);
    		ps.executeUpdate();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    
	@Override
	public void delete(UserModel t) {
		try {
    		String sql = "delete from user where id = ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setInt(1, t.getID());
    		ps.executeUpdate();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
	}

	@Override
	public void update(UserModel t) {
		try {
    		String sql = AbstractImpl.buildSqlUpdateUser(t);
    		System.out.println(sql);
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.executeUpdate();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
	}

}