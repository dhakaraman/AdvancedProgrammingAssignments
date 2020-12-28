package mypackage;

import java.util.*;

public class Game {

	static int userID;
	private String userType;
	private int assigned[];
	static int numberOfPlayers;
	ArrayList<Detective> detective;
	ArrayList<Mafia> mafia;
	ArrayList<Healer> healer;
	ArrayList<Commoner> commoner;
	ArrayList<Player> players;
	ArrayList<Player> players2;

	public void run(){

		detective = new ArrayList<>();
		mafia = new ArrayList<>();
		healer = new ArrayList<>();
		commoner = new ArrayList<>();
		players = new ArrayList<>();
		players2 = new ArrayList<>();

		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Mafia");

		while(true) {
			System.out.print("Enter number of players:");
			numberOfPlayers = sc.nextInt();
			if(numberOfPlayers>=6){
				assigned = new int[numberOfPlayers];
				Arrays.fill(assigned, 0);
				break;
			}
			else {
				System.out.println("Numbers of players should be greater than of equal to 6");
			}
		}

		System.out.println("Choose a character");
		System.out.println("1) Mafia\r\n" + 
				"2) Detective\r\n" + 
				"3) Healer\r\n" + 
				"4) Commoner\r\n" + 
				"5) Assign Randomly");
		int character = sc.nextInt();
		this.assignCharacterToUser(character);
		this.assignCharacters();
		this.printDetails();

		int gameState;
		int round=1;


		while(true) {
			System.out.println("Round "+round+":");
			gameState = gamePlay(round);
			if(gameState==-1 || gameState==0)
				break;
			round++;

		}
		System.out.println("Game over.");

		if(gameState==-1) {
			System.out.println("The mafias have won");
		}
		else if(gameState==0) {
			System.out.println("The mafias have lost");
		}
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=0;i<players2.size();i++) {
			if(players2.get(i) instanceof Mafia) {
				list.add(players2.get(i).getID());
			}
		}
		User<Integer> obj1 = new User<Integer>(list,"mafias");
		list.clear();

		for(int i=0;i<players2.size();i++) {
			if(players2.get(i) instanceof Detective) {
				list.add(players2.get(i).getID());
			}
		}
		User<Integer> obj2 = new User<Integer>(list,"detectives");
		list.clear();

		for(int i=0;i<players2.size();i++) {
			if(players2.get(i) instanceof Healer) {
				list.add(players2.get(i).getID());
			}
		}
		User<Integer> obj3 = new User<Integer>(list,"healers");
		list.clear();

