package org.ucm.tp1.control.Commands;

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
	public Command parse(String[] commandWords) {
		return (this.matchCommandName(commandWords[0])) ? this : null;
	}
}
