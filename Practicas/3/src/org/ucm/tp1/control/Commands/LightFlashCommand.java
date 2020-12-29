package org.ucm.tp1.control.Commands;

import org.ucm.tp1.Exceptions.*;
import org.ucm.tp1.logic.Game;

public class LightFlashCommand extends Command {
	
	public static final String name = "light";
	public static final String shortcut = "l";
	public static final String details = "[l]ight";
	public static final String help = "kills all the vampires";
	public static final String failedLightFlash = "[ERROR]: Failed to light flash";
	
	public LightFlashCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.lightFlash();
			game.update();
			return true;
		}catch (CommandExecuteException ex) {
			throw new CommandExecuteException(String.format("%s\n%s", ex.getMessage(), failedLightFlash));
		}		
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return (parseNoParamsCommand(commandWords) != null) ? this : null;
	}
}
