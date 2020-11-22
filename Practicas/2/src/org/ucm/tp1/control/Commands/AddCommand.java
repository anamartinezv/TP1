package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;

public class AddCommand extends Command {
	
	public AddCommand() {
		super("add", "a", "[a]dd <x> <y>", "add a slayer in position x, y");
	}

	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		return (this.matchCommandName(commandWords[0])) ? this : null;
	}
}
