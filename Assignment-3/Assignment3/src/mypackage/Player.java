package mypackage;

import java.util.ArrayList;

public abstract class Player {

	protected final int ID;
	protected int HP;
	protected int votes;

	Player(int ID, int HP){
		this.ID = ID;
		this.HP = HP;
		this.votes=0;
	}


	public abstract int chooseTarget(Object obj, int targetID, ArrayList<Player> players); 

	public void votePlayer(ArrayList<Player> players, int target) {
		for(int i=0;i<players.size();i++) {
			while(true) {
				int temp = Game.getRandom(players.size());
				if(i!=temp) {
					if(!players.get(i).equals(players.get(temp))){
						players.get(temp).setVotes(players.get(temp).getVotes()+1);
						break;
					}
					else if(!players.get(i).equals(players.get(temp)) && players.get(i) instanceof Commoner){
						players.get(temp).setVotes(players.get(temp).getVotes()+1);
						break;
					}
				}
			}
		}
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getID() {
		return ID;
	}


	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

}
