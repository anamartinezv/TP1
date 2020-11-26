package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;

public class AddCommand extends Command {
	
	public final int PARAMS_NUMBER = 2;
	public static final String invalidAddCommandMsg = String.format("Unexpected input. "
			+ "Usage: add <x:int> <y:int>");
	
	private int x;
	private int y;
	
	public AddCommand() {
		super("add", "a", "[a]dd <x> <y>", "add a slayer in position x, y");
	}

	@Override
	public boolean execute(Game game) {
		game.addSlayer(x, y);
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if (this.matchCommandName(commandWords[0])) {
			if (this.parseCommandWithParams(commandWords, PARAMS_NUMBER) != null) {
				try {
					x = Integer.parseInt(commandWords[2]);
					y = Integer.parseInt(commandWords[1]);
					return this;
				} catch (NumberFormatException numberException){
					System.err.println(invalidAddCommandMsg);
				}
			}			
		}
		
		return null;
	}
}
