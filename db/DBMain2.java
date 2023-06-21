package db;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//JDBCUtill과 연동
public class DBMain2 {

	public static void main(String[] args) {
				
		//Service로 이동 되었음 : JDBC 등록, 연결, 객체 준비
		//Connection conn = JDBCUtill.getConnection();
		//3.객체준비 & 4. 쿼리실행
		//DeptDAO deptDao = new DeptDAO();
	
		Scanner sc = new Scanner(System.in);
		boolean isRunning = true;
		DeptService deptService = new DeptService();
		
		
		
		System.out.println("==============<<부서관리시스템>==============");
		System.out.println("1.모든부서조회\t2.부서명목록조회\t3.부서상세조회");
		
		while(isRunning) { //시작하자마자 반복문 처리는 true값 넣어줘야하지
			System.out.println();
			System.out.println("메뉴: 1.모든부서조회 2.부서명목록조회 3.부서상세조회 4.입력 5. 수정 0.종료");
			System.out.print("메뉴 번호를 입력하세요: ");
		try {
				int menuNo = sc.nextInt(); //int형태로 다음값을 받겠다.
				switch(menuNo) {
				case 1:	System.out.println("1.모든부서조회.");	//모든부서조회
						
				List<DeptDTO> deptList =deptService.getDeptList();
				if(deptList.size()>0) {
				System.out.println("부서번호\t\t부서명\t\t위치");
					System.out.println("-------------------------------------------");
					for(DeptDTO data: deptList) {
						System.out.println(data.toString());//DTO에서 오버라이딩후 toString()호출

					}//for문
					}else{
							System.out.println("부서가 존재하지 않습니다");
					}
				break;
				case 2: System.out.println("2.부서명목록조회");//부서명 목록조회
					
				//Service 클래스의 메서드 호출getDeptNameList()호출
					List<String> list =deptService.getDeptNameList();
					for(int i=0;i<list.size();i++) {
						String dname = list.get(i);
						System.out.println(dname);
					}break;
						
					
				case 3: System.out.println("조회할 부서번호: ");	//부서상세조회
						int deptNo = sc.nextInt(); //조회할 부서번호 입력
						//Service 클래스의 메서드 호출 getDeptByDeptno메서드호출
						DeptDTO deptDTO = deptService.getDeptByDeptno(deptNo);
						if(deptDTO!=null) {
						System.out.println("부서번호\t\t부서명\t\t위치");
						System.out.println("-------------------------------------------");
						System.out.printf("%5d  %14s\t %13s\r\n",
								deptDTO.getDeptno(),
								deptDTO.getDname(),
								deptDTO.getLoc());
						}else{
							System.out.println("해당 부서가 존재하지 않습니다");
						}
						break;
						
				case 4: System.out.println("부서명 입력");//입력
						System.out.println("지역  입력");//입력
						String dname = sc.next();
						String loc = sc.next();
						deptService.insertDept(dname, loc);
						break;
						
				case 5: System.out.println("조회할 부서번호: ");
						System.out.println("부서명 수정");
						System.out.println("지역 수정");
						int dnum = sc.nextInt();
						String dName = sc.next();
						String location = sc.next();
						deptService.updateDept(dName, location, dnum);
						break;
						
				case 0: System.out.println("종료합니다"); //종료
						isRunning = false;break;
				default:System.out.println("유효하지 않은 메뉴번호입니다.");
						break;
						
				}//switch문
		}catch(InputMismatchException e ) {
				System.out.println("올바르지 않은 입력형식입니다. 정수형태로 입력하세요");
				sc.nextLine();
			}
		}//while
		
		//삭제하기
		System.out.println("6.삭제할 번호 입력");
		int deptno = sc.nextInt();
		deptService.deleteDept(deptno);
		
	
			
		sc.close();	
		
		
		
		
	
		
//		//*부서번호상세조회
//		DeptDTO deptDTO = deptDao.getDeptByDeptno(conn, 70);
//		
//		}
//		
//		//부서명 목록조회
//		System.out.println();
//		System.out.println("----부서명 목록조회 결과---------");
//	
//		List<String> list =deptDao.getDeptNameList(conn);
//		for(int i=0;i<list.size();i++) {
//			String dname = list.get(i);
//			System.out.println(dname);
//		}
//		
//		//모든 부서 조회
//		System.out.println();
//		System.out.println("----모든 부서 조회 결과 ---");
//		
//		List<DeptDTO> deptList =deptDao.getDeptList(conn);
//		if(deptList.size()>0) {
//			System.out.println("부서번호\t\t부서명\t\t위치");
//			System.out.println("-------------------------------------------");
//			for(DeptDTO data: deptList) {
//				System.out.println(data.toString());//DTO에서 오버라이딩후 toString()호출
//				//	System.out.println(data); //주소지 출력됨
//			
//				/*System.out.printf("%5d  %14s\t %13s\r\n",
//						data.getDeptno(),
//						data.getDname(),
//						data.getLoc());
//				*/
//				
//				}//for문
//			}else{
//					System.out.println("부서가 존재하지 않습니다");
//				}
//				
//		
//		
//		//5.conn 자원반납
//		JDBCUtill.close(conn);//static이라 클래스명을 통해서 클로즈 해도 된다
//							  
//		
 }
}
