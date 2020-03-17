package Client;

public class Client {
	public String cId="";
	public String cPw="";
	
	public Client() {
		
	}
	
	public Client(String id,String pw) {
		this.cId=id;
		this.cPw=pw;
		
	}
	public void prt() {
		System.out.println("아이디: "+this.cId+"/"+"비번: "+this.cPw);
	}

	public  String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getcPw() {
		return cPw;
	}

	public void setcPw(String cPw) {
		this.cPw = cPw;
	}



}
