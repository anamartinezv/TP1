package org.ucm.tp1.logic;


public class Vampire {
	public static final int RESISTANCE = 5;
	public static final int DAMAGE = 1;
	public static final int ADVANCE = 2;
	
	private static int remainingVampires;
	private static int vampiresOnBoard = 0;
	
	private int x;
	private int y;
	private int life;
	private int lastCycle;
	
	private Game game;
		
	public Vampire(Game game, int x, int y, int cycleNumber) {
		this.game = game;
		this.x = x;
		this.y = y;
		this.lastCycle = cycleNumber;
		this.life = RESISTANCE; 
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public static int getRemainingVampires() {
		return remainingVampires;
	}
	
	public static int getVampiresOnBoard() {
		return vampiresOnBoard;
	}
	
	public void setCycle(int cycleNumber) {
		lastCycle = cycleNumber;
	}
	
	public static void setRemainingVampires(int amount) {
		remainingVampires = amount;
	}
	
	public static void setVampiresOnBoard(int amount) {
		vampiresOnBoard = amount;
	}
	
	public static void increaseVampiresOnBoard() {
		vampiresOnBoard++;
	}
	
	public static void decreaseRemainingVampires() {
		remainingVampires--;
	}
	
	public static void decreaseVampiresOnBoard() {
		vampiresOnBoard--;
	}
	
 	public static boolean noMoreVampires() {
 		return remainingVampires == 0 && vampiresOnBoard == 0 ? true : false;
 	}
 	
 	public boolean isDead() {
 		return life <= 0 ? true : false;
 	}
 	
 	public boolean isValidCycle(int cyclesNumber) {
 		return cyclesNumber - ADVANCE >= lastCycle ? true : false;
 	}
 	
 	public void harm(int amount) {
 		life -= amount;
 	}
 	
 	public void move() {
 		if (!game.objectInPosition(y, x - 1) && isValidCycle(game.getCycles())) {
 			x--;
 			lastCycle = game.getCycles();	
 		}
 	}
 	
 	public void attack() {
 		if (game.isSlayer(y, x - 1))
 			game.harmSlayer(y, x - 1, DAMAGE);
 	}
 	
	public String toString() {
		return "V [" + life + "]";
	}
}
