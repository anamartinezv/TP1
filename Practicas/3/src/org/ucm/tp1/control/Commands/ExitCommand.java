package org.ucm.tp1.control.Commands;

import org.ucm.tp1.Exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class ExitCommand extends Command {
	
	public static final String name = "exit";
	public static final String shortcut = "e";
	public static final String details = "[e]xit";
	public static final String help = "exit game";
	
	public ExitCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		game.endGame();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return (parseNoParamsCommand(commandWords) != null) ? this : null;
	}
}
