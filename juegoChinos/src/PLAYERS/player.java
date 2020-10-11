package PLAYERS;

public class player {
	
	private String playerID;
	public int coinAmount;
	public int choice;
	
	public player(String playerID) {
		this.playerID = playerID;
	}
	
	public void printChoice() {
		System.out.println("Player " + playerID + 
							" has chosen " + choice + " coins");
	}
}
