package mypackage;

public class Cart {
	private String name;
	private int price;
	private int ID;
	private int quantity;
	private String category;
	private int offer;
	private boolean status;
	private String resType;
	private String restaurantName;
	
	Cart(String name, int price, int quantity, String category, int offer, int ID,String restaurantName, String resType){
		setID(ID);
		setName(name);
		setPrice(price);
		setQuantity(quantity);
		setCategory(category);
		setOffer(offer);
		setStatus(false);
		setRes(resType);
		setRestaurantName(restaurantName);
	}
	
	public float OrderValue() {
		
		float total=0;
		float p = quantity*price;
		float discount = (p*offer)/100;
		total = p-discount;
		
		return total;
	}
	
	public void showDetails(int charges) {
		System.out.println("Bought item: "+getName()+" quantity: "+getQuantity()+" for Rs "+ getPrice()+""
				+ " from restaurant "+getRestaurantName()+" and Delivery Charge: "+charges);
	}
	
	
	public void DisplayItemsInCart() {
		System.out.println(ID+" - "+name+" - "+price+" - "+quantity+" - "+offer+"% off ");
	}
	
	public void setRes(String s) {
		this.resType = s;
	}
	
	public String getRes() {
		return resType;
	}
	
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getOffer() {
		return offer;
	}
	public void setOffer(int offer) {
		this.offer = offer;
	}


	public String getRestaurantName() {
		return restaurantName;
	}


	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
}
