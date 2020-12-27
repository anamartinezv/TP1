package org.ucm.tp1.logic;

import java.util.Random;

public class Player {
	
	public final int COINS_AT_START = 50;
	public final int COINS_PER_ROUND = 10;
	public final String notEnoughCoinsMsg = "[ERROR]: Not enough coins";

	private int coins;
	
	private Random random;
	
	public Player(Random random) {
		coins = COINS_AT_START;
		
		this.random = random;
	}
	
	public boolean hasEnoughCoins(int amount) {
		return (coins >= amount) ? true : false;
	}
	
	public void resetCoins() {
		coins = COINS_AT_START;
	}
	
	public int getCoins() {
		return coins;
	}
	
	public void addCoins(int amount) {
		coins += amount;
	}
	
	public void buy(int amount) {
		coins -= amount;
	}
	
	public void updateCoinsRandom() {
		if (random.nextFloat() > 0.5)
			coins += COINS_PER_ROUND;
	}
}
