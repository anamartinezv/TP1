package CHINOS.GAME;

import CHINOS.PLAYERS.*;

import java.util.ArrayList;
import java.util.Iterator;


public class game {
	
	// Declare constants
	final public int NUM_PLAYERS = 10;
	final public int MIN_PLAYERS = 1;
	final public int MAX_COINS = 4;
	final public int MAX_NUM = 3;
	
	public ArrayList<randomPlayer> 
							randomPlayers = new ArrayList<randomPlayer>();
	public ArrayList<algorithmPlayer> 
							algorithmPlayers = new ArrayList<algorithmPlayer>();
	
	
	public void initializeGame() {
		
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
	
	
	public void printPlayersAmount() {
		
		for (randomPlayer player : randomPlayers)
			player.printAmount();
			
		for (algorithmPlayer player : algorithmPlayers)
			player.printAmount();
	}
	
	
	public void printPlayersChoice() {
		
		for (randomPlayer player : randomPlayers)
			player.printChoice();
			
		for (algorithmPlayer player : algorithmPlayers)
			player.printChoice();
	}
	
	
	public void setPlayersAmount() {
		
		for (randomPlayer player : randomPlayers)
			player.setAmount(player.getRandomNumber(MAX_COINS));
			
		for (algorithmPlayer player : algorithmPlayers)
			player.setAmount(player.getRandomNumber(MAX_COINS));
	}
	
	
	public void setPlayersChoice() {
		
		for (randomPlayer player : randomPlayers)
			player.makeChoice(MAX_NUM, NUM_PLAYERS);
			
		for (algorithmPlayer player : algorithmPlayers)
			player.makeChoice(NUM_PLAYERS);
	}
	
		
	public int calculateTotal() {
		
		int totalCoins = 0;
		
		for (randomPlayer player : randomPlayers)
			totalCoins += player.getCoinAmount();
			
		for (algorithmPlayer player : algorithmPlayers)
			totalCoins += player.getCoinAmount();
		
		return totalCoins;
	}
	
	
	public void playRound() {

		setPlayersAmount();
		setPlayersChoice();
		
		int totalCoins = calculateTotal();
		
		for (Iterator<randomPlayer> i = randomPlayers.iterator(); i.hasNext(); ) {
			randomPlayer v = i.next();
			if (totalCoins == v.getChoice()) {
				System.out.println("  - Player " + v.getPlayerID() + " coincide");
				i.remove();
			}
		}
					
		for (Iterator<algorithmPlayer> i = algorithmPlayers.iterator(); i.hasNext(); ) {
			algorithmPlayer v = i.next();
			if (totalCoins == v.getChoice()) {
				System.out.println("  - Player " + v.getPlayerID() + " coincide");
				i.remove();
			}
		}
	}
	
	
	public void printWinner() {
		
		if (randomPlayers.size() == MIN_PLAYERS)
			System.out.println("\nPlayer " + randomPlayers.get(0).getPlayerID() 
								+ " has lost");
		else
			System.out.println("\nPlayer " + algorithmPlayers.get(0).getPlayerID() 
								+ " has lost");
	}
}
