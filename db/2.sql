/* 합성 연산자
문자와 문자 연결: || */

select deptno ||'의 부서명: '||dname as "부서번호명"
from dept;


select deptno,dname,loc
from dept;
-- dept 테이블에 seq_dno.nextval, 디%자인, 경주 입력
insert into dept(deptno,dname,loc) values(seq_dno.nextval,'디%자인', '경주');
-- dept 테이블에 seq_dno.nextval, 총무_부, 대천 입력
insert into dept(deptno,dname,loc) values(seq_dno.nextval, '총무_부', '대천');

--부서명이 accounting 정보조회
select deptno,dname,loc
from dept
where dname = 'ACCOUNTING'; --영문대소문자까지 일치해야함.

--UPPER(문자열) : 대문자로 변환 
--LOWER(문자열) : 소문자로 변환 
--INITCAP(문자열) : 앞글자 대문자로 변환 

--부서명이 accounting정보조회
select deptno,dname,loc
from dept
where dname='ACCOUNTING'; --영문대소문자까지 일치. 아님 검색이 안됨 

--괄호안의 글자 대문자로 변경 
select dname,UPPER(dname),LOWER(dname),INITCAP(dname)
from dept
where dname = UPPER('accounting'); 

-- LIKE 와일드카드(와일드문자)
/*와일드카드
% : 모든(0개 이상의 임의 문자)
_ : 한글자 (언더바)

ESCAPE '문자' : 문자뒤의 % 또는 _는 와일드카드가 아닌 특수문자로 인식
예문>LIKE '%&_%' ESCAPE '&';
*/

 -- Like 'A%' : A로 시작하는 
 --'AC' 'AC1' 'AC12' 'ACASBDWERRT
select deptno,dname,loc
from dept
where dname LIKE 'A%'; 

--언더바는 한글자 의미,A뒤에도 글이 나오면 뒤에 %붙여줘여함
--Like '_A%' : 2번째 위치에 'A'가 포함되는
--1A, AA, aA
select deptno,dname,loc
from dept
where dname LIKE '_A%'; 


--부서명이 S로 끝나는 부서정보조회
--'_S'는 두글자라고 말해주는것
--'%S'는 몇글잔지 몰라도 S로 끝난다
select deptno,dname,loc
from dept
where dname LIKE '%S'; 

--부서명에 A가 포함된 부서정보 조회
select deptno,dname,loc
from dept
where dname LIKE '%A%';


-- 부서명에 %가 포함된 정보조회
select deptno,dname,loc
from dept
where dname LIKE '%@%%' ESCAPE '@';

-- 부서명에 _가 포함된 정보조회
select deptno,dname,loc
from dept
where dname LIKE '%&_%' ESCAPE '&';
