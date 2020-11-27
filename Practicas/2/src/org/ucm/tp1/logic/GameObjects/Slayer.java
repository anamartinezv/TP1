package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public class Slayer extends GameObject {
	
	public final int RESISTANCE = 3;
	public final int DAMAGE = 1;
	public final int ADVANCE = 1;
	
	private static int COST = 50;
	
	public Slayer(Game game, int x, int y) {
		super(game, x, y);
		this.life = RESISTANCE;
	}

	public static int getCost() {
		return COST;
	}
	
	@Override
	public void attack() {
		if (isAlive()) {
			for (int i = x + 1; i < game.getLevel().getX(); i++) {
				IAttack other = game.getAttackableInPosition(i, y);
				if (other != null) {
					other.receiveSlayerAttack(DAMAGE);
					break;
				}
			}
			
		}
	}
	
	@Override
	public boolean receiveVampireAttack(int damage) {
		life -= damage;
		return true;
	}
	
	@Override
	public void move(int cycleNumber) {
		// EMPTY
	}
	
	public String toString() {
		return "S [" + life + "]";
	}
}
