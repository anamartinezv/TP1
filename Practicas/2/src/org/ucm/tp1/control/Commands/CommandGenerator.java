package org.ucm.tp1.control.Commands;

public class CommandGenerator {
	public CommandGenerator() {
		
	}
	
	private static Command[] availableCommands = {
			new AddCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new UpdateCommand()
	};
	
	public static Command parseCommand(String[ ] commandWords) {
		for (Command c : availableCommands) {
			Command parsedInput = c.parse(commandWords);
			if ( parsedInput != null) return parsedInput;
		}
		
		return null;
	}
	
	public static String commandHelp() {
		StringBuilder stringBuilder = new StringBuilder();
		
		for (Command c : availableCommands)
			stringBuilder.append(c.helpText());
		
		return stringBuilder.toString();
	}
}
