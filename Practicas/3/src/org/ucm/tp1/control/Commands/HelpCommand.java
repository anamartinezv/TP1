package org.ucm.tp1.control.Commands;

import org.ucm.tp1.Exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class HelpCommand extends Command {
	
	public static final String name = "help";
	public static final String shortcut = "h";
	public static final String details = "[h]elp";
	public static final String help = "show this help";
	
	public HelpCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(CommandGenerator.commandHelp());
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return (parseNoParamsCommand(commandWords) != null) ? this : null;
	}
}
