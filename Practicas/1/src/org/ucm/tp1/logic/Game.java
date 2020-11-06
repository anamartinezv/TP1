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
	public Vampire vampire;
	
	public Game(Long seed, Level level) {
		this.level = level;
		
		finished = false;
		cyclesNumber = 0;
		
		// Set vampires
		Vampire.setRemainingVampires(level.getVampires());
		
		// Instance classes
		gamePrinter = new GamePrinter(this, level.getX(), level.getY());
		gameObjectBoard = new GameObjectBoard();
		//vampire = new Vampire(this);
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
		if (Vampire.noMoreVampires()) {
			System.out.println("Player wins!");
			endGame();
		}else {
			for (int i = 0; i < level.getY(); i++) {
				if (isVampire(0, i)) {
					System.out.println("Vampires win!");
					endGame();
				}
			}		
		}
	}
	
	public void increaseCycles() {
		cyclesNumber++;
	}
	
	
	
	
	// VAMPIRE METHODS
	public boolean isVampire(int x, int y) {
		return gameObjectBoard.isVampire(x, y);
	}
	
 	public boolean isVampireDead(int x, int y) {
		return gameObjectBoard.isVampireDead(x, y);
	}
	
	public boolean canPlaceVampire() {
		for (int i = 0; i < level.getY(); i++)
			if (!isVampire(level.getX() - 1, i))
				return true;
		
		return false;
	}
	
	public void moveVampires() {
 		for (int i = 0; i < level.getY(); i++) {
 			for (int j = 0; j < level.getX(); j++) {
 				if (isVampire(j, i)) {
 					if (!isSlayer(i, j - 1) && !isVampire(j - 1, i)) {
 						gameObjectBoard.moveVampire(i, j);
 					}
 				}
 			}
 		}
	}
	
	public int newVampirePosition() {
		while (true) {
			int col = random.nextInt(level.getY());
			if (!isVampire(level.getX() - 1, col))
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
	
	public void vampireAttack() {
 		for (int i = 0; i < level.getY(); i++) {
 			for (int j = 0; j < level.getX(); j++) {
 				if (isVampire(j, i)) {
 					if (isSlayer(i, j - 1)) {
 						attackSlayer(j - 1, i);
 					}
 				}
 			}
 		}
	}
	
	public void attackVampire(int x, int y) {
		gameObjectBoard.attackVampire(x, y);
	}
	
	

	// SLAYER METHODS
	public boolean isSlayer(int x, int y) {
		return gameObjectBoard.isSlayer(x, y);
	}
	
	public boolean isSlayerDead(int x, int y) {
		return gameObjectBoard.isSlayerDead(x, y);
	}
	
	public boolean canPlaceSlayer(int x, int y) {
		if (!isSlayer(x, y) && !isVampire(x, y) 
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
	
	public void attackSlayer(int x, int y) {
		gameObjectBoard.attackSlayer(x, y);
	}
	
	public void slayerAttack() {
 		for (int i = 0; i < level.getX(); i++) {
 			for (int j = 0; j < level.getY(); j++) {
 				if (isSlayer(j, i)) {
 					for (int x = i + 1; x < level.getX(); x++)
 						if (isVampire(x, j))
 							attackVampire(x, j);
 				}
 			}
 		}
	}
	
	
	
	
	// GAME METHODS
	public void updateGame() {
		player.updateCoinsRandom(); // Get 10 coins with 50% probability
		
		if (cyclesNumber % 2 == 0)
			moveVampires();
		
		increaseCycles();
	}
	
	public void attack() {
		slayerAttack();
		vampireAttack();
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
    	updateGame(); // TODO: Check if V collapses with S
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
