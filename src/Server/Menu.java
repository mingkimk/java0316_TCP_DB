package Server;

public class Menu {
	private String mName="";
	private String mPrice="";
	
	public Menu() {
	
	}
	public Menu(String name,String price) {
		this.mName=name;
		this.mPrice=price;
	}
	protected void prt() {
		System.out.println("�޴��̸�: "+this.mName+"����: "+this.mPrice);
	}

	private String getmName() {
		return mName;
	}

	private void setmName(String mName) {
		this.mName = mName;
	}

	private String getmPrice() {
		return mPrice;
	}

	private void setmPrice(String mPrice) {
		this.mPrice = mPrice;
	}


}
