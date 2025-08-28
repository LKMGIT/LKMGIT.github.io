# 실행 가이드

3개의 컴파일 파일로 나뉨.

### Server.java - 채팅방의 관리자
### Client.java - 채팅방에 접속하는 사용자
### ClientHandler.java - 클라이언트 한 명을 전담하는 직원

## 컴파일
javac -d out src/Server.java src/Client.java src/ClientHandler.java

## 서버 실행 (기본 포트 5010)
java -cp out Server 5010

## 클라이언트 실행
java -cp out Client 127.0.0.1 5010 KHG

java -cp out Client 127.0.0.1 5010 Json

## 문제해결(FAQ)

Address already in use 오류 발생

원인: 이전 서버 프로세스가 정상적으로 종료되지 않았거나 다른 프로그램이 해당 포트(5010)를 사용 중입니다.

해결: lsof -i :5010 명령어로 해당 포트를 사용하는 프로세스를 찾아 kill -9 [PID]로 종료하거나, 서버 코드의 포트 번호를 변경.