package PLAYERS;

public class algorithmPlayer extends player {

	/*
	 * Default constructor
	 */
	public algorithmPlayer(String playerID) {
		
		super(playerID);
	}
	
	/*
	 * Returns the players choice calculated with an algorithm
	 * 
	 * @param nPlayers, number of players in the current game
	 * @return int, player choice
	 */
	public int makeChoice(int nPlayers) {
		
		// The algorithm multiplies the player coinAmount
		// by the number of players, then divides it by 2
		return (this.coinAmount * nPlayers) / 2;
	}

}
