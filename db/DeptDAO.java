package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//dao(data acess object): db에 접근하여(Access) 쿼리실행 등을 하는 클래스
public class DeptDAO {
	//field
	//constructor

	//method
	//입력  insert
	public void insertDept(Connection conn,String dname, String loc) { 
		PreparedStatement stmt=null;
		String sql = "insert into dept(deptno,dname,loc) " //여기는 쿼리문의 컬럼명, 모든 컬럼 넣을때는 ( )내용 생략해도 됨
				+  "values(seq_dno.NEXTVAL,?,?)";
		
		//3.객체준비
		try {
			stmt = conn.prepareStatement(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//4.쿼리실행
		try {
			stmt.setString(1,dname);
			stmt.setString(2,loc);
			
			int cnt = stmt.executeUpdate();
			if(cnt>0) {
				System.out.println("쿼리실행성공="+cnt); //1 1개 실행문  
			}
		} catch (SQLException e1) {
			System.out.println("executeUpdate 에러발생=");
			e1.printStackTrace();
		}
		
		//5. 자원 반납
		JDBCUtill.close(stmt);
	}//입력하기
	
	//수정-시작
	public void updateDept(Connection conn,String dname, String location, int dnum ) {
		PreparedStatement stmt=null;
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
		}finally {
			//5.자원반납
		JDBCUtill.close(stmt);
		}
	}//수정-끝

	
	//삭제
	public void deleteDept(Connection conn, int deptno) {
		PreparedStatement stmt=null;
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
		}finally {
			//5.자원반납
		JDBCUtill.close(stmt);
		}
			
	}
	
	//부서번호 상세조회
	/*파라미터
	 * Connection conn : 커넥션 객체
	 * int dno : 조회하고싶은 부서번호
	 * 리턴유형
	 * DeptDno : 부서정보
	 */
	public DeptDTO getDeptByDeptno (Connection conn, int d) {
		PreparedStatement stmt=null;
		ResultSet rs=null; //conn과 달리 매번 달라질 수 있기때문에 매개변수 아니고 아래 선언
		String sql = "select deptno as dno,dname as name,loc as location "
				+  "from dept "
				+ "where deptno=?";
		DeptDTO deptDTO=null;//select결과를 row(행,record)단위로 저장하기위한 객체선언 및 초기화
		
		
		//4.쿼리문 실행
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, d);
			rs = stmt.executeQuery(); 
			
			//select 결과가 존재하면
			if(rs.next()) { 
				//컬럼에 접근에 값을 가져와 변수에 저장
				int d_no = rs.getInt("dno");
				String d_name=rs.getString("name");
				String loc = rs.getString("location");
				//저장된 변수의 값을 DeptDTO 객체로 생성, deptno 하나별 다 다른 객체
				//new DeptDTO(int deptno, String dname,String loc)
				deptDTO = new DeptDTO(d_no,d_name,loc);
				
			}	
			
		} catch (SQLException e1) {
			System.out.println("executeQuery 에러발생=");
			e1.printStackTrace();
		}finally {
			//5.자원반납
			JDBCUtill.close(rs);
			JDBCUtill.close(stmt);
		}
		return deptDTO;
	}//getDeptByDeptno()상세조회 끝
	
	//부서명목록조회
	public List<String> getDeptNameList(Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();; //부서명목록을 저정할 변수선언 및 초기화
		String sql = "select dname " + 
					 "from dept " + 
				     "order by dname asc";
		
		//3.객체준비
		try {
			stmt = conn.prepareStatement(sql);
		} catch (SQLException e1) {
			System.out.println("객체관련 에러발생="+e1);
			e1.printStackTrace();
		}
		
		//4.쿼리실행
		try {
			rs = stmt.executeQuery();
			//select결과가 존재동안
			while(rs.next()) {
				//컬럼에 접근하여 값을 가져와  목록에 추가
				list.add(rs.getString("dname"));
			}
			
		} catch (SQLException e1) {
			System.out.println("executeUpdate()실행관련 에러발생");
			e1.printStackTrace();
		}
		return list;
	}//부서명목록조회
	
		//모든부서조회 시작
		public List<DeptDTO> getDeptList(Connection conn) { 
			PreparedStatement stmt=null;
			ResultSet rs=null; //conn과 달리 매번 달라질 수 있기때문에 매개변수 아니고 아래 선언
			List<DeptDTO> list =new ArrayList<DeptDTO>(); ///가져올 컬럼이 2개 이상이면 DTO로 하면 됨.왜? DTO는 클래스. 걍 외워!
														//부서 목록을 저장할 변수 선언 및 초기화
			String sql = "select deptno, dname, loc "
						+"from dept "
						+"order by deptno asc";
			
			//3.객체준비
			try {
				stmt = conn.prepareStatement(sql);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}				

			try {
				rs = stmt.executeQuery(); 
				
				//select 결과가 존재하면 가져와->반복
				//컬럼에 접근하여 값을 가져와 -> DeptDto객체를 생성하여 ->목록에 추가
				while(rs.next()) { 
					//컬럼에 접근하여 값을 가져와 변수에 저장
					int deptno = rs.getInt("deptno");
					String dname=rs.getString("dname");
					String loc=rs.getString("loc");
					
					//변수에 저장된 값을 이용하여 DeptDTO객체를 생성
					DeptDTO deptDTO = new DeptDTO(deptno, dname, loc);
					//생성된 DeptDTO 객체를 목록에 추가
					list.add(deptDTO);
				}	
				
			} catch (SQLException e1) {
				System.out.println("executeQuery 에러발생=");
				e1.printStackTrace();
			}finally {
				//5.자원반납
				JDBCUtill.close(rs);
				JDBCUtill.close(stmt);
			}
			return list;
		}//모든부서조회 끝	

		
		
	}


