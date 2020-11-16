package org.ucm.tp1.control;

import java.util.Scanner;
import org.ucm.tp1.logic.Game;

public class Controller {

	public final String prompt = "Command > ";
	public static final String helpMsg = String.format(
			"\nAvailable commands:%n" +
			"[a]dd <x> <y>: add a slayer in position x, y%n" +
			"[h]elp: show this help%n" + 
			"[r]eset: reset game%n" + 
			"[e]xit: exit game%n"+ 
			"[n]one | []: update%n");
	
	public static final String unknownCommandMsg = String.format("Unknown command. "
			+ "Type [h]elp for more info.");
	public static final String invalidCommandMsg = String.format("Invalid command");
	public static final String invalidPositionMsg = String.format("Invalid position");
	public static final String missingArgumentsMsg = String.format("Missing arguments. "
			+ "Usage: add <x:int> <y:int> ");
	public static final String invalidAddCommandMsg = String.format("Unexpected input. "
			+ "Usage: add <x:int> <y:int>");
	

    private Game game;
    private Scanner scanner;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
    }
    
    public void printGame() {
    	System.out.println(game);
    }
    
    public void run() {
    	printGame();
    	
    	do {
    		System.out.print(prompt);
			
			String command = scanner.nextLine();
			String[] commandParts = command.split(" ");
			
			switch(commandParts[0].toLowerCase()) {
				case "a":
				case "add":
					try {
						int x = Integer.parseInt(commandParts[2]);
						int y = Integer.parseInt(commandParts[1]);
						
						if (game.addSlayer(x, y)) {
							game.newCycle();
							if (!game.isFinished()) {
								game.increaseCycles();
								printGame();
							}
						}
					} catch (NumberFormatException numberException){
						System.out.println(invalidAddCommandMsg);
					} catch (ArrayIndexOutOfBoundsException argsException) {
						System.out.println(missingArgumentsMsg);
					}
					
					break;
					
				case "h":
				case "help":
					System.out.println(helpMsg);
					game.newCycle();
					break;
					
				case "r":
				case "reset":
					game.resetGame();
					printGame();
					break;
					
				case "e":
				case "exit":
					game.endGame();
					System.out.println("Nobody wins...");
					break;
				
				case "":
				case "n":
				case "none":
					game.newCycle();
					if (!game.isFinished()) {
						game.increaseCycles();
						printGame();
					}
					break;
					
				default:
					System.out.println(unknownCommandMsg);
	 				break;
			}	    	
    	} while(!game.isFinished());
    }

}

