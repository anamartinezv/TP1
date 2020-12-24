package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;

public class SuperCoinsCommand extends Command {
	
	public static final String name = "coins";
	public static final String shortcut = "c";
	public static final String details = "[c]oins";
	public static final String help = "add 1000 coins";

	public SuperCoinsCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		game.addSuperCoins();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		return (matchCommandName(commandWords[0])) ? this : null;
	}
}
