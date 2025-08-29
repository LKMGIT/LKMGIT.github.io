---
title: "자바 네트워크 정리"
date: "2025-08-27T00:00:00+09:00"   
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
## 복습 정리

## 자바네트워크 정리
네트워크부터 TCP 네트워킹 까지

# 자바네트워크

## 네트워크

- **네트워크** : 여러 컴퓨터들을 통신 회선으로 연결한 것 (물리적인 관점에서 바라본 환경)
- **LAN(Local Area Network)** : 가정, 회사, 건물, 특정 영역에 존재하는 컴퓨터를 연결한 것
- **WAN(Wide Area Network)** : 국가나 대륙처럼 매우 넓은 지역을 연결하는 네트워크
- **MAN (Metropolitan Area Network)**: 도시와 같이 LAN보다 넓은 지역을 연결하는 네트워크

## 서버와 클라이언트

- **서버(Server)** : 서비스를 제공하는 프로그램
- **클라이언트(Client)** : 서비스를 요청하는 프로그램
- 먼저 클라이언트가 서비스를 요청하고, 서버는 처리 결과를 응답으로 제공

## IP 주소

- 네트워크 어댑터(LAN)마다 할당되는 컴퓨터의 고유한 주소

## Port 번호

- 운영체제가 관리하는 서버 프로그램의 연결 번호. 서버 시작 시 특정 Port 번호에 바인딩
- 전용포트는 클라이언트가 자신이 원하는 서비스를 요청할 때 각각의 서버한테 해당 데이터를 요청하기 위해서는 원하는 포트에게 스트림을 요청을 해서 리스폰스를 받기위한 네트워크를 통해서 포트를 요청한다.

## InetAddress

- 자바는 IP 주소를 [java.net](http://java.net) 패키지의 InetAddress로 표현
- 로컬 컴퓨터의 InetAddress를 얻으려면 InetAddress.getLocalHost()메소드 호출

## TCP (Transmission Control Protocol)

- 연결형 프로토콜로 상대방이 연결된 상태에서 데이터를 주고 받는 전송용 프로토콜
- 클라이언트가 연결 요청을 하고 서버가 연결을 수락하면 통신 회선이 고정되고, 데이터는 고정 회선을 통해 전달. TCP는 보낸 데이터가 순서대로 전달되며 손실이 발생하지 않음
- IP 주소로 프로그램들이 통신할 때 약속된 데이터 전송 규약이 있어야 한다.
- 서버는 스레드 단위로 소켓을 생성함.

    ```java
    1. TCP 서버 프로그램을 개발하려면 우선 ServerSocket 객체를 생성해야한다.
    		ServerSocketㅇ느 Port를 가져야 하는데 50001번이라고 임의 지정 하였다.
    
    1-1. ServerSocket server = new ServerSocket(50001);
    
    1-2. ServerSocket server = new ServerSocket();
    		 server.bind(new InetSocketAddress("IP 주소", 50001));
    ```

    ```java
    2. Server 소켓이 생성되었다면 연결 요청이 수락을 위해서 accept()를 실행해야 한다. 
    	 accept() 메소드는 클라이언트가 연결을 요청하기 전까지 블로킹 됨.
    		 블로킹이란, 실행을 멈춘 상태가 된다는 뜻.
    	 클라이언트의 연결 요청이 들어오면 블로킹이 해제되고 통신용 Socket을 리턴한다.
    	 
    	 Socket socket = server.accept();
    ```


### 클라이언트

- 서버에서 연결요청을 하려면 Socket 객체를 생성하고, 생성자 매개값에 **서버 IP**, **Port**(필수)를 제공한다.
    - `Socket socket = new Socket(”IP”, 50001);`

## TCP 네트워킹

클라이언트가 연결요청(`Connect()`) 을 하고 서버가 연결 수락 (`accept()`) 했다면

양쪽의 Socket 객체로부터 각각 `입력스트림`과 `아웃스트림`을 얻을 수 있다.

```java
상대방에게 데이터를 보낼때에는 보낼데이터를 byte[] 배열로 생성하고, 
매개값으로 해서 OutputStream 의 write() 메소드를 호출해서 UTF-8 인코딩한 바이트 배열로 전송한다.

== 쓰기 로직 1 ==
String data = "보낼 데이터";
byte[] bytes = data.getBytes("UTF-8");
OutputStream os = socket.getOutputStream();
os.write(bytes);
os.flush();

== 쓰기 로직 2 ==
String data = "보낼 데이터";
DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
os.writeUTF(data);
os.flush;
```

```java
받은 데이터를 저장할 byte[] 배열을 하나 생성하고,
매개값으로 해서 InputStream의 read() 메소드를 호출해서 읽음
- read() 메소드는 읽은 데이터를 byte[] 배열에 저장하고, 읽은 바이트 수를 리턴한다.
- 조건: UTF-8 인코딩

== 읽기 로직 (데이터를 받을 때) ==
byte[] bytes = new byte[1024];
InputStream is = socket.getInputStream();
int number = is.read(bytes);
String data = new String(bytes, 0, number, "UTF-8");

// 단, DataOutputStream 으로 문자열을 보낼때만 DataInputStream 으로 읽을 수 있음.
DataInputStream dis = new DataInputStream(socket.getInputStream());
String data = dis.readUTF();
```