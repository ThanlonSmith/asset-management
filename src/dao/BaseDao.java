package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {

	public static Connection getConnection() {
		Connection conn = null;// ���ݿ�����
		try {
			Class.forName("com.mysql.jdbc.Driver"); // �������ݿ�������ע�ᵽ����������
			String url = "jdbc:mysql://localhost:3306/asset?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
			String username = "root"; // ���ݿ��û���
			String password = "root"; // ���ݿ�����
			conn = DriverManager.getConnection(url, username, password);// ����Connection����
		} catch (ClassNotFoundException e) {
			System.out.println("�������������쳣");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("�������ݿ�����쳣");
			e.printStackTrace();
		}
		return conn;
	}
}
