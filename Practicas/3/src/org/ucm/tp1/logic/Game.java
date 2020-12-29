package org.ucm.tp1.logic;

import org.ucm.tp1.view.*;
import org.ucm.tp1.Exceptions.*;
import org.ucm.tp1.Exceptions.ExecuteExceptions.*;
import org.ucm.tp1.logic.GameObjects.*;
import java.util.Random;

public class Game implements IPrintable {
	
	public static final int SUPER_COINS = 1000;
	public static final int LIGHT_FLASH_COST = 50;
	public static final int GARLIC_PUSH_COST = 10;
	
	//public static final String invalidPositionMsg = String.format("[ERROR]: Position (%d, %d): Unvalid position", 1, 1);
	public static final String playerWinsMsg = String.format("Player wins");
	public static final String vampiresWinMsg = String.format("Vampires win!");
	public static final String nobodyWinMsg = String.format("Nobody wins...");
	public static final String draculaAliveMsg = String.format("Dracula is alive");
	public static final String draculaAlreadyMsg = String.format("[ERROR]: Dracula is already on board");
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
	
	public boolean validCoordinates(int x, int y, int xLimit) {
		return ((x >= 0 && x < xLimit) && (y >= 0 && y < level.getY())) ? true : false;
	}
	
	public boolean canPlaceDefense(int x, int y, int cost) throws CommandExecuteException {
		if (!validCoordinates(x, y, level.getX() - 1))
			throw new UnvalidPositionException(String.format("[ERROR]: Position (%d, %d): Unvalid position", x, y));
		else if (!player.hasEnoughCoins(cost))
			throw new NotEnoughCoinsException(String.format("[ERROR]: Defender cost is %s: Not enough coins", cost));
		
		return true;
	}
	
	public boolean isDraculaPresent() throws DraculaIsAliveException {
		if (!Dracula.getIsPresent())
			return false;
		
		throw new DraculaIsAliveException(draculaAlreadyMsg);
	}
	
	public boolean canPlaceVampire() {
		return (Vampire.getRemainingVampires() > 0 && random.nextDouble() < level.getFrecuency()) ? true : false;
	}
	
	public boolean canPlaceVampireDebug(int x, int y) throws CommandExecuteException {
		if (objectInPosition(x, y) || !validCoordinates(x, y, level.getX()))
			throw new UnvalidPositionException(String.format("[ERROR]: Position (%d, %d): Unvalid position", x, y));
		else if (Vampire.getRemainingVampires() <= 0)
			throw new NoMoreVampiresException(noMoreRemainingVampiresMsg);

		return true;
	}
	
	// New GameObjects creation
	public boolean addSlayer(int x, int y) throws CommandExecuteException{
		if (canPlaceDefense(x, y, Slayer.getSlayerCost())) {
			gameObjectBoard.addObject(new Slayer(this, x, y));
			player.buy(Slayer.getSlayerCost());
			return true;
		}
			
		return false;
	}
	
	public boolean addBloodBank(int x, int y, int z) throws CommandExecuteException {
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

	public boolean addVampireDebug(int x, int y) throws CommandExecuteException {
		if (canPlaceVampireDebug(x, y)) {
			gameObjectBoard.addObject(new Vampire(this, x, y));
			return true;
		}
		
		return false;
	}
	
	public boolean addExplosiveVampireDebug(int x, int y) throws CommandExecuteException {
		if (canPlaceVampireDebug(x, y)) {
			gameObjectBoard.addObject(new ExplosiveVampire(this, x, y));
			return true;
		}
		
		return false;
	}
	
	public boolean addDraculaDebug(int x, int y) throws CommandExecuteException {
		if (canPlaceVampireDebug(x, y) && !isDraculaPresent()) {
			gameObjectBoard.addObject(new Dracula(this, x, y));
			return true;
		}
		
		return false;
	}
	
	
	// Attacks
	public void attack() {
		gameObjectBoard.attackObjects();
	}
	
	public boolean garlicPush() throws NotEnoughCoinsException {
		if (player.hasEnoughCoins(GARLIC_PUSH_COST)) {
			gameObjectBoard.garlicPush();
			player.buy(GARLIC_PUSH_COST);
			return true;
		}
		
		throw new NotEnoughCoinsException(String.format("[ERROR]: Garlic Push cost is %s: Not enough coins", GARLIC_PUSH_COST));
	}
	
	public boolean lightFlash() throws NotEnoughCoinsException {
		if (player.hasEnoughCoins(LIGHT_FLASH_COST)) {
			gameObjectBoard.lightFlash();
			player.buy(LIGHT_FLASH_COST);
			return true;
		}
		
		throw new NotEnoughCoinsException(String.format("[ERROR]: Light Flash cost is %s: Not enough coins", LIGHT_FLASH_COST));
	}
	
	
	// Other GameObject methods
	public boolean objectInPosition(int x, int y) {
		return (gameObjectBoard.objectInPosition(x, y)) ? true : false;
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
	
	public String serializeGame() {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(String.format("Cycles: %d%n", cycleNumber));
		stringBuilder.append(String.format("Coins: %d%n", player.getCoins()));
		stringBuilder.append(String.format("Level: %s%n", level.getDifficulty().toUpperCase()));
		stringBuilder.append(String.format("Remaining Vampires: %d%n", Vampire.getRemainingVampires()));
		stringBuilder.append(String.format("Vampires on Board: %d%n%n", Vampire.getVampiresOnBoard()));
		stringBuilder.append(String.format("Game Object List: %n%s", gameObjectBoard.serializeObjects()));
		
		return stringBuilder.toString();
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
