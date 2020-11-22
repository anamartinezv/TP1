package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;

public class ResetCommand extends Command {
	
	public ResetCommand() {
		super("reset", "r", "[r]eset", "reset game");
	}

	@Override
	public boolean execute(Game game) {
		game.resetGame();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		return (this.matchCommandName(commandWords[0])) ? this : null;
	}
}
