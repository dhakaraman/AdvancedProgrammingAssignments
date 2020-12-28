package mypackage;

import java.util.Scanner;

class FastFoodRestaurant extends Restaurant {
	FastFoodRestaurant(String name,String type, String address){
		super(name,type,address);
	}
	
	public void displayUserWithType(int i) {
		System.out.println(i+1+") "+name+" ("+type+")");
	}
	
	public int assignRewardPoints(float spent) {
		
		int r = 10*(int)(spent/150);
		rewards+=r;
		return r;	
	}
}
