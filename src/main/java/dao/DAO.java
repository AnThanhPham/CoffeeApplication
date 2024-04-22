package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	protected static Connection conn;
	private static final String db_name = "coffeeapplication";
	private static final String userName = "root";
<<<<<<< HEAD
	private static final String password = "A123456@";
=======
	private static final String password = "12345";
>>>>>>> 4355d9c109e3c53e06aa2b212e713fe874359b15

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