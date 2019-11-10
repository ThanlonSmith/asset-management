package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {

	public static Connection getConnection() {
		Connection conn = null;// 数据库连接
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载数据库驱动，注册到驱动管理器
			String url = "jdbc:mysql://localhost:3306/asset?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
			String username = "root"; // 数据库用户名
			String password = "root"; // 数据库密码
			conn = DriverManager.getConnection(url, username, password);// 创建Connection连接
		} catch (ClassNotFoundException e) {
			System.out.println("加载驱动出现异常");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("链接数据库出现异常");
			e.printStackTrace();
		}
		return conn;
	}
}
