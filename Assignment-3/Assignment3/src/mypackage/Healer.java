package mypackage;

import java.util.ArrayList;
import java.util.Scanner;

public class Healer extends Player {

	private int targetID;

	Healer(int ID){
		super(ID,800);
		this.setTargetID(0);
	}


	public static void healersTurn(ArrayList<Healer> healer,ArrayList<Player> players, int userID, String userType) {
		Scanner sc = new Scanner(System.in);
		if(healer.size()>0 && userType.equals("Healer") && userID!=-1) {
			while(true) {
				System.out.println("Choose a player to heal:");
				int targetID = sc.nextInt();
				int i = Game.getRandom(players.size());
				int a = healer.get(0).chooseTarget(players.get(i), targetID, players);
				if(a==1) {
					break;
				}
			}
		}
		else if (healer.size()>0){
			while(true) {
				int targetID = Game.getRandom(players.size());
				int a = healer.get(0).chooseTarget(players.get(0), targetID, players);
				if(a==1) {
					System.out.println("Healers have chosen someone to heal.");
					break;
				}
			}
		}
	}

	@Override
	public int chooseTarget(Object obj, int targetID, ArrayList<Player> players) {
		this.setTargetID(targetID);

		for(int i=0;i<players.size();i++) {
			if(players.get(i).getID()==targetID) {
				players.get(i).setHP(players.get(i).getHP()+500);
				return 1;
			}
		}

		return 0;
	}

	@Override
	public boolean equals(Object o1) {
		if(o1 != null && getClass() == o1.getClass()) {
			Healer o = (Healer) o1; //type casting
			return (getID()==o.getID());
		}
		else {
			return false;
		}
	}


	public int getTargetID() {
		return targetID;
	}


	public void setTargetID(int targetID) {
		this.targetID = targetID;
	}

}
