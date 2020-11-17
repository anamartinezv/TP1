package org.ucm.tp1.logic;

public class Slayer {
	public static final int COST = 50;
	public static final int RESISTANCE = 3;
	public static final int DAMAGE = 1;
	
	private int x;
	private int y;
	private int life;
	
	private Game game;
	
	public Slayer(Game game, int x, int y) {
		this.game = game;
		this.x = x;
		this.y = y;
		life = RESISTANCE;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void harm(int amount) {
		life -= amount;
	}
	
	public boolean isDead() {
		return life <= 0 ? true : false;
	}
	
	public void attack() {
		for (int i = y; i < game.getLevel().getX(); i++) 
			if (game.isVampire(i, x)) {
				game.harmVampire(x, i, DAMAGE);
				break;
			}
	}
	
	public String toString() {
		return "S [" + life + "]";
	}
}
