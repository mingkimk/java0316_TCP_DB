package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Client.Client;

public class MenuInfo extends Thread {
	Scanner in = new Scanner(System.in);
	ArrayList<Menu> mList = new ArrayList<>();
	ArrayList<Client> cList = new ArrayList<>();
	private Socket withClient = null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	private String id = "";
	private String pw = "";

	public void MenuInfo() {
		mList.add(new Menu("김밥", "1000"));
		mList.add(new Menu("라면", "2000"));
		mList.add(new Menu("떡볶이", "3000"));

	}

	public String getcId() {
		return id;

	}

	MenuInfo(Socket c) {
		this.withClient = c;
	
	}

	@Override
	public void run() {
		// ServerChat(Socket c){ 에 있는 streamSet();을 멀티스래드 만들려고 override 만들어서 돌리기
		streamSet();
		receive();
		// send();
	}
	private void receive() {
		// recevie() 만 별도로 쓰레드 처리
		new Thread(new Runnable() {

			@Override
			public void run() { // 독자적인 스레드 받는기능만
				// TODO Auto-generated method stub
				try {
					System.out.println("receive start!");
					while (true) {
						reMsg = withClient.getInputStream();
						byte[] reBuffer = new byte[100];
						reMsg.read(reBuffer);
						String msg = new String(reBuffer);
						msg = msg.trim();

						System.out.println("[" + id + "]" + msg);
					}
				} catch (Exception e) {
					System.out.println("receive End");
					return;
				}

			}
		}).start();

	}

	private void send() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					System.out.println("send start!!");
					while (true) {
						String reMsg = in.nextLine();
						if (reMsg.indexOf("/Menu") == 0) {
							int firstInt = reMsg.indexOf(" ") + 1;
							int endInt = reMsg.indexOf(" ", firstInt);

							sendMsg = withClient.getOutputStream();
							sendMsg.write(reMsg.getBytes());
							System.out.println(mList);

						}
					}
				} catch (Exception e) {
					System.out.println("send End");
					return;
				}

			}
		}).start();
	}

	private void streamSet() {
		try {
			// while(getcId()==(id)) {
			// System.out.println("아이디를 입력 하세요");
			id = in.nextLine();
			reMsg = withClient.getInputStream();
			byte[] reBuffer = new byte[100];
			reMsg.read(reBuffer);
			id = new String(reBuffer);
			id = id.trim(); // 트림 공백 제거
			System.out.println(cList);
			for (int i = 0; i < cList.size(); i++) {
				if (cList.get(i).getcId().equals(id)) {
					System.out.println("[" + id + "]님께서 로그인 하셨습니다.");

				} else if (!cList.get(i).getcId().equals(id)) {
					System.out.println("등록되지 않은 ID입니다.");

				} else {
					System.out.println("비밀번호를 확인해주세요.");
				}
				// }
				String reMsg = "접속 되었습니다";
				sendMsg = withClient.getOutputStream();
				sendMsg.write(reMsg.getBytes());
				// System.out.println(mList);
				InetAddress c_ip = withClient.getInetAddress();
				String ip = c_ip.getHostAddress();
			}
		} catch (IOException e) {

		}

	}

	private boolean idCheck(String id) {
		boolean check = true;
		Client member = FindById(id);
		if (member == null)
			check = false;
		return check;
	}

	Client FindById(String id) {
		for (Client client : cList) {
			if (client.getcId().equals(id)) {
				return client;
			}
		}
		return null;
	}

}