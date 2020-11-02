package org.ucm.tp1.control;

import java.util.Scanner;
import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.Player;

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
			+ "Usage: add <x:int> <y:int>: ");
	public static final String invalidAddCommandMsg = String.format("Unexpected input. "
			+ "Usage: add <x:int> <y:int>");

    private Game game;
    private Scanner scanner;
    private Player player;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
    }
    
    public void printGame() {
    	System.out.println(game);
    }
    
    public void run() {
    	do {
    		printGame();
    		
    		System.out.print(prompt);
			
			String command = scanner.nextLine();
			String[] commandParts = command.split(" ");
			
			switch(commandParts[0]) {
				case "a":
				case "add":
					try {
						int xCoordinate, yCoordinate;
						xCoordinate = Integer.parseInt(commandParts[1]);
						yCoordinate = Integer.parseInt(commandParts[2]); 
								
						// TODO: call place slayer
					} catch (NumberFormatException numberException){
						System.out.println(invalidAddCommandMsg);
					} catch (ArrayIndexOutOfBoundsException argsException) {
						System.out.println(missingArgumentsMsg);
					}
					
					break;
					
				case "h":
				case "help":
					System.out.println(helpMsg);
					break;
					
				case "r":
				case "reset":
					System.out.println("TODO");
					break;
					
				case "e":
				case "exit":
					game.endGame();
					break;
					
				case "n":
				case "none":
					// Nothing to do, skip to next round
					break;
					
				default:
					System.out.println(unknownCommandMsg);
	 				break;
			}
			
	    	game.updateGame();
	    	game.addVampire();
	    	game.checkEndGame();
	    	
    	} while(!game.isFinished());
    }

}

