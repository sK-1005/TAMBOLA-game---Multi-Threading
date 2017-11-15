# TAMBOLA-game---Multi-Threading
Java. 1 Manager/Announcer - N number of players - each player with a Ticket - Let's Play!!!!!!

Description of how the game is played: 

In this game there are two primary entities involved (a) Dealer and (b) Player. 
There is just one Dealer and there can be multiplePlayers (however we restrict it to only two players). Dealer repeatedly makes an
announcement of a number in the range 1 to 30 (the numbers are randomly selected and once the number is announced it is not announced again) until all the numbers are not announced or the game is not complete i.e., one or more players have won the
game. 

Both players have a ticket on which there are 6 random numbers in the range 1 to 30. Once the dealer makes an announcement of a number all the players start searching for that number in their tickets. If the number is found the player marks this
number. 

Either one or both the players who finds all the numbers in their ticket is/are declared as a winner.

Description of Concurrent Tasks: 

Both the Dealer and Players run as separate threads. Given below is the description of both the Dealer and Player threads.

Dealer carries out the following tasks:

a) The dealer thread continues to execute until either of the player threads reports success i.e., won the game.

b) The dealer thread repeatedly announces a number in the range 1 to 30 (basically the announced number is an input to the dealer thread via the game GUI).
(i) The dealer thread waits for the user to press a number button on the game GUI which is then used as an announced number.
(ii) The dealer thread continuous to execute until all the numbers are not announced or one or more player is not detected as a winner i.e., if one or more players have found all the numbers on his or her ticket.

c) Before announcing the next number the dealer checks if one or more players have found all the numbers on his or her ticket and then declares the winner and sets the game status as complete.

d) Before announcing the next number the dealer allows all the players to search for currently announced number on their tickets and wait for all of them to finish searching for the currently announced number.

Player carries out the following activities:

a) Both player threads continues to execute until the game is not complete.

b) Both player threads waits for the dealer thread to announce a number.

c) Once the dealer thread announces the number both the player threads search for the currently announced number in their tickets, if the number is found then the players mark this number as found in their respective tickets (see more on this in the Player class description below).

d) Both the player threads check if they have won the game i.e., found all the numbers on their tickets, then they set their corresponding success flag.
