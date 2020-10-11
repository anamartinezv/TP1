package GAME;

import PLAYERS.*;

public class game {
	
	// Declare constants
	final public static int NUM_PLAYERS = 10;

	public static void main(String[] args) {
		int chineseID = 0;
		randomPlayer[] randomPlayers = new randomPlayer[5];
		algorithmPlayer[] algorithmPlayers = new algorithmPlayer[5];
		

		for (int i = 0; i < NUM_PLAYERS / 2; i++) {
			randomPlayers[i] = new randomPlayer("chinese_" 
												+ chineseID);
			chineseID++;
		}
			
		
		for (int i = 0; i < NUM_PLAYERS / 2; i++) {
			algorithmPlayers[i] = new algorithmPlayer("chinese_" 
													  + chineseID);
			chineseID++;
		}
			
		
		// Present players
		for (int i = 0; i < NUM_PLAYERS / 2; i++)
			randomPlayers[i].printChoice();
		
		for (int i = 0; i < NUM_PLAYERS / 2; i++)
			algorithmPlayers[i].printChoice();
		
	}
}
