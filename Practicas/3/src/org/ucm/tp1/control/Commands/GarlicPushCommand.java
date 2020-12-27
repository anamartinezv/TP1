package org.ucm.tp1.control.Commands;

import org.ucm.tp1.Exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class GarlicPushCommand extends Command {
	
	public static final String name = "garlic";
	public static final String shortcut = "g";
	public static final String details = "[g]arlic ";
	public static final String help = "pushes back vampires";
	
	public GarlicPushCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		if (game.garlicPush()) {
			game.update();
			return true;
		}
		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return (parseNoParamsCommand(commandWords) != null) ? this : null;
	}

}
