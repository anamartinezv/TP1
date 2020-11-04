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
	
	public String getSlayer(int x, int y) {
		return slayerList.getSlayer(x, y);
	}
	
	public void moveVampires() {
		vampireList.moveVampires();
	}
	
	public boolean isVampire(int x, int y) {
		return vampireList.isVampire(x, y);
	}
	
	public boolean isSlayer(int x, int y) {
		return slayerList.isSlayer(x, y);
	}
	
	public void addVampire(Vampire vampire) {
		vampireList.newVampire(vampire);
	}
	
	public void addSlayer(Slayer slayer) {
		slayerList.newSlayer(slayer);
	}
}
