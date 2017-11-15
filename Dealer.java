package P2012A7PSXXX;

import javax.swing.JLabel;

public class Dealer implements Runnable {
	
	private GameData gameData; //shared data 
	private int numberAnnounced = 0; //it is set when a button on GUI is pressed
		
	/* **** DO NOT MODIFY **** this label is used by the dealer to set the game status */
	public final JLabel lblGameStatus = new JLabel();   
	
	/************************** DONOT MODIFY **************************/
	public Dealer(GameData gameData) {
		this.gameData = gameData;			
		lblGameStatus.setAlignmentX(JLabel.CENTER_ALIGNMENT);		
	}
	
	/************************* WRITE CODE FOR THIS METHOD *******************/
	public void run() {
		
		/* STEP-1: write code to take a lock on gameData using lock1*/ 
		// 2 MARKS
		synchronized(gameData.lock1) {			
			
			/* STEP-2: specify condition for player1 and specify condition for player2 */
			// dealer executes until either (or both) players sets their playerSuccessFlag 
			// 4 MARKS
			while (!gameData.playerSuccessFlag[0] && !gameData.playerSuccessFlag[1]) {
				
				// set number announced flag to false before announcing the number
				gameData.noAnnouncedFlag = false;
				
				// set checked flag of both players as false before the number is announced
				gameData.playerChanceFlag[0] = false;
				gameData.playerChanceFlag[1] = false;
		
				/* STEP-3: write code to take a lock on gameData using lock2 and wait while 
				 * no number has been pressed by the user on the GUI (See actionPerformed
				 * method of the GameGUI class 
				 * HINT: until the number is not announced the variable numberAnnounced 
				 * remains 0 (zero)
				 */
				// wait while no number has been pressed by the user on the GUI 
				// 3 MARKS
				synchronized(gameData.lock2){			
					while(0 == numberAnnounced){
						try {
							gameData.lock2.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				
				// STEP-4: initialize the announcedNumber in GameStat with the 
				// number pressed on GameGUI for the players to read
				// 1 MARKS
				gameData.announcedNumber = numberAnnounced;

				// STEP-5: reset the announced number
				// reset the announced number
				// 1 MARKS
				numberAnnounced = 0;  
				
				// STEP-6: communicate to the players that the number is announced
				// using one of the variables in GameData 
				// set number announced to true on GameData for waiting players
				// 1 MARKS
				gameData.noAnnouncedFlag = true;
				
				// STEP-7: notify all the players waiting for the number to be announced 
				// by the dealer using lock1 of GameData
				// Notify all the players waiting for the number to be announced by the dealer
				// 1 MARKS
				gameData.lock1.notifyAll();
				
				// STEP-8: wait using lock1 of GameData while the players haven't checked 
				// the numbers 								
				// wait while the players haven't checked the numbers
				// 6 MARKS
				while(!gameData.playerChanceFlag[0] || !gameData.playerChanceFlag[1]) {
					try {
						//Dealer is waiting for both the players to finish searching the 
						//announced number
						gameData.lock1.wait(); 
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}				
			}
			
			
			// STEP-9: Specify Condition to Check if Player1 has won
			// 2 MARKS
			if(gameData.playerSuccessFlag[0] && !gameData.playerSuccessFlag[1]) { 
				lblGameStatus.setText("PLAYER-1 HAS WON");				
			} 
			// STEP-10: Specify Condition to Check if Player1 has won
			// 2 MARKS
			else if(gameData.playerSuccessFlag[1] && !gameData.playerSuccessFlag[0]){ 
				lblGameStatus.setText("PLAYER-2 HAS WON");				
			} 
			// STEP-11: Specify Condition to Check if Player1 has won
			// 2 MARKS
			else if(gameData.playerSuccessFlag[0] && gameData.playerSuccessFlag[1]) {
				lblGameStatus.setText("BOTH PLAYER-1 AND PLAYER-2 HAVE WON");				
			}

			gameData.gameCompleteFlag = true; // Set the complete flag to true 
			
			gameData.lock1.notifyAll(); // If at all any player is waiting			
		}		
	}

	public void setAnnouncedNumber(int i) {
		this.numberAnnounced = i;	
	}
}
