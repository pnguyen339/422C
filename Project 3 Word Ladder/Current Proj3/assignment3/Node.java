/* WORD LADDER Node.java
 * EE422C Project 3 submission by
 * Bryan Yao
 * byy77
 * 16225
 * Peter Nguyen
 * ppn229
 * 16225
 * Slip days used: <0>
 * Git URL: https://github.com/pnguyen339/422C/tree/master/Project%203%20Word%20Ladder/Current%20Proj3
 * Spring 2017
 */

package assignment3;
import java.util.*;

public class Node {
	public String word;
	public ArrayList<String> edges;
	public String parent; //doubles as a "visited flag"
	public int edgeNo; //number of edges
	public int position; //for use in DFS, picking which edge to go to, keeps track of visited branches
	
	//Constructor from string
	Node(String s){
		word = s.toLowerCase();
		edges = new ArrayList<String>();
		parent = ""; //empty if unvisited
		edgeNo = 0;
		position = 0;
	}
	
	/**
	 * @param none
	 * @return none
	 * resets node by erasing parent/visited, and position to 0
	 */
	public void reset(){
		parent = ""; //all nodes set to unvisited/no parent
		position = 0; //reset position for DFS
	}
}
