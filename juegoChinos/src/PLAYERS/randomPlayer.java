package PLAYERS;

import java.util.Random; 

public class randomPlayer extends player{

	public randomPlayer(String playerID) {
		super(playerID);
	}
	
	public int makeChoice() {
		Random rand = new Random();
		return rand.nextInt(4); // Returns a num btwn 0-3
	}

}
