package mypackage;

import java.util.Scanner;

class AuthenticRestaurant extends Restaurant {
	AuthenticRestaurant(String name, String type, String address){
		super(name,type,address);
	}
	
	
	public void displayUserWithType(int i) {
		System.out.println(i+1+") "+name+" ("+type+")");
	}
	
	public int assignRewardPoints(float spent) {
		int r = 25*(int)(spent/200);
		rewards+=r;
		return r;
	}
}
