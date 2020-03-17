package Server;

import java.util.ArrayList;

import Client.Client;



public class MenuCenter {
	private ArrayList<MenuInfo> mList = new ArrayList<>();
	private ArrayList<Client>cList= new ArrayList<>();
	public void addServerChat(MenuInfo s) {
		this.mList.add(s);

	}

	public void reMsg(String msg, String id) {
		
		if (msg.indexOf("/to") == 0) {
			int firstInt = msg.indexOf(" ") + 1; 
			int endInt = msg.indexOf(" ", firstInt);
			String targetID = msg.substring(firstInt, endInt);
			String targetMsg = "[@" + id + "]" + msg.substring(endInt + 1);
			sendOne(targetID, targetMsg);
		} else {
	
		sendAll("[" + id + "]" + msg);
	}

	}
	public void sendOne(String targetID,String targetMsg) {
		for (int i = 0; i < mList.size(); i++) {
			if (targetID.equals(mList.get(i).getMyId())) {
				mList.get(i).send(targetMsg);
			} 
		}
	}

	public void sendAll(String msg) {
		for (int i = 0; i < mList.size(); i++) {
			mList.get(i).send(msg);
		}
	}

}
