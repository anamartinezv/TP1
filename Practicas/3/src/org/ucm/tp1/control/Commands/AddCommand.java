package org.ucm.tp1.control.Commands;

import org.ucm.tp1.Exceptions.*;
import org.ucm.tp1.logic.Game;

public class AddCommand extends Command {
	
	public static final String invalidArguments = "Unvalid argument for add slayer command, number expected: ";
	public static final String failedToAddSlayer = "[ERROR]: Failed to add slayer";

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
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.addSlayer(x, y);
			game.update();
			return true;
		}catch (CommandExecuteException ex) {
			throw new CommandExecuteException(String.format("%s\n%s", ex.getMessage(), failedToAddSlayer));
		}
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if (this.matchCommandName(commandWords[0])) {
			if (this.parseCommandWithParams(commandWords, PARAMS_NUMBER) != null) {
				try {
					x = Integer.parseInt(commandWords[1]);
					y = Integer.parseInt(commandWords[2]);
					return this;
				} catch (NumberFormatException numberFormat){
					throw new CommandParseException("[ERROR]: " + 
							invalidArguments + details);
				}
			}			
		}
		
		return null;
	}
}
