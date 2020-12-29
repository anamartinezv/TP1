package org.ucm.tp1.control.Commands;

import org.ucm.tp1.Exceptions.CommandParseException;

public class CommandGenerator {
	
	public static final String unknownCommand = "[ERROR]: Unknown command";
	
	public CommandGenerator() {
		
	}
	
	private static Command[] availableCommands = {
			new AddCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new GarlicPushCommand(),
			new LightFlashCommand(),
			new BloodBankCommand(),
			new SuperCoinsCommand(),
			new AddVampireCommand(),
			new SaveCommand(),
			new SerializeCommand()
	};
	
	public static Command parseCommand(String[ ] commandWords) throws CommandParseException {
		for (Command c : availableCommands) {
			Command parsedInput = c.parse(commandWords);
			if ( parsedInput != null) return parsedInput;
		}
		
		throw new CommandParseException(unknownCommand);
	}
	
	public static String commandHelp() {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("Available commands:\n");
		
		for (Command command : availableCommands)
			stringBuilder.append(command.helpText());
		
		return stringBuilder.toString();
	}
}
