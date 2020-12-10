package org.ucm.tp1.control.Commands;

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
	public boolean execute(Game game) {
		game.update();
		return true;
	}

	private boolean matchConditions(String word) {
		return (this.matchCommandName(word) || word.isBlank()) ? true : false;
	}
	
	@Override
	public Command parse(String[] commandWords) {
		return (matchConditions(commandWords[0])) ? this : null;
	}
}
