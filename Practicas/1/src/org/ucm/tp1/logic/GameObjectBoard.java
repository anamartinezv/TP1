package org.ucm.tp1.logic;

public class GameObjectBoard {
		
	private VampireList vampireList;
	private SlayerList slayerList;
	
	public GameObjectBoard() {
		vampireList = new VampireList();
		slayerList = new SlayerList();
	}
	
	// VAMPIRES METHOD
	public String getVampire(int x, int y) {
		return vampireList.getVampire(x, y);
	}
	
	public boolean isVampire(int x, int y) {
		return vampireList.isVampire(x, y);
	}
	
	public boolean isValidCycle(int x, int y, int cyclesNumber) {
		return vampireList.getVampireAtPosition(x, y).isValidCycle(cyclesNumber);
	}
	
	public boolean isVampireDead(int x, int y) {
		return vampireList.getVampireAtPosition(x, y).isDead();
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
	
	public void attackVampire(int x, int y) {
		vampireList.getVampireAtPosition(x, y).harm(Slayer.DAMAGE);
	}
	
	public void resetVampires() {
		vampireList.resetArray();
	}
	
	// SLAYER METHOD
	public String getSlayer(int x, int y) {
		return slayerList.getSlayerToString(x, y);
	}
	
	public boolean isSlayer(int x, int y) {
		return slayerList.isSlayer(x, y);
	}
	
	public boolean isSlayerDead(int x, int y) {
		return slayerList.getSlayerAtPosition(x, y).isDead();
	}
	
	public void addSlayer(Game game, int x, int y) {
		slayerList.addSlayer(game, x, y);
	}
	
	public void deleteDeadSlayers() {
		slayerList.deleteSlayers();
	}
	
	public void attackSlayer(int x, int y) {
		slayerList.getSlayerAtPosition(y, x).harm(Vampire.DAMAGE);
	}
	
	public void resetSlayers() {
		slayerList.resetArray();
	}
}
