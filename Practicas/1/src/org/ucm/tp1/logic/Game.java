package org.ucm.tp1.logic;

import org.ucm.tp1.view.GamePrinter;
import java.util.Random;

public class Game {
	private Long seed;
	private boolean finished;
	private int cyclesNumber;
	
	private Level level;
	private GamePrinter gamePrinter;
	private GameObjectBoard gameObjectBoard;
	private Player player;
	private Random random;
	
	public Game(Long seed, Level level) {
		this.seed = seed;
		this.level = level;
		
		finished = false;
		cyclesNumber = 0;
		
		// Instance classes
		gamePrinter = new GamePrinter(this, level.getX(), level.getY());
		gameObjectBoard = new GameObjectBoard(this);
		player = new Player();
		random = new Random(seed);
	}
	
	// Getters
	public int getCycles() {
		return cyclesNumber;
	}
	
	public int getPlayerCoins() {
		return player.getCoins();
	}
	
	public String getPositionToString(int x, int y) {
		if (gameObjectBoard.isVampire(y, x))
			return gameObjectBoard.getVampire(y, x);
		return " ";
	}
	
	public Level getLevel() {
		return level;
	}
	
	public Random getRandom() {
		return random;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public int getVampiresOnBoard() {
		return gameObjectBoard.getVampiresOnBoard();
	}
	
	public int getRemainingVampires() {
		return level.getVampires() - getVampiresOnBoard();
	}
	//////
	
	public void endGame() {
		finished = true;
	}
	
	public void checkEndGame() {
		// TODO
	}
	
	public void increaseCycles() {
		cyclesNumber++;
	}
	
	public boolean canPlaceVampire() {
		return gameObjectBoard.canPlaceVampire();
	}
	
	public void updateGame() {
		player.updateCoinsRandom(); // Get 10 coins with 50% probability
		
		if (cyclesNumber % 2 == 0)
			gameObjectBoard.moveVampires();
		
		increaseCycles();
	}

	public void addVampire() {
		if (canPlaceVampire())
			gameObjectBoard.addVampire();
	}
	
	public String toString() {
		return gamePrinter.toString();
	}
 }
