package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public class Vampire extends GameObject {
	public final int RESISTANCE = 5;
	public final int DAMAGE = 1;
	public final int ADVANCE = 2;
	
	private static int remainingVampires;
	private static int vampiresOnBoard = 0;
	
	private int lastCycle;
	
	public Vampire(Game game, int x, int y, int cycleNumber) {
		super(game, x, y);
		
		this.life = RESISTANCE;
		this.lastCycle = cycleNumber;
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
 	
 	public static void addVampireToCounter() {
		remainingVampires--;
		vampiresOnBoard++;
 	}

 	public boolean validCycle(int cycleNumber) {
 		return cycleNumber - ADVANCE >= lastCycle ? true : false;
 	}
 	
	@Override
	public void attack() {
		if (isAlive()) {
			IAttack other = game.getAttackableInPosition(x - 1, y);
			if (other != null) other.receiveVampireAttack(DAMAGE);
		}	
	}
	
	@Override
	public boolean receiveSlayerAttack(int damage) {
		life -= damage;
		if (life <= 0) vampiresOnBoard--;
		return true;
	}
	
	@Override
	public void move(int cycleNumber) {
		if (validCycle(cycleNumber) && isAlive()) {
			if (!game.objectInPosition(x - 1, y)) {
				x--;
				lastCycle = game.getCycles();
			}
		}		
				
	}
	
	public String toString() {
		return "V [" + life + "]";
	}	
}
