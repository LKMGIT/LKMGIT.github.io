package java_advanced_01.day23_0828.socketProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    // 클라이언트 애플리케이션의 시작점
    public static void main(String[] args) {
        // 인자가 부족할 경우 사용법을 안내
        if (args.length != 3) {
            System.out.println("사용법: java Client <서버IP> <포트> <닉네임>");
            return;
        }

        String serverIp = args[0];
        int port = Integer.parseInt(args[1]);
        String nickname = args[2];

        try (
                Socket socket = new Socket(serverIp, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("서버에 연결되었습니다. 닉네임 등록을 진행합니다.");

            // ⭐ 서버로부터의 메시지 수신을 전담하는 스레드 시작
            // 이 스레드는 서버 메시지를 받아 화면에 출력하는 역할만 수행
            Thread readerThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    System.err.println("서버 연결이 끊어졌습니다.");
                }
            });
            readerThread.start();

            // 닉네임 등록 로직
            out.println("NICK " + nickname);

            // 사용자 입력 및 전송 루프
            String userMessage;
            while (true) {
                userMessage = scanner.nextLine();
                if ("/quit".equalsIgnoreCase(userMessage)) {
                    out.println(userMessage); // 서버에 종료 알림
                    break;
                }
                out.println(userMessage);
            }

            System.out.println("채팅을 종료합니다.");
            readerThread.interrupt(); // 리더 스레드 종료

        } catch (IOException e) {
            System.err.println("서버 연결 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}