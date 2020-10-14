package CHINOS.TESTS;

import CHINOS.GAME.game;

public class test {
	
	public test() {
		// Empty constructor
	}
	
	
	public static void main(String[] args) {
		runNtimes(1000);
	}
	
	
	public static void runNtimes(int times) {
		int random = 0, algo = 0;
		
		game game = new game();

		for (int i = 0; i < times; i++) {
			game.initializeGame();
			
			// Play till the game ends
			do {
				game.playRound();
			} while(game.MIN_PLAYERS < (game.randomPlayers.size() + game.algorithmPlayers.size()));
			
			if (game.randomPlayers.size() == game.MIN_PLAYERS)
				random++;
			else
				algo++;
		}
		
		if (random < algo)
			System.out.println("\nRandom player has won more games");
		else
			System.out.println("\nAlgorithm player has won more games");
	}
}
