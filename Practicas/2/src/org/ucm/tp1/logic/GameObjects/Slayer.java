package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public class Slayer extends GameObject {
	
	public static final String SYMBOL = "S";
	
	public final int RESISTANCE = 3;
	public final int DAMAGE = 1;
	public final int ADVANCE = 1;
	
	private static int LIGHT_FLASH = 50;
	private static int SLAYER_COST = 50;
	private static int GARLIC_PUSH_COST = 10;
	
	public Slayer(Game game, int x, int y) {
		super(game, x, y, SYMBOL);
		this.life = RESISTANCE;
	}

	public static int getSlayerCost() {
		return SLAYER_COST;
	}
	
	public static int getGarlicPushCoins() {
		return GARLIC_PUSH_COST;
	}
	
	public static int getLightFlashCost() {
		return LIGHT_FLASH;
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
	public boolean receiveDraculaAttack() {
		life = 0;
		return true;
	}
	
	@Override
	public void move(int cycleNumber) {
		// EMPTY
	}
	
}
