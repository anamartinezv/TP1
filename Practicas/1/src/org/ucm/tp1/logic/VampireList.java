package org.ucm.tp1.logic;

public class VampireList {
	private Vampire[] vampires;
	
	public VampireList(int vampiresAmount) {
		vampires = new Vampire[vampiresAmount];
	}
	
	public int getVampireAmount() {
		return vampires.length;
	}
	
	public void addVampire(Vampire vampire) {
		vampires[vampires.length] = vampire;
	}
}
