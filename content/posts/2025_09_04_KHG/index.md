---
title: "MySQL"
date: "2025-09-04T00:00:00+09:00"   
draft: false            
author: ["김형근"]         
tags: ["Study"]      # 자유롭게
categories: ["Devlog"]
description: "복습 정리"
ShowToc: false
TocOpen: false
cover:
  image: ""               # 같은 폴더에 이미지 두고 파일명만 적기(예: cover.jpg)
  alt: ""
  relative: true
---
## 💡 EXISTS 연산자

`EXISTS`는 특정 조건을 만족하는 데이터가 서브쿼리(subquery, 하위 질의)에 **하나라도 존재하는지** 여부를 확인하는 연산자. 

서브쿼리에서 결과값이 한 행이라도 반환되면 `TRUE`를, 하나도 없으면 `FALSE`를 반환

주로 `WHERE` 절에서 사용되며, 데이터의 존재 유무만 확인하고 바로 다음 작업을 수행하기 때문에 특정 상황에서는 `IN`이나 `JOIN`보다 효율적일 수 있음.

"이 조건을 만족하는 데이터가 적어도 하나라도 있는가?"라는 질문을 던지는 것과 같다.

### 예시

`buytbl`에서 한 번이라도 구매한 기록이 있는 회원의 이름과 키를 `usertbl`에서 조회해보기

```sql
SELECT name, height
FROM usertbl U
WHERE EXISTS (SELECT *
              FROM buytbl B
              WHERE B.userID = U.userID);
```

**설명:** `usertbl`의 각 회원(U)마다, `buytbl`에 해당 회원의 `userID`와 일치하는 구매 기록(B)이 **존재하는지** 확인합니다. 

단 하나의 기록이라도 발견되면 `EXISTS`는 `TRUE`를 반환하고, 해당 회원의 이름과 키를 결과에 포함시킵니다.

---

## 🏛️ DDL (Data Definition Language, 데이터 정의어)

DDL은 데이터베이스의 구조(스키마)와 그 객체들을 정의하고 관리하는 데 사용되는 언어입니다. 

데이터베이스의 '설계도'를 다루는 명령어라고 생각할 수 있습니다.

- **`CREATE`**: 데이터베이스, 테이블, 뷰 등 새로운 객체를 **생성**합니다.
- **`ALTER`**: 기존 객체의 구조를 **수정**합니다. (예: 테이블에 열 추가)
- **`DROP`**: 객체를 영구적으로 **삭제**합니다.

---

## ⛓️ 테이블 제약조건 (Constraint)

제약조건은 데이터의 **무결성(Integrity)**을 지키기 위해 테이블의 열에 설정하는 규칙입니다. 

즉, 정확하고 신뢰할 수 있는 데이터만 저장되도록 강제하는 조건입니다.

### 1. PRIMARY KEY (기본 키)

- 기본 키(Primary Key)는 테이블의 각 행(레코드)을 고유하게 식별할 수 있는 값입니다. 
중복될 수 없으며, 절대로 `NULL` 값을 가질 수 없습니다. 
하나의 테이블에는 단 하나의 기본 키만 설정할 수 있습니다.
- **특징:** `NOT NULL` + `UNIQUE`
- **비유:** 학생 테이블에서 각 학생을 유일하게 구분하는 '학번'과 같습니다. 
모든 학생은 학번이 있고, 같은 학번을 가진 학생은 없습니다.

### 예시: 기본 키를 포함한 테이블 생성

```sql
- 1. 열 정의 시 바로 지정하는 방법
CREATE TABLE usertbl ( userID CHAR(8) PRIMARY KEY, name VARCHAR(10) NOT NULL, birthYear INT NOT NULL
);
- 2. 제약조건을 따로 명시하는 방법 (제약조건에 이름을 부여할 수 있어 관리 용이)
CREATE TABLE usertbl ( userID CHAR(8) NOT NULL, name VARCHAR(10) NOT NULL, birthYear INT NOT NULL, CONSTRAINT PK_usertbl_userID PRIMARY KEY (userID)
);
```

### 2. FOREIGN KEY (외래 키)

- 외래 키(Foreign Key)는 한 테이블을 다른 테이블과 연결하는 역할을 합니다. 
한 테이블에 있는 열이 다른 테이블의 `PRIMARY KEY`를 참조하도록 만들어, 두 테이블 간의 관계를 형성합니다. 
이를 통해 존재하지 않는 데이터를 참조하는 것을 막아 데이터의 일관성을 유지합니다.
- **비유:** `usertbl`이 '부모' 테이블, `buytbl`이 '자식' 테이블이라면, `buytbl`의 `userID`는 `usertbl`에 실제로 존재하는 `userID`(기본 키)만을 참조할 수 있습니다. 
덕분에 존재하지 않는 회원의 구매 기록이 생기는 것을 방지할 수 있습니다.

### 예시: 외래 키 생성

```sql
CREATE TABLE buytbl (
    num INT AUTO_INCREMENT PRIMARY KEY,
    userID CHAR(8) NOT NULL,
    prodName CHAR(6) NOT NULL,
    -- FK_usertbl_buytbl 이라는 이름으로 외래 키 제약조건 설정
    CONSTRAINT FK_usertbl_buytbl FOREIGN KEY (userID) REFERENCES usertbl(userID)
);
```

### 외래 키 옵션: `ON UPDATE` & `ON DELETE`

참조하는 부모 테이블의 데이터가 변경(수정/삭제)될 때, 자식 테이블의 데이터에 어떤 작업을 수행할지 정의하는 옵션입니다.

