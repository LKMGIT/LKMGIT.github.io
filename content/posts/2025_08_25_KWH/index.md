---
title: "복습정리"
date: "2025-08-26T11:00:00+09:00"
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

### 데이터 입출력

### 1. 입출력 스트림의 개념

- 프로그램을 기준으로 데이터가 들어오면 입력 스트림, 데이터가 나가면 출력 스트림이다.
- 프로그램이 다른 프로그램과 데이터를 교환하려면 양쪽 모두 입력 스트림과 출력 스트림이 필요하다.
- 바이트 스트림: 그림, 멀티미디어, 문자 등 모든 종류의 데이터를 입출력할 때 사용한다.
- 문자 스트림: 문자만 입출력할 때 사용
- 바이트의 입출력 스트림의 최상위 클래스는 InputStream과 OutputStream이다.
- 문자 입출력 스트림의 최상위 클래스는 Reader와 Writer이다,
---
### 2. 바이트 입출력 스트림

- **최상위 클래스**:
    - 입력: `InputStream`
    - 출력: `OutputStream`
- **메서드**
    - `read()`, `read(byte[])` → 입력
    - `write(int b)`, `write(byte[])` → 출력
---
### 3. 문자 스트림

- **최상위 클래스**:
    - 입력: `Reader`
    - 출력: `Writer`
- 문자 데이터 전용 처리.

---

### 4. 보조 스트림

- 다른 스트림에 연결해서 기능 보완.
- 예: `BufferedInputStream`, `InputStreamReader`, `PrintWriter` 등.
- 스트림 체인 가능: `new BufferedReader(new InputStreamReader(System.in))`

---

### 5. 성능/변환 스트림

- **버퍼 스트림**: 속도 향상 (`BufferedReader`, `BufferedWriter`)
- **문자 변환 스트림**: `InputStreamReader`, `OutputStreamWriter` (바이트 ↔ 문자 변환)

---
