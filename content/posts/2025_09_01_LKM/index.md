---
title: "데이터베이스 정리 중"
date: "2025-09-01T10:00:00+09:00"
draft: false              
author: ["이경민"]     
tags: ["LKM"]     # 자유롭게
categories: ["Devlog"]
description: "데이터베이스 정리"
ShowToc: false
TocOpen: false
cover:
  image: ""               # 같은 폴더에 이미지 두고 파일명만 적기(예: cover.jpg)
  alt: ""
  relative: true
---
<!--more-->
## 데이터베이스란?
조직에 필요한 정보를 얻기 위해 논리적으로 연관된 데이터를 모아 구조적으로 통합해 놓은 것

### 데이터베이스의 특징
1. 공용 데이터 : 한 사람 또는 한 업무를 위해 사용되는 데이터가 아니라 공통으로 사용되는 데이터
2. 통합 데이터 : 데이터를 통합하는 개념으로, 각자 사용하던 데이터의 중복을 최소화하여 중복으로 인한 데이터 불일치 현상 제거
3. 운영 데이터 : 조직의 목적을 달성하는데 필요한 데이터
4. 저장 데이터 : 문서로 보관된 데이터가 아니라 저장장치에 저장된 데이터

### 데이터베이스 언어
1. DDL(Data Defunition Language) : 데이터 정의어
2. DML(Data Manipulation Language) : 데이터 조작어
3. DCL(Data Control Language) : 데이터 제어어

### 데이터베이스 구조
1. 외부 스키마 : 일반 사용자가 프로그래머가 접근하는 계층
2. 개념 스키마 : 전체 데이터베이스의 정의를 의미.
3. 내부 스키마 : 물리적 저장 장치에 데이터베이스가 실제로 저장되는 방법의 표현 

### 관계 데이터 모델
릴레이션: 행과 열로 구성된 테이블
관계: 릴레이션 내 데이터들의 관계, 릴레이션 관의 관계

### 릴레이션 구성 요소
#### 1. 스키마
#### 2. 인스턴스
스키마 구성
속성(attribute): 릴레이션 스키마의 열
도메인(domain): 속성이 가질 수 있는 값
차수(degree): 속성의 개수

인스턴스 구성
투플(tuple): 릴레이션의 행 
카디날리티(cardinality): 투플의 수

### 릴레이션의 특징
- 속성은 단일 값을 가진다.
- 속성은 서로 다른 이름을 가진다
- 한 속성의 값은 모두 같은 도메인 값을 가진다
- 속성의 순서는 상관없다.
- 릴레이션 내의 중복된 투플은 허용하지 않는다
- 투플의 순서는 상관없다.

### 키(Key)란?
특정 튜플을 식별할 때 사용하는 속성 혹은 속성의 집합 
### 키의 종류
- 슈퍼키: 튜플을 유일하게 식별할 수 있는 하나의 속성 혹은 속성의 집합 (유일성만 만족시키면 슈퍼키가 될 수 있다)
- 후보키: 튜플을 유일하게 식별할 수 있는 속성의 최소집합 (유일성과 최소성을 동시에 만족시켜야함)
- 기본키 : 후보키들 중에서 하나를 선택한 키 (Null값을 허용하지 않는다)
- 대체키 : 기본키를 제외한 나머지 후보키
- 외래키 : 다른 테이블의 데이터를 참조한 키 (이때 참조되는 키는 해당 테이블의 기본키로 설정 되어 있어야 한다) 

### 무결성 제약 조건
- 데이터 무결성 : 데이터베이스에서 저장된 데이터의 일관성과 정확성을 지키는 것을 말함
- 도메인 무결성 : 릴레이션 내의 튜플들이 각 속성의 도메인에 저장된 값만을 가져야 한다는 조건
- 개체 무결성 :  기본키는 NULL값을 가져서는 안 되며 리렐이션 내에 오직 하나의 값만 존재하는 조건
- 참조 무결성 : 외래키는 부모 릴레이션의 기본키와 동일하며 NULL값을 참조 할 수 없다.

