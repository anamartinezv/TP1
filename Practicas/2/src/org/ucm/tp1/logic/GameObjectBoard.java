package org.ucm.tp1.logic;

import org.ucm.tp1.logic.GameObjects.GameObject;
import org.ucm.tp1.logic.GameObjects.GameObjectList;

public class GameObjectBoard {
			
	private GameObjectList gameObjectList;
	
	public GameObjectBoard() {
		gameObjectList = new GameObjectList();
	}
	
	// GAME OBJECTS
	public boolean objectInPosition(int x, int y) {
		return gameObjectList.objectInPosition(x, y);
	}
	
	public String objectToString(int x, int y) {
		return gameObjectList.objectToString(x, y);
	}
	
	public void addObject(GameObject gameObject) {
		gameObjectList.addObject(gameObject);
	}
	
	
}
