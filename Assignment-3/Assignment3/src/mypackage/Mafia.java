package mypackage;

import java.util.ArrayList;
import java.util.Scanner;

public class Mafia extends Player {


	private int targetID;

	Mafia(int ID){
		super(ID,2500);
	}

	public static void mafiasTurn(ArrayList<Mafia> mafia,ArrayList<Player> players, int userID, String userType) {

		Scanner sc = new Scanner(System.in);
		if(mafia.size()>0 && userType.equals("Mafia") && userID!=-1) {

			while(true) {
				System.out.println("Choose a player to test:");
				int targetID = sc.nextInt();
				int a = mafia.get(0).chooseTarget(players.get(0),targetID,players);

				if(a==1)
					break;
				else if(a==0){
					System.out.println("You can't choose Mafia as target");
				}
				else {
					System.out.println("Player died or votes out");
				}
			}
		}
		else if(mafia.size()>0) {
			while(true) {
				int targetID = Game.getRandom(Game.numberOfPlayers);
				int a = mafia.get(0).chooseTarget(players.get(0),targetID,players);
				if(a==1) {
					System.out.println("Mafias have chosen their target");
					break;
				}

			}
		}
	}

	@Override
	public int chooseTarget(Object o,int targetID, ArrayList<Player> players) {
		int hpValueMafias=0;
		int flag=0;
		int flag2=0;
		int index=0;

		for(int j=0;j<players.size();j++) {
			if(players.get(j).getID()==targetID) {
				flag2=1;
				break;
			}
		}

		if(flag2==0) {
			return -1;
		}

		for(int j=0;j<players.size();j++) {
			if(!(players.get(j) instanceof Mafia) && players.get(j).getID()==targetID) {
				this.targetID = targetID;
				index=j;
				flag=1;
				break;
			}
		}

		if(flag==1) {

			int hpValueTarget = players.get(index).getHP();
			for(int j=0;j<players.size();j++) {
				if(players.get(j) instanceof Mafia) {
					hpValueMafias+=players.get(j).getHP();
				}
			}
			setMafiasHP(players,hpValueMafias,hpValueTarget);
			return 1;
		}
		else {
			return 0;
		}

	}

	public void setMafiasHP(ArrayList<Player> players, int MafiasHP,int targetHP) {
		if(MafiasHP==0 || targetHP==0) {

			for(int i=0;i<players.size();i++) {
				if(players.get(i).getID()==targetID) {
					players.get(i).setHP(targetHP);
				}
			}

			return;
		}
		int countNumberOfMafias = 0;
		int minimumHP=100000;
		for(int i=0;i<players.size();i++) {
			if(players.get(i) instanceof Mafia && players.get(i).getHP()>0) {
				countNumberOfMafias++;
			}
		}

		int temp = targetHP/countNumberOfMafias;

		for(int i=0;i<players.size();i++) {
			if(players.get(i) instanceof Mafia && players.get(i).getHP()>0) {
				if(players.get(i).getHP()>temp) {
					players.get(i).setHP(players.get(i).getHP()-temp);
					targetHP  = targetHP - temp;
					MafiasHP = MafiasHP -temp;
				}
				else{
					targetHP  = targetHP - players.get(i).getHP();
					MafiasHP = MafiasHP - players.get(i).getHP();
					players.get(i).setHP(0);
				}
			}
		}

		setMafiasHP(players,MafiasHP,targetHP);

	}

	@Override
	public boolean equals(Object o1) {
		if(o1 != null && getClass() == o1.getClass()) {
			Mafia o = (Mafia) o1; //type casting
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
