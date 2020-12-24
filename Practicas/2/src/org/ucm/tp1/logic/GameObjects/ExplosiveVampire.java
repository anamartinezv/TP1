package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public class ExplosiveVampire extends Vampire {
	
	public static final String SYMBOL = "EV";
	
	private static int EXPLOSION_DAMAGE = 1;

	public ExplosiveVampire(Game game, int x, int y) {
		super(game, x, y);
		
		symbol = SYMBOL;
	}
	
	public static int getExplosionDamage() {
		return EXPLOSION_DAMAGE;
	}
	
	private void explode() {
		for (int i = x - 1; i < x + 2; i++)
			for (int j = y - 1; j < y + 2; j++) {
				IAttack other = game.getAttackableInPosition(i, j);
				if (other != null) other.receiveSlayerAttack(EXPLOSION_DAMAGE);
			}
				
	}
	
	@Override
	public boolean receiveSlayerAttack(int damage) {
		if (isAlive()) {
			life -= damage;
			
			if (life <= 0) {
				explode();
				Vampire.setVampiresOnBoard(Vampire.getVampiresOnBoard() - 1);
			}
			
			return true;
		}
		
		return false;
	}

}
