package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;

public class AddCommand extends Command {
	
	public static final String name = "add";
	public static final String shortcut = "a";
	public static final String details = "[a]dd <x> <y>";
	public static final String help = "add a slayer in position x, y";
	
	public final int PARAMS_NUMBER = 2;
	
	private int x;
	private int y;
	
	public AddCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		if (game.addSlayer(x, y)) {
			game.update();
			return true;
		}
		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if (this.matchCommandName(commandWords[0])) {
			if (this.parseCommandWithParams(commandWords, PARAMS_NUMBER) != null) {
				try {
					x = Integer.parseInt(commandWords[1]);
					y = Integer.parseInt(commandWords[2]);
					return this;
				} catch (NumberFormatException numberException){
					return null;
				}
			}			
		}
		
		return null;
	}
}
