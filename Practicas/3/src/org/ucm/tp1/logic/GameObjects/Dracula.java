package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public class Dracula extends Vampire {
	
	public static final String SYMBOL = "D";
	
	private static boolean isPresent;

	public Dracula(Game game, int x, int y) {
		super(game, x, y);
		
		symbol = SYMBOL;
		isPresent = true;
	}
	
	public static boolean getIsPresent() {
		return isPresent;
	}
	
	public static void setIsPresent(boolean status) {
		isPresent = status;
	}
	
	@Override
	public void attack() {
		if (isAlive()) {
			IAttack other = game.getAttackableInPosition(x - 1, y);
			if (other != null) other.receiveDraculaAttack();
		}
	}
	
	@Override
	public boolean receiveSlayerAttack(int damage) {
		if (isAlive()) {
			life -= damage;
			if (life <= 0) {
				isPresent = false;
				Vampire.setVampiresOnBoard(Vampire.getVampiresOnBoard() - 1);
			}
			return true;
		}		
		
		return false;
	}
	
	@Override
	public boolean receiveGarlicPush() {
		if (!game.objectInPosition(x + 1, y)) 
		x++;
		
		if (x >= game.getLevel().getX()) {
			life = 0;
			isPresent = false;
			Vampire.decreaseVampiresOnBoard();
		}
		
		resetCycleCounter();
		return true;
	}
	
	@Override
	public boolean receiveLightFlash() {
		return false;
	}

}
