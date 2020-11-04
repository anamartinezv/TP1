package org.ucm.tp1.logic;


public class Vampire {
	public static final int RESISTANCE = 5;
	public static final int DAMAGE = 1;
	
	private static int remainingVampires;
	private static int vampiresOnBoard = 0;
	
	private int x;
	private int y;
	private int life;
	
	private Game game;
		
	public Vampire(Game game, int x, int y) {
		this.game = game;
		this.x = x;
		this.y = y;
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
	
	public static void setRemainingVampires(int amount) {
		remainingVampires = amount;
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
	
 	public void decreaseX() {
 		x--;
 	}
 	
 	public static boolean noMoreVampires() {
 		if (remainingVampires == 0 && vampiresOnBoard == 0)
 			return true;
 		
 		return false;
 	}
 	
	public String toString() {
		return "V [" + life + "]";
	}
}
