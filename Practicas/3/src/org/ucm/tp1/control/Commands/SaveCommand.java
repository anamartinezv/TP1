package org.ucm.tp1.control.Commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.ucm.tp1.BuffyVampireSlayer;
import org.ucm.tp1.Exceptions.CommandExecuteException;
import org.ucm.tp1.Exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class SaveCommand extends Command {

	public static final String name = "save";
	public static final String shortcut = "s";
	public static final String details = "[S]ave <filename>";
	public static final String help = "Save the state of the game to a file.";
	public static final String FILE_EXTENSION = ".dat";
	public static final String saveSuccess = "Game successfully saved to file %s.";
	public static final String cannotWriteFile = "Cannot write to file. Caused by: ";
	
	public static final int PARAMS_NUMBER = 1;
	
	private String filename;
		
	public SaveCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			BufferedWriter buffer = new BufferedWriter(new FileWriter(filename));
			
			buffer.write(BuffyVampireSlayer.getGameName());
			buffer.write(game.serializeGame());
			
			buffer.flush();
			buffer.close();
			
			System.out.println(String.format(saveSuccess, filename));
		} catch (IOException e) {
			throw new CommandExecuteException(cannotWriteFile + e.getMessage());
		}
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if (parseCommandWithParams(commandWords, PARAMS_NUMBER) != null) {
			filename = commandWords[1] + FILE_EXTENSION;
			return this;
		}
		
		return null;
	}

}
