package Server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class sMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//new MenuInfo();

		ServerSocket serverS = null; // 서버를 기다림
//ServerSocket 클래스는 TCP 서버의 역할을 합니다. 클라이언트의 연결 요청을 기다리며 요청이 오면 요청을 수락합니다.

		Socket withClient = null;
//통신하기 위한 자원

		serverS = new ServerSocket();
		serverS.bind(new InetSocketAddress("10.0.0.96", 9999)); // 바인딩

		ArrayList<Socket> cList = new ArrayList<>(); // 저장용

		while (true) {
			System.out.println("서버 대기중");
			withClient = serverS.accept();
// 클라이언트 대기  socket을 리턴   withClient이 소캣  클라이언트랑 통신 하는 소캣 만들어줌
			cList.add(withClient);
			System.out.println(cList);
			System.out.println(withClient.getInetAddress() + "클라이언트 접속 함");
			MenuInfo s = new MenuInfo(withClient);
			s.start();
		//	new MenuInfo(withClient);
		}
	}

}
