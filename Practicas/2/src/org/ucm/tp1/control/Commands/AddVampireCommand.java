package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.GameObjects.Dracula;

public class AddVampireCommand extends Command {
	
	public static final String name = "vampire";
	public static final String shortcut = "v";
	public static final String details = "[v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}";
	public static final String help = "add a vampire in position x, y";
	public static final String invalidTypeMsg = String.format("[ERROR]: invalid type");
	
	private int x;
	private int y;
	private String type;
	
	public AddVampireCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		boolean executed = false;
		
		switch(type) {
		case "":
			if (game.addVampireDebug(x, y)) executed = true;
			break;
		case "d":
			if (game.addDraculaDebug(x, y)) executed = true;
			break;
		case "e":
			if (game.addExplosiveVampireDebug(x, y)) executed = true;
			break;
		default:
			System.out.println(invalidTypeMsg);
			break;
		}
		
		return executed;
	}
	
	@Override
	public Command parse(String[] commandWords) {
		if (this.matchCommandName(commandWords[0])) {
			if (commandWords.length == 3) {
				try {
					type = "";
					x = Integer.parseInt(commandWords[1]);
					y = Integer.parseInt(commandWords[2]);
					return this;
				} catch (NumberFormatException numberException){
					return null;
				}
			} else if (commandWords.length == 4) {
				try {
					type = commandWords[1];
					x = Integer.parseInt(commandWords[2]);
					y = Integer.parseInt(commandWords[3]);
					return this;
				} catch (NumberFormatException numberException){
					return null;
				}
			}
		}
		
		return null;
	}
	
	
}
