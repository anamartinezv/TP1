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
	
	public static final String unknownCommandMsg = String.format("Unknown command");
	public static final String invalidCommandMsg = String.format("Invalid command");
	public static final String invalidPositionMsg = String.format("Invalid position");
	public static final String invalidAddCommandMsg = 
								String.format("Unexpected input. Usage: add <x> <y>");


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
    
    public boolean parseAdd(String command) {  	
    	String[] commandParts = command.split(" ");
    	
    	if (commandParts.length == 3)
    		if (commandParts[1].matches("[0-9]+") && 
				commandParts[2].matches("[0-9]+"))
    			return true;
    	else
    		return false;
    	
    	return false;
    }
    
    public void run() {
    	do {
    		System.out.print(prompt);
			
			String command = scanner.nextLine();
			String[] commandParts = command.split(" ");
			
			switch(commandParts[0]) {
				case "a":
				case "add":
					if (parseAdd(command) && player.canPlaceSlayer()) {
						System.out.println("TODO");
						// TODO: call place slayer
					}else
						System.out.println(invalidAddCommandMsg);
						
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
				default: 
	 				break;
			}
    	} while(!game.isFinished());
    }

}

