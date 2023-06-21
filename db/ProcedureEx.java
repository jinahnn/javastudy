package db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//scott 계정의 UPDATE_SAL_PROC 프로시저 실행 클래스
public class ProcedureEx {

	public static void main(String[] args) throws SQLException {
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
		//String sql = "{CALL 프로시저명(?,...?)";
		String sql = "{CALL 프로시저명(?,?)";
		CallableStatement stmt=conn.prepareCall(sql);
		
		//4.쿼리실행 //exec UPDATE_SAL_PROC(7369,300);
		stmt.setInt(1, 7369);
		stmt.setInt(2, 7369);
		boolean result=stmt.execute();
		if(result) {
			System.out.println("실행성공");//콘솔출력
		}else {
			System.out.println("실패");//콘솔출력
		}finally {
			
			//5.자원반납
			try {
				if(stmt!=null) {stmt.close();}
				if(conn!=null) {conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	
		
		
	}

}
