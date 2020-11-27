package org.ucm.tp1.logic;

import org.ucm.tp1.view.*;
import org.ucm.tp1.logic.GameObjects.*;
import java.util.Random;

public class Game implements IPrintable {
	private final String coordinatesOutOfRangeMsg = "Error! Coordinates out of board range.";
	private final String invalidCoordinatesMsg = "Invalid position! Please, check x and y coordinates";

	private boolean finished;
	private int cycleNumber;
	
	private Level level;
	private GamePrinter gamePrinter;
	private GameObjectBoard gameObjectBoard;
	private Player player;
	private Random random;
	
	public Game(Long seed, Level level) {
		this.level = level;
		
		finished = false;
		cycleNumber = 0;
		
		// Set vampires
		Vampire.setRemainingVampires(level.getVampires());

		// Instance classes
		gameObjectBoard = new GameObjectBoard();
		//gamePrinter = new GamePrinter(e, 3, 3);
		random = new Random(seed);
		player = new Player(random);
	}
	
	// Getters
	public int getCycles() {
		return cycleNumber;
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
		if (!isFinished()) cycleNumber++;
	}
	
	/*public boolean checkPlayerWin() {
		if (Vampire.noMoreVampires()) {
			System.out.println("Player wins!");
			return true;
		}
		
		return false;
	}
	
	public boolean checkVampiresWin() {
		gameObjectBoard.checkVampireWin();
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
	
	public boolean objectInPosition(int x, int y) {
		return gameObjectBoard.objectInPosition(x, y);
	}
	
	public IAttack getAttackableInPosition(int x, int y) {
		return gameObjectBoard.getAttackableInPosition(x, y);
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
			gameObjectBoard.addObject(new Slayer(this, y, x));
			player.buySlayer();
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
			if (!objectInPosition(randomRow, level.getX() - 1)) {
				gameObjectBoard.addObject(new Vampire(this, randomRow, 
											level.getX() - 1, cycleNumber));
				Vampire.addVampireToCounter();
			}
				
		}
	}
	
	public void moveObjects() {
		gameObjectBoard.moveObjects(cycleNumber);
	}
	
	public void attack() {
		gameObjectBoard.attackObjects();
	}
	
	public void deleteDeadObjects() {
		gameObjectBoard.deleteDeadObjects();
	}
	
	// GAME METHODS
	public void update() {
		player.updateCoinsRandom(); // Get 10 coins with 50% probability
		moveObjects();
		attack();
		addVampire();
    	deleteDeadObjects();
    	//checkEndGame();
		if (!isFinished()) cycleNumber++;
	}
	
	public void resetGame() {
		Vampire.setRemainingVampires(level.getVampires());
		Vampire.setVampiresOnBoard(0);
		gameObjectBoard.resetList();
		player.resetCoins();
		cycleNumber = 0;
	}
	
	public String getPositionToString(int x, int y) {
		return gameObjectBoard.objectToString(x, y);
	}
	
	public String getInfo() {		
		String nCycles = String.format("Number of cycles: %s", cycleNumber);
		String coins = String.format("\nCoins: %s", player.getCoins());
		String nVampires = String.format("\nRemaining vampires: %s", Vampire.getRemainingVampires());
		String vampiresOnBoard = String.format("\nVampires on the board: %s", Vampire.getVampiresOnBoard());
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(nCycles).append(coins).append(nVampires).append(vampiresOnBoard);
		
		return stringBuilder.toString();
	}
 }
