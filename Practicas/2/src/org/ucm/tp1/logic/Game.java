package org.ucm.tp1.logic;

import org.ucm.tp1.view.*;
import org.ucm.tp1.logic.GameObjects.*;
import java.util.Random;

public class Game implements IPrintable {
	private final String invalidCoordinatesMsg = "Invalid position";
	private final String playerWinsMsg = "Player wins";
	private final String vampiresWinMsg = "Vampires win!";

	private boolean finished;
	private int cycleNumber;
	private String winnerMessage;
	
	private Level level;
	private GameObjectBoard gameObjectBoard;
	private GamePrinter gamePrinter;
	private Player player;
	private Random random;
	
	public Game(Long seed, Level level) {
		this.level = level;
		
		winnerMessage = "Nobody wins...";
		finished = false;
		cycleNumber = 0;
		
		// Set vampires
		Vampire.setRemainingVampires(level.getVampires());

		// Instance classes
		gameObjectBoard = new GameObjectBoard();
		gamePrinter = new GamePrinter(this, level.getX(), level.getY());
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
	
	public String getWinnerMessage() {
		return winnerMessage;
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
	
	public boolean checkPlayerWin() {
		if (Vampire.noMoreVampires()) {
			winnerMessage = playerWinsMsg;
			return true;
		}
		
		return false;
	}
	
	public boolean checkVampiresWin() {
		if (gameObjectBoard.checkVampireWin()) {
			winnerMessage = vampiresWinMsg;
			return true;
		}
		
		return false;
	}
	
	public void checkEndGame() {
		if (checkPlayerWin() || checkVampiresWin())
			endGame();
	}
	

	// OBJECTS METHODS
	public boolean validX(int x) {
		return (x >= 0 && x < level.getX() - 1) ? true : false;
	}
	
	public boolean validY(int y) {
		return (y >= 0 && y < level.getY()) ? true : false;
	}
	
	public boolean validCoordinates(int x, int y) {
		if (validX(x) && validY(y))
			return true;

		System.out.println(invalidCoordinatesMsg);
		return false;
	}
	
	public boolean objectInPosition(int x, int y) {
		return gameObjectBoard.objectInPosition(x, y);
	}
	
	public IAttack getAttackableInPosition(int x, int y) {
		return gameObjectBoard.getAttackableInPosition(x, y);
	}
	
	public boolean canPlaceSlayer(int x, int y) {
		if (validCoordinates(x, y) && player.hasEnoughCoins())
			if (objectInPosition(x, y))
				System.out.println(invalidCoordinatesMsg);
			else
				return true;
		return false;
	}
	
	public boolean addSlayer(int x, int y) {
		if (canPlaceSlayer(x, y)) {
			gameObjectBoard.addObject(new Slayer(this, x, y));
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
			if (!objectInPosition(level.getX() - 1, randomRow)) {
				gameObjectBoard.addObject(new Vampire(this, level.getX() - 1, 
											randomRow, cycleNumber));
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
    	checkEndGame();
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
		String vampiresOnBoard = String.format("\nVampires on the board: %s\n", Vampire.getVampiresOnBoard());
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(nCycles).append(coins).append(nVampires).append(vampiresOnBoard);
		
		return stringBuilder.toString();
	}
	
	public String toString() {
		return gamePrinter.toString();
	}
 }
