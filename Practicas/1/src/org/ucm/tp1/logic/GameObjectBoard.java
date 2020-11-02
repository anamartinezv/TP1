package org.ucm.tp1.logic;

public class GameObjectBoard {
		
	private VampireList vampireList;
	private SlayerList slayerList;

	private Game game;
	
	public GameObjectBoard(Game game) {
		this.game = game;
		vampireList = new VampireList();
		slayerList = new SlayerList();
	}
	
	public int getVampiresOnBoard() {
		return vampireList.getVampiresOnBoard();
	}
	
	public String getVampire(int x, int y) {
		return vampireList.getVampire(x, y);
	}
	
	public void moveVampires() {
		vampireList.moveVampires();
	}
	
	public boolean isVampire(int x, int y) {
		return vampireList.isVampire(x, y);
	}
	
	public boolean canPlaceVampire() {
		for (int i = 0; i < game.getLevel().getY(); i++)
			if (!vampireList.isVampire(game.getLevel().getX() - 1, i))
				return true;
		
		return false;
	}
	
	public int newVampirePosition() {
		while (true) {
			int col = game.getRandom().nextInt(game.getLevel().getY());
			if (!vampireList.isVampire(game.getLevel().getX() - 1, col))
				return col;
		}
	}
	
	public void addVampire() {
		if (game.getRandom().nextDouble() < game.getLevel().getFrecuency())
			vampireList.newVampire(game, game.getLevel().getX() - 1, newVampirePosition());
	}
}
