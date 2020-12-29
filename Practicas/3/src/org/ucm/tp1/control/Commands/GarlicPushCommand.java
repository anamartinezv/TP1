package org.ucm.tp1.control.Commands;

import org.ucm.tp1.Exceptions.*;
import org.ucm.tp1.logic.Game;

public class GarlicPushCommand extends Command {
	
	public static final String name = "garlic";
	public static final String shortcut = "g";
	public static final String details = "[g]arlic ";
	public static final String help = "pushes back vampires";
	public static final String failedGarlicPush = "[ERROR]: Failed to garlic push";

	
	public GarlicPushCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.garlicPush();
			game.update();
			return true;
		}catch (CommandExecuteException ex) {
			throw new CommandExecuteException(String.format("%s\n%s", ex.getMessage(), failedGarlicPush));
		}
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return (parseNoParamsCommand(commandWords) != null) ? this : null;
	}

}
