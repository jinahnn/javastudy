package db;

import java.sql.Connection;
import java.util.List;

//JDBCUtill과 연동
public class DBMain {

	public static void main(String[] args) {
		//1. JDBC Driver 등록 & 2. 연결 connection 얻기
		Connection conn = JDBCUtill.getConnection(); //=> JDBCUtill 에서 connection 메소드를 static으로 선언, 클래스명으로 불러온다.
		//JDBCUtill jdbc = new JDBCUtill();
		//Connection conn = jdbc.getConnection();
		
		
		//3.객체준비 & 4. 쿼리실행
		// 입력 > 목록조회 > 수정 > 상세조회 > 삭제
		//*입력
		DeptDAO deptDao = new DeptDAO();
		//deptDao.insertDept(conn, "해외영업", "뉴욕"); //DeptDAO에서 시퀀스를 통해 부서 자동증가 시켜놓음.시퀀스없으면 컴파일에러
		//*수정
		deptDao.updateDept(conn,"마케팅","서울",68);
		//*삭제
		//deptDao.deleteDept(conn,61);
		//*상세조회
		DeptDTO deptDTO = deptDao.getDeptByDeptno(conn, 70);
		
		if(deptDTO!=null) {
		System.out.println("부서번호\t\t부서명\t\t위치");
		System.out.println("-------------------------------------------");
		System.out.printf("%5d  %14s\t %13s\r\n",
				deptDTO.getDeptno(),
				deptDTO.getDname(),
				deptDTO.getLoc());
		}else{
			System.out.println("번 부서가 존재하지 않습니다");
		}
		
		//부서명목록조회
		System.out.println();
		System.out.println("--부서명목록조회 결과------");		
		List<String> list = deptDao.getDeptNameList(conn);
		for(int i=0;i<list.size();i++) {
			String dname = list.get(i);
			System.out.println(dname);
		}
		
		//모든 부서 조회
		System.out.println();
		System.out.println("----모든 부서 조회 결과 ---");
		
		List<DeptDTO> deptList =deptDao.getDeptList(conn);
		if(deptList.size()>0) {
			System.out.println("부서번호\t\t부서명\t\t위치");
			System.out.println("-------------------------------------------");
			for(DeptDTO data: deptList) {
				System.out.println(data.toString());//DTO에서 오버라이딩후 toString()호출
				//	System.out.println(data); //주소지 출력됨
			
				/*System.out.printf("%5d  %14s\t %13s\r\n",
						data.getDeptno(),
						data.getDname(),
						data.getLoc());
				*/
				
				}//for문
			}else{
					System.out.println("부서가 존재하지 않습니다");
				}
				
		
		
		//5.conn 자원반납
		JDBCUtill.close(conn);//static이라 클래스명을 통해서 클로즈 해도 된다
							  
		
	}
}
