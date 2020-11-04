package org.ucm.tp1.logic;

import java.util.Random;

public class Player {
	
	public final String notEnoughCoinsMsg = "Error! You dont have enough coins!";
	public final int COINS_AT_START = 50;
	public final int COINS_PER_ROUND = 10;
	
	private int coins;
	
	private Random random;
	
	public Player(Long seed) {
		coins = COINS_AT_START;
		
		random = new Random(seed);
	}
	
	public boolean haveEnoughCoins() {
		if (coins >= Slayer.COST) return true;
		
		System.out.println(notEnoughCoinsMsg);
		return false;
	}
	
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
	
	public void buySlayer() {
		coins -= Slayer.COST;
	}
}
