---
title: "복습정리"
date: "2026-09-03T18:00:00+09:00"
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
## 📌 조인의 종류

### 1. **INNER JOIN (내부 조인)**

- 한 테이블의 행을 다른 다른 테이블의 행에 연결하여 두 개 이상의 테이블을 결합하는 연산
- 양쪽 테이블에서 **조건이 일치하는 행만** 가져옴
- 관계대수에서는 카티션 프로덕트 연산
- 동등연산자(=) 조건에 의하여 테이블을 조인하는 것을 동등(equi join)조인이라고 한다.
---
### 2. LEFT OUTER JOIN (LEFT JOIN)

- 왼쪽 테이블을 기준으로, 매칭되지 않아도 **왼쪽은 다 출력**
- 오른쪽 테이블 값이 없으면 `NULL`
---
### 3. RIGHT OUTER JOIN (RIGHT JOIN)

- 오른쪽 테이블을 기준으로, 매칭되지 않아도 **오른쪽은 다 출력**
- 왼쪽 테이블 값이 없으면 `NULL`
---
### 4. FULL OUTER JOIN

- **양쪽 다 포함** (매칭되든 안 되든 전부 표시)
- MySQL은 직접 지원 ❌, 대신 `UNION`으로 구현
---
### 부속질의 (서브쿼리) - subquery

- select문의 where 절에 또는 다른 테이블의 결과를 이용하기 위해서 다시select문을 괄호로 묶어서 부속 질의의 결과를 넘겨주는 방법
- 질의가 중첩질의 nested query 라고도 한다.
---
### 결과테이블의 반환형태

- 단일행 - 단일열(1x1)
- 다중행 - 단일열(nx1)
- 단일행 - 다중열(1xn)
- 다중행 - 다중열(nxn)


