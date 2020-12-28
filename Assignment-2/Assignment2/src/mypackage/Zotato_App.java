package mypackage;

import java.util.ArrayList;
import java.util.Scanner;

public class Zotato_App {
	Restaurant restaurants[];
	Customer customers[];
	static int res =0;
	static float companyBalance=0;
	public static float deliveryCharges = 0;
	static int inc=1;
	
	Zotato_App(){
		restaurants = new Restaurant[5];
		customers = new Customer[5];
	}
	
	public void run() {
		restaurants[0] = new AuthenticRestaurant("Shah", "Authentic","gali no. 6");
		restaurants[1] = new Restaurant("Ravi's","Normal","gali no.7");
		restaurants[2] = new AuthenticRestaurant("The chinese", "Authentic","rk puram");
		restaurants[3] = new FastFoodRestaurant("Wang's", "Fast Food","krishna nagar");
		restaurants[4] = new Restaurant("Paradise","Normal","arjun nagar");
		
		customers[0] = new EliteCustomer("Ram","Elite", "saket",0);
		customers[1] = new EliteCustomer("Sam","Elite", "hauj khas",0);
		customers[2] = new SpecialCustomer("Tim","Special","sd block",20);
		customers[3] = new Customer("Kim","Normal","block 2",40);
		customers[4] = new Customer("Jim","Normal","humayun pur", 40);
		
		while(true) {
			
			/*Main menu*/
			Scanner sc = new Scanner(System.in);
			System.out.println("Welcome to zotato");
			System.out.println("1) Enter as Restaurant Owner");
			System.out.println("2) Enter as Customer");
			System.out.println("3) Check User Details");
			System.out.println("4) Company Account details");
			System.out.println("5) Exit");
			int type = sc.nextInt();

			/*Restaurant owner login*/
			if(type==1) {
				System.out.println("Choose Restaurant");
				for(int i=0;i<5;i++) {
					restaurants[i].displayUserWithType(i);
				}
				int t = sc.nextInt();
				while(true) {
					int choice = restaurants[t-1].displayMenu();
					if(choice==5) {
						break;
					}
				}
			}
			
   			/*Customer login*/
			else if(type==2) {
				for(int i=0;i<5;i++) {
					customers[i].displayUserWithType(i);
				}
				int t = sc.nextInt();
				while(true) {
					int choice = customers[t-1].displayMenu(restaurants);
					if(choice==5)
						break;
					
				}
				
			}
			/*check user details*/
			else if(type==3) {
				System.out.println("1) Customer List");
				System.out.println("2) Restaurant List");
	
				int user = sc.nextInt();
				
				if(user==1) {
					for(int i=0;i<5;i++) {
						System.out.print(i+1+". ");
						customers[i].displayUser();
					}
					int id = sc.nextInt();
					customers[id-1].displayUserDetails();
				}
				else if(user==2) {
					for(int i=0;i<5;i++) {
						System.out.print(i+1+". ");
						restaurants[i].displayUser();
					}
					int id = sc.nextInt();
					restaurants[id-1].displayUserDetails();
				}
			}
			/*Company details*/
			else if(type==4) {
				System.out.println("Total Company balance - INR "+companyBalance+"/-");
				System.out.println("Total Delivery Charges Collected - INR "+deliveryCharges+"/-");
			}
			/*Exit*/
			else if(type==5) {
				break;
			}
		}
	}
}
