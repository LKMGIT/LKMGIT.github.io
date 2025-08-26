---
title: "복습정리"
date: "2026-08-26T17:00:00+09:00"
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
## 정리

## 데이터 입출력

### 객체 스트림

- **직렬화(ObjectOutputStream)**: 객체 → 바이트 변환
- **역직렬화(ObjectInputStream)**: 바이트 → 객체 복원
- `Serializable` 인터페이스 필요.
- `serialVersionUID` 관리 주의.
---
### 바이트 스트림 (Byte Stream)
- Stream은 클라이언트나 서버 간에 출발지 목적지로 입출력하기 위한 데이터가 흐르는 통로
- 자바는 스트림의 기본 단위를 바이트로 두고 있어, 네트워크, 데이터베이스로 전송하기 위해 최소 단위를 바이트 스트림으로 변환하여 처리한다.

## 기술 자바기술 스택에서 어디서 응용되어 사용하는가?
- 직렬화를 응용하면 휘발성이 있는 캐싱 데이터(객체)를 영구 저장할 때 사용
---
### JSON(JavaScript Object Notation)

- 객체 표기{} brace, 중괄호
- {
- “속성명” : 값,
- “속성명” : 값,
- }
    
JSONObject 클래스 : JSON 객체 표기를 생성하거나 파싱할 때 사용
    
JSONArray클래스 : JSON 배열 표기 생성하거나 파싱할 떄 사용