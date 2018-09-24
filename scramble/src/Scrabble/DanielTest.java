package Scrabble;

public class DanielTest {
	public static void main(String[] args) {
		ScrabblePlayer[] p = new ScrabblePlayer[] {new ScrabblePlayer(), new ScrabblePlayer(), new ScrabblePlayer()};
		
		ScrabbleGame game = new ScrabbleGame(p);
		System.out.println(game.printTiles());
		game.dealHand();
		
		for (ScrabblePlayer pl : p) {
			System.out.println(pl.printHand());
		}
		
	}
}
