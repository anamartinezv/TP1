package org.ucm.tp1.logic;

public class VampireList {
	
	private final int MAX_VAMPIRES = 10;
	
	private int vampireCounter;
	private Vampire[] vampires;
	
	public VampireList() {
		vampireCounter = 0;
		vampires = new Vampire[MAX_VAMPIRES];
	}
	
	public String getVampire(int x, int y) {
		for (int i = 0; i < vampireCounter; i++) {
			if (vampires[i].getX() == x && vampires[i].getY() == y)
				return vampires[i].toString();
		}
		
		return "";
	}
	
	public boolean isVampire(int x, int y) {
		for (int i = 0; i < vampireCounter; i++) {
			if (vampires[i].getX() == x && vampires[i].getY() == y)
				return true;
		}
		
		return false;
	}
	
	public void newVampire(Vampire vampire) {
		vampires[vampireCounter] = vampire;
		vampireCounter++;
	}
	
	public Vampire getVampireAtPosition(int x, int y) {
		for (int i = 0; i < vampireCounter; i++)
			if (vampires[i].getX() == x && vampires[i].getY() == y)
				return vampires[i];
		
		return null;
	}
	
	public int getVampireIndex(int x, int y) {
		for (int i = 0; i < vampireCounter; i++)
			if (vampires[i].getX() == y && vampires[i].getY() == x)
				return i;
		return -1;
	}
	
	public void deleteVampire(int x, int y) {
		int index = getVampireIndex(x, y);
		
		for (int i = index; i < vampireCounter; i++)
			vampires[i] = vampires[i + 1];
		
		vampireCounter--;
	}
}
