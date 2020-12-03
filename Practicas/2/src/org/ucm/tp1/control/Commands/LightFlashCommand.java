package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;

public class LightFlashCommand extends Command {
	
	public LightFlashCommand() {
		super("light", "l", "[l]ight", "kills all the vampires");
	}

	@Override
	public boolean execute(Game game) {
		if (game.lightFlash()) {
			game.update();
			return true;
		}
		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		return (matchCommandName(commandWords[0])) ? this : null;
	}
}
