package mypackage;

class EliteCustomer extends Customer {
	EliteCustomer(String name,String type, String address, int charges){
		super(name,type,address,charges);
	}
	
	@Override
	public void displayUserDetails() {
		System.out.println(name+"("+type+"), "+address+", "+wallet);
	}
	
	public float calculateTotalBill(Restaurant obj) {
		float total = 0;
		for(int i=0;i<objC.size();i++) {
			if(!objC.get(i).getStatus())
				total+=objC.get(i).OrderValue();
		}
		float overall =0;
		
		overall = (total/100)*obj.getOveralBillDiscount();
		
		if(total>200) {
			total = total - overall -50;
		}
		else {
			total = total - overall;
		}
		
		return total;
	}
}




