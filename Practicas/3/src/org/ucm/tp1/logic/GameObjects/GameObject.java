package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public abstract class GameObject implements IAttack, IMove {
	
	protected int x;
	protected int y;
	protected int life;
	protected String symbol;
	
	protected Game game;
	
	public GameObject(Game game, int x, int y) {
		this.game = game;
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public abstract String serializeObject();
	
 	public boolean isAlive() {
 		return (life > 0) ? true : false;
 	}
 	
	public String toString() {
		return symbol + " [" + life + "]";
	}
}
