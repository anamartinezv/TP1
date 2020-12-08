package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public class Dracula extends Vampire {
	
	private static boolean isPresent = false;

	public Dracula(Game game, int x, int y) {
		super(game, x, y);
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
	public boolean receiveLightFlash() {
		return false;
	}
	
	@Override
	public String toString() {
		return "D [" + life + "]";
	}
}
