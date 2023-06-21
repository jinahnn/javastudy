package db;
//DTO: Data Transfer Object =>getter,setter로 변수 조정 가능                                //1월은 0부터 시작 10=>9
//new deptDTO(int deptno,String name, String loc)
//new deptDTO(int deptno,String name, String loc)
//new deptDTO(0,"부서1","대전")
//new deptDTO("부서1","대전")

//VO: Value Object =>getter : 한번 값을 넣어주면 가져다 쓰기만 하는데 사용,불변성(set메소드 없다)

public class DeptDTO {
	//field
	private int deptno; //부서번호  number deptno //필드명이 컬렴명과 달라도 된다.
	private String dname; //부서명 varchar2 dname
	private String loc ; //위치   varchar2 loc
	
	//constructor
	//이렇게 해도 됨. but deptno는 notnull 값이라서 여기서 안넣으면 실행할때 꼭 넣어야힘.
	public DeptDTO(String dname, String loc) {
		super();
		this.dname = dname;
		this.loc = loc;
	}
	public DeptDTO(int deptno, String dname, String loc) {
		super();
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
	}

	//getter&setter
	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}
	@Override
	public String toString() {
		return "DeptDTO [deptno=" + deptno + ", dname=" + dname + ", loc=" + loc + "]";
	}
	
	
	
	
}
