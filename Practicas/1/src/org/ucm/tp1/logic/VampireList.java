package org.ucm.tp1.logic;

public class VampireList {
	
	private int maxVampires;
	private Vampire[] vampires;
	
	public VampireList(int maxVampires) {
		this.maxVampires = maxVampires;
		vampires = new Vampire[0]; // Initialize array with 0 vampires
	}
	
	public int getVampiresOnBoard() {
		return vampires.length;
	}
	
	public int getRemainingVampires() {
		return maxVampires - vampires.length;
	}
	
	public void addVampire(Vampire vampire) {
		if (getRemainingVampires() > 0)
			vampires[vampires.length] = vampire;
	}
}
