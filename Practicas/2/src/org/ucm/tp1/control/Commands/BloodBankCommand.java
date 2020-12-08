package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;

public class BloodBankCommand extends Command {
	
	public final int PARAMS_NUMBER = 3;
	public final String invalidAddCommandMsg = String.format("Unexpected input. "
			+ "Usage: bank <x:int> <y:int> <z:int>");
	
	private int x;
	private int y;
	private int z;

	public BloodBankCommand() {
		super("bank", "b", "[b]ank <x> <y> <z>", "add a blood bank with cost z in position x, y.");
	}

	@Override
	public boolean execute(Game game) {
		if (game.addBloodBank(x, y, z)) {
			game.update();
			return true;
		}
		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if (this.matchCommandName(commandWords[0])) {
			if (this.parseCommandWithParams(commandWords, PARAMS_NUMBER) != null) {
				try {
					x = Integer.parseInt(commandWords[1]);
					y = Integer.parseInt(commandWords[2]);
					z = Integer.parseInt(commandWords[3]);
					return this;
				} catch (NumberFormatException numberException) {
					System.err.println(invalidAddCommandMsg);
				}
			}			
		}
		
		return null;
	}

}
