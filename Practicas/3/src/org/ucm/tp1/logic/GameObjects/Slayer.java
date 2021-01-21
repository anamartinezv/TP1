package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public class Slayer extends GameObject {
	
	public static final String SYMBOL = "S";
	
	public final int RESISTANCE = 3;
	public final int DAMAGE = 1;
	public final int ADVANCE = 1;

	private static int SLAYER_COST = 50;
	
	public Slayer(Game game, int x, int y) {
		super(game, x, y);
		
		symbol = SYMBOL;

		this.life = RESISTANCE;
	}

	public static int getSlayerCost() {
		return SLAYER_COST;
	}
	
	private void attackFunctionality() {
		for (int i = x + 1; i < game.getLevel().getX(); i++) {
			IAttack other = game.getAttackableInPosition(i, y);
			if (other != null) {
				other.receiveSlayerAttack(DAMAGE);
				break;
			}
		}
	}
	
	@Override
	public void attack() {
		if (isAlive()) {
			attackFunctionality();
		}
	}
	
	/*
	 * When a Slayer receives a Slayer attack it doesn't harm the Slayer
	 * but it needs to keep moving the attack until it reaches a Vampire
	 * We don't  check if the Slayer that receives the attack is alive because
	 * the slayer that originally threw the attack is.
	 */
	@Override
	public boolean receiveSlayerAttack(int damage) {
		attackFunctionality();
		return true;
	}
	
	@Override
	public boolean receiveVampireAttack(int damage) {
		life -= damage;
		return true;
	}
	
	@Override
	public boolean receiveDraculaAttack() {
		life = 0;
		return true;
	}
	
	@Override
	public void move() {
		// EMPTY
	}
	
}
