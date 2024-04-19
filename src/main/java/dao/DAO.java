package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	protected static Connection conn;
	private static final String db_name = "coffeeapplication";
	private static final String userName = "root";
<<<<<<< HEAD
	private static final String password = "AnThanhPham098@";
=======
	private static final String password = "1312";
>>>>>>> c8e2c45a63a814b870c4b0e7b46f94bcdcae645e

	DAO() {
		if(conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/"+db_name;
				conn = DriverManager.getConnection(url, userName, password);
				System.out.println("connect successfully!");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
