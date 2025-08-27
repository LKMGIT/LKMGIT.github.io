---
title: "Jang"
date: 2025-08-27T12:24:10+09:00
draft: false
authors: ["장현우ID"]   # 기본값, 각자 바꿔 쓰기
tags: []
categories: []
description: ""
ShowToc: true
TocOpen: false
cover:
  image: ""
  alt: ""
  relative: true
---
# JSON

- 객체 표기 { } brace , 중괄호

{

   “속성명” : 값,

   “속성명” : 값,

   ,,,,,,,

}

배열 [ ]

{

“id” : “sym” ,

“pwd” : “1234” ,

“address” : “강남구 신사동”,

“phone” : {”010-1234-1234”, “010-1111-2222},

“skill” : [”java”, “HTML” , “JPA”]

}

JSONObject 클래스 : JSON 객체 표기를 생성하거나 파싱할때 사용

JSONArray 클래스 : JSON 배열 표기를 생성하거나 파싱할때 사용

# 네트워크 기초

네트워크 : 여러 컴퓨터들을 회선으로 연결해 놓은 것

LAN : 가정, 회사, 건물 특정 영역에 존재하는 컴퓨터를 연결한 것

WAN : LAN으로 묶어놓은 망 = 인터넷

서버 : 서비스를 제공 하는 프로그램

클라이언트 : 서비스를 요청 하는 프로그램

클라이언트가 서비스를 요청하고 서버는 처리 결과를 응답으로 제공

1. TCP서버 프로그램을 개발하려면 우선 ServerSocket 객체를 생성해야한다

ServerSocket은 Port를 가져야 하는데 50001번이라고 내맘대로 지정

 1 - 1 : ServerSocket server = new ServerSocket(50001);

 1 - 2 ServerSocket server = new ServerSocket( );

       server.bind(new InetSocketAddress(”192.16.180.181” , 50001));

1. Server소켓이 생성되었다면 연결 요청이 수락을 위해서 accpet()를 실행해야 한다
- accpet() 메소드는 클라이언트가 연결요청하기 전까지 블로킹(실행을 멈춘 상태)된다
- 클라이언트 연결 요청이 들어오면 블로킹이 해제되고 통신용 socket을 리턴한다
    
    Socket socket = server.accpet();
    
1. 클라이언트가 서버에서 연결요청을 하려면 Socket객체를 생성하고 생성자 매개값에 서버 IP,Port(필수값) 
    
    제공
    
    Socket socket = new Socket(”IP” , 50001);
    
2. 클라이언트가 연결요청(Connect()) 을 하고 서버가 연결 수락(accept()) 했다면 양쪽의
    
    socket객체로부터 각각 입력스트림과 출력 스트림을 얻을 수 있다
    
    상대방에게 데이터를 보낼때에는 보낼데이터는 byte[ ]배열로 생성하고 
    
    매개값으로 해서 OutputStream의 write()메소드를 호출해서 UTF-8인코딩한 바이트 배열로 전송
    

== 쓰기 로직 ==

String data = “보낼 데이터”;

byte[] bytes = data.getBytes(”UTF-8);

OutputStream os = socket.getOutputStream( );

os.write(bytes);

os.flush( )

String data = “보낼 데이터”;

DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

os.writeUTF(data);

os.flush( )

== 읽기 로직 (데이터를 받을 때) ==

받을 데이터를 저장할 byte[ ] 배열을 하나 생성하고

이걸 매개값으로 해서 InputStream의 read( ) 메소드를 호출해서 읽어야한다

read( ) 메소드는 읽은 데이터를 byte[ ] 배열에 저장하고 읽은 바이트 수를 리턴한다

조건) UTF-8 인코딩

byte[] bytes = new byte[1024];

InputStream is = socket.getInputStream( );

int number = is.read(bytes);

String data = new String(bytes, 0, number, “UTF-8”);

단 DataOutputStream으로 문자열을 보낼떄만 DataInputStream으로 읽을 수 있다

DataInputStream dis = new DataInputStream(socket.getInputStream());

String data  = dis.readUTF();

## TCP / UDP

1. TCP(T**ransmission Control Protocol)**
- TCP는 연결 지향적 프로토콜이다.
- 연결 지향적은 클라이언트와 서버가 연결된 상태에서 데이터를 주고받는 프로토콜

1. TCP 특징

  1. 연결형 서비스로 가상 회선 방식을 제공 

- 3-way 과정을 통해 연결을 설정하고
- 4-way 과정을 통해 연결을 해제한다

1. 흐름제어
- 데이터 처리 속도를 조절하여 수신자의 버퍼 오버플로우를 방지

1. 혼잡제어 
- 네트워크 내의 패킷 수가 과도하게 증가하지 않도록 방지

4 .높은 신뢰성 보장

- 신뢰성이 높은 전송을 하기 떄문에 UDP보다 속도가 느림

1. 전이중, 점대점
- 전이중 - 전송이 양방향으로 동시에 일어날 수 있다
- 점대점 - 각 연결이 정확히 2개의 종단점을 가지고 있다

1. **UDP(User Datagram Protocol)**

 1. UDP 특징

- UDP는 비연결형 프로토콜이다.
- 연결을 위해 할당되는 논리적인 경로가 없고, 각각의 패킷은 다른 경로로 전송되며, 독립적인 관계를 지닌다.

2. 비연결형 서비스로 데이터그램 방식을 제공한다.

- 데이터의 전송 순서가 바뀔 수 있다.

1.  데이터 수신 여부를 확인하지 않는다.
- TCP의 3-way handshaking과 같은 과정 X

1. 신뢰성이 낮다
- 흐름 제어(flow control)가 없어서 제대로 전송되었는지, 오류가 없는지 확인할 수 없다.

1. TCP보다 속도가 빠르다.