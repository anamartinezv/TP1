package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public class BloodBank extends GameObject {
	
	public final int COINS_PERCENTAGE = 10;
	public final int RESISTANCE = 1;

	private int z;
	
	public BloodBank(Game game, int x, int y, int z) {
		super(game, x, y);
		
		this.z = z;
		this.life = RESISTANCE;
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	// This game object actually doesn't move but instead
	// its movement method gives the player 10% of z in coins each round
	public void move(int cycleNumber) {
		game.addCoins((z * COINS_PERCENTAGE) / 100);
	}
	
	@Override
	public boolean receiveDraculaAttack() {
		life = 0;
		return true;
	}
	
	public String toString() {
		return "B [" + z + "]";
	}	
}
