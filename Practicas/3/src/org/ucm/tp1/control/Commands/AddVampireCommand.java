package org.ucm.tp1.control.Commands;

import org.ucm.tp1.Exceptions.*;
import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.GameObjects.Dracula;

public class AddVampireCommand extends Command {
	
	public static final String name = "vampire";
	public static final String shortcut = "v";
	public static final String details = "[v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}";
	public static final String help = "add a vampire in position x, y";
	public static final String invalidTypeMsg = "[ERROR]: Unvalid type: ";
	public static final String invalidArgument = "[ERROR]: Unvalid argument for add vampire command, number expected: %s";
	public static final String failedToAddVampire = "[ERROR]: Failed to add this vampire";
	
	private int x;
	private int y;
	private String type;
	
	public AddVampireCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {	
		switch(type) {
		case "":
			try {
				game.addVampireDebug(x, y);
			}catch (CommandExecuteException ex) {
				throw new CommandExecuteException(String.format("%s\n%s", 
													ex.getMessage(), failedToAddVampire));
			}
			break;
		case "d":
			try {
				game.addDraculaDebug(x, y);
			}catch (CommandExecuteException ex) {
				throw new CommandExecuteException(String.format("%s\n%s", 
													ex.getMessage(), failedToAddVampire));
			}
			break;
		case "e":
			try {
				game.addExplosiveVampireDebug(x, y);
			}catch (CommandExecuteException ex) {
				throw new CommandExecuteException(String.format("%s\n%s", 
													ex.getMessage(), failedToAddVampire));
			}
			break;
		default:
			throw new CommandExecuteException(invalidTypeMsg + details);
		}
		
		return true;
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if (this.matchCommandName(commandWords[0])) {
			try {
				if (commandWords.length == 3) {
					type = "";
					x = Integer.parseInt(commandWords[1]);
					y = Integer.parseInt(commandWords[2]);
					return this;
				} else if (commandWords.length == 4) {
					type = commandWords[1];
					x = Integer.parseInt(commandWords[2]);
					y = Integer.parseInt(commandWords[3]);
					return this;
				}	
			}catch (NumberFormatException numberException) {
				throw new CommandParseException(String.format(invalidArgument, details));
			}
		}
		
		return null;
	}
	
	
}
