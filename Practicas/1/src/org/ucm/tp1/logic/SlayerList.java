package org.ucm.tp1.logic;

public class SlayerList {
	
	private final int MAX_SLAYERS = 50;
	
	private int slayerCount;
	private Slayer[] slayers;

	
	public SlayerList() {
		slayerCount = 0;
		
		slayers = new Slayer[MAX_SLAYERS];
	}
	
	public Slayer getSlayer(int x, int y) {
		for (int i = 0; i < slayerCount; i++)
			if (slayers[i].getX() == x && slayers[i].getY() == y)
				return slayers[i];
		return null;
	}
	
	public String getSlayerToString(int x, int y) {		
		return getSlayer(x, y).toString();
	}
	
	public boolean isSlayer(int x, int y) {
		return getSlayer(x, y) != null ? true : false;
	}
	
	public void harmSlayer(int x, int y, int damage) {
		getSlayer(x, y).harm(damage);
	}
	
	public void attack() {
		for (int i = 0; i < slayerCount; i++)
			slayers[i].attack();
	}
	
	public void newSlayer(Slayer slayer) {
		slayers[slayerCount] = slayer;
		slayerCount++;
	}
	
	public void deleteSlayers() {
		for (int i = 0; i < slayerCount; i++)
			if (slayers[i].isDead())
				shiftArray(i);
	}
	
	public void shiftArray(int index) {
		for (int i = index; i < slayerCount; i++)
			slayers[i] = slayers[i + 1];
		
		slayerCount--;
	}
	
	public void resetArray() {
		slayers = new Slayer[MAX_SLAYERS];
		slayerCount = 0;
	}
}
