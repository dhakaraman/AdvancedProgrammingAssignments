package mypackage;

import java.util.ArrayList;
import java.util.Scanner;

public class Detective extends Player {


	private int targetID;

	Detective(int ID){
		super(ID,800);
	}


	public static int detectivesTurn(ArrayList<Detective> detective,ArrayList<Player> players, int userID, String userType, ArrayList<Mafia> mafia){

		Scanner sc = new Scanner(System.in);
		int flag=0;
		if(detective.size()>0 && userType.equals("Detective") && userID!=-1) {
			while(true) {
				System.out.println("Choose a player to test:");
				int targetID = sc.nextInt();
				int i = Game.getRandom(players.size());
				int a = detective.get(0).chooseTarget(players.get(i), targetID, players);
				if(a==-1) {
					flag=1;
					System.out.println("Player"+targetID+" is a mafia.");
					for(int j=0;j<mafia.size();j++) {
						if(mafia.get(j).getID()==targetID) {
							mafia.remove(j);
						}
					}
					if(userID == targetID)
						Game.userID = -1;
					break;
				}else if(a==1) {
					System.out.println("Player"+targetID+" is not a Mafia");
					break;
				}
				System.out.println("You cannot test a detective.");
			}
		}
		else if(detective.size()>0){
			while(true) {
				int targetID = Game.getRandom(Game.numberOfPlayers)+1;
				int i = Game.getRandom(players.size());
				int a = detective.get(0).chooseTarget(players.get(i), targetID, players);
				if(a==-1) {
					flag=1;
					System.out.println("Detectives have chosen their target");
					System.out.println("Player"+targetID+" is a mafia.");
					break;
				}else if(a==1) {
					System.out.println("Detectives have chosen their target");
					System.out.println("Player"+targetID+" is not a Mafia");
					break;
				}
			}
		}
		return flag;
	}


	@Override
	public int chooseTarget(Object obj, int targetID, ArrayList<Player> players) {

		for(int i=0;i<players.size();i++) {
			if(players.get(i) instanceof Mafia) {
				if(players.get(i).getID()==targetID) {
					this.setTargetID(targetID);
					players.remove(i);
					return -1;
				}
			}
			else if(players.get(i) instanceof Detective) {
				if(players.get(i).getID()==targetID) {
					//System.out.println("You cannot test a detective.");
					return 0;
				}
			}
			else if(players.get(i) instanceof Healer || players.get(i) instanceof Commoner) {
				if(players.get(i).getID()==targetID) {
					//System.out.println("Player"+targetID+" is not a Mafia");
					return 1;
				}
			}
		}
		return 2;
	}


	@Override
	public boolean equals(Object o1) {
		if(o1 != null && getClass() == o1.getClass()) {
			Detective o = (Detective) o1; //type casting
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
