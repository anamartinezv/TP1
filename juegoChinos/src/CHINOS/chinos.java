package CHINOS;

import CHINOS.GAME.game;

public class chinos {

	public static void main(String[] args) {
		game game = new game();
		
		game.initializeGame();
		
		// Play till the game ends
		do {
			System.out.println("\nPlaying round...");
			game.playRound();
		} while(game.MIN_PLAYERS < (game.randomPlayers.size() 
										+ game.algorithmPlayers.size()));
		
		game.printWinner();
	}

}
