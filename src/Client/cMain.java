package Client;

import java.net.Socket;

public class cMain {
	

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
	//	new ClientInfo();
		
		Socket withServer=null;
		withServer= new Socket("10.0.0.96",9999);
		new ClientInfo(withServer); 
	}

}
