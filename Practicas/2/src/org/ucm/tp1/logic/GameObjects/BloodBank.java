package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public class BloodBank extends GameObject {
	
	public static final String SYMBOL = "B";
	
	public final int COINS_PERCENTAGE = 10;
	public final int RESISTANCE = 1;
	public final int DAMAGE = 1;

	private int z;
	
	public BloodBank(Game game, int x, int y, int z) {
		super(game, x, y, SYMBOL);
		
		this.z = z;
		this.life = RESISTANCE;
	}

	@Override
	public void attack() {
		/*if (isAlive()) {		
			IAttack other = game.getAttackableInPosition(x + 1, y);
			if (other != null)
				other.receiveSlayerAttack(DAMAGE);
		}*/
	}

	@Override
	// This game object actually doesn't move but instead
	// its movement method gives the player 10% of z in coins each round
	public void move(int cycleNumber) {
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
	
}
