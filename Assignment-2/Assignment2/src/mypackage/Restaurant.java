package mypackage;

import java.util.ArrayList;
import java.util.Scanner;

class Restaurant implements Outer {
	protected final String name;
	protected final String type;
	protected final String address;
	protected int orders;
	public static float rewards=0;
	protected int offers;
	protected int discount;
	protected int OrdersTaken=0;
	public ArrayList<Food> objF;
	protected int overalBillDiscount;
	
	
	public Restaurant(String name, String type, String address) {
		this.name = name;
		this.address = address;
		this.type = type;
		this.objF = new ArrayList<Food>();
		this.setOveralBillDiscount(1);
	}
	
	
	public void addFood(String res, String type, int ID) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Food name:");
		String n = sc.nextLine();
		System.out.println("Item price");
		int p = sc.nextInt();
		System.out.println("Item quantity");
		int q = sc.nextInt();
		System.out.println("item category");
		String c = sc.next();
		System.out.println("Offer");
		int o = sc.nextInt();
		objF.add(new Food(n,p,q,c,o,ID,res,type));
		System.out.println(objF.get(objF.size()-1).getID()+" "+n+" "+p+" "+q+" "+o+"% off "+c);
		
	}
	
	
public int displayMenu() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome "+getName());
		
		System.out.println("1) Add item");
		System.out.println("2) Edit item");
		System.out.println("3) Print Rewards");
		System.out.println("4) Discount on bill value");
		System.out.println("5) Exit");
		
		int choice = sc.nextInt();
		
		if(choice==1) {
			addFood(getName(),getType(),Zotato_App.inc);
			Zotato_App.inc++;
		}
		else if(choice==2) {
			showMenu();
		}
		else if(choice==3) {
			displayRewards();
		}
		else if(choice==4) {
			System.out.println("Enter discount on bill value - ");
			setOveralBillDiscount(sc.nextInt());
		}
		return choice;
	}
	
	public int assignRewardPoints(float spent) {
		int r = 5*(int)(spent/100);
		rewards+=r;
		return r;
	}
	
	
