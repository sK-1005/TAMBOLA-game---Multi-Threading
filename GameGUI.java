package P2012A7PSXXX;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameGUI implements ActionListener{
	
	/************ DONOT MODIFY THIS CODE BY INTRODUCING NEW VARIABLES *************/
	private Dealer dealer;
	private GameData gameData;	
	private JButton[] btnDealerBoardNumbers;	
	
	/********************** DONOT MODIFY THE CONSTRUCTOR CODE *********************/	
	GameGUI(GameData gameData, Dealer dealer, Player player1, Player player2) {
		
		this.dealer = dealer;
		this.gameData = gameData;
				
		JFrame mainGameFrame = new JFrame("MINI TAMBOLA");
		mainGameFrame.setSize(400,400);
		mainGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblDealer = new JLabel("Dealer",JLabel.CENTER);
		mainGameFrame.setLayout(new BoxLayout(mainGameFrame.getContentPane(),BoxLayout.Y_AXIS));		
		mainGameFrame.add(lblDealer);
		
		// Panel for Dealer buttons
		JPanel dealerPanel = new JPanel();
		dealerPanel.setLayout(new GridLayout(6,5));
		
		// initialize dealer board number buttons
		btnDealerBoardNumbers = new JButton[30];
		
		for(int i = 0; i < 30; i++) {
			btnDealerBoardNumbers[i] = new JButton(String.valueOf(i+1));
			btnDealerBoardNumbers[i].addActionListener(this);
			dealerPanel.add(btnDealerBoardNumbers[i]);
		}
		
		mainGameFrame.add(dealerPanel);
		
		JLabel lblPlayer1 = new JLabel("Player1",JLabel.CENTER);
		mainGameFrame.add(lblPlayer1);
		mainGameFrame.add(player1.getPlayerTicketPanel());// Add player1 ticket
		
		JLabel lblPlayer2 = new JLabel("Player2",JLabel.CENTER);
		mainGameFrame.add(lblPlayer2);
		mainGameFrame.add(player2.getPlayerTicketPanel());// Add player2 ticket
		
		mainGameFrame.add(dealer.lblGameStatus);
		
		mainGameFrame.setVisible(true);
	}

	/**************************** DONOT MODIFY THE CODE *************************/
	/* Action taken when the user presses a button on the dealer board */
	public void actionPerformed(ActionEvent e) {
		// we will have to run a for loop 30 times for each time a button is pressed 
		// we have to identify which button has raised an event
		for(int i = 0; i < 30; i++) {			
			if(e.getSource() == btnDealerBoardNumbers[i]) {				
				// this thread will take a lock on the game object  
				synchronized(gameData.lock2) {									
					dealer.setAnnouncedNumber(i+1);
					btnDealerBoardNumbers[i].setForeground(Color.gray);
					btnDealerBoardNumbers[i].setEnabled(false);
					gameData.lock2.notify();
				}				
				break;
			}
		}
	}		
}
