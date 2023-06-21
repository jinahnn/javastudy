package db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

//scott 계정의 UPDATE_SAL_PROC 사용자정의함수 호출 클래스
public class FunctionEx {

	public static void main(String[] args) {
		//1. JDBC Driver 등록
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("1.JDBC Driver 등록");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//2. 연결 connection 얻기
		String url= "jdbc:oracle:thin:@localhost:1521/xe";
		String user = "scott";
		String password="tiger";
		Connection conn = null; //connection은 인터페이스
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("2.연결connection 얻기-성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//3.객체준비 => throws절 처리
		//String sql ="{? = call update_sal_fc (?,?)";
		String sql = "{? = call update_sal_fc (?,?)";
		CallableStatement stmt=null;
		try {
			stmt=conn.prepareCall(sql);
			stmt.registerOutParameter(1, Types.NUMERIC);
			stmt.setInt(2, 7369);
			stmt.setInt(3, 600);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//4.쿼리실행 //exec UPDATE_SAL_PROC(7369,300);
		
		int result=stmt.getInt(1);
		
		//5.자원반납
			try {
				if(stmt!=null) {stmt.close();}
				if(conn!=null) {conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	
		
		
	}


