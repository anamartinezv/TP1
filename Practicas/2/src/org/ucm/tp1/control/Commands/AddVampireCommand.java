package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;

public class AddVampireCommand extends Command {
	
	public AddVampireCommand() {
		super("vampire", "v", "[v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}", 
				"add a vampire in position x, y");
	}

	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
