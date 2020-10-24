package org.ucm.tp1.logic;

public class Game {
	private Long seed; // Upper L because the seed can be a NULL value
	private Level level;
	private boolean finished = false;
	
	
	public Game(Long seed, Level level) {
		this.seed = seed;
		this.level = level;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public void endGame() {
		finished = true;
	}
 }
