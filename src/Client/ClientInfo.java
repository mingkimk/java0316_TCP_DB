package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


import Server.Menu;

public class ClientInfo {
	Scanner in = new Scanner(System.in);
	ArrayList<Client> cList = new ArrayList<>();
	ArrayList<Menu> mList = new ArrayList<>();
	private Socket withServer = null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	private String id = "";
	private String pw = "";
//	String cId;
//	String cPw;

	public void Client() {

		cList.add(new Client("이씨","111"));
		cList.add(new Client("박씨", "222"));
		cList.add(new Client("김씨", "333"));

	}


	

	ClientInfo(Socket c) {
		this.withServer = c;
		streamSet();
	
		send();
		receive();
	}

	private void receive() {
		// recevie() 만 별도로 쓰레드 처리
		new Thread(new Runnable() {

			@Override
			public void run() { // 독자적인 스레드 받는기능만
				// TODO Auto-generated method stub
				try {
					System.out.println("recevie start!");
					while (true) {
						reMsg = withServer.getInputStream();
						byte[] reBuffer = new byte[100];
						reMsg.read(reBuffer);
						String msg = new String(reBuffer);
						msg = msg.trim();
						System.out.println(msg);
					}
				} catch (Exception e) {
					System.out.println("client receive end");
					return;
				}

			}
		}).start();

	}

	private void send() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					System.out.println("send start!");
				//	Scanner in = new Scanner(System.in);
					while (true) {
						String reMsg = in.nextLine();

						sendMsg = withServer.getOutputStream();
						sendMsg.write(reMsg.getBytes());

					}
				} catch (Exception e) {
					System.out.println("client send end");
					return;

				}
			}
		}).start();

	}
	   


	private void streamSet() {
		try {
			System.out.println("ID 를 입력 하세요");
			id= in.nextLine();
			
			sendMsg= withServer.getOutputStream();
			sendMsg.write(id.getBytes());
			
			reMsg= withServer.getInputStream();
			byte[] reBuffer= new byte[100];
			reMsg.read(reBuffer);
			String msg= new String(reBuffer);
			msg=msg.trim();
			System.out.println(msg);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
		
	}
}

	
	
