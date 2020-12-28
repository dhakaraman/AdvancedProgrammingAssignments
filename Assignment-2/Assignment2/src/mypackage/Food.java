package mypackage;

public class Food {
	private String name;
	private int price;
	private final int ID;
	private int quantity;
	private String category;
	private int offer;
	private String restaurant;
	private String Restype;
	
	Food(String name, int price, int quantity, String category, int offer, int ID, String res, String type){
		this.ID = ID;
		setName(name);
		setPrice(price);
		setQuantity(quantity);
		setCategory(category);
		setOffer(offer);
		setRestaurant(res);
		setRestype(type);
	}
	
	public void displayFood() {
		System.out.println(ID+" "+restaurant+"-"+name+" "+price+" "+quantity+" "+offer+"% off"+category);
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

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public String getRestype() {
		return Restype;
	}

	public void setRestype(String restype) {
		Restype = restype;
	}
}
