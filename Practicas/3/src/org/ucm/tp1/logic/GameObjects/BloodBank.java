package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public class BloodBank extends GameObject {
	
	public static final String SYMBOL = "B";
	
	public final int COINS_PERCENTAGE = 10;
	public final int RESISTANCE = 1;
	public final int DAMAGE = 1;

	private int z;
	
	public BloodBank(Game game, int x, int y, int z) {
		super(game, x, y);
		
		symbol = SYMBOL;

		this.z = z;
		this.life = RESISTANCE;
	}
	
	@Override
 	public String serializeObject() {
 		return super.serializeObject() + ";" + z;
 	}

	@Override
	public void attack() {
		// Empty
	}

	@Override
	// This game object actually doesn't move but instead
	// its movement method gives the player COINS_PERCENTAGE of z in coins each round
	public void move() {
		game.addCoins((z * COINS_PERCENTAGE) / 100);
	}
	
	@Override
	public boolean receiveVampireAttack(int amount) {
		life = 0;
		return true;
	}
	
	@Override
	public boolean receiveDraculaAttack() {
		life = 0;
		return true;
	}
	
	@Override
	public String toString() {
		return symbol + " [" + z + "]"; 
	}
	
}
