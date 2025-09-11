---
title: "MySQL 내장함수"
date: "2025-09-11T00:00:00+09:00"   
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
## MySQL 내장함수

```sql
- MySQL 내장함수 
-- 제어 흐름 함수
 -- 문자열 함수
  -- 수학 함수
   -- 날짜 / 시간 함수
    -- 전체 텍스트 검색 함수
     -- 형 변환 함수
      -- 보안 / 압축 함수
       -- 공간분석 함수
        -- 정보 함수
```

### 1. 제어 흐름 함수

프로그램의 흐름 제어한다.

```sql
IF(수식, 참, 거짓) -> 수식이 참 또는 거짓인지 결과에 따라 2중 분기
SELECT IF(100 > 200, '참', '거짓');

IFNULL(수식1, 수식2) -> 수식1이 NULL이 아니면, 수식1 반환, 수식1이 NULL이면 수식2가 반환 
SELECT IFNULL(NULL, '널입니다.'), IFNULL(100, '널이군요.');
SELECT IFNULL(100, '널입니다.'), IFNULL(NULL, '널이군요.');

NULLIF(수식1, 수식2) -> 수식1과 수식2가 같으면 NULL 반환, 다르면 수식1 반환
SELECT NULLIF(100, 100), NULLIF(200, 100);

CASE ~WHEN ~ELSE ~END 내장함수는 아니고, 연산자로 분류됨
다중분기를 처리함.

SELECT CASE 100
	WHEN 1 THEN '일요일'
	WHEN 5 THEN '숫자 5'
	WHEN 10 THEN '숫자 10'
	ELSE '해당 매칭 값이 없음'
	END AS 'CASE 연습';
```

### 2. 문자열 함수

문자열을 조작한다. 활용도 높음.

```sql
MySQL은 기본문자셋 으로 UTF-8 코드 사용하기 때문에 영문은 1byte, 한글은 3byte

ASCII(아스키코드), CHAR(숫자)
SELECT ASCII('A'); -- 65
SELECT ASCII(65) -- 54
SELECT ASCII('A'), CHAR(65); -- 65, A

BIT_LENGTH(문자열), CHAR_LENGTH(문자열), LENGTH(문자열)
BIT_LENGTH(문자열) -> 할당된 BIT 크기 또는 문자 크기를 반환
CHAR_LENGTH(문자열) -> 문자의 개수를 반환
LENGTH(문자열) -> 할당된 Byte 수를 반환
SELECT bit_length('abc') as bitLength,
	CHAR_LENGTH('abc') as charLength,
	length('abc') as Length; -- 24, 3, 3
SELECT bit_length('가나다') as bitLength,
	CHAR_LENGTH('가나다') as charLength,
	length('가나다') as Length; -- 72, 3, 9
	
CONCAT(), CONCAT_WS() => 문자열 연결함수
SELECT CONCAT_WS('/', '2025', '09', '11'); -- 구분자 (delimiter)
SELECT CONCAT_WS('siu', '2025', '09', '11');

FORMAT(숫자, 소수점자릿수) => 숫자를 소수점 아래 자릿수까지 표현, 1000단위로 콤마를 표시
SELECT FORMAT(123456.123456, 4); -- 123,456.1235

BIN(숫자) => 2진수, HEX(숫자) => 16진수, OCT(숫자) => 8진수
SELECT BIN(10), HEX(10), OCT(10); -- 1010, A, 12

INSERT(기준 문자열, 위치, 길이, 삽입할 문자열)
=> 기준 문자열의 위치부터 길이만큼을 지우고 삽입할 문자열을 끼워 넣는다.
SELECT INSERT('abcdefghi', 3, 4, '####'); -- ab####ghi
SELECT INSERT('abcdefghi', 3, 2, '@@@@'); -- ab@@@@efghi

LEFT(문자열, 길이)  RIGHT(문자열, 길이) => 왼쪽 또는 오른쪽에서 길이만큼 
SELECT LEFT('abcdefghi', 3), right('abcdefghi', 3); -- abc / ghi

대소문자 변경
SELECT UPPER('abc'), LOWER('ABC'); -- ABC / abc

LPAD(문자열, 길이, 채울문자열), RPAD(문자열, 길이, 채울문자열)
SELECT LPAD('THIS', 5, '***'), RPAD('THIS', 5, '##'); -- *THIS / THIS#
SELECT LPAD('THIS', 10, '*'), RPAD('THIS', 10, '#'); -- ******THIS / THIS######
SELECT CHAR_LENGTH(' THIS     '); -- 10
SELECT TRIM(' THIS     '); -- 공백 삭제 TRIM
SELECT CHAR_LENGTH(TRIM(' THIS     ')); -- 4

지정된 문자열 횟수만큼 반복
SELECT REPEAT('-', 10); -- ----------

문자열에서 원래 문자열을 찾아서 바꿀 문자열로 치환해줌
SELECT REPLACE('This is MySQL', 'This', 'That'); -- That is MySQL

문자열의 순서를 거꾸로 만드는 기능 => REVERSE(문자열)
SELECT REVERSE('My SQL'); -- LQS yM
SELECT TRIM(REVERSE(' My SQL')); -- LQS yM

시작 위치부터 지정한 길이 만큼 문자열을 반 => SUBSTRING(문자열, 시작위치, 길이)
SUBSTRING(), SUBSTR(), MID() => 동일한 함수.
SELECT SUBSTRING('This is MySQL', 3, 1); -- i
SELECT SUBSTRING('This is MySQL', 3, 2); -- is

SUBSTRING_INDEX(문자열, 구분자, 횟수)
SELECT SUBSTRING_INDEX('cafe.naver.com', '.', 2); -- cafe.naver
SELECT SUBSTRING_INDEX('cafe.naver.com', '.', -2); -- naver.com 
	=> 음수이면 구분자를 오른쪽부터 카운트

```

### 3. 수학 함수

```sql
올림 CEILING(숫자), 내림 FLOOR(숫자), 반올림 ROUND(숫자)
SELECT CEILING(5.67); -- 6
SELECT CEILING(-5.67); -- -5

SELECT FLOOR(4.7); -- 4
SELECT FLOOR(-4.7); -- -5

SELECT ROUND(4.3); -- 4
SELECT ROUND(4.5); -- 5
SELECT ROUND(-4.5); -- -5
SELECT ROUND(-4.2); -- -4

MOD(숫자1, 숫자2) => 숫자1을 숫자2로 나누어 나머지값 반환
SELECT MOD(157, 10); -- 7
SELECT 157 MOD 10; -- 7

거듭제곱값 POW(숫자1, 숫자2) => 숫자1을 숫자2만큼 거듭제곱하라.
SELECT POW(10, 2); -- 100
```