- **`ON DELETE CASCADE`**: 부모 테이블의 행이 삭제되면, 이를 참조하는 자식 테이블의 행도 **연쇄적으로 자동 삭제**됩니다.
    - **예시**: `usertbl`에서 'LSG' 회원을 삭제하면, `buytbl`에 있던 'LSG'의 모든 구매 기록이 자동으로 함께 삭제됩니다.
- **`ON UPDATE CASCADE`**: 부모 테이블의 기본 키 값이 변경되면, 자식 테이블에서 참조하던 값도 **연쇄적으로 자동 수정**됩니다.

```sql
- CASCADE 옵션을 포함하여 외래 키 수정
ALTER TABLE buytbl ADD CONSTRAINT FK_usertbl_buytbl FOREIGN KEY (userID) REFERENCES usertbl(userID) ON DELETE CASCADE ON UPDATE CASCADE;
```

### 3. UNIQUE 제약조건

**UNIQUE** 제약조건은 해당 열의 모든 값이 중복되지 않고 유일해야 함을 보장합니다. `PRIMARY KEY`와 비슷하지만, **`NULL` 값을 딱 한 번 허용**한다는 중요한 차이점이 있습니다. 
한 테이블에 여러 개의 `UNIQUE` 제약조건을 설정할 수 있습니다.

- `PRIMARY KEY` = `NOT NULL` + `UNIQUE`
- `UNIQUE` = `NULL` 허용(1개만) + `UNIQUE`
- **비유:** 회원 테이블에서 '이메일' 주소는 중복되면 안 되므로 `UNIQUE`로 설정할 수 있습니다. 하지만 회원이 아직 이메일을 입력하지 않은 경우(`NULL`)도 있을 수 있습니다.

### 예시

```sql
CREATE TABLE user_info (
    userID INT PRIMARY KEY,
    email VARCHAR(50) UNIQUE - 이메일 중복 방지
);
```

### 4. CHECK 제약조건

**CHECK** 제약조건은 열에 입력될 수 있는 값의 범위를 제한하거나 특정 조건을 만족하는 데이터만 허용하는 기능입니다. 데이터가 비즈니스 규칙에 맞는지 검사할 때 유용합니다.

- **활용:** 출생연도를 1900년 이후로 제한하거나, 성별은 '남' 또는 '여'만 입력되도록 강제할 수 있습니다.

### 예시

```sql
CREATE TABLE usertbl (
    userID CHAR(8) PRIMARY KEY,
    name VARCHAR(10) NOT NULL,
    birthYear INT NOT NULL CHECK (birthYear >= 1900 AND birthYear <= 2023),
    mobile1 CHAR(3) CHECK (mobile1 IN ('010', '011', '016', '019'))
);
```

### 5. DEFAULT 제약조건

**DEFAULT** 제약조건은 데이터를 삽입할 때 특정 열에 값을 명시하지 않으면, 자동으로 입력될 기본 값을 미리 지정하는 방법입니다.

- **활용:** 대부분의 사용자가 '서울'에 거주한다면 주소의 기본값을 '서울'로 설정하거나, 키를 입력하지 않았을 때 기본값으로 `160`을 넣을 수 있습니다.

### 예시: 테이블 생성 시 DEFAULT 정의

```sql
CREATE TABLE usertbl (
    userID CHAR(8) PRIMARY KEY,
    name VARCHAR(10) NOT NULL,
    addr CHAR(2) NOT NULL DEFAULT '서울', -- 명시하지 않으면 '서울'로 자동 입력
    height SMALLINT DEFAULT 160           -- 명시하지 않으면 160으로 자동 입력
);
```

이미 존재하는 테이블의 열에 `DEFAULT` 제약조건을 추가할 때는 `ALTER COLUMN`을 사용합니다.

```sql
- height 열의 기본값을 160으로 설정
ALTER TABLE usertbl ALTER COLUMN height SET DEFAULT 160;
```

### 6. NULL / NOT NULL

가장 기본적인 제약조건으로, 열에 `NULL` 값을 허용할지 여부를 결정합니다.

- **`NOT NULL`**: 해당 열에는 반드시 값이 입력되어야 하며 `NULL` 값을 허용하지 않습니다.
- **`NULL`**: `NULL` 값을 허용합니다. (따로 명시하지 않으면 기본값)
- **주의!**: `NULL`은 0이나 공백 문자(`' '`)와는 다릅니다. '알 수 없거나 존재하지 않는 값'을 의미합니다.

---

## ⚙️ 시스템 카탈로그 (메타데이터)

시스템 카탈로그는 데이터베이스 자체에 대한 정보를 담고 있는 테이블들의 모음입니다. 
데이터베이스의 구조, 객체, 제약조건, 사용자 등에 대한 정보, 즉 **데이터에 대한 데이터(메타데이터)**를 저장합니다.

이 카탈로그를 조회하면 데이터베이스의 전체 구조를 파악할 수 있습니다.

### 예시: 테이블의 인덱스(키) 정보 확인

`SHOW INDEX`는 테이블에 설정된 인덱스 정보를 보여주는 유용한 명령어입니다. 
`PRIMARY KEY`, `FOREIGN KEY`, `UNIQUE`제약조건이 올바르게 생성되었는지 확인할 때 사용합니다.

```sql
- buytbl에 설정된 모든 인덱스(기본 키, 외래 키 등) 정보를 보여줌
SHOW INDEX FROM buytbl;
```

이 명령어는 여러 열을 묶어 하나의 기본 키로 만든 복합 키(Composite Key)의 구조를 확인할 때 특히 유용합니다.