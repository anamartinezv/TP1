package org.ucm.tp1.control.Commands;

import org.ucm.tp1.Exceptions.*;
import org.ucm.tp1.logic.Game;

public class BloodBankCommand extends Command {
	
	public static final String name = "bank";
	public static final String shortcut = "b";
	public static final String details = "[b]ank <x> <y> <z>";
	public static final String help = "add a blood bank with cost z in position x, y";
	
	public final int PARAMS_NUMBER = 3;
	
	private int x;
	private int y;
	private int z;

	public BloodBankCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		if (game.addBloodBank(x, y, z)) {
			game.update();
			return true;
		}
		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if (this.matchCommandName(commandWords[0])) {
			if (this.parseCommandWithParams(commandWords, PARAMS_NUMBER) != null) {
				try {
					x = Integer.parseInt(commandWords[1]);
					y = Integer.parseInt(commandWords[2]);
					z = Integer.parseInt(commandWords[3]);
					return this;
				} catch (NumberFormatException numberException) {
					throw new CommandParseException("BloodBank: ", numberException);
				}
			}			
		}
		
		return null;
	}

}
