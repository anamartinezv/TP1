package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;

public class UpdateCommand extends Command {
	
	public UpdateCommand() {
		super("none", "n", "[n]one | []", "update");
	}

	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
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
