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
		System.out.println("메뉴이름: "+this.mName+"가격: "+this.mPrice);
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
