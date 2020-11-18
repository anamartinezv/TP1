package org.ucm.tp1.logic;

public class GameObjectBoard {
		
	private VampireList vampireList;
	private SlayerList slayerList;
	
	public GameObjectBoard() {
		vampireList = new VampireList();
		slayerList = new SlayerList();
	}
	
	// VAMPIRES METHOD
	public String getVampireToString(int x, int y) {
		return vampireList.getVampireToString(x, y);
	}
	
	public boolean isVampire(int x, int y) {
		return vampireList.isVampire(x, y);
	}
	
	public void addVampire(Vampire vampire) {
		vampireList.newVampire(vampire);
	}
	
	public void moveVampires() {
		vampireList.moveVampires();
	}
	
	public void deleteDeadVampires() {
		vampireList.deleteVampires();
	}
	
	public void harmVampire(int x, int y, int damage) {
		vampireList.harmVampire(x, y, damage);
	}
	
	public void vampiresAttack() {
		vampireList.attack();
	}
	
	public void resetVampires() {
		vampireList.resetArray();
	}
	
	// SLAYER METHOD
	public String getSlayerToString(int x, int y) {
		return slayerList.getSlayerToString(x, y);
	}
	
	public boolean isSlayer(int x, int y) {
		return slayerList.isSlayer(x, y);
	}
	
	public void addSlayer(Slayer slayer) {
		slayerList.newSlayer(slayer);
	}
	
	public void deleteDeadSlayers() {
		slayerList.deleteSlayers();
	}
	
	public void slayersAttack() {
		slayerList.attack();
	}
	
	public void harmSlayer(int x, int y, int damage) {
		slayerList.harmSlayer(x, y , damage);
	}
	
	public void resetSlayers() {
		slayerList.resetArray();
	}
}
