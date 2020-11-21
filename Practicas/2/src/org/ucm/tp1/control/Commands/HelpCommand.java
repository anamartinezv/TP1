package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;

public class HelpCommand extends Command {
	
	public HelpCommand() {
		super("help", "h", "[h]elp", "show this help");
	}

	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords[1] == "help" || commandWords[1].startsWith("h"))
			return new HelpCommand();
		else
			return null;
	}
}
