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
		
		return (object != null) ? object.toString() : "";
	}
	
	public boolean objectInPosition(int x, int y) {
		return (getObject(x, y) != null) ? true : false;
	}
	
	public IAttack getAttackableInPosition(int x, int y) {
		IAttack attackable = getObject(x, y);
		
		return (attackable != null) ? attackable : null;
	}

	public void addObject(GameObject gameObject) {
		gameObjects.add(gameObject);
	}
	
	public void moveObject() {
		for (GameObject object : gameObjects)
			object.move();
	}
	
	public void attackObjects() {
		for (GameObject object : gameObjects)
			object.attack();
	}
	
	public void garlicPush() {
		for (GameObject object : gameObjects)
			object.receiveGarlicPush();
	}
	
	public void lightFlash() {
		for (GameObject object : gameObjects)
			object.receiveLightFlash(); 
	}
	
	public void deleteDeadObjects() {
		// Looped backwards to prevent ConcurrentModificationException
		// due to inconsistent Iterator.
		for (int i = gameObjects.size() - 1; i >= 0; i--)
			if (!gameObjects.get(i).isAlive()) gameObjects.remove(i);
	}
	
	public void resetList() {
		gameObjects = new ArrayList<GameObject>();
	}
}
