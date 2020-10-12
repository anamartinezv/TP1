package PLAYERS;

import java.util.Random;

public class player {
	
	public String playerID;
	public int coinAmount;
	public int choice;
	
	
	public player(String playerID) {
		
		this.playerID = playerID;
	}
	
	
	public void printChoice() {
		
		System.out.println("Player " + playerID + 
							" has chosen " + choice + " coins");
	}
	
	
	public void printAmount() {
		
		System.out.println("Player " + playerID + 
							" has " + coinAmount + " coins");
	}
	
	
	public int setAmount() {
		
		Random rand = new Random();
		return rand.nextInt(4); // Returns a num btwn 0-3
	}
}
