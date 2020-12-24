package org.ucm.tp1.control.Commands;

public class CommandGenerator {
	
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
			new AddVampireCommand()
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
		
		stringBuilder.append("Available commands:\n");
		
		for (Command command : availableCommands)
			stringBuilder.append(command.helpText());
		
		return stringBuilder.toString();
	}
}
