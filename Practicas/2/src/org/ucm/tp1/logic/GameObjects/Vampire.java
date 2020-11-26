package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public class Vampire extends GameObject implements IAttack {
	
	private static int remainingVampires;
	private static int vampiresOnBoard = 0;
	
	private int cycleNumber;
	
	public Vampire(Game game, int x, int y, int cycleNumber) {
		super(game, x, y);
		
		this.cycleNumber = cycleNumber;
	}
	
	public static int getRemainingVampires() {
		return remainingVampires;
	}
	
	public static int getVampiresOnBoard() {
		return vampiresOnBoard;
	}
	
	public static void setRemainingVampires(int amount) {
		remainingVampires = amount;
	}
	
	public static void setVampiresOnBoard(int amount) {
		vampiresOnBoard = amount;
	}
	
 	public static boolean noMoreVampires() {
 		return remainingVampires == 0 && vampiresOnBoard == 0 ? true : false;
 	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}
}
