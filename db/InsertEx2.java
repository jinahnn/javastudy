package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//DBConnection 테스트용 클래스이다
/* 라이브러리 등록 - 해당 프로젝트 build path > Add External JARs >ojdbc6.java
*	1. JDBC Driver 등록
*	2. 연결 Connection 얻기
*	3. 객체 준비 
*	4. 쿼리 실행
*	5. 자원 반납
*/
public class InsertEx2 {

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
		PreparedStatement stmt=null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("2.연결connection 얻기-성공");
		} catch (SQLException e1) {
			System.out.println("객체관련 에러발생="+e1);
			e1.printStackTrace();
		}
	/*	String sql = "insert into dept(deptno,dname,loc) "
				+  "values("+inputDNO+",'"+inputDname+"','"+inputLoc+"')"; */
		String sql = "insert into dept(deptno,dname,loc) "
				+  "values(?,?,?)";
		
		//3. 객체준비
		try {
			//Statement stmt = conn.createStatement();
			stmt = conn.prepareStatement(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//4. 쿼리실행 
		//주의 : 공백, ; 세미콜론, 문법
		//executeUpdate(String 실행쿼리): insert, update, delete실행시 
		//실행된 row를 반환받는다. 
		//0이 반환되면 실행결과가 없다
		
		int inputDNO = 95;
		String inputDname= "개발부95";
		String inputLoc= "삼척";
		
		try {
			//set데이터타입(?순서 ,값 )  
			stmt.setInt(1,inputDNO); //변수대신 리터럴 값 넣어도 됨
			stmt.setString(2, inputDname);
			stmt.setString(3, inputLoc); 
			
			int cnt = stmt.executeUpdate(); //3.객체준비에서 sql이미 선언, ()에 안넣어도 됨 
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
