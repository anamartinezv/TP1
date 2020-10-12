package PLAYERS;

import java.util.Random; 

public class randomPlayer extends player{

	/*
	 * Default constructor
	 */
	public randomPlayer(String playerID) {
		
		super(playerID);
	}	
	
	
	/*
	 * Returns the players choice a random number between 
	 * 0 and 3*nPlayers
	 * 
	 * @param nPlayers, number of players in the current game
	 * @return int, player choice
	 */
	public int makeChoice(int nPlayers) {
		
		Random rand = new Random();
		return rand.nextInt(3 * nPlayers);
	}
}
