package org.ucm.tp1.logic;

public class GameObjectBoard {
	
	private int vampireNumber;
	
	private VampireList vampireList;
	private SlayerList slayerList;

	public GameObjectBoard(int vampireNumber) {
		this.vampireNumber = vampireNumber;
		
		vampireList = new VampireList(vampireNumber);
		slayerList = new SlayerList();
	}
	
	public int getVampiresOnBoard() {
		return vampireList.getVampiresOnBoard();
	}
	
	public int getRemainingVampires() {
		return vampireList.getRemainingVampires();
	}
}
