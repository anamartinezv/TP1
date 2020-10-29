package org.ucm.tp1.logic;

import org.ucm.tp1.view.GamePrinter;

public class Game {
	private Long seed;
	private boolean finished;
	private int cyclesNumber;
	
	private Level level;
	private GamePrinter gamePrinter;
	private Player player;
	private GameObjectBoard gameObjectBoard;
	
	public Game(Long seed, Level level) {
		this.seed = seed;
		this.level = level;
		
		finished = false;
		cyclesNumber = 0;
		
		// Instance classes
		gamePrinter = new GamePrinter(this, level.getX(), level.getY());
		player = new Player();
		gameObjectBoard = new GameObjectBoard(level.getVampires());
	}
	
	// Getters
	public int getCycles() {
		return cyclesNumber;
	}
	
	public int getPlayerCoins() {
		return player.getCoins();
	}

	public int getRemainingVampires() {
		return gameObjectBoard.getRemainingVampires();
	}
	
	public int getVampiresOnBoard() {
		return gameObjectBoard.getVampiresOnBoard();
	}
	
	public String getPositionToString() {
		return " ";
	}
	public boolean isFinished() {
		return finished;
	}
	
	public void endGame() {
		finished = true;
	}
	
	public void increaseCycles() {
		cyclesNumber++;
	}
	
	public void updateGame() {
		// Get 10 coins with 50% probability
		player.updateCoinsRandom();
		increaseCycles();
	}

	public void placeVampire() {
		// TODO
	}
	
	public String toString() {
		return gamePrinter.toString();
	}
 }
