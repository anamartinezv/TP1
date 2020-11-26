package org.ucm.tp1.logic;

import org.ucm.tp1.view.GamePrinter;
import org.ucm.tp1.logic.GameObjects.*;
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
		gameObjectBoard = new GameObjectBoard();
		random = new Random(seed);
		player = new Player(random);
	}
	
	// Getters
	public int getCycles() {
		return cyclesNumber;
	}
	
	public int getPlayerCoins() {
		return player.getCoins();
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
		
	public void endGame() {
		finished = true;
	}
	
	public void increaseCycles() {
		if (!isFinished()) cyclesNumber++;
	}
	
	/*public String getPositionToString(int x, int y) {
		if (gameObjectBoard.isVampire(y, x))
			return gameObjectBoard.getVampireToString(y, x);
		else if (gameObjectBoard.isSlayer(x, y))
			return gameObjectBoard.getSlayerToString(x, y);
		return " ";
	}
	
	public boolean checkPlayerWin() {
		if (Vampire.noMoreVampires()) {
			System.out.println("Player wins!");
			return true;
		}
		
		return false;
	}
	
	public boolean checkVampiresWin() {
		for (int i = 0; i < level.getY(); i++)
			if (isVampire(-1, i)) {
				System.out.println("Vampires win!");
				return true;
			}
		
		return false;
	}
	
	public void checkEndGame() {
		if (checkPlayerWin() || checkVampiresWin()) {
			System.out.println(toString());
			endGame();
		}
	}*/
	

	// OBJECTS METHODS
	public boolean validX(int x) {
		return (x >= 0 && x < level.getY()) ? true : false;
	}
	
	public boolean validY(int y) {
		return (y >= 0 && y < level.getX()) ? true : false;
	}
	
	public boolean validCoordinates(int x, int y) {
		if (validX(x) && validY(y) && level.getX() - 1 > y)
			return true;

		System.out.println(coordinatesOutOfRangeMsg);
		return false;
	}
	
	public String objectToString(int x, int y) {
		return gameObjectBoard.objectToString(x, y);
	}
	
	public boolean objectInPosition(int x, int y) {
		return gameObjectBoard.objectInPosition(x, y);
	}
	
	public boolean canPlaceSlayer(int x, int y) {
		if (player.hasEnoughCoins() && validCoordinates(x, y))
			if (objectInPosition(x, y))
				System.out.println(invalidCoordinatesMsg);
			else
				return true;
		return false;
	}
	
	public boolean addSlayer(int x, int y) {
		if (canPlaceSlayer(x, y)) {
			gameObjectBoard.addObject(new Slayer(this, x, y));
			return true;
		}
			
		return false;
	}
	
	public boolean canPlaceVampire() {
		if (random.nextDouble() < level.getFrecuency())
			if (Vampire.getRemainingVampires() > 0)
				return true;
		
		return false;
	}
	
	public void addVampire() {
		if (canPlaceVampire()) {
			int randomRow = random.nextInt(level.getY());
			if (!objectInPosition(level.getX() - 1, randomRow))
				gameObjectBoard.addObject(new Vampire(this, level.getX() - 1, 
											randomRow, cyclesNumber));
		}
	}
	
	// GAME METHODS
	public void updateGame() {
		player.updateCoinsRandom(); // Get 10 coins with 50% probability
		//moveVampires();
	}
	
	public void attack() {
		//slayersAttack();
		//vampiresAttack();
	}
	
	/*public boolean objectInPosition(int x, int y) {
		if (gameObjectBoard.isVampire(x, y) || 
			gameObjectBoard.isSlayer(x, y))
			return true;
		
		return false;
	}
	
	public void deleteDeadObjects() {
		gameObjectBoard.deleteDeadVampires();
		gameObjectBoard.deleteDeadSlayers();
	}*/
	
	public void resetGame() {
		//Vampire.setRemainingVampires(level.getVampires());
		//Vampire.setVampiresOnBoard(0);
		//gameObjectBoard.resetVampires();
		//gameObjectBoard.resetSlayers();
		player.resetCoins();
		cyclesNumber = 0;
	}
	
	public void newCycle() {
    	updateGame();
    	attack();
    	addVampire();
    	//deleteDeadObjects();
    	//checkEndGame();
	}
	
	public String gameStats() {		
		String nCycles = String.format("\nNumber of cycles: %s", cyclesNumber);
		String coins = String.format("\nCoins: %s", player.getCoins());
		//String nVampires = String.format("\nRemaining vampires: %s", Vampire.getRemainingVampires());
		//String vampiresOnBoard = String.format("\nVampires on the board: %s", Vampire.getVampiresOnBoard());
		
		StringBuilder stringBuilder = new StringBuilder();
		//stringBuilder.append(nCycles).append(coins).append(nVampires).append(vampiresOnBoard);
		stringBuilder.append(nCycles).append(coins);
		
		return stringBuilder.toString();
	}
	
	public String toString() {
		System.out.println(gameStats());
		return gamePrinter.toString();
	}
 }
