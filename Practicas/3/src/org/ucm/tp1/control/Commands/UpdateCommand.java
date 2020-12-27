package org.ucm.tp1.control.Commands;

import org.ucm.tp1.Exceptions.*;
import org.ucm.tp1.logic.Game;

public class UpdateCommand extends Command {
	
	public static final String name = "none";
	public static final String shortcut = "n";
	public static final String details = "[n]one | []";
	public static final String help = "update";
	
	public UpdateCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		game.update();
		return true;
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return (parseNoParamsCommand(commandWords) != null || 
				commandWords[0].isBlank()) ? this : null;
	}
}
