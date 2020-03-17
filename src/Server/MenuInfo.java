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
		mList.add(new Menu("���", "1000"));
		mList.add(new Menu("���", "2000"));
		mList.add(new Menu("������", "3000"));

	}

	public String getcId() {
		return id;

	}

	MenuInfo(Socket c) {
		this.withClient = c;
	
	}

	@Override
	public void run() {
		// ServerChat(Socket c){ �� �ִ� streamSet();�� ��Ƽ������ ������� override ���� ������
		streamSet();
		receive();
		// send();
	}
	private void receive() {
		// recevie() �� ������ ������ ó��
		new Thread(new Runnable() {

			@Override
			public void run() { // �������� ������ �޴±�ɸ�
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
			// System.out.println("���̵� �Է� �ϼ���");
			id = in.nextLine();
			reMsg = withClient.getInputStream();
			byte[] reBuffer = new byte[100];
			reMsg.read(reBuffer);
			id = new String(reBuffer);
			id = id.trim(); // Ʈ�� ���� ����
			System.out.println(cList);
			for (int i = 0; i < cList.size(); i++) {
				if (cList.get(i).getcId().equals(id)) {
					System.out.println("[" + id + "]�Բ��� �α��� �ϼ̽��ϴ�.");

				} else if (!cList.get(i).getcId().equals(id)) {
					System.out.println("��ϵ��� ���� ID�Դϴ�.");

				} else {
					System.out.println("��й�ȣ�� Ȯ�����ּ���.");
				}
				// }
				String reMsg = "���� �Ǿ����ϴ�";
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