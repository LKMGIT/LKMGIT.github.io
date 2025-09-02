---
title: "복습정리"
date: "2026-09-01T18:00:00+09:00"
draft: false              
author: ["강우혁"]     
tags: ["Study"]     # 자유롭게
categories: ["Devlog"]
description: "복습정리"
ShowToc: false
TocOpen: false
cover:
  image: ""               # 같은 폴더에 이미지 두고 파일명만 적기(예: cover.jpg)
  alt: ""
  relative: true
---
<!--more-->
## 데이터베이스

- 데이터 : 관찰의 결과로 나타난 정량적 혹은 정성적인 실제 값
- 정보 : 데이터에 의미를 부여한 것
- 지식 : 사물이나 현상에 대한 이해

### 데이터베이스란?

→  조직에 필요한 정보를 얻기 위해 논리적으로 연관된 이터를 모아 구조적으로 통합해 놓은 것

- 데이터의 검색과 변경 작업을 주로 수행한다.
- 변경이란 시간에 따라 변하는 데이터 값을 데이터베이스에 반영하기 위해 수행하는 삽입, 삭제, 수정 등의 작업

---

### 데이터베이스 유형

1. 통합된 데이터(intergrated data)
- 데이터를 통합하는 개념으로, 각자 사용하던 데이터의 중복을 최소화하여 중복으로 인한 데이터 불일치 현상을 제거
2. 저장된 데이터(stored data)
- 문서로 보관된 데이터가 아니라 디스크, 테이프 같은 컴퓨터 저장장치에 저장된 데이터를 의미
3. 운영 데이터(operational data)
- 조직의 목적을 위해 사용되는 데이터를 의미한다. 즉 업무를 위한 검색을 할 목적으로 저장된 데이터
4. 공용 데이터(shared data)
- 한 사람 또는 한 업무를 위해 사용되는 데이터가 아니라 공동으로 사용되는 데이터를 의미

---

### 데이터베이스의 특징

1. **실시간 접근성 (real time accessibility)**
- 데이터베이스는 실시간으로 서비스, 사용자가 데이터를 요청하면 몇 시간이나 몇 일 뒤에 결과를 전송하는 것이 아니라 수 초 내에 결과를 서비스
2. **계속적인 변화 (continuous change)**
- 데이터베이스에 저장된 내용은 어느 한 순간의 상태를 나타내나지만, 데이터 값은 시간에 따라 항상 바뀜. 데이터베이스는 삽입(insert), 삭제(delete), 수정(update) 등의 작업을 통하여 바뀐 데이터 값을 저장
3. **동시 공유 (concurrent sharing)**
- 데이터베이스는 서로 다른 업무 또는 여러 사용자에게 동시에 공유통시(concurrent)는 병행이라고도 하며, 데이터베이스에 접근하는 프로그램이 여러 개 있다는 의미
4. **내용에 따른 참조 (reference by content)**
- 데이터베이스에 저장된 데이터는 데이터의 물리적인 위치가 아니라 데이터 값에 따라 참조

---

###데이터베이스 언어

SQL (구조화된 질의 언어)

- DDL (데이터 정의어) - Create, Alter, Drop
- DML (데이터 조작어) - Insert, Updata, Delete
- DCL (데이터 제어어) - Revoke, Grant
- DQL (데이터 질의어) - Select
- TCL (트랜젝처리 언어) - Commit, Rollback

---

### 3단계 데이터베이스 구조

■ 외부 스키마

- 일반 사용자나 응용 프로그램머가 접근하는 계층, 전체 데이터베이스 중에서 하나의 논리적인 부분
- 여러 개의 외부 스키마(external schema)가 있을 수 있음
- 서브 스키마(sub schema)라고도 하며, 뷰(view)의 개념임

■ 개념 스키마

- 전체 데이터베이스의 정의를 의미
- 통합 조직별로 하나만 존재하며 DBA가 관리함
- 하나의 데이터베이스에는 하나의 개념 스키마(conceptual schema)가 있음

■ 내부 스키마

- 물리적 저장 장치에 데이터베이스가 실제로 저장되는 방법의 표현
- 내부 스키마(internal schema)는 하나
- 인덱스, 데이터 레코드의 배치 방법, 데이터 압축 등에 관한 사항이 포함됨

---

## MySQL

### 파일생성

- create database shop_db;
- create user 'shopadmin'@'localhost' identified BY 'shop1234!';
- GRANT ALL PRIVILEGES ON shop_db.* TO 'shopadmin'@'localhost';

- use 파일이름;

---

select → 질의 결과 추출되는 속성 리스트를 열거한다.

from → 질의에 어느 테이블이 사용되는지 열거한다.

where → 질의의 조건을 작성한다.

distinct → 중복제거