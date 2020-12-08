package org.ucm.tp1.control.Commands;

import org.ucm.tp1.logic.Game;

public class AddVampireCommand extends Command {
	
	public final String invalidCommandMsg = String.format("Unexpected input. "
			+ "Usage: add <x:int> <y:int>");
	
	private int x;
	private int y;
	private String type;
	
	public AddVampireCommand() {
		super("vampire", "v", "[v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}", 
				"add a vampire in position x, y");
	}

	@Override
	public boolean execute(Game game) {
		return false;
	}
	
	@Override
	public Command parse(String[] commandWords) {
		/*if (this.matchCommandName(commandWords[0])) {
			if (commandWords.length == 3) {
				try {
					type = "";
					x = Integer.parseInt(commandWords[1]);
					y = Integer.parseInt(commandWords[2]);
					return this;
				} catch (NumberFormatException numberException){
					System.err.println(invalidCommandMsg);
				}
			} else if (commandWords.length == 4) {
				try {
					type = commandWords[1];
					x = Integer.parseInt(commandWords[2]);
					y = Integer.parseInt(commandWords[3]);
					return this;
				} catch (NumberFormatException numberException){
					System.err.println(invalidCommandMsg);
				}
			}
		}*/
		
		return this;
	}
	
	
}
