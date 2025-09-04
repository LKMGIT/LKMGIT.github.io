---
title: "복습정리"
date: "2026-09-04T16:00:00+09:00"
draft: false              
author: ["강우혁"]     
tags: ["Study"]     # 자유롭게
categories: ["Devlog"]
description: "데이터베이스"
ShowToc: false
TocOpen: false
cover:
  image: ""               # 같은 폴더에 이미지 두고 파일명만 적기(예: cover.jpg)
  alt: ""
  relative: true
---
<!--more-->
## DDL(데이터 정의어)

→ 객체들을 생성(create)할 때, 수정(alter)할 때, 삭제(drop)할 때

- create 테이블
 제약조건(constraint) => 데이터의 무결성을 지키기 위한 제한된 조건을 의미한다.
- 특정 데이터를 입력할 때, 조건 없이 입력되는 것이 아니라, 어떤 조건을 만족했을 때만 입력 되도록 제약할 수 있다
---
### 6가지 제약조건

- PRIMARY KEY(기본 키 제약조건)
- FOREIGN KEY
- UNIQUE
- CHECK
- DEFAULT
- NULL
---
### 1. PRIMARY KEY 제약조건 (기본키 제약조건)

- 테이블에 존재하는 많은 튜플(행)의 데이터를 구분할 수 있는 식별자 (기본키)
- not null and unique 모두 만족해야 한다.
- 설계 방법에 따라서 회원 아이디가 기본키가 안 될 수도 있다.
- email 이나 휴대폰 번호를 구분하는 경우도 있다.
---
### 2. FOREIGN KEY 제약조건

→ 다른 테이블의 기본키(primary key) 또는 고유키(unique key)를 참조하도록 강제하는 규칙

---
### 3. UNIQUE 제약 조건

→ 중복되지 않는 유일한 값

- primary key => not null(null 허용 안 함) + unique(유일값)
- unique => null 허용 + 유일값
---
### 4. CHECK 제약조건

→ 입력되는 데이터를 점검하는 기능

- 입력데이터 제약 ⇒ 음수값 입력 X
---
### 5. DEFAULT 제약조건

→ 값을 입력하지 않아도 자동으로 입력되는 기본 값을 정의하는 방법

- 예) 출생년도를 입력하지 않으면 -1을 자동으로 할당
- 예) 주소를 입력하지 않았다면 ‘서울’, 키를 입력하지 않으면 170,160
- default 제약조건 추가시 column으로 수정한다.
---
### 6. NULL 제약조건

- null (값 허용), null(생략가능),
- not null(값 허용 안 함)
- null 값은 ‘아무것도 없다’
- 0이나 공백이 아님
