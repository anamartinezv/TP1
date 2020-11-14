package org.ucm.tp1.logic;

import org.ucm.tp1.view.GamePrinter;
import java.util.Random;

public class Game {
	private final String coordinatesOutOfRangeMsg = "Error! Coordinates out of board range.";
	private final String invalidCoordinatesMsg = "Invalid position! Please, check x and y coordinates";

	private boolean finished;
	private boolean printFinalBoard;
	private int cyclesNumber;
	
	private Level level;
	private GamePrinter gamePrinter;
	private GameObjectBoard gameObjectBoard;
	private Player player;
	private Random random;
	
	public Game(Long seed, Level level) {
		this.level = level;
		
		finished = false;
		printFinalBoard = false;
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
	
	public boolean getPrintFinalBoard() {
		return printFinalBoard;
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
		cyclesNumber++;
	}
	
	public String getPositionToString(int x, int y) {
		if (gameObjectBoard.isVampire(y, x))
			return gameObjectBoard.getVampire(y, x);
		else if (gameObjectBoard.isSlayer(x, y))
			return gameObjectBoard.getSlayer(x, y);
		return " ";
	}
	
	public void checkEndGame() {
		if (Vampire.noMoreVampires()) {
			endGame();
			System.out.println("Player wins!");
			printFinalBoard = true;
		}else {
			for (int i = 0; i < level.getY(); i++)
				if (isVampire(0, i)) {
					endGame();
					System.out.println("Vampires win!");
					printFinalBoard = true;
				}
		}
	}
	
	
	// VAMPIRE METHODS
	public boolean isVampire(int x, int y) {
		return gameObjectBoard.isVampire(x, y);
	}
	
	public boolean isValidCycle(int x, int y, int cyclesNumber) {
		return gameObjectBoard.isValidCycle(x, y, cyclesNumber);
	}
	
 	public boolean isVampireDead(int x, int y) {
		return gameObjectBoard.isVampireDead(x, y);
	}
 	
	public void attackVampire(int x, int y) {
		gameObjectBoard.attackVampire(x, y);
	}
	
	public boolean canPlaceVampire() {
		for (int i = 0; i < level.getY(); i++)
			if (!isVampire(level.getX() - 1, i))
				return true;
		
		return false;
	}
	
	public void moveVampires() {
		gameObjectBoard.moveVampires();
	}
	
	public int newVampirePosition() {
		// Loop until an empty position is found
		// If this method is called it's assured there is an empty position
		while (true) {
			int col = random.nextInt(level.getY());
			if (!isVampire(level.getX() - 1, col))
				return col;
		}
	}
	
	public void addVampire() {
		if (random.nextDouble() < level.getFrecuency()) {
			if (canPlaceVampire() && Vampire.getRemainingVampires() > 0) {
				Vampire vampire = new Vampire(this, level.getX() - 1, newVampirePosition(), cyclesNumber);
				gameObjectBoard.addVampire(vampire);
				
				Vampire.increaseVampiresOnBoard();
				Vampire.decreaseRemainingVampires();
			}	
		}
	}
	
	public void vampireAttack() {
 		for (int i = 0; i < level.getY(); i++)
 			for (int j = 0; j < level.getX(); j++)
 				if (isVampire(j, i) && !isVampireDead(j, i))
 					if (isSlayer(i, j - 1))
 						attackSlayer(j - 1, i);
	}
	

	// SLAYER METHODS
	public boolean validX(int x) {
		return (x >= 0 && x < level.getY()) ? true : false;
	}
	
	public boolean validY(int y) {
		return (y >= 0 && y < level.getX()) ? true : false;
	}
	
	public boolean validCoordinates(int x, int y) {
		if (validX(x) && validY(y) && level.getX() - 1 > y)
			return true;
		else {
			System.out.println(coordinatesOutOfRangeMsg);
			return false;
		}
	}
	
	public boolean isSlayer(int x, int y) {
		return gameObjectBoard.isSlayer(x, y);
	}
	
	public boolean isSlayerDead(int x, int y) {
		return gameObjectBoard.isSlayerDead(x, y);
	}
	
	public void attackSlayer(int x, int y) {
		gameObjectBoard.attackSlayer(x, y);
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
			gameObjectBoard.addSlayer(this, x, y);
			player.buySlayer();
			return true;
		} else 
			return false;
	}
	
	public void findVampireToAttack(int x, int y) {
		for (int k = x + 1; k < level.getX(); k++)
			if (isVampire(k, y)) {
				attackVampire(k, y);
				break;
			}	
	}
	
	public void slayerAttack() {
 		for (int i = 0; i < level.getX(); i++)
 			for (int j = 0; j < level.getY(); j++)
 				if (isSlayer(j, i)) findVampireToAttack(i, j);
	}
	
	
	// GAME METHODS
	public void updateGame() {
		player.updateCoinsRandom(); // Get 10 coins with 50% probability
		moveVampires();
	}
	
	public void attack() {
		slayerAttack();
		vampireAttack();
	}
	
	public boolean objectInPosition(int x, int y) {
		if (gameObjectBoard.isVampire(x, y) || 
			gameObjectBoard.isSlayer(x, y))
			return true;
		return false;
	}
	
	public void deleteDeadObjects() {
		for (int i = 0; i < level.getY(); i++) {
 			for (int j = 0; j < level.getX(); j++) {
 				if (isVampire(j, i) && isVampireDead(j, i)) {
 					gameObjectBoard.deleteVampire(j, i);
 					Vampire.decreaseVampiresOnBoard();
 				} else if (isSlayer(i, j) && isSlayerDead(i, j))
 					gameObjectBoard.deleteSlayer(j, i);
 			}
 		} 
	}
	
	public void resetGame() {
		Vampire.setRemainingVampires(level.getVampires());
		Vampire.setVampiresOnBoard(0);
		gameObjectBoard.resetVampires();
		gameObjectBoard.resetSlayers();
		player.resetCoins();
		cyclesNumber = 0;
	}
	
	public void newCycle() {
    	updateGame();
    	attack();
    	addVampire();
    	deleteDeadObjects();
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
