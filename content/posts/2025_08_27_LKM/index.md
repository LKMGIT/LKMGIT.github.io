---
title: "자바 네트워크"
date: "2025-08-27T10:00:00+09:00"
draft: false              
author: ["이경민"]     
tags: ["Study"]     # 자유롭게
categories: ["Devlog"]
description: "TCP 통신 프로그램"
ShowToc: false
TocOpen: false
cover:
  image: ""               
  alt: ""
  relative: true
---
<!--more-->
### 네트워크 정리
네트워크 : 여러 컴퓨터들을 통신 회선으로 연결한 것
LAN : (지역적) 가정, 회사, 건물, 특정 영역에 존재하는 컴퓨터를 연결한 것
WAN : LAN을 연결한 것 (인터넷)
Port : OS가 관리하는 서버 프로그램의 연결 번호. 서버 시작 시 특정 Port 번호에 바인딩

### 서버와 클라이언트
서버: 서비스를 제공하는 프로그램 
클라이언트 : 서버에 서비스를 요청하는 프로그램 

클라이언트는 서버에게 서비스를 요청하고 서버는 클라이언트에게 서비스를 응답한다. 
![서버 클라](서버클라.png)

## TCP란 무엇인가?
TCP는 **연결형 프로토콜**로, 상대방이 연결된 상태에서 데이터를 주고 받는 전송용 프로토콜이다. 
클라이언트가 연결 요청을 하고 서버가 연결을 수락하면 **회선이 고정**, 데이터는 고정 회선을 통해 전달 된다. 

#### 서버 부분
1. 서버 소켓이 존재해야한다. (ServerSocket 객체 생성)
2. ServerSocket의 포트를 지정해준다. 
```java
ServerSocket server = new ServerSocket;
sever.bind(new InetSocketAddress(50001)); // 특정 포트번호를 할당하고 싶을 경우 bind 메서드 사용 
```
3. 소켓이 생성 되었다면 클라이언트 연결 요청의 수락을 위해서 accpet()를 실행한다.
```java
Socket socket = server.accept();
```
accpet()를 사용하게 되면 소켓은 클라이언트의 연결 요청 이전까지 블로킹 상태가 된다.
그 후 클라이언트의 요청이 들어오면 블로킹이 해제 되고 통신용 Socket을 리턴하게 된다.

```java
public static void startServer() {
        // 스레드 Thread (실행단위)
        Thread thread = new Thread(){
            public void run() {

                try {
                    serverSocket = new ServerSocket(50001);
                    System.out.println("서버 시작");
                    while (true) {
                        System.out.println("\n 서버 연결 요청 기다림");
                        //연결 수락
                        Socket socket = serverSocket.accept();

                        // 연결된 클라이언트의 정보를 얻기
                        InetSocketAddress ia = (InetSocketAddress)socket.getRemoteSocketAddress();
                        System.out.println("서버 주소: " + ia.getAddress().getHostAddress() + ":" + ia.getPort());
                        
                        //연결 끊기
                        socket.close();
                        System.out.println("서버 종료");


                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        };
        thread.start();
    }
```

#### 클라이언트 부분 
클라이언트가 서버에게 연결 요청을 하려면 Socket 객체를 생성하고 생성자 매개값에 IP, Port를 제공한다. 
```java
Socket socket = new Socket("IP",50001);
```
```
 public static void main(String[] args) {
        try{
            Socket socket = new Socket("localhost", 50001);
            System.out.println("클라이언트 서버 성공");
            
            socket.close();
            System.out.println("클라이언트 서버 종료");
        
        }catch (IOException e){
            System.out.println("연결 실패");
        }

    }
```