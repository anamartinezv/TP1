package org.ucm.tp1.logic;

import java.util.Random;

public class Player {
	public final int COINS_AT_START = 50;
	public final int COINS_PER_ROUND = 10;
	
	private int coins;
	private Random random;
	
	public Player() {
		coins = COINS_AT_START;
		random = new Random();
	}
	
	/*public boolean canPlaceSlayer() {
		//if (coins >= slayer.COST) return true; else return false;
		return (coins >= slayer.COST) ? true : false;
	}*/
	
	public int getCoins() {
		return coins;
	}
	
	public void updateCoinsRandom() {
		if (randomProbability())
			coins += COINS_PER_ROUND;
	}
	
	public boolean randomProbability() {
		return random.nextBoolean();
	}
 
}
