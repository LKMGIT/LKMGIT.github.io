package java_advanced_01.day23_0828.socketProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private String nickname;
    private PrintWriter out;

    // ClientHandler 생성자. 클라이언트와 연결된 소켓을 받아서 초기화.
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        // try-with-resources 이 블록을 벗어나면 스트림과 소켓이 자동으로 닫힘
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true, StandardCharsets.UTF_8);
        ) {
            // 클래스 변수 `out`에 현재 스트림을 할당
            this.out = out;

            // 닉네임 등록
            while (true) {
                out.println("NICK <이름>");
                String message = br.readLine();

                // 메시지가 없거나 형식이 잘못되면 다시 요청
                if (message == null || !message.startsWith("NICK ")) {
                    out.println("ERR : 잘못된 형식입니다.");
                    continue;
                }

                // 닉네임 추출 및 공백 제거
                String proposedNickname = message.substring(5).trim();

                // 닉네임이 비어있거나, 이미 사용 중이면 오류 메시지 전송
                if (proposedNickname.isEmpty() || Server.isNicknameInUse(proposedNickname)) {
                    out.println("ERR : 사용 중이거나 유효하지 않은 닉네임입니다.");
                    continue;
                }

                this.nickname = proposedNickname;
                Server.addClient(this.nickname, this); // 서버의 접속자 목록에 추가
                System.out.println("[Server] " + nickname + " joined");
                out.println("OK " + nickname + " joined");
                Server.broadcastMessage(nickname + " joined");
                break; // 닉네임 등록 성공, 루프 종료
            }

            // 메인 통신
            String line;
            while ((line = br.readLine()) != null) {
                // '/'로 시작하는지 확인하여 명령어를 처리
                if (line.startsWith("/")) {
                    handleCommand(line);
                } else {
                    // 일반 채팅 메시지는 모든 클라이언트에게 브로드캐스트
                    Server.broadcastMessage("[" + nickname + "]" + line);
                }
            }
        } catch (IOException e) {
            // 연결이 끊어진 경우.
            if (e.getMessage().equals("Socket closed")) {
                System.out.println("정상적으로 연결 종료됨.");
            } else {
                System.out.println("통신 중 오류 발생: " + e.getMessage());
            }
        } finally {
            // 연결 종료 시 정리
            if (nickname != null) {
                // 접속자 목록에서 해당 클라이언트 제거
                Server.removeClient(this.nickname);
                // 모든 사용자에게 퇴장 알림 전송
                Server.broadcastMessage(nickname + " left");
                System.out.println("[Server] " + nickname + " left");
            }
            try {
                // 소켓이 열려있으면 닫기
                if (clientSocket != null) clientSocket.close();
            } catch (IOException e) {
                System.out.println("[ClientHandler] 소켓 닫기 오류: " + e.getMessage());
            }
        }
    }

    // 클라이언트로부터 받은 명령어를 처리
    private void handleCommand(String command) {
        if ("/quit".equalsIgnoreCase(command)) {
            // /quit 명령어를 받으면 소켓을 닫아 연결 종료를 유도
            try {
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if ("/who".equalsIgnoreCase(command)) {
            // /who 명령어를 받으면 현재 접속자 목록을 가져와 전송
            String users = String.join(", ", Server.getConnectedUsers());
            sendMessage("현재 접속자: " + users);
        } else {
            // 알 수 없는 명령어에 대한 안내
            sendMessage("ERR : 알 수 없는 명령어입니다.");
        }
    }

    // 클라이언트에게 메시지를 전송
    public void sendMessage(String message) {
        out.println(message);
    }
}