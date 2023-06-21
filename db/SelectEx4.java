package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
public class SelectEx4 {

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
		ResultSet rs=null;
		
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
		/*executeUpdate(String 실행쿼리): insert, update, delete실행시
		 * ㄴ execute로 사용해도 문제 없음
		 *executeQuery(String 실행쿼리) : select 실행시
		 *실행된 row를 반환받는다. 
		 *0이 반환되면 실행결과가 없다
		 *
		 *ResultSet은 select 할때만 사용한다. 
		 * rs.get타입(int 컬럼순서); //첫번째 컬럼은 1, 2번째 컬럼은 2, ... 				
		 * rs.get타입(String 컬럼명);
		 * 컬럼명을 쓰는 것이 일반적이지만,
		 * select절에서는 컬럼명 as 별칭사용시에는 as 절을 사용한다 => 별칭
		 */
		
		//컬럼 별칭 사용 시, 
//		String sql = "select deptno,dname,loc from dept ";
		String sql = "select deptno as dno,dname as 부서명,loc as location "
				+ "from dept "
				+ "where deptno<=93";
		try {
			rs = stmt.executeQuery(sql); 
			System.out.println("부서번호\t\t부서명\t\t위치");
			System.out.println("-------------------------------------------");
			while(rs.next()) { 
				// rs.get타입(int 컬럼순서); //첫번째 컬럼은 1, 2번째 컬럼은 2, ... 				
			/*	int d_no = rs.getInt(1);
				String d_name=rs.getString(2);
				String loc = rs.getString(3);
				System.out.printf("%5d  %s\t%s\r\n",d_no,d_name,loc);
			*/ // rs.get타입(String 컬럼명);
			/*	int d_no = rs.getInt("deptno");
				String d_name=rs.getString("dname");
				String loc = rs.getString("loc");
				System.out.printf("%5d  %s\t%s\r\n",d_no,d_name,loc);
			*/
				//컬럼 별칭 사용시
				int d_no = rs.getInt("dno");
				String d_name=rs.getString("부서명");
				String loc = rs.getString("location");
				System.out.printf("%5d  %s\t%s\r\n",d_no,d_name,loc);
				
			
			
			}
		} catch (SQLException e1) {
			System.out.println("executeQuery 에러발생=");
			e1.printStackTrace();
		}
			
		//5. 자원반납 /자원해제 : 최근에 사용한것부터 닫아준다. 
		try {
			if(rs!=null) {rs.close();}  //null 
			if(stmt!=null) {stmt.close();}
			if(conn!=null) {conn.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("5.자원반납~");
	}//main

}
