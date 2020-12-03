package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;

public class SuperCoinsCommand extends Command {

	public SuperCoinsCommand() {
		super("coins", "c", "[c]oins", "add 1000 coins");
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
