/* EE422C Project 2 (Mastermind) 
 * Peter Nguyen
 * ppn229
 * Slip days used: <0>
 * Spring 2017
 */
import java.util.*;
package assignment2;


public class Game{
	private Mastermind ga;
	Game(){
		boolean i =false;
		ga = new Mastermind(i);
	}

	public void runGame()
	{
		ga.play();
	}

}
