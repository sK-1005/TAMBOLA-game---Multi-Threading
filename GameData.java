package P2012A7PSXXX;

/************ DO NOT MODIFY THIS CODE *************/
public class GameData {
	public int announcedNumber = 0;	 
	public boolean gameCompleteFlag = false;	
	public boolean noAnnouncedFlag = false;
	public boolean[] playerSuccessFlag = new boolean[2];
	public boolean[] playerChanceFlag = new boolean[2];
	
	public Object lock1 = new Object();
	public Object lock2 = new Object();
}
