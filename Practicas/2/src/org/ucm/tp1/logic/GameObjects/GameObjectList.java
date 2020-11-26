package org.ucm.tp1.logic.GameObjects;

import java.util.ArrayList;
import org.ucm.tp1.logic.Game;

public class GameObjectList {
	private ArrayList<GameObject> gameObjects;
		
	public GameObjectList() {
		gameObjects = new ArrayList<GameObject>();
	}
	
	private GameObject getObject(int x, int y) {
		for (GameObject object : gameObjects)
			if (object.getX() == x && object.getY() == y) return object;
		
		return null;
	}
	
	public String objectToString(int x, int y) {
		GameObject object = getObject(x, y);
		
		return (object != null) ? object.toString() : " ";
	}
	
	public boolean objectInPosition(int x, int y) {
		return (getObject(x, y) != null) ? true : false;
	}

	public void addObject(GameObject gameObject) {
		gameObjects.add(gameObject);
	}
}
