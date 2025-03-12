package Sever_Shop;

public class Shop_Commodity {
	private int ID;
	private String commodity;
	private String money;
	private int amount;
	private String category;
	private String income;
	

	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getCommodity() {
		return commodity;
	}


	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}


	public String getMoney() {
		return money;
	}


	public void setMoney(String money) {
		this.money = money;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getIncome() {
		return income;
	}


	public void setIncome(String income) {
		this.income = income;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	 @Override
	    public String toString() {
	        return "Shop_Commodity{" +
	                "ID=" + ID +
	                ", commodity='" + commodity + '\'' +
	                ", money='" + money + '\'' +
	                ", amount=" + amount +
	                ", category='" + category + '\'' +
	                ", income='" + income + '\'' +
	                '}';
	    }

}
