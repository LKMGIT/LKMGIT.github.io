---
title: "복습정리"
date: "2026-08-27T15:00:00+09:00"
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
## Network

### Socket Program
- Server소켓이 생성되었다면  연결 요청이 수락을 위해서 accept()를 실행해야 한다.
- accept()메소드는 클라이언트가 연결요청하기 전까지 블로킹된다.
- 블로킹이란 실행을 멈춘 상태가 된다는 뜻이다.
- 클라이언트의 요청이 들어오면 블로킹이 해제되고 통신용 Socket을 리턴한다.

    Socket socket = sever.accept();
---
### 네트워크란?

→ 여러 컴퓨터들을 통신 회선으로 연결한 것.

- 물리적인 관점에서 바라보는 환경(하드웨어 환경)
- LAN(Local Area Network) : 가정, 회사, 건물, 특정 영역에 존재하는 컴퓨터를 연결한 것. (근거리 통신망)
- WAN(Wide Area Network) : LAN을 묶어놓은 망 (관역 통신망)
---
### 서버와 클라이언트

- 서버 : 서비스를 제공하는 프로그램
- 클라이언트 : 서비스를 요청하는 프로그램
- 클라이언트가 서비스를 요청하면, 서버는 처리결과를 응답으로 제공.
---
### TCP(Transmission Control Protocol)란?

 → TCP는 연결형 트로토콜로, 상대방이 연결된 상태에서 데이터를 주고 받는 전송용 프로토콜이다.

- 약속된 데이터 전송 규약이 있어야 한다.(Ex 언어)
- 보낸 데이터가 순서대로 전달되며, 손실이 발생하지 않음
- 높은 신뢰성
---
### UDP(User Datagram Protocol)란?

→ 데이터를 데이터그램 단위로 처리하는 프로토콜

- 비연결형 서비스로 데이터그램 방식을 제공한다.
- 정보를 주고 받을 때 정보를 보내거나 받는다는 신호절차를 거치지 않는다.
- TCP보다 속도가 빠르다
- 신뢰성이 낮다.

