/* EE422C Project 2 (Mastermind) 
 * Peter Nguyen
 * ppn229
 * Slip days used: <0>
 * Spring 2017
 */

import java.util.*;
package assignment2;
public class Driver{

	public static void main(String [] args){
		Game master;
		if(args.length ==0)
			master = new Game(false);
		else
			master = new Game(true);
		
		master.start();



	}


}
