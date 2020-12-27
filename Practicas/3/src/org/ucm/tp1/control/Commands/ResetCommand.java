package org.ucm.tp1.control.Commands;

import org.ucm.tp1.Exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class ResetCommand extends Command {
	
	public static final String name = "reset";
	public static final String shortcut = "r";
	public static final String details = "[r]eset";
	public static final String help = "reset game";
	
	public ResetCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		game.resetGame();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return (parseNoParamsCommand(commandWords) != null) ? this : null;
	}
}
