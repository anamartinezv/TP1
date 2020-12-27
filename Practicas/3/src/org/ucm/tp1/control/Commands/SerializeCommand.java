package org.ucm.tp1.control.Commands;

import org.ucm.tp1.Exceptions.*;
import org.ucm.tp1.logic.Game;

public class SerializeCommand extends Command {

	public static final String name = "serialize";
	public static final String shortcut = "z";
	public static final String details = "Seriali[z]e";
	public static final String help = "Serializes the board.";
	
	public SerializeCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		System.out.println(game.serializeGame());
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return (parseNoParamsCommand(commandWords) != null) ? this : null;
	}

}
