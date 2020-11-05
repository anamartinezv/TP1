package org.ucm.tp1.logic;

import java.util.Random;

public class Player {
	
	public final String notEnoughCoinsMsg = "Error! You dont have enough coins!";
	public final int COINS_AT_START = 50;
	public final int COINS_PER_ROUND = 10;
	
	private int coins;
	
	private Random random;
	
	public Player(Random random) {
		coins = COINS_AT_START;
		
		this.random = random;
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
		if (random.nextFloat() > 0.5)
			coins += COINS_PER_ROUND;
	}
	
	public void buySlayer() {
		coins -= Slayer.COST;
	}
}
