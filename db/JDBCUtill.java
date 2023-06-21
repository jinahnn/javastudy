package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//db연동을 위한 클래스이다
public class JDBCUtill {
	//field
	static String url= "jdbc:oracle:thin:@localhost:1521/xe";
	static	String user = "scott";
	static	String password="tiger";
		
	//constructor
	
	//method
		public static Connection getConnection() {
			Connection conn = null; //connection은 인터페이스
			//1. JDBC 등록
			try {
				Class.forName("oracle.jdbc.OracleDriver");
			//	System.out.println("1.JDBC Driver 등록");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			//2. connection 연결 
			
			try {
				conn = DriverManager.getConnection(url, user, password);
				//System.out.println("2.연결connection 얻기-성공");
			} catch (SQLException e1) {
				System.out.println("객체관련 에러발생="+e1);
				e1.printStackTrace();
			}		
			return conn;
		}//getDBConnection()

		//connection 반납 자원반납
		public static void close (Connection conn) {
			try {
				if(conn!=null) {conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Connection 자원반납");
		}
		
		//PreapredStatement 반납
		public static void close (PreparedStatement stmt) {
			try {
				if(stmt!=null) {stmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Connection 자원반납");
		}
		
		//Statement 반납
		public static void close (Statement stmt) {
			try {
				if(stmt!=null) {stmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Connection 자원반납");
		}
		//ResultSet 반납
		public static void close (ResultSet rs) {
			try {
				if(rs!=null) {rs.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Connection 자원반납");
		}
		
		
		
}