## DDL
### CREATE, ALTER, DROP
CREATE : 테이블 생성
```sql
CREATE 테이블 이름 (
	속성1 int unsigned not null,
    속성2 varchar(20) not null,
    속성3 decimal(8,0),
    속성4 date not null
    primary key (속성1)
);
```
ALTER : 테이블의 속성과 속성에 관한 제약을 변경
```sql
ALTER TABLE 테이블 이름 
	[ADD 속성이름 데이터타입]
 	[DROP COLUMN 속성이름]
 	[MODIFY 속성이름 데이터타입]
 	[MODIFY 속성이름 [NULL┃NOT NULL]]
 	[ADD PRIMARY KEY(속성이름)]
 	[[ADD┃DROP] 제약이름]
```
FK 설정 예시
```sql
ALTER TABLE 테이블 이름
	ADD 
    foreign key(속성이름)
    references
    참조테이블이름 (참조 속성 이름)
```
DROP
```sql
DROP TABLE 테이블 이름 
```

## DML

### SELECT, INSERT, UPDATE 등
SELECT : 속성을 추출
```sql
SELECT 속성이름, 속성이름2 ... from 테이블이름 WHERE 검색조건 
```
### ORDER BY
검색 결과 정렬
```sql
SELECT 속성이름, 속성이름2 ... from 테이블이름 WHERE ORDER BY 검색조건 ASC,DESC
```
### INSERT
테이블에 새로운 튜플을 삽입하는 명령
```sql
INSERT INTO 테이블이름[(속성 리스트)] VALUES (값리스트);
```
### UPDATE
특정 속성 값을 수정하는 명령
```sql
UPDATE 테이블명 SET 속성이름 = 값 ;
```
### DML 연습 문제
```sql
-- [문제 1] 사원정보(EMPLOYEE) 테이블에서 사원번호, 이름, 급여, 업무, 입사일, 상사의 사원번호를 출력하시오. 이때 이름은 성과 이름을 연결하여 Name이라는 별칭으로 출력하시오 
-- 결과(107행)

SELECT employee_id AS '사원 번호', 
concat(first_name,' ',last_name) AS NAME, 
salary AS 급여,
job_id AS 업무,
hire_date AS 입사일,
manager_id AS '상사 사원번호'
from employees;

-- [문제 2] 사원정보(EMPLOYEES) 테이블에서 사원의 성과 이름은 Name, 업무는 Job, 급여는 Salary, 연봉에 $100 보너스를 추가하여 
-- 계산한 값은 Increased Ann_Salary, 급여에 $100 보너스를 추가하여 계산한 연봉은 “Increased Salary”라는 별칭으로 출력하시오(107행).
select concat(first_name, ' ', last_name) AS NAME,
job_id AS job,
salary AS Salary,
12 * salary + 100 AS 'Increased Ann_Salary',
(salary+ 100) * 12 AS 'Increased Salary'
from employees;

-- 사원정보(EMPLOYEE) 테이블에서 모든 사원의 이름(last_name)과 연봉을 “이름: 1Year Salary = $연봉” 형식으로 출력하고, “1 Year Salary”라는 별칭을 붙여 출력하시오(107
SELECT 
concat('이름: ',last_name, ', 1 Year Salary = $', salary * 12) AS '1 Year Salary'
from employees;

-- [문제 4] 부서별로 담당하는 업무를 한 번씩만 출력하시오(20행).
select distinct department_id, job_id from employees;

-- [문제 5] HR 부서에서 예산 편성 문제로 급여 정보 보고서를 작성하려고 한다. 사원정보(EMPLOYEES) 테이블에서 급여가
-- $7,000~$10,000 범위 이외인 사람의 성과 이름(Name으로
-- 별칭) 및 급여를 급여가 작은 순서로 출력하시오(75행).
select concat(first_name, ' ', last_name) AS NAME from employees where salary not between 7000 and 10000 order by salary ASC;

-- [문제 6] 사원의 이름(last_name) 중에 ‘e’ 및 ‘o’ 글자가 포함된 사원을 출력하시오. 이때 머리글은 ‘e and o Name’라고 출력하시오(10행).
SELECT 
concat(first_name, ' ' , last_name) AS 'e and o Name'
from employees where last_name like '%e%' and last_name like '%o%';

-- [문제 7] 현재 날짜 타입을 날짜 함수를 통해 확인하고, 1995년 05월 20일부터 1996년 05월
-- 20일 사이에 고용된 사원들의 성과 이름(Name으로 별칭), 사원번호, 고용일자를 출력하시오. 단, 입사일이 빠른 순으로 정렬하시오(8행).

SELECT last_name AS NAME, employee_id, hire_date
from employees 
where hire_date 
between str_to_date('1995/05/20','%Y/%m/%d') and  str_to_date('1996/05/20','%Y/%m/%d') 
order by hire_date ASC;


-- 급여(salary)와 수당율(commission_pct)에 대한 지출 보고서를 작성하려고 한다. 이에 수당을 받는 모든 사원의 
-- 성과 이름(Name으로 별칭), 급여, 업무, 수당율을 출력하시오. 이때 급여가 큰 순서대로 정렬하되, 급여가 같으면 수당율이 큰 순서대로 정렬하시오(35행).

SELECT 
concat(first_name, ' ', last_name) AS NAME,
salary, job_id, commission_pct
from employees where commission_pct is not NULL order by salary DESC, commission_pct DESC;
```

