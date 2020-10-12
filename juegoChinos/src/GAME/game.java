package GAME;

import PLAYERS.*;

public class game {
	
	// Declare constants
	final public static int NUM_PLAYERS = 10;
	public static randomPlayer[] randomPlayers = new randomPlayer[5];
	public static algorithmPlayer[] algorithmPlayers = new algorithmPlayer[5];

	
	public static void main(String[] args) {
		
		initializeGame();
		
		// Play till the game ends
		for (int i = 0; i < 100; i++)
			playRound();
	}
	
	
	public static void initializeGame() {
		
		int chineseID = 0;
		
		for (int i = 0; i < NUM_PLAYERS / 2; i++) {
			randomPlayers[i] = new randomPlayer("chinese_" + chineseID);
			chineseID++;
		}
				
		for (int i = 0; i < NUM_PLAYERS / 2; i++) {
			algorithmPlayers[i] = new algorithmPlayer("chinese_" + chineseID);
			chineseID++;
		}
	}
	
	
	public static void printPlayersAmount() {
		
		for (int i = 0; i < NUM_PLAYERS / 2; i++)
			randomPlayers[i].printAmount();
			
		for (int i = 0; i < NUM_PLAYERS / 2; i++)
			algorithmPlayers[i].printAmount();
	}
	
	
	public static void printPlayersChoice() {
		
		for (int i = 0; i < NUM_PLAYERS / 2; i++)
			randomPlayers[i].printChoice();
			
		for (int i = 0; i < NUM_PLAYERS / 2; i++)
			algorithmPlayers[i].printChoice();
	}
	
	
	public static void setPlayersAmount() {
		
		for (int i = 0; i < NUM_PLAYERS / 2; i++)
			randomPlayers[i].coinAmount = randomPlayers[i].setAmount();
			
		for (int i = 0; i < NUM_PLAYERS / 2; i++)
			algorithmPlayers[i].coinAmount = algorithmPlayers[i].setAmount();
	}
	
	
	public static void setPlayersChoice() {
		
		for (int i = 0; i < NUM_PLAYERS / 2; i++)
			randomPlayers[i].choice = 
							randomPlayers[i].makeChoice(NUM_PLAYERS);
			
		for (int i = 0; i < NUM_PLAYERS / 2; i++)
			algorithmPlayers[i].choice = 
							algorithmPlayers[i].makeChoice(NUM_PLAYERS);
	}
	
		
	public static int calculateTotal() {
		
		int totalCoins = 0;
		
		for (int i = 0; i < NUM_PLAYERS / 2; i++)
			totalCoins += randomPlayers[i].coinAmount;
			
		for (int i = 0; i < NUM_PLAYERS / 2; i++)
			totalCoins += algorithmPlayers[i].coinAmount;
		
		return totalCoins;
	}
	
	
	public static void playRound() {
		
		setPlayersAmount();
		setPlayersChoice();
		
		int totalCoins = calculateTotal();
		
		for (int i = 0; i < NUM_PLAYERS / 2; i++)
			if (totalCoins == randomPlayers[i].choice)
				System.out.println("Player " + randomPlayers[i].playerID
									+ " coincide");
			
		for (int i = 0; i < NUM_PLAYERS / 2; i++)
			if (totalCoins == algorithmPlayers[i].choice)
				System.out.println("Player " + algorithmPlayers[i].playerID
									+ " coincide");
	}
}
