package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public class Slayer extends GameObject implements IAttack {
	public final int RESISTANCE = 3;
	public final int DAMAGE = 1;
	public final int ADVANCE = 1;
	
	private static int COST = 50;
	
	public Slayer(Game game, int x, int y) {
		super(game, x, y);
		this.life = RESISTANCE;
	}

	public static int getCost() {
		return COST;
	}
	
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}
}
