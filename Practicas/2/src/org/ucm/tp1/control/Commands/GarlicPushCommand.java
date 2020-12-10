package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;

public class GarlicPushCommand extends Command {
	
	public GarlicPushCommand() {
		super("garlic", "g", "[g]arlic ", "pushes back vampires");
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
	public Command parse(String[] commandWords) {
		return (matchCommandName(commandWords[0]) 
				&& parseNoParamsCommand(commandWords) != null) ? this : null;
	}

}
