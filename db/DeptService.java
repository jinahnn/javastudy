package db;
//JDBCUtil : 공통모듈
//DTO 
//Main<->Service<->DAO<->DB
import java.sql.Connection;
import java.util.List;

//Main->Service->DAO호출 <->DAO가 DB연동, 
public class DeptService {
	//field
	DeptDAO deptDao = new DeptDAO();
	Connection conn = JDBCUtill.getConnection();
	//constructor
	
	//method
	/*파라미터
	 * int dno : 조회하고 싶은 부서번호
	 *리턴유형
	 * DeptDTO : 부서정보
	 */
	
	//부서번호 상세조회
	public DeptDTO getDeptByDeptno (int dno) { //매개변수의 변수명 > main에서 호출 변수)
			Connection conn = JDBCUtill.getConnection();
			DeptDTO deptDTO = deptDao.getDeptByDeptno(conn, dno);
			return deptDTO;
	}
	
	//부서명 목록조회
	public List<String> getDeptNameList(){
		Connection conn = JDBCUtill.getConnection();
		List<String> dnameList=  deptDao.getDeptNameList(conn);
		return dnameList;
	}
	
	//모든 부서 조회
	public List<DeptDTO> getDeptList(){
		Connection conn = JDBCUtill.getConnection();
		List<DeptDTO> deptList =deptDao.getDeptList(conn);
		return deptList;
	}
	//입력
	public void insertDept(String dname, String loc) {
		Connection conn = JDBCUtill.getConnection();
		deptDao.insertDept(conn, dname, loc);
	}

	//수정 
	public void updateDept(String dname, String location,int dnum ) {
		Connection conn = JDBCUtill.getConnection();
		deptDao.updateDept(conn, dname, location, dnum);
	}
		
	//삭제
	public void deleteDept(int deptno) {
		Connection conn = JDBCUtill.getConnection();
		deptDao.deleteDept(conn, deptno);
		
	}
	
	
	
	
	
}
