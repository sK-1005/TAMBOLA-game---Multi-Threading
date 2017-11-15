package P2012A7PSXXX;

import javax.swing.SwingUtilities;

public class GameApp {

	public static void main(String[] args) {
		
		final GameData game  = new GameData();
		final Dealer dealer  = new Dealer(game);
		final Player player1 = new Player(game, 0);
		final Player player2 = new Player(game, 1);
		
		Thread dealerThread  = new Thread(dealer );
		Thread player1Thread = new Thread(player1);
		Thread player2Thread = new Thread(player2);
		
		dealerThread. start();
		player1Thread.start();
		player2Thread.start();
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new GameGUI(game,dealer,player1,player2);
			}
		});		
	}
}
