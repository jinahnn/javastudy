package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//DBConnection 테스트용 클래스이다
/* 라이브러리 등록 - 해당 프로젝트 build path > Add External JARs >ojdbc6.java
*	1. JDBC Driver 등록
*	2. 연결 Connection 얻기
*	3. 객체 준비 
*	4. 쿼리 실행
*	5. 자원 반납
*/
public class UpdateEx2 {

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
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("2.연결connection 얻기-성공");
		} catch (SQLException e1) {
			System.out.println("객체관련 에러발생="+e1);
			e1.printStackTrace();
		}
		//3. 객체준비
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//4. 쿼리실행 
		//주의 : 공백, ; 세미콜론, 문법 : 공백은 한줄 넘어가기 전 문장 마지막 꼭 띄워야함!
		//executeUpdate(String 실행쿼리): insert, update, delete실행시 
		//실행된 row를 반환받는다. 
		//0이 반환되면 실행결과가 없다
		String sql = "update dept "
				+ "set loc='쿠바', dname='개발2부' "
				+ "where deptno=96";
		try {
			int cnt = stmt.executeUpdate(sql);
			if(cnt>0) {
				System.out.println("쿼리실행성공="+cnt); //1 1개 실행문  
			}
		} catch (SQLException e1) {
			System.out.println("executeUpdate 에러발생=");
			e1.printStackTrace();
		}
			
		//5. 자원반납
		try {
			if(stmt!=null) {stmt.close();}
			if(conn!=null) {conn.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("5.자원반납~");
	}//main

}
