package org.ucm.tp1.logic;

import org.ucm.tp1.view.GamePrinter;
public class Game {
	private Long seed;
	private Level level;
	private boolean finished = false;
	private GamePrinter gamePrinter;
	
	public Game(Long seed, Level level) {
		this.seed = seed;
		this.level = level;
		
		gamePrinter = new GamePrinter(this, level.getX(), level.getY());
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public void endGame() {
		finished = true;
	}
	
	public String toString() {
		return gamePrinter.toString();
	}

	public void placeVampire() {
		// TODO
	}
 }