## Join
두 릴레이션의 공통 속성을 기중느로 속성 값이 같은 튜플을 수평으로 결합하는 연산

### 조인 연산의 구분
- 기본연산 : 세타조인, 동등조인, 자연조인
- 확장연산 : 세미조인, 외부조인


### 30문제 
```sql
use hr;

-- 1. 모든 사원의 이름을 조회하세요

select concat(first_name,' ',last_name) from employees;

-- 2. 모든 사원의 모든 정보를 조회하세요

select * from employees;

-- 3. 모든 도시명을 조회하세요

select * from locations; 

-- 4. 이름(first_name)이 'M'으로 시작하는 사원의 모든 정보를 조회하세요

select * from employees where first_name like 'M%';

-- 5. 이름(first_name)의 두번째 글자가 'a'인 사원의 이름(first_name)과 연봉을 조회하세요

select first_name AS 이름, salary*12 AS 연봉 from employees where first_name like'_a%';

-- 6. 도시명을 오름차순 하세요 

select city AS 도시명 from locations order by city asc;

-- 7. 부서명을 내림차순 정려하여 조회하세요 

select department_name AS 부서명 from departments order by department_name desc; 

-- 8. 연봉이 7000이상인 사원들의 모든 정보를 연봉순(오름차순)으로 정렬하세요

select * from employees where salary >= 7000;

-- 9. 인센티브(COMMISSION_PCT)를 받지 않는 사원들의 모든 정보를 조회하세요

select * from employees where commission_pct is null;

-- 10. 2007년 06월 21일에 입사한 사원의 사원번호, 이름 , 부서번호를 조회하세요 

select employee_id as 사원번호, concat(first_name, ' ', last_name) AS 이름, department_id As 부서번호 from employees where hire_date = '2007-06-21';

-- 11. 2006년 입사한 사원의 사원번호와 입사일을 조회하세요 

select employee_id as 사원번호, hire_date as 입사일 from employees where hire_date >= '2006-01-01';

-- 12. 이름의 길이가 5글자 이상인 사원을 조회하세요

select concat(first_name,' ',last_name) as 이름 from employees where length(last_name) > 5;

-- 13. 부서별 사원 수를 조회하고 부서번호로 오름차순 정렬하세요

select department_id as 부서번호, count(*) as 부서사원수 from employees group by department_id order by department_id asc;

-- 14. 직무별 평균 연봉을 조회하고 직무아이디로 내림차순 정렬하세요 

select job_title as 직무명, salary*12 as 연봉 from employees, jobs where employees.job_id = jobs.job_id order by jobs.job_id asc; 

-- 15. 상사가 있는 사원들의 모든 정보를 조회하세요 

select * from employees where manager_id is not null;

-- 16. 모든 사원들의 사원번호, 이름, 부서번호, 부서명을 조회하세요 

-- 모든 사원의 사번, 이름, 부서번호, 부서명
select 
  e.employee_id                    AS 사원번호,
  CONCAT(e.first_name, ' ', e.last_name) AS 이름,
  e.department_id                  AS 부서번호,
  d.department_name                AS 부서명
from employees e
join departments d
  on e.department_id = d.department_id
order by e.employee_id;

-- 17. 모든 부서의 부서명과 도시명을 조회하세요 

select department_name as 부서명, city as 도시명 from departments, locations where departments.location_id = locations.location_id;

-- 18. 모든 사원들의 사원번호, 부서명, 직무명(Job_title)을 조회하세요 

select employee_id as 사원번호, department_name as 부서명, job_title as 직무명 from employees, departments, jobs where employees.department_id = departments.department_id and employees.job_id = jobs.job_id;

-- 19. 모든 사원들의 사원번호, 부서명, 직무명, 근무하는 도시명을 조회하세요 

select employee_id as 사원번호, department_name as 부서명, job_title as 직무명, city as 도시명 from employees, departments, jobs, locations 
where employees.department_id = departments.department_id and employees.job_id = jobs.job_id and departments.location_id = locations.location_id;

-- 20. 부서번호가 10,20,30 번 부서에서 근무하는 사원들의 모든 정보를 조회하세요 

select * from employees where department_id = 10 or department_id = 20 or department_id = 30;

-- 21. 4인 미만의 사원이 근무하는 부서의 평균연봉과 부서명을 조회하세요 

-- 4인 미만(1~3명) 사원이 근무하는 부서의 평균연봉과 부서명
select
  d.department_name AS 부서명,
  AVG(e.salary * 12) AS 평균연봉
from employees   e
join departments d
  on e.department_id = d.department_id
group by d.department_id, d.department_name
having COUNT(e.employee_id) < 4;
     
-- 22.  6인 미만의 사원이 근무하는 부서의 이름을 조회하세요 

select
  d.department_name AS 부서명
from employees   e
join departments d
  on e.department_id = d.department_id
group by d.department_id, d.department_name
having COUNT(e.employee_id) < 6;

-- 23. IT 부서의 연봉 총합을 조회하세요 

SELECT d.department_name as 부서명, sum(salary*12) as 연봉총합
from departments d join employees e on d.department_id = e.department_id 
group by d.department_name
having d.department_name = 'IT';

-- 24. REGIONS 별 도시의 개수를 조회하세요 

select region_name as region, count(*) as 개수 from countries c join regions r on c.region_id = r.region_id group by region_name;

-- 25. 도시별 부서의 개수를 조회하세요  

select city as 도시명, count(department_id) as 부서개수 from locations l join departments d on l.location_id = d.location_id group by city ;

-- 26. 연봉이 12000이상이 되는 직원들의 LAST_NAME 및 급여를 조회하세요 

select last_name as 이름, salary as 급여 from employees where salary*12 >= 12000;

-- 27. 사원 번호가 176번인 사람의 LAST_NAME 및 부서명을 조회하세요 

select last_name as 이름, department_name as 부서명 from employees e join departments d on e.department_id = d.department_id where employee_id = 176;

-- 28. 연봉이 5000 에서 12000 의 범위 이외인 사원들의 last_name 과 급여를 조회하세요 

select last_name as 이름, salary as 급여 from employees where not salary >= 5000 and salary <=12000;

-- 29. 직속상사(manager_id)가 없는 사람들의 last_name과 부서명을 조회하세요 

select last_name as 이름, department_name as 부서명 from employees e join departments d on e.department_id = d.department_id where e.manager_id is null;

-- 30. 커미션을 받는 사원들의 이름(full name), 급여, 커미션 내역을 조회하세요 

select concat(first_name,' ',last_name) as 이름, salary as 급여, commission_pct as 커미션 from employees where commission_pct is not null;

-- 1. 부서별 인원수를 출력하세요.

select department_name as 부서명, count(employee_id) as 인원수 from employees e join departments d on e.department_id = d.department_id group by d.department_name;

-- 2. IT 부서에서 일하는 직원의 first_name, last_name, salary 를 이름으로 출력하시요. 출력결과는 salary 가 낮은 사람부터 출력하시요. 

select concat(first_name, ' ', last_name) as 이름, salary as 급여, department_name as 부서명
from employees e join departments d on e.department_id = d.department_id group by d.department_name,employee_id having d.department_name = 'IT';

-- 3. 각 사원(employee)에 대해서 사번(employee_id), 이름(first_name), 업무명(job_title), 부서명(department_name)를 조회하시오. 단 도시명(city)이 ‘Seattle’인 지역(location)의 부서(department)에 근무하는 직원만 출력하시오.

select employee_id as 사번, 
first_name as 이름,
job_title as 업무명,
department_name as 부서명
from employees e 
join departments d
on e.department_id = d.department_id
join locations l
on d.location_id = l.location_id
join jobs j
on e.job_id = j.job_id 
where city = 'Seattle';

-- 4. 각 직책 별(job_title)로 급여의 총합을 구하되 직책이 Representative 인 사람은 제외하십시오. 단, 급여 총합이 30000 초과인 직책만 나타내며, 급여 총합에 대한 오름차순으로 정렬하십시오. 출력 결과의 컬럼명은 아래 결과와 동일하게 주십시오.

select sum(salary) from employees e 
join jobs j on e.job_id = j.job_id
where j.job_title not like '%Representative%' 
group by job_title having sum(e.salary) > 30000 order by sum(e.salary) asc;

-- 5. 각 부서 이름 별로 2005년 이전에 입사한 직원들의 인원수를 조회하시오.

select d.department_name as 부서명,count(employee_id) as 인원수 from employees e
join departments d 
on e.department_id = d.department_id
where e.hire_date < '2005-01-01'
group by d.department_name;

-- 5. 사원수가 3명 이상의 사원을 포함하고 있는 부서의 부서번호(department_id), 부서이름(department_name), 사원 수, 최고급여, 최저급여, 평균급여, 급여총액을 조회하여 출력하십시오. 출력 결과는 부서에 속한 사원의 수가 많은 순서로 출력하세요. (평균급여 계산시 소수점 이하는 버리시오)

select d.department_id as 부서번호, 
d.department_name as 부서이름, 
count(e.employee_id) as 사원수, 
max(e.salary) as 최고급여,
min(e.salary) as 최소급여,
format(avg(e.salary),0) as 평균급여,
sum(e.salary) as 급여총액
from employees e
join departments d
on e.department_id = d.department_id
group by d.department_id, d.department_name
HAVING COUNT(e.employee_id) >= 3
order by count(e.employee_id) desc ;

-- 6. Employees 테이블에서 입사일자(hire_date)에 따라 2005년 입사한 직원들 가운데 first_name이 'Lisa'인 직원보다 빨리 입사한 직원의 사번(employee_id), 이름(first_name), 성(last_name), 입사일자(hire_date)를 조회하는 SQL 문장을 서브쿼리로 작성하세요.

select employee_id as 사번, 
concat(first_name,' ',last_name) as 이름 ,
hire_date as 입사일자
from employees 
where hire_date >= '2005-01-01' and hire_date < '2006-01-01' and first_name like 'Lisa';

```