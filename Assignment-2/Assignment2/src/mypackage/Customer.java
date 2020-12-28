package mypackage;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer implements Outer{
	protected final String name;
	protected final String type;
	protected final String address;
	protected float wallet;
	protected int charges;
	protected float rewards;
	public ArrayList<Cart> objC;
	static int res=0;

	Customer(String name,String type,String address, int charges){
		this.wallet = 1000;
		this.name = name;
		this.address = address;
		this.type = type;
		this.charges = charges;
		this.rewards = 0;
		objC = new ArrayList<Cart>();
	}
	
	public int displayMenu(Restaurant restaurants[]) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome "+getName());
		
		System.out.println("Customer menu");
		System.out.println("1) Select Restaurant");
		System.out.println("2) Checkout cart");
		System.out.println("3) Reward won");
		System.out.println("4) print the recent orders");
		System.out.println("5) Exit");
		
		int choice = sc.nextInt();
		
		if(choice==1) {
			System.out.println("Choose Restaurant");
			for(int i=0;i<5;i++) {
				restaurants[i].displayUserWithType(i);
			}
			
			res = sc.nextInt();
			
			if(restaurants[res-1].objF.size()!=0) {
				
				restaurants[res-1].AvailableFood();
				
				addToCart(restaurants[res-1]);
			
			}
		}
		
		else if(choice==2) {

			ItemsInCart();
			System.out.println("Delivery charge - "+getCharges()+"/-");
			Zotato_App.deliveryCharges+=getCharges();
			float temp = restaurants[res-1].totalOrderValue(this);
			Zotato_App.companyBalance+=temp/100;
			
			int r = restaurants[res-1].assignRewardPoints(temp);
			
			setRewards(r);
			
			restaurants[res-1].setOrderTaken();
			
		}
		else if(choice==3) {
			displayRewards();
		}
		else if(choice==4) {
			recentOrders();
		}
		return choice;
	}
	
	
	
	public void addToCart(Restaurant r) {
		
		Scanner sc = new Scanner(System.in);
		int code = sc.nextInt();
		System.out.println("Enter item quantity");
		int quant = sc.nextInt();
		
		int temp=0;
		for(int i=0;i<r.objF.size();i++) {
			if(code==r.objF.get(i).getID()) {
				temp = i;
				break;
			}
		}
		
		objC.add(new Cart(r.objF.get(temp).getName(),r.objF.get(temp).getPrice(),quant,r.objF.get(temp).getCategory(),
				r.objF.get(temp).getOffer(),code,r.objF.get(temp).getName(),r.objF.get(temp).getRestype()));
		
		objC.get(objC.size()-1).setRes(r.type);
		System.out.println("Items added to cart");
	}
	
	
	public void displayUserWithType(int i) {
		if(getType().equals("Normal"))
			System.out.println(i+1+". "+getName());
		else
			System.out.println(i+1+". "+getName()+" ("+getType()+")");
	}
	
	
	public void ItemsInCart() {
		System.out.println("Items in Cart -");
		for(int i=0;i<objC.size();i++) {
			if(objC.get(i).getStatus()==false)
				objC.get(i).DisplayItemsInCart();
		}
	}
	
	public void recentOrders() {
		int count=0;
		for(int i = objC.size()-1;i>0;i--){
			objC.get(i).showDetails(getCharges());
			count++;
			if(count>10)
				break;
		}
	}
	
	public int TotalItemsInCart() {
		int t=0;
		for(int i=0;i<objC.size();i++) {
			if(!objC.get(i).getStatus())
				t+=objC.get(i).getQuantity();
		}
		return t;
	}
	
	public void checkOut() {
		for(int i=0;i<objC.size();i++) {
			objC.get(i).setStatus(true);;
		}
	}
	
	public void displayUser() {
		System.out.println(name);
	}
	
	@Override
	public void displayUserDetails() {
		System.out.println(name+" "+address+", "+wallet);
	}
	public float calculateTotalBill(Restaurant obj) {
		
		float total = 0;
		for(int i=0;i<objC.size();i++) {
			if(objC.get(i).getStatus()==false)
				total+=objC.get(i).OrderValue();
		}
		float overall =0;
	
		total = total - overall;
		
		return total;
	}
	

	
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getAddress() {
		return address;
	}

	public float getWallet() {
		return wallet;
	}

	public void setWallet(int wallet) {
		this.wallet = wallet;
	}

	public int getCharges() {
		return charges;
	}

	public void setCharges(int charges) {
		this.charges = charges;
	}

	public float getRewards() {
		return rewards;
	}

	public void setRewards(int rewards) {
		this.rewards = rewards;
	}

	@Override
	public void displayRewards() {
		System.out.println("Total Rewards - "+getRewards());
		
	}
	
}
