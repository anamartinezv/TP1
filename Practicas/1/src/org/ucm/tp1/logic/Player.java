package org.ucm.tp1.logic;

import java.util.Random;

public class Player {
	private int coins;
	private Random random;
	private Slayer slayer;
	
	public Player() {
		
	}
	
	public boolean canPlaceSlayer() {
		//if (coins >= slayer.COST) return true; else return false;
		return (coins >= slayer.COST) ? true : false;
	}
 
}
