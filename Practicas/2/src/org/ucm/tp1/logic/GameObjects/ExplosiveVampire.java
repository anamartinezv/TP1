package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public class ExplosiveVampire extends Vampire {
	
	private static int EXPLOSION_DAMAGE = 1;

	public ExplosiveVampire(Game game, int x, int y) {
		super(game, x, y);
	}
	
	public static int getExplosionDamage() {
		return EXPLOSION_DAMAGE;
	}
	
	// Doesn't work because the vampire is still not deleted from the list
	// so it creates a loop of vampireExplodes();
	/*@Override
	public boolean receiveSlayerAttack(int damage) {
		life -= damage;
		
		if (life <= 0) {
			game.vampireExplodes();
			Vampire.setVampiresOnBoard(Vampire.getVampiresOnBoard() - 1);
		}
		
		return true;
	}*/
	
	@Override
	public String toString() {
		return "EV [" + life + "]";
	}

}
