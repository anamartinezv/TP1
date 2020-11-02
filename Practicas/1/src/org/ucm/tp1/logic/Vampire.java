package org.ucm.tp1.logic;


public class Vampire {
	public final int RESISTANCE = 5;
	
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
	
 	public void decreaseX() {
 		x--;
 	}
 	
	public String toString() {
		return "V [" + life + "]";
	}
}
