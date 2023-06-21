package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//한페이지에 작업하기
public class DMLEx {

	//field
	String url= "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "scott";
	String password="tiger";
	Connection conn = null; //connection은 인터페이스
	PreparedStatement stmt = null;
	ResultSet rs=null;
	//constructor
	
	//method 
	//1.JDBC Driver 등록 & 2.연결 Connection 얻기
		public void getDBConnection() {
			//1. JDBC 등록
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				System.out.println("1.JDBC Driver 등록");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			//2. connection 연결 
			
			try {
				conn = DriverManager.getConnection(url, user, password);
				System.out.println("2.연결connection 얻기-성공");
			} catch (SQLException e1) {
				System.out.println("객체관련 에러발생="+e1);
				e1.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			//1.JDBC 등록 & 2.연결 Connection 얻기
			DMLEx obj = new DMLEx(); //클래스 생성자
			obj.getDBConnection(); //위에 static 타입이 아니라서 그냥 불러오지 못하고 객체 생성하고 호출해야함
		
			//3. 객체생성 & 4.쿼리문 실행
			//목록조회 -> 입력 -> 목록조회-> 수정 -> 상세조회-> 삭제
			System.out.println();
			System.out.println("---insert 전 목록조회결과-------");
			//obj.getDeptList();
			
			System.out.println();
			System.out.println("---부서정보입력-------");
		//	obj.insertDept(97,"개발부97","제주");
			
			System.out.println();
			System.out.println("---insert 후 목록조회결과-------");
			//obj.getDeptList();
			
			//수정
			System.out.println();
			System.out.println("---수정하기 -------");
			//obj.updateDept("해외영업","서울", 97);
			
			
			System.out.println();
			System.out.println("---부서 상세조회 결과-------");
			obj.getDept(97);
			
			System.out.println();
			System.out.println("---삭제하기---");
			obj.deleteDept(97);
					
			//5. 자원반납 
			//Connection 자원반납
			obj.connClose();
	}//main
		// 수정하기 => 매개변수에 쿼리실행시 주는 변수 선언
		public void updateDept(String dname, String location, int dnum ) {
			String sql = "update dept "
					+"set dname=? ,loc=? "
					+"where deptno=?";
			
			//3.객체준비
			try {
				stmt = conn.prepareStatement(sql);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			//4.쿼리실행
			try {
				//?없음 set 안해도됨
				stmt.setString(1,dname); //자바에서다루는데이터타입(순서,값);
				stmt.setString(2,location); 
				stmt.setInt(3,dnum); 
				
				int cnt = stmt.executeUpdate();
				if(cnt>0) {
					System.out.println("쿼리실행성공="+cnt); //1 1개 실행문  
				}
			} catch (SQLException e1) {
				System.out.println("executeUpdate 에러발생=");
				e1.printStackTrace();
			}
			//5.자원반납
			try {
				if(stmt!=null) {stmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("5.자원반납~");
	
		
		}
		//삭제하기 
		public void deleteDept(int deptno) {
			String sql= "delete dept "
					+ "where deptno=?";
		//3.객체준비
			try {
				stmt = conn.prepareStatement(sql);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}				
		//4.쿼리실행
			try {
				stmt.setInt(1,deptno);
				int cnt = stmt.executeUpdate();
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("5.자원반납~");
			
		}
		
				
		//입력하기   insertDept(96,'개발부96','우도')
		public void insertDept(int dno, String dname, String loc) { //여기는 변수명
			String sql = "insert into dept(deptno,dname,loc) " //여기는 쿼리문의 컬럼명, 모든 컬럼 넣을때는 ( )내용 생략해도 됨
					+  "values(?,?,?)";
			
			//3.객체준비
			try {
				stmt = conn.prepareStatement(sql);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			//4.쿼리실행
			try {
				stmt.setInt(1,dno);
				stmt.setString(2,dname);
				stmt.setString(3,loc);
				
				int cnt = stmt.executeUpdate();
				if(cnt>0) {
					System.out.println("쿼리실행성공="+cnt); //1 1개 실행문  
				}
			} catch (SQLException e1) {
				System.out.println("executeUpdate 에러발생=");
				e1.printStackTrace();
			}
			
			//5. 자원 반납
			try {
				if(stmt!=null) {stmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
		
		
		
		//부서 번호에 따른 상세조회 
		public void getDept (int d) {
			String sql = "select deptno as dno,dname as name,loc as location "
					+  "from dept "
					+ "where deptno=?";
			
			//3. 객체 준비
			try {
				stmt = conn.prepareStatement(sql);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			//4.쿼리문 실행
			try {
				stmt.setInt(1, d);
				rs = stmt.executeQuery(); 
				
				System.out.println("부서번호\t\t부서명\t\t위치");
				System.out.println("-------------------------------------------");
				while(rs.next()) { 
					int d_no = rs.getInt("dno");
					String d_name=rs.getString("name");
					String loc = rs.getString("location");
					System.out.printf("%5d  %14s\t %13s\r\n",d_no,d_name,loc);
				}	
				
			} catch (SQLException e1) {
				System.out.println("executeQuery 에러발생=");
				e1.printStackTrace();
			}
			//5. 자원반납 /자원해제 : 최근에 사용한것부터 닫아준다. 
			try {
				if(rs!=null) {rs.close();}  
				if(stmt!=null) {stmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		//목록조회: 값 가져올때는 보통 get카워드 자주 씀
		public void getDeptList() {
			String sql = "select deptno as dno,dname as name,loc as location "
					+ "from dept";
			
			//3. 객체 준비
			try {
				stmt = conn.prepareStatement(sql);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		
			//4.쿼리문 실행
			try {
				rs = stmt.executeQuery(); 
			
				System.out.println("부서번호\t\t부서명\t\t위치");
				System.out.println("-------------------------------------------");
				while(rs.next()) { 
					int d_no = rs.getInt("dno");
					String d_name=rs.getString("name");
					String loc = rs.getString("location");
					System.out.printf("%5d  %14s\t %13s\r\n",d_no,d_name,loc);
					}	
								
			} catch (SQLException e1) {
				System.out.println("executeQuery 에러발생=");
				e1.printStackTrace();
			}
			//5. 자원반납 /자원해제 : 최근에 사용한것부터 닫아준다. 
			try {
				if(rs!=null) {rs.close();}  //null 
				if(stmt!=null) {stmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		//수정하기public 리턴유형 메서드명(매개변수리스트) {}
		
		//삭제하기public 리턴유형 메서드명(매개변수리스트) {}
		
		
		//5. Connection 자원반납 
		public void connClose() {
				try {
					if(conn!=null) {conn.close();}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("Connection 자원반납");
			}
	}