public void showMenu() {
		
		Scanner sc = new Scanner(System.in);
		
		AvailableFood();
		
		int item = sc.nextInt();
		System.out.println("Choose an attribute to edit:");
		System.out.println("1) Name");
		System.out.println("2) Price");
		System.out.println("3) Quantity");
		System.out.println("4) Category");
		System.out.println("5) Offer");
		editMenu(item);
	}
	
	public void editMenu(int item) {
		
		Scanner sc = new Scanner(System.in);
		
		int attribute = sc.nextInt();
		if(attribute==1) {
			System.out.println("Enter the new name");
			objF.get(item-1).setName(sc.nextLine());
			objF.get(item-1).displayFood();
		}
		else if(attribute==2) {
			System.out.println("Enter the new price");
			objF.get(item-1).setPrice(sc.nextInt());
			objF.get(item-1).displayFood();
		}
		else if(attribute==3) {
			System.out.println("Enter the new quantity");
			objF.get(item-1).setQuantity(sc.nextInt());
			objF.get(item-1).displayFood();
		}
		else if(attribute==4) {
			System.out.println("Enter the new category");
			objF.get(item-1).setCategory(sc.next());
			objF.get(item-1).displayFood();
		}
		else if(attribute==5) {
			System.out.println("Enter the new offer");
			objF.get(item-1).setOffer(sc.nextInt());
			objF.get(item-1).displayFood();
		}
	}
	
	public float totalOrderValue(Customer obj) {
		Scanner sc = new Scanner(System.in);
		outer:
			while(true)	{
			
			float total = obj.calculateTotalBill(this);

			total = total+obj.charges;
			System.out.println("Total order value - "+(total));
			System.out.println("1) Proceed to checkout ");
			int proceed = sc.nextInt();
			if(proceed==1) {
				
				if(obj.wallet-total<0) {
					System.out.println("Dont have enough money in your wallet to buy!!!!");
					System.out.println("Delete by food code");
					obj.ItemsInCart();
					
					int del = sc.nextInt();
					
					System.out.println("Do you want to delete all quantity of the selected food?1-yes/0-no");
					
					int c = sc.nextInt();
					
					if(c==1) {
						for(int i=0;i<obj.objC.size();i++) {
							if(obj.objC.get(i).getID()==del) {
								obj.objC.remove(i);
								break;
							}
						}
					}
					else if(c==0) {
						System.out.println("New quantity");
						int q = sc.nextInt();
						for(int i=0;i<obj.objC.size();i++) {
							if(obj.objC.get(i).getID()==del) {
								obj.objC.get(i).setQuantity(q);
							}	
						}
					}
					
					continue outer;
				}
				else {
					int flag=0;
					for(int i=0;i<objF.size();i++) {
						for(int j=0;j<obj.objC.size();j++) {
							if(objF.get(i).getID()==obj.objC.get(j).getID()) {
								if(objF.get(i).getQuantity()>=obj.objC.get(j).getQuantity()) {
									//objF.get(i).setQuantity(objF.get(i).getQuantity()-obj.objC.get(j).getQuantity());
								}
								else {
									System.out.println("Can't buy!!! Please recheck our food quantity and enter again");
									System.out.println("Available quantity for the food item with ID no. "+objF.get(i).getID()+" is "+objF.get(i).getQuantity());
									System.out.println("Enter new quantity? 1-yes/0-no");
									int nq = sc.nextInt();
									if(nq==1) {
										System.out.println("Enter new quantity");
										int qu = sc.nextInt();
										obj.objC.get(j).setQuantity(qu);
										continue outer;
									}
									else {
										obj.objC.remove(j);
										break;
									}
								}
								if(i==objF.size()-1 && j==obj.objC.size()-1) {
									flag=1;
									break;
								}
							}
						}
						if(flag==1)
							break;
						
					}
				
					for(int i=0;i<objF.size();i++) {
						for(int j=0;j<obj.objC.size();j++) {
							if(objF.get(i).getID()==obj.objC.get(j).getID()) {
								if(objF.get(i).getQuantity()>=obj.objC.get(j).getQuantity()) {
									objF.get(i).setQuantity(objF.get(i).getQuantity()-obj.objC.get(j).getQuantity());
									if(objF.get(i).getQuantity()==0) {
										objF.remove(i);
									}
								}
				
							}
						}
					}
					System.out.println(obj.TotalItemsInCart()+" items successfully bought for INR "+total);
					obj.checkOut();
					if(total>=rewards) {
						obj.wallet = obj.wallet - total  - rewards;
						rewards = 0;
					}
					else {
						rewards = rewards - total;
					}
					return total;
				}
			}
		}
	}

	
	@Override
	public void displayRewards() {
		System.out.println("Reward points - "+rewards);
	}
	
	public void AvailableFood() {
		System.out.println("Choose item by code");
		for(int i=0;i<objF.size();i++) {
			objF.get(i).displayFood();
		}
	}
	
	public void displayUser() {
		System.out.println(name);
	}
	
	public void displayUserWithType(int i) {
		
		System.out.println(i+1+") "+name);

	}

	@Override
	public void displayUserDetails() {
		System.out.println(name+" "+address+" "+getOrderTaken());
	}
	
	public void setOrderTaken() {
		OrdersTaken++;
	}

	public int getOrderTaken() {
		return OrdersTaken;
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
	
	public void setOrders(int o) {
		this.orders = o;
	}
	
	public int getOrders() {
		return orders;
	}
	
	public void setOffers(int r) {
		this.offers = r;
	}
	
	public int getOffers() {
		return offers;
	}
	
	public void setDiscount(int r) {
		this.offers = r;
	}
	
	public int getDiscount() {
		return offers;
	}

	public int getOveralBillDiscount() {
		return overalBillDiscount;
	}

	public void setOveralBillDiscount(int overalBillDiscount) {
		this.overalBillDiscount = overalBillDiscount;
	}
	
}
