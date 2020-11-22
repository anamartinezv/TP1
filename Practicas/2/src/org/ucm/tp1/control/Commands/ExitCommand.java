package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;

public class ExitCommand extends Command {
	
	public ExitCommand() {
		super("exit", "e", "[e]exit", "exit game");
	}

	@Override
	public boolean execute(Game game) {
		game.endGame();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		return (this.matchCommandName(commandWords[0])) ? this : null;
	}
}
