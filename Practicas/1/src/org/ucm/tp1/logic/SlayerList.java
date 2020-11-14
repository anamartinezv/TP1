package org.ucm.tp1.logic;

public class SlayerList {
	
	private final int MAX_SLAYERS = 50;
	
	private int slayerCount;
	private Slayer[] slayers;

	
	public SlayerList() {
		slayerCount = 0;
		
		slayers = new Slayer[MAX_SLAYERS];
	}
	
	public String getSlayerToString(int x, int y) {
		for (int i = 0; i < slayerCount; i++)
			if (slayers[i].getX() == x && slayers[i].getY() == y)
				return slayers[i].toString();
		
		return "";
	}
	
	public boolean isSlayer(int x, int y) {
		for (int i = 0; i < slayerCount; i++)
			if (slayers[i].getX() == x && slayers[i].getY() == y)
				return true;
		return false;
	}
	
	public Slayer getSlayerAtPosition(int x, int y) {
		for (int i = 0; i < slayerCount; i++)
			if (slayers[i].getX() == x && slayers[i].getY() == y)
				return slayers[i];
		
		return null;
	}
	
	/*public void newSlayer(Slayer slayer) {
		slayers[slayerCount] = slayer;
		slayerCount++;
	}*/
	
	public void addSlayer(Game game, int x, int y) {
		slayers[slayerCount] = new Slayer(game, x, y);
		slayerCount++;
	}
	
	public int getSlayerIndex(int x, int y) {
		for (int i = 0; i < slayerCount; i++)
			if (slayers[i].getX() == x && slayers[i].getY() == y)
				return i;
		return -1;
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
