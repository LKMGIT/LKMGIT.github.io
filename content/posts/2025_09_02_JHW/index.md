---
title: "데이터베이스"
date: 2025-09-02T12:34:10+09:00
draft: false
authors: ["장현우ID"]   # 기본값, 각자 바꿔 쓰기
tags: []
categories: []
description: ""
ShowToc: true
TocOpen: false
cover:
  image: ""
  alt: ""
  relative: true
---

1. 통합된 데이터
- 데이터를 통합하는 개념으로 각자 사용하던 데이터의 중복을 최소화 하여 중복으로 인한 데이터 불일치 현상 제거

1. 저장된 데이터
- 문서로 보관된 데이터가 아니라 디스크, 테이프 같은 컴퓨터 저장장치에 저장된 데이터를 의미

1. 운영 데이터
- 조직의 목적을 위해 사용되는 데이터

1. 공용데이터 
- 한 사람 또는 한 업무를 위해 사용 되는 데이터가 아니라 공동으로 사용되는 데이터

## 데이터베이스 특징

1. 실시간 접근성
- 데이터베이스는 실시간 서비스, 사용자가 데이터를 요청하면 수 초 내에 결과를 서비스

1. 계속적인 변화
- 데이터베이스 저장된 내용은 어느 한 순간의 상태를 나타내지만 데이터 값은 시간에 따라 항상 바뀜

1. 동시 공유
- 데이터베이스는 서로 다른 업무 또는 여러 사용자에게 동시에 공유동시는 병행이라고 한다

1. 내용에 따른 참조
- 데이터베이스에 저장된 데이터는 데이터의 물리적인 위치가 아니라 데이터 값에 따라 참조

![스크린샷 2025-09-01 오후 12.12.42.png](attachment:83fd615e-cece-426d-aa86-3aef0021e7cb:스크린샷_2025-09-01_오후_12.12.42.png)

![스크린샷 2025-09-01 오후 12.41.49.png](attachment:2efecfaa-9ab3-4726-9ff2-0552d8e9a7ae:스크린샷_2025-09-01_오후_12.41.49.png)

## 용어 정리

![스크린샷 2025-09-01 오후 3.37.53.png](attachment:1e2ea281-2cbb-4a08-8db2-b9931ed55cfe:스크린샷_2025-09-01_오후_3.37.53.png)

![스크린샷 2025-09-01 오후 3.45.46.png](attachment:a5acd3b7-a5e4-4783-9628-adb17c7030a7:스크린샷_2025-09-01_오후_3.45.46.png)

## Db / 테이블 생성

→ create database 이름;

### 계정 생성  아이디 / 비밀번호

**→  create user '계정아이디'@localhost identified by '비밀번호';**

### 권한 부여

 특정 데이터베이스의 모든 테이블에 모든 권한을 줌

ex) 

```jsx
→  grant all privileges on DB이름.* to '사용자'@localhost;
```

이 데이터베이스를 사용하겠다

→ use 데이터베이스 이름

### 테이블 생성

→ create table 테이블명 ( );

ex )

```jsx
create table products  (
product_id varchar(60) primary key ,
name varchar(50) ,
company varchar(50) ,
price int ,
stock_qty int ,
create_at varchar(50)
);
```

## 용어 사용

### insert into 테이블 명 values (벨류);  → 벨류는 순서대로 작성해야함

```jsx
ex)  insert into book values ( 1 , ‘축구의 역사’ , ‘굿스포츠’ , 7000);
     select * from book;
     commit;
```

### Foregin key - 외래키

- FK가 있는 컬럼이(자식) →   컬럼(FK)이 없는쪽(부모)으로 요청
- 사용법

→ alter table 자식테이블  add foreign key ( 컬럼(FK) ) references 부모테이블 ( 컬럼(FK)와 같은 이름) 

![스크린샷 2025-09-02 오전 11.59.07.png](attachment:7e9a3fc0-ad61-466c-b368-e343d5e872eb:스크린샷_2025-09-02_오전_11.59.07.png)

```jsx
-- 테이블 수정(Foregin key)
alter table Countries add foreign key (region_id) references Regions(region_id);
alter table Locations add foreign key (country_id) references Countries(country_id);
alter table Departments add foreign key (location_id) references Locations (location_id);
alter table job_history add foreign key (department_id) references Departments (department_id);
alter table job_history add foreign key (employee_id) references employees (employee_id);
alter table employees add foreign key (job_id) references jobs (job_id);
alter table employees add foreign key (manager_id) references employees (employee_id);

```

### 오름차순 정렬

- ORDER BY ASC

```
SELECT * FROM 테이블 ORDER BY 컬럼1 ASC;
```

### 내림차순 정렬

- ORDER BY DESC

```
SELECT * FROM 테이블 ORDER BY 컬럼1 DESC;
```

### 양수값으로만 설정

- 테이블명 int unsigned

```jsx
location_id int unsigned not null
```

### 조건 없이 모든 걸 출력

→  select *

### 특정 문자열이 들어가면 모두 다 출력

→ %문자%

```jsx
select publisher 
from book
where bookname like '%축구%';

// 두번째 문자에 구가 들어가는 거 출력
select *
from book
where bookname like '_구%';
```

### 현재 시간을 출력

 →  select now( )   /    now( )

```jsx
create table customers (
    customer_id varchar(60) primary key ,
    full_name varchar(30) ,
    email varchar(50) ,
    phone varchar(40) ,
    create_at date  // now 쓸때는 date 사용 해야함
);

insert into customers values('cust01' , 'Kim Yuna' , 'yuna.kim@naver.com', '010-1111-2222' , now() );
```