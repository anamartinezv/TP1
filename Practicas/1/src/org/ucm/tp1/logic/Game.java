package org.ucm.tp1.logic;

import org.ucm.tp1.view.GamePrinter;
import java.util.Random;

public class Game {
	private final String coordinatesOutOfRangeMsg = "Error! Coordinates out of board range.";
	private final String invalidCoordinatesMsg = "Invalid position! Please, check x and y coordinates";
	
	private boolean finished;
	private int cyclesNumber;
	
	private Level level;
	private GamePrinter gamePrinter;
	private GameObjectBoard gameObjectBoard;
	private Player player;
	private Random random;
	
	public Game(Long seed, Level level) {
		this.level = level;
		
		finished = false;
		cyclesNumber = 0;
		
		// Set vampires
		Vampire.setRemainingVampires(level.getVampires());
		
		// Instance classes
		gamePrinter = new GamePrinter(this, level.getX(), level.getY());
		gameObjectBoard = new GameObjectBoard(this);
		player = new Player(seed);
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
		else if (gameObjectBoard.isSlayer(x, y))
			return gameObjectBoard.getSlayer(x, y);
		return " ";
	}
	
	public boolean isFinished() {
		return finished;
	}
		
	public void endGame() {
		finished = true;
	}
	
	public void checkEndGame() {
		// TOOODOOO
		if (Vampire.noMoreVampires())
			endGame();
	}
	
	public void increaseCycles() {
		cyclesNumber++;
	}
	
	// VAMPIRE METHODS
	public boolean canPlaceVampire() {
		for (int i = 0; i < level.getY(); i++)
			if (!gameObjectBoard.isVampire(level.getX() - 1, i))
				return true;
		
		return false;
	}
	
	public int newVampirePosition() {
		while (true) {
			int col = random.nextInt(level.getY());
			if (!gameObjectBoard.isVampire(level.getX() - 1, col))
				return col;
		}
	}
	
	public void addVampire() {
		if (random.nextDouble() < level.getFrecuency()) {
			if (canPlaceVampire() && Vampire.getRemainingVampires() > 0) {
				Vampire vampire = new Vampire(this, level.getX() - 1, newVampirePosition());
				gameObjectBoard.addVampire(vampire);
				
				Vampire.increaseVampiresOnBoard();
				Vampire.decreaseRemainingVampires();
			}	
		}
	}
	
	// SLAYER METHODS
	public boolean canPlaceSlayer(int x, int y) {
		if (!gameObjectBoard.isSlayer(x, y) && !gameObjectBoard.isVampire(x, y) 
			&& validCoordinates(x, y) && level.getX() - 1 > y)
			return true;
		
		System.out.println(invalidCoordinatesMsg);
		return false;
	}
	
	public void addSlayer(int x, int y) {
		if (canPlaceSlayer(x, y) && player.haveEnoughCoins()) {
			Slayer slayer = new Slayer(this, x, y);
			gameObjectBoard.addSlayer(slayer);
			player.buySlayer();
		}
	}
	
	public boolean validCoordinates(int x, int y) {
		if (x >= 0 && x < level.getY() && y >= 0 && y < level.getX())
			return true;
		
		System.out.println(coordinatesOutOfRangeMsg);
		return false;
	}
	
	
	// GAME METHODS
	public void updateGame() {
		player.updateCoinsRandom(); // Get 10 coins with 50% probability
		
		if (cyclesNumber % 2 == 0)
			gameObjectBoard.moveVampires();
		
		increaseCycles();
	}
	
	public void attack() {
		// TODO
	}
	
	public void newCycle() {
    	updateGame(); // TODO: Check if V collapses with S
    	attack();
    	addVampire(); 
    	checkEndGame();
	}
	
	public String gameStats() {		
		String nCycles = String.format("\nNumber of cycles: %s", cyclesNumber);
		String coins = String.format("\nCoins: %s", player.getCoins());
		String nVampires = String.format("\nRemaining vampires: %s", Vampire.getRemainingVampires());
		String vampiresOnBoard = String.format("\nVampires on the board: %s", Vampire.getVampiresOnBoard());
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(nCycles).append(coins).append(nVampires).append(vampiresOnBoard);
		
		return stringBuilder.toString();
	}
	
	public String toString() {
		return gamePrinter.toString();
	}
 }
