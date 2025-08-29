package java_advanced_01.day23_0828.socketProgramming;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static final int PORT = 5010;    // 상수 : 서버가 사용할 포트 번호
    public static ServerSocket serverSocket = null; // 서버 소켓 : 클라이언트의 연결 요청을 받기 위한 메인 소켓
    public static ExecutorService executorService = null;   // 스레드풀 : 클라이언트 처리를 위한 스레드들을 관리

    // 접속 중인 클라이언트들을 저장하는 맵 (닉네임 -> ClientHandler 객체)
    // ConcurrentHashMap 은 여러 스레드가 동시에 접근해도 안전 -> HashMap 이 멀티스레드 환경에서 안전하지 않은 문제를 해결하기 위한 고안된 유틸리티.
    // 맵의 일부에만 잠금을 거는 방식을 사용 [세그머트 잠금, 동시 접근 허용, 성능 향상]
    private static final Map<String, ClientHandler> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        // 프로그램이 종료될 때(Ctrl+C 등) 자원을 정리하기 위한 "종료 훅" 등록
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\n[Server] 종료 전 자원 정리 중");
            try {
                // 서버 소켓이 열려있으면 닫기
                if (serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                System.out.println("[Server] 소켓 닫기 오류 " + e.getMessage());
            }
            // 스레드풀 종료
            if (executorService != null) {
                executorService.shutdown();
            }
            System.out.println("[Server] 종료 완료.");
        }));

        serverStart();
    }


    public static void serverStart() {  // 서버 시작
        try {
            serverSocket = new ServerSocket(PORT);  // 서버 소켓 생성 및 포트 바인딩

            executorService = Executors.newCachedThreadPool(); // 스레드풀 생성 (스레드 수 제한 X)
            System.out.println("[Server] Start. PORT " + PORT + "번 대기 중");

            while (!serverSocket.isClosed()) {  // 서버가 종료될 때 까지 클라이언트 연결 계속 받음
                System.out.println("[Server] 연결 요청 대기");
                Socket socket = serverSocket.accept();  // 클라이언트 연결 요청 수락
                System.out.println("[Server] 연결 수락: " + socket.getRemoteSocketAddress());

                // 새로운 클라이언트 처리 작업을 스레드풀에 제출
                // ClientHandler 객체가 클라이언트와의 통신을 전달함
                executorService.submit(new ClientHandler(socket));
            }
        } catch (IOException e) {
            System.out.println("[Server] 소켓 생성 오류: " + e.getMessage());
        }
    }


    // --- 클라이언트 목록 관리 메서드 ---

    // 새로운 클라이언트를 접속자 목록에 추가
    public static void addClient(String nickname, ClientHandler handler) {
        clients.put(nickname, handler);
    }

    // 클라이언트를 접속자 목록에서 제거
    public static void removeClient(String nickname) {
        clients.remove(nickname);
    }

    // 닉네임이 이미 사용 중인지 확인
    public static boolean isNicknameInUse(String nickname) {
        return clients.containsKey(nickname);
    }

    // 현재 접속 중인 모든 사용자의 닉네임을 가져옴
    public static Set<String> getConnectedUsers() {
        return Collections.unmodifiableSet(clients.keySet());
    }

    // 모든 접속자에게 메시지를 전송
    public static void broadcastMessage(String message) {
        // 모든 클라이언트 핸들러를 순회하며 메시지 전송
        for (ClientHandler client : clients.values()) {
            client.sendMessage(message);
        }
    }

}
