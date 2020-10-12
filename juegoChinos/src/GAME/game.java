package GAME;

import PLAYERS.*;

import java.util.ArrayList;
import java.util.Iterator;


public class game {
	
	// Declare constants
	final public static int NUM_PLAYERS = 10;
	final public static int MIN_PLAYERS = 1;
	
	public static ArrayList<randomPlayer> 
							randomPlayers = new ArrayList<randomPlayer>();
	public static ArrayList<algorithmPlayer> 
							algorithmPlayers = new ArrayList<algorithmPlayer>();


	public static void main(String[] args) {
		
		initializeGame();
		
		// Play till the game ends
		do {
			System.out.println("\nPlaying round...");
			playRound();
		} while(MIN_PLAYERS < (randomPlayers.size() + algorithmPlayers.size()));
		
		printWinner();
	}
	
	
	public static void initializeGame() {
		
		int chineseID = 0;
		
		for (int i = 0; i < NUM_PLAYERS / 2; i++) {
			randomPlayers.add(new randomPlayer("chinese_" + chineseID));
			chineseID++;
		}

		for (int i = 0; i < NUM_PLAYERS / 2; i++) {
			algorithmPlayers.add(new algorithmPlayer("chinese_" + chineseID));
			chineseID++;
		}
			
	}
	
	
	public static void printPlayersAmount() {
		
		for (randomPlayer player : randomPlayers)
			player.printAmount();
			
		for (algorithmPlayer player : algorithmPlayers)
			player.printAmount();
	}
	
	
	public static void printPlayersChoice() {
		
		for (randomPlayer player : randomPlayers)
			player.printChoice();
			
		for (algorithmPlayer player : algorithmPlayers)
			player.printChoice();
	}
	
	
	public static void setPlayersAmount() {
		
		for (randomPlayer player : randomPlayers)
			player.coinAmount = player.setAmount();
			
		for (algorithmPlayer player : algorithmPlayers)
			player.coinAmount = player.setAmount();
	}
	
	
	public static void setPlayersChoice() {
		
		for (randomPlayer player : randomPlayers)
			player.choice = player.makeChoice(NUM_PLAYERS);
			
		for (algorithmPlayer player : algorithmPlayers)
			player.choice = player.makeChoice(NUM_PLAYERS);
	}
	
		
	public static int calculateTotal() {
		
		int totalCoins = 0;
		
		for (randomPlayer player : randomPlayers)
			totalCoins += player.coinAmount;
			
		for (algorithmPlayer player : algorithmPlayers)
			totalCoins += player.coinAmount;
		
		return totalCoins;
	}
	
	
	public static void playRound() {

		setPlayersAmount();
		setPlayersChoice();
		
		int totalCoins = calculateTotal();
		
		for (Iterator<randomPlayer> i = randomPlayers.iterator(); i.hasNext(); ) {
			randomPlayer v = i.next();
			if (totalCoins == v.choice) {
				System.out.println("  - Player " + v.playerID + " coincide");
				i.remove();
			}
		}
					
		for (Iterator<algorithmPlayer> i = algorithmPlayers.iterator(); i.hasNext(); ) {
			algorithmPlayer v = i.next();
			if (totalCoins == v.choice) {
				System.out.println("  - Player " + v.playerID + " coincide");
				i.remove();
			}
		}
	}
	
	
	public static void printWinner() {
		
		if (randomPlayers.size() == MIN_PLAYERS)
			System.out.println("\nPlayer " + randomPlayers.get(0).playerID 
								+ " has lost");
		else
			System.out.println("\nPlayer " + algorithmPlayers.get(0).playerID 
								+ " has lost");
	}
}
