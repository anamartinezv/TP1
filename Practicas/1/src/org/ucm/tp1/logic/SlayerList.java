package org.ucm.tp1.logic;

public class SlayerList {
	
	private final int MAX_SLAYERS = 100;
	
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
	
	public void newSlayer(Slayer slayer) {
		slayers[slayerCount] = slayer;
		slayerCount++;
	}
	
	public int getSlayerIndex(int x, int y) {
		for (int i = 0; i < slayerCount; i++)
			if (slayers[i].getX() == x && slayers[i].getY() == y)
				return i;
		return -1;
	}
	
	public void deleteSlayer(int x, int y) {
		int index = getSlayerIndex(x, y);
		
		for (int i = index; i < slayerCount; i++)
			slayers[i] = slayers[i + 1];
		
		slayerCount--;
	}
}
