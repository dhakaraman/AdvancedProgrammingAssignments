package mypackage;

import java.util.ArrayList;

public class Commoner extends Player {

	Commoner(int ID){
		super(ID,1000);
	}

	@Override
	public int chooseTarget(Object obj, int targetID, ArrayList<Player> players){
		return 1;
	}
	@Override
	public boolean equals(Object o1) {
		if(o1 != null && getClass() == o1.getClass()) {
			Commoner o = (Commoner) o1; //type casting
			return (getID()==o.getID());
		}
		else {
			return false;
		}
	}

}
