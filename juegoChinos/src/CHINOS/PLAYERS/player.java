package CHINOS.PLAYERS;

import java.util.Random;

public class player {

	private String playerID;
	private int coinAmount;
	private int choice;
	
	
	public player(String playerID) {
		
		this.playerID = playerID;
	}
	
	
	public String getPlayerID() {
		
		return playerID;
	}
	
	
	public int getCoinAmount() {
		
		return coinAmount;
	}
	
	
	public int getChoice() {
		
		return choice;
	}
	
	
	public void printChoice() {
		
		System.out.println("Player " + playerID + 
							" has chosen " + choice + " coins");
	}
	
	
	public void printAmount() {
		
		System.out.println("Player " + playerID + 
							" has " + coinAmount + " coins");
	}
	
	
	public void setAmount(int coinAmount) {
		
		this.coinAmount = coinAmount;
	}
	
	
	public void setChoice(int choice) {
		
		this.choice = choice;
	}
	
	
	public int getRandomNumber(int bound) {
		
		Random rand = new Random();
		return rand.nextInt(bound); // Returns a num btwn 0-3
	}
}
