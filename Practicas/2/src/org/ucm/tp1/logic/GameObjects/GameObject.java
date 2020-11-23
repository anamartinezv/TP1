package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public abstract class GameObject {
	private int x;
	private int y;
	private int RESISTANCE;
	private int DAMAGE;
	private int ADVANCE;
	
	private Game game;
	
	public GameObject(Game game) {
		this.game = game;
	}
}
