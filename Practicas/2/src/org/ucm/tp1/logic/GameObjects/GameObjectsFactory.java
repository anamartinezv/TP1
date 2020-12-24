package org.ucm.tp1.logic.GameObjects;

import org.ucm.tp1.logic.Game;

public class GameObjectsFactory {

	public GameObject createObject(String object, Game game, int x, int y) {
		
		GameObject gameObject = null;
		
		switch (object.toLowerCase()) {
			case "slayer":
				gameObject = new Slayer(game, x, y);
				break;
			case "vampire":
				gameObject = new Vampire(game, x, y);
				break;
			case "explosivevampire":
				gameObject = new ExplosiveVampire(game, x, y);
				break;
			case "dracula":
				gameObject = new Dracula(game, x, y);
				break;
		}
		
		return gameObject;
	}
	
	public GameObject createObject(String object, Game game, int x, int y, int z) {
		
		GameObject gameObject = null;
		
		switch (object.toLowerCase()) {
			case "bloodbank":
				gameObject = new BloodBank(game, x, y, z);
				break;
		}
		
		return gameObject;
	}
}
