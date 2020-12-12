package org.ucm.tp1.logic;

import org.ucm.tp1.logic.GameObjects.GameObject;
import org.ucm.tp1.logic.GameObjects.GameObjectList;
import org.ucm.tp1.logic.GameObjects.IAttack;

public class GameObjectBoard {
			
	private GameObjectList gameObjectList;
	
	public GameObjectBoard() {
		gameObjectList = new GameObjectList();
	}
	
	public boolean objectInPosition(int x, int y) {
		return gameObjectList.objectInPosition(x, y);
	}
	
	public String objectToString(int x, int y) {
		return gameObjectList.objectToString(x, y);
	}
	
	public IAttack getAttackableInPosition(int x, int y) {
		return gameObjectList.getAttackableInPosition(x, y);
	}
	
	public void addObject(GameObject gameObject) {
		gameObjectList.addObject(gameObject);
	}
	
	public void moveObjects() {
		gameObjectList.moveObject();
	}
	
	public void attackObjects() {
		gameObjectList.attackObjects();
	}
	
	public void vampireExplodes() {
		gameObjectList.vampireExplodes();
	}
	
	public void garlicPush() {
		gameObjectList.garlicPush();
	}
	
	public void lightFlash() {
		gameObjectList.lightFlash();
	}
	
	public void deleteDeadObjects() {
		gameObjectList.deleteDeadObjects();
	}
	
	public void resetList() {
		gameObjectList.resetList();
	}
}
