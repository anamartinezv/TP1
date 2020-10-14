package CHINOS.PLAYERS;


public class randomPlayer extends player{

	/*
	 * Default constructor
	 */
	public randomPlayer(String playerID) {
		
		super(playerID);
	}	
	
	
	/*
	 * Stores a random number between 0 and MAX_NUM*nPlayers 
	 * in the player choice variable.
	 * 
	 * @param nPlayers, number of players in the current game
	 */
	public void makeChoice(int maxNum, int nPlayers) {
		
		setChoice(getRandomNumber(maxNum * nPlayers));
	}
}
