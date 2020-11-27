package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;

public class HelpCommand extends Command {
	
	public HelpCommand() {
		super("help", "h", "[h]elp", "show this help");
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(CommandGenerator.commandHelp());
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		return (this.matchCommandName(commandWords[0])) ? this : null;
	}
}
