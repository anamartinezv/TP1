package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public class Vampire extends GameObject {
	
	public static final String SYMBOL = "V";
	
	public final int RESISTANCE = 5;
	public final int DAMAGE = 1;
	public final int ADVANCE = 2;
	
	private static int remainingVampires;
	private static int vampiresOnBoard = 0;
	private static boolean vampiresWin = false;
	
	protected int cycleCounter;
	
	public Vampire(Game game, int x, int y) {
		super(game, x, y);
		
		symbol = SYMBOL;
		
		this.life = RESISTANCE;
		
		addVampireToCounter();
		resetCycleCounter();
	}
	
	public static int getRemainingVampires() {
		return remainingVampires;
	}
	
	public static int getVampiresOnBoard() {
		return vampiresOnBoard;
	}
	
	public static boolean getVampiresWin() {
		return vampiresWin;
	}
	
	public static void setRemainingVampires(int amount) {
		remainingVampires = amount;
	}
	
	public static void setVampiresOnBoard(int amount) {
		vampiresOnBoard = amount;
	}
	
	public static void decreaseVampiresOnBoard() {
		vampiresOnBoard--;
	}
	
 	public static boolean noMoreVampires() {
 		return (remainingVampires == 0 && vampiresOnBoard == 0) ? true : false;
 	}
 	
 	public static void addVampireToCounter() {
		remainingVampires--;
		vampiresOnBoard++;
 	}
 	
 	public static void initVampires(int maxVampires) {
		remainingVampires = maxVampires;
		vampiresOnBoard = 0;
		Dracula.setIsPresent(false);
 	}

 	public void resetCycleCounter() {
 		cycleCounter = 1;
 	}
 	
 	@Override
 	public String serializeObject() {
 		return symbol + ";" + x + ";" + y + ";" + life + ";" + cycleCounter;
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
		if (isAlive()) {
			life -= damage;
			if (life <= 0) vampiresOnBoard--;
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
			vampiresOnBoard--;
		}
		
		cycleCounter = 1;
		return true;
	}
	
	@Override
	public boolean receiveLightFlash() {
		life = 0;
		vampiresOnBoard--;
		return true;
	}
	
	@Override
	public void move() {
		if (cycleCounter == ADVANCE && isAlive()) {
			if (!game.objectInPosition(x - 1, y)) {
				x--;
				resetCycleCounter();
				if (x <= -1) vampiresWin = true;
			}
		} else cycleCounter++;
	}
	
}
