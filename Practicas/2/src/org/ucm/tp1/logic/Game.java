package org.ucm.tp1.logic;

import org.ucm.tp1.view.*;
import org.ucm.tp1.logic.GameObjects.*;
import java.util.Random;

public class Game implements IPrintable {
	
	public static final int SUPER_COINS = 1000;
	public static final int LIGHT_FLASH_COST = 50;
	public static final int GARLIC_PUSH_COST = 10;
	
	public static final String invalidCoordinatesMsg = String.format("[ERROR]: Invalid position");
	public static final String playerWinsMsg = String.format("Player wins");
	public static final String vampiresWinMsg = String.format("Vampires win!");
	public static final String nobodyWinMsg = String.format("Nobody wins...");
	public static final String draculaAliveMsg = String.format("Dracula is alive");
	public static final String noMoreRemainingVampiresMsg = String.format("[ERROR]: No more remaining vampires left");

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
		
		winnerMessage = nobodyWinMsg;
		finished = false;
		cycleNumber = 0;
		
		// Set vampires
		Vampire.initVampires(level.getVampires());

		// Instance classes
		gameObjectBoard = new GameObjectBoard();
		gamePrinter = new GamePrinter(this, level.getX(), level.getY());
		random = new Random(seed);
		player = new Player(random);
	}
	
	// Getters and setters
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
	
	
	// HELPERS
	public boolean checkPlayerWin() {
		if (Vampire.noMoreVampires()) {
			winnerMessage = playerWinsMsg;
			return true;
		}
		
		return false;
	}
	
	public boolean checkVampiresWin() {
		if (Vampire.getVampiresWin()) {
			winnerMessage = vampiresWinMsg;
			return true;
		}
		
		return false;
	}
	
	public void checkEndGame() {
		if (checkPlayerWin() || checkVampiresWin())
			endGame();
	}
	
	public boolean validX(int x, int xLimit) {
		return (x >= 0 && x < xLimit) ? true : false;
	}
	
	public boolean validY(int y) {
		return (y >= 0 && y < level.getY()) ? true : false;
	}
	
	public boolean validCoordinates(int x, int y) {
		if (validX(x, level.getX() - 1) && validY(y) && !objectInPosition(x, y))
			return true;

		System.out.println(invalidCoordinatesMsg);
		return false;
	}
	
	public boolean canPlaceDefense(int x, int y, int cost) {
		return (validCoordinates(x, y) && player.hasEnoughCoins(cost)) ? true : false;
	}
	
	public boolean canPlaceVampire() {
		if (Vampire.getRemainingVampires() > 0 &&
				random.nextDouble() < level.getFrecuency())
				return true;
		
		return false;
	}
	
	public boolean canPlaceVampireDebug(int x, int y) {
		if (objectInPosition(x, y) || !validX(x, level.getX()) || !validY(y)) {
			System.out.println(invalidCoordinatesMsg);
			return false;
		} else if (Vampire.getRemainingVampires() <= 0) {
			System.out.println(noMoreRemainingVampiresMsg);
			return false;
		}
		
		return true;
	}
	
	// New GameObjects creation
	public boolean addSlayer(int x, int y) {
		if (canPlaceDefense(x, y, Slayer.getSlayerCost())) {
			gameObjectBoard.addObject(new Slayer(this, x, y));
			player.buy(Slayer.getSlayerCost());
			return true;
		}
			
		return false;
	}
	
	public boolean addBloodBank(int x, int y, int z) {
		if (canPlaceDefense(x, y, z)) {
			gameObjectBoard.addObject(new BloodBank(this, x, y, z));
			player.buy(z);
			return true;
		}
		
		return false;
	}
	
	public void addVampire() {
		if (canPlaceVampire()) {
			int randomRow = random.nextInt(level.getY());
			if (!objectInPosition(level.getX() - 1, randomRow))
				gameObjectBoard.addObject(new Vampire(this, level.getX() - 1, randomRow));			
		}
	}
	
	public void addDracula() {
		if (!Dracula.getIsPresent() && canPlaceVampire()) {
			int randomRow = random.nextInt(level.getY());
			if (!objectInPosition(level.getX() - 1, randomRow))
				gameObjectBoard.addObject(new Dracula(this, level.getX() - 1, randomRow));
		}
 	}
	
	public void addExplosiveVampire() {
		if (canPlaceVampire()) {
			int randomRow = random.nextInt(level.getY());
			if (!objectInPosition(level.getX() - 1, randomRow))
				gameObjectBoard.addObject(new ExplosiveVampire(this, 
											level.getX() - 1, randomRow));
		}
 	}
	
	public void addAllVampires() {
		addVampire();
		addDracula();
		addExplosiveVampire();
	}

	public boolean addVampireDebug(int x, int y) {
		if (canPlaceVampireDebug(x, y)) {
			gameObjectBoard.addObject(new Vampire(this, x, y));
			return true;
		}
		
		return false;
	}
	
	public boolean addExplosiveVampireDebug(int x, int y) {
		if (canPlaceVampireDebug(x, y)) {
			gameObjectBoard.addObject(new ExplosiveVampire(this, x, y));
			return true;
		}
		
		return false;
	}
	
	public boolean addDraculaDebug(int x, int y) {
		if (canPlaceVampireDebug(x, y)) {
			if (!Dracula.getIsPresent()) {
				gameObjectBoard.addObject(new Dracula(this, x, y));
				return true;
			} else System.out.println("[ERROR]: " + draculaAliveMsg);
		}
		return false;
	}
	
	
	// Attacks
	public void attack() {
		gameObjectBoard.attackObjects();
	}
	
	public boolean garlicPush() {
		if (player.hasEnoughCoins(GARLIC_PUSH_COST)) {
			gameObjectBoard.garlicPush();
			player.buy(GARLIC_PUSH_COST);
			return true;
		}
		
		return false;
	}
	
	public boolean lightFlash() {
		if (player.hasEnoughCoins(LIGHT_FLASH_COST)) {
			gameObjectBoard.lightFlash();
			player.buy(LIGHT_FLASH_COST);
			return true;
		}
		
		return false;
	}
	
	
	// Other GameObject methods
	public boolean objectInPosition(int x, int y) {
		return gameObjectBoard.objectInPosition(x, y);
	}
	
	public IAttack getAttackableInPosition(int x, int y) {
		return gameObjectBoard.getAttackableInPosition(x, y);
	}
	
	public void moveObjects() {
		gameObjectBoard.moveObjects();
	}
	
	public void deleteDeadObjects() {
		gameObjectBoard.deleteDeadObjects();
	}
	
	
	// GAME METHODS
	public void update() {
		player.updateCoinsRandom(); // Get 10 coins with 50% probability
		moveObjects();
		attack();
		addAllVampires();
		deleteDeadObjects();
		checkEndGame();
		if (!isFinished()) cycleNumber++;
	}
	
	public void resetGame() {
		Vampire.initVampires(level.getVampires());
		gameObjectBoard.resetList();
		player.resetCoins();
		cycleNumber = 0;
	}
	
	public void addSuperCoins() {
		player.addCoins(SUPER_COINS);
	}
	
	public void addCoins(int amount) {
		player.addCoins(amount);
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
		
		if (Dracula.getIsPresent()) {
			String draculaAlive = String.format(draculaAliveMsg + '\n');
			stringBuilder.append(draculaAlive);
		}
		
		return stringBuilder.toString();
	}
	
	public String toString() {
		return gamePrinter.toString();
	}
	
 }