		for(int i=0;i<players2.size();i++) {
			if(players2.get(i) instanceof Commoner) {
				list.add(players2.get(i).getID());
			}
		}
		User<Integer> obj4 = new User<Integer>(list,"commoners");
		list.clear();

	}

	private int gamePlay(int round) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> alivePlayer = new ArrayList<>();
		for(int i=0;i<players.size();i++){
			alivePlayer.add(players.get(i).getID());
		}
		Collections.sort(alivePlayer);
		System.out.println(players.size()+" players are alive");

		for(int i=0;i<players.size();i++) {
			System.out.print("Player"+alivePlayer.get(i)+",");
		}
		alivePlayer.clear();
		System.out.println();

		Mafia.mafiasTurn(mafia,players,userID,userType);
		int flag=Detective.detectivesTurn(detective,players,userID,userType,mafia);
		Healer.healersTurn( healer,players,userID,userType);

		int diedPlayer = funOut();

		if(flag==0) {
			int target = getRandom(numberOfPlayers);
			if(userType.equals("Commoner") && userID!=-1) {
				while(true) {
					System.out.println("Enter player id to vote");
					target = sc.nextInt();
					int temp=0;
					for(int i=0;i<players.size();i++) {
						if(target==players.get(i).getID())
							temp=1;
					}
					if(temp==1) break;
					else System.out.println("You can't vote this player");
				}
			}
			players.get(0).votePlayer(players,target);
		}

		System.out.println("--End of actions--");
		if(diedPlayer==-1 && flag!=-1) {
			System.out.println("No player died");
		}
		else if(flag!=-1){
			System.out.println("Player"+diedPlayer+" has died.");
		}
		else if(flag==-1) {

		}

		int maxVotes=0;
		int index=getRandom(players.size());
		for(int i=0;i<players.size();i++) {
			if(maxVotes<players.get(i).getVotes()) {
				maxVotes=players.get(i).getVotes();
				index = i;
			}
		}

		for(int i=0;i<players.size();i++) {
			players.get(i).setVotes(0);
		}

		players.get(index).setHP(0);

		int playerID=-1;

		if(index!=-1) {
			playerID = funOut();
		}


		if(playerID!=-1) {
			System.out.println("Player"+playerID+" has been voted out");
		}

		else if(playerID==-1) {
			System.out.println("Player"+userID+" has been voted out");

			for(int i=0;i<players.size();i++) {
				if(players.get(i) instanceof Mafia && players.get(i).getHP()==0) {
					diedPlayer = players.get(i).getID();
					players.remove(i);
					i--;
					for(int j=0;j<mafia.size();j++) {
						if(mafia.get(j).getHP()==0) {
							mafia.remove(j);
							break;
						}
					}
				}
			}
			userID=-1;
		}
		System.out.println("--End of Round "+round+"--"+"\n\n");

		if(mafia.size()>=(players.size()-mafia.size())) {
			return -1;
		}
		if(mafia.size()==0) {
			return 0;
		}

		return 1;
	}

	int funOut() {
		int diedPlayer=-1;

		for(int i=0;i<players.size();i++) {
			if(players.get(i) instanceof Detective && players.get(i).getHP()==0) {
				diedPlayer = players.get(i).getID();
				players.remove(i);
				i--;
				for(int j=0;j<detective.size();j++) {
					if(detective.get(j).getHP()==0) {
						detective.remove(j);
						break;
					}
				}
			}
			else if(players.get(i) instanceof Healer && players.get(i).getHP()==0) {
				diedPlayer = players.get(i).getID();
				players.remove(i);
				i--;
				for(int j=0;j<healer.size();j++) {
					if(healer.get(j).getHP()==0) {
						healer.remove(j);
						break;
					}
				}
			}
			else if(players.get(i) instanceof Commoner && players.get(i).getHP()==0) {
				diedPlayer = players.get(i).getID();
				players.remove(i);
				i--;
				for(int j=0;j<commoner.size();j++) {
					if(commoner.get(j).getHP()==0) {
						commoner.remove(j);
						break;
					}
				}
			}

		}

		return diedPlayer;
	}

	private void printDetails() {
		System.out.println("You are player"+userID);
		System.out.print("You are a "+userType+". Other "+userType+"s are:[");

		for(int i=0;i<numberOfPlayers;i++) {
			if(userID==players.get(i).getID()) {
				if(players.get(i) instanceof Mafia) {
					for(int j=0;j<mafia.size();j++) {
						if(mafia.get(j).getID()!=userID) {
							System.out.print("Player"+mafia.get(j).getID()+",");
						}
					}
					System.out.println("]");
					break;
				}
				else if(players.get(i) instanceof Detective) {
					for(int j=0;j<detective.size();j++) {
						if(detective.get(j).getID()!=userID) {
							System.out.print("Player"+detective.get(j).getID()+",");
						}
					}
					System.out.println("]");
				}
				else if(players.get(i) instanceof Healer) {
					for(int j=0;j<healer.size();j++) {
						if(healer.get(j).getID()!=userID) {
							System.out.print("Player"+healer.get(j).getID()+",");
						}
					}
					System.out.println("]");
				}
				else if(players.get(i) instanceof Commoner) {
					for(int j=0;j<commoner.size();j++) {
						if(commoner.get(j).getID()!=userID) {
							System.out.print("Player"+commoner.get(j).getID()+",");
						}
					}
					System.out.println("]");
				}
			}
		}
	}

	//this generates random numbers
	static int getRandom(int n) {
		Random rand = new Random();
		return rand.nextInt(n);
	}

	//assigning character and IDs to all other user in the game
	private void assignCharacters() {
		int temp = numberOfPlayers;
		while(temp>1) {

			if(mafia.size()<(numberOfPlayers/5)) {
				Mafia obj = new Mafia(uniqueRandomID());
				mafia.add(obj);
				players.add(obj);
				players2.add(obj);
				temp--;
			}
			else if(detective.size()<(numberOfPlayers/5)) {
				Detective obj = new Detective(uniqueRandomID());
				detective.add(obj);
				players.add(obj);
				players2.add(obj);
				temp--;
			}
			else if(healer.size()<Math.max(1,(numberOfPlayers/10))){
				Healer obj = new Healer(uniqueRandomID());
				healer.add(obj);
				players.add(obj);
				players2.add(obj);
				temp--;
			}
			else{
				Commoner obj = new Commoner(uniqueRandomID());
				commoner.add(obj);
				players.add(obj);
				players2.add(obj);
				temp--;
			}


		}
		return;
	}

	//this function assigns the character and character ID to the user
	private void assignCharacterToUser(int n) {

		if(n==1) {
			int id=uniqueRandomID();
			userID = id;
			Mafia obj = new Mafia(id);
			mafia.add(obj);
			players.add(obj);
			players2.add(obj);
			this.userType = "Mafia";
			assigned[id-1]=1;
		}
		else if(n==2) {
			int id=uniqueRandomID();
			userID = id;
			Detective obj = new Detective(id);
			detective.add(obj);
			players.add(obj);
			players2.add(obj);
			this.userType = "Detective";
			assigned[id-1]=1;
		}
		else if(n==3) {
			int id=uniqueRandomID();
			userID = id;
			Healer obj = new Healer(id);
			healer.add(obj);
			players.add(obj);
			players2.add(obj);
			this.userType = "Healer";
			assigned[id-1]=1;
		}
		else if(n==4) {
			int id=uniqueRandomID();
			userID = id;
			Commoner obj = new Commoner(id);
			commoner.add(obj);
			players.add(obj);
			players2.add(obj);	
			this.userType = "Commoner";
			assigned[id-1]=1;
		}
		else if(n==5) {
			assignCharacterToUser(getRandom(4)+1);
		}

	}
	//this function generates unique and random ID
	private int uniqueRandomID() {
		while(true) {
			Random rand = new Random();
			int a = rand.nextInt(numberOfPlayers)+1;
			if(assigned[a-1]==0) {
				assigned[a-1]=1;
				return a;
			}
		}
	}
}
