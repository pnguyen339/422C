/* EE422C Project 2 (Mastermind) 
 * Peter Nguyen
 * ppn229
 * Slip days used: <0>
 * Spring 2017
 */

package assignment2;
import java.util.*;

public class Game{
	private Mastermind ga;
	
	public Game(boolean i){
		ga = new Mastermind(i);
	}

	public void runGame()
	{
		ga.play();
	}

}